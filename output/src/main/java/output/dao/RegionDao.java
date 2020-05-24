package output.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import output.tables.Region;
import output.util.DaoUtil;

public class RegionDao implements Dao<Region> {

    public static final RegionDao regionDao = new RegionDao();

    @Override
    public int count() {
        int count = -1;
        String sql = "SELECT count(*) AS total FROM region";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) count = rs.getInt("total");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DaoUtil.closeConnection(connection, statement, rs);
        }
        return count;
    }

    @Override
    public boolean insert(Region region) {
        if (region.name == null || region.name.isEmpty()) return false;
        String sql = "INSERT INTO region (name, abbreviation, min, max, average, standard_deviation, rank) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, region.name);
            statement.setString(2, region.abbreviation);
            statement.setDouble(3, region.min);
            statement.setDouble(4, region.max);
            statement.setDouble(5, region.average);
            statement.setDouble(6, region.standardDeviation);
            statement.setInt(7, region.rank);
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DaoUtil.closeConnection(connection, statement);
        }
    }

    @Override
    public boolean insertMany(List<Region> regions) {
        if (regions == null || regions.isEmpty()) return false;
        String sql = "INSERT INTO region (name, abbreviation, min, max, average, standard_deviation, rank) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.prepareStatement(sql);
            for (Region region : regions) {
                if (region == null || region.name == null || region.name.isEmpty()) continue;
                statement.setString(1, region.name);
                statement.setString(2, region.abbreviation);
                statement.setDouble(3, region.min);
                statement.setDouble(4, region.max);
                statement.setDouble(5, region.average);
                statement.setDouble(6, region.standardDeviation);
                statement.setInt(7, region.rank);
                statement.addBatch();
            }
            statement.executeBatch();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DaoUtil.closeConnection(connection, statement);
        }
    }

    @Override
    public boolean updateManyByName(List<Region> regions, String column) {
        if (regions == null || regions.isEmpty() || column == null || column.isEmpty()) return false;
        String sql = "UPDATE region SET " + column + " = ? WHERE name = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.prepareStatement(sql);
            for (Region region : regions) {
                if (region == null || region.name == null || region.name.isEmpty()) continue;
                statement.setDouble(1, region.getDouble(column));
                statement.setString(2, region.name);
                statement.addBatch();
            }
            statement.executeBatch();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DaoUtil.closeConnection(connection, statement);
        }
    }

    @Override
    public boolean updateManyByAbbreviation(List<Region> regions, String column) {
        if (regions == null || regions.isEmpty() || column == null || column.isEmpty()) return false;
        String sql = "UPDATE region SET " + column + " = ? WHERE abbreviation = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.prepareStatement(sql);
            for (Region region : regions) {
                if (region == null || region.abbreviation == null || region.abbreviation.isEmpty()) continue;
                statement.setDouble(1, region.getDouble(column));
                statement.setString(2, region.abbreviation);
                statement.addBatch();
            }
            statement.executeBatch();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DaoUtil.closeConnection(connection, statement);
        }
    }

    @Override
    public boolean updateManyById(List<Region> regions, String column) {
        if (regions == null || regions.isEmpty() || column == null || column.isEmpty()) return false;
        String sql = "UPDATE region SET " + column + " = ? WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.prepareStatement(sql);
            for (Region region : regions) {
                if (region == null) continue;
                statement.setDouble(1, region.getDouble(column));
                statement.setInt(2, region.id);
                statement.addBatch();
            }
            statement.executeBatch();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DaoUtil.closeConnection(connection, statement);
        }
    }

    @Override
    public boolean delete(Region region) {
        if (region == null || region.name == null || region.name.isEmpty()) return false;
        String sql = "DELETE FROM region WHERE name = '" + region.name + "'";
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.createStatement();
            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DaoUtil.closeConnection(connection, statement);
        }
    }

    @Override
    public boolean deleteAll() {
        String sql = "DELETE FROM region";
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.createStatement();
            statement.execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DaoUtil.closeConnection(connection, statement);
        }
    }

    @Override
    public Region selectByName(String regionName) {
        if (regionName == null || regionName.isEmpty()) return null;
        String sql = "SELECT * FROM region WHERE name = '" + regionName + "'";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            if (!rs.next()) return null;
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String abbreviation = rs.getString("abbreviation");
            double min = rs.getDouble("min");
            double max = rs.getDouble("max");
            double average = rs.getDouble("average");
            double standardDeviation = rs.getDouble("standardDeviation");
            int rank = rs.getInt("rank");
            return new Region(id, name, abbreviation, min, max, average, standardDeviation, rank);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DaoUtil.closeConnection(connection, statement, rs);
        }
    }

    @Override
    public Region selectByAbbreviation(String regionAbbreviation) {
        if (regionAbbreviation == null || regionAbbreviation.length() != 2 && regionAbbreviation.length() != 1) return null;
        String sql = "SELECT * FROM region WHERE abbreviation = '" + regionAbbreviation + "'";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            if (!rs.next()) return null;
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String abbreviation = rs.getString("abbreviation");
            double min = rs.getDouble("min");
            double max = rs.getDouble("max");
            double average = rs.getDouble("average");
            double standardDeviation = rs.getDouble("standardDeviation");
            int rank = rs.getInt("rank");
            return new Region(id, name, abbreviation, min, max, average, standardDeviation, rank);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DaoUtil.closeConnection(connection, statement, rs);
        }
    }

    @Override
    public Region selectById(int id) {
        String sql = "SELECT * FROM region WHERE id = '" + id + "'";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            if (!rs.next()) return null;
            String name = rs.getString("name");
            String abbreviation = rs.getString("abbreviation");
            double min = rs.getDouble("min");
            double max = rs.getDouble("max");
            double average = rs.getDouble("average");
            double standardDeviation = rs.getDouble("standardDeviation");
            int rank = rs.getInt("rank");
            return new Region(id, name, abbreviation, min, max, average, standardDeviation, rank);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DaoUtil.closeConnection(connection, statement, rs);
        }
    }

    @Override
    public List<Region> selectAll() {
        String sql = "SELECT * FROM region";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            List<Region> regions = new ArrayList<Region>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String abbreviation = rs.getString("abbreviation");
                double min = rs.getDouble("min");
                double max = rs.getDouble("max");
                double average = rs.getDouble("average");
                double standardDeviation = rs.getDouble("standardDeviation");
                int rank = rs.getInt("rank");
                regions.add(new Region(id, name, abbreviation, min, max, average, standardDeviation, rank));
            }
            return regions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DaoUtil.closeConnection(connection, statement, rs);
        }
    }
    
}