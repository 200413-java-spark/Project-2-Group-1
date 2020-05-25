package output.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import output.tables.County;
import output.util.DaoUtil;

public class CountyDao implements Dao<County> {

    public static final CountyDao countyDao = new CountyDao();

    @Override
    public int count() {
        int count = -1;
        String sql = "SELECT count(*) AS total FROM county";
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
    public boolean insert(County county) {
        if (county.name == null || county.name.isEmpty()) return false;
        String sql = "INSERT INTO county (name, state, min, max, average, standard_deviation, rank) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, county.name);
            statement.setString(2, county.state);
            statement.setDouble(3, county.min);
            statement.setDouble(4, county.max);
            statement.setDouble(5, county.average);
            statement.setDouble(6, county.standardDeviation);
            statement.setInt(7, county.rank);
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
    public boolean insertMany(List<County> counties) {
        if (counties == null || counties.isEmpty()) return false;
        String sql = "INSERT INTO county (name, state, min, max, average, standard_deviation, rank) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.prepareStatement(sql);
            for (County county : counties) {
                if (county == null || county.name == null || county.name.isEmpty()) continue;
                statement.setString(1, county.name);
                statement.setString(2, county.state);
                statement.setDouble(3, county.min);
                statement.setDouble(4, county.max);
                statement.setDouble(5, county.average);
                statement.setDouble(6, county.standardDeviation);
                statement.setInt(7, county.rank);
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
    public boolean updateManyByName(List<County> counties, String column) {
        if (counties == null || counties.isEmpty() || column == null || column.isEmpty()) return false;
        String sql = "UPDATE county SET " + column + " = ? WHERE name = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.prepareStatement(sql);
            for (County county : counties) {
                if (county == null || county.name == null || county.name.isEmpty()) continue;
                statement.setDouble(1, county.getDouble(column));
                statement.setString(2, county.name);
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
    public boolean updateManyByAbbreviation(List<County> counties, String column) {
        return false;
    }

    @Override
    public boolean updateManyById(List<County> counties, String column) {
        if (counties == null || counties.isEmpty() || column == null || column.isEmpty()) return false;
        String sql = "UPDATE county SET " + column + " = ? WHERE id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.prepareStatement(sql);
            for (County county : counties) {
                if (county == null) continue;
                statement.setDouble(1, county.getDouble(column));
                statement.setInt(2, county.id);
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
    public boolean delete(County county) {
        if (county == null || county.name == null || county.name.isEmpty()) return false;
        String sql = "DELETE FROM county WHERE name = '" + county.name + "'";
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
        String sql = "DELETE FROM county";
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
    public County selectByName(String countyName) {
        if (countyName == null || countyName.isEmpty()) return null;
        String sql = "SELECT * FROM county WHERE name = '" + countyName + "'";
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
            String state = rs.getString("state");
            double min = rs.getDouble("min");
            double max = rs.getDouble("max");
            double average = rs.getDouble("average");
            double standardDeviation = rs.getDouble("standardDeviation");
            int rank = rs.getInt("rank");
            return new County(id, name, state, min, max, average, standardDeviation, rank);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DaoUtil.closeConnection(connection, statement, rs);
        }
    }

    @Override
    public County selectByAbbreviation(String s) {
        return null;
    }

    @Override
    public County selectById(int id) {
        String sql = "SELECT * FROM county WHERE id = '" + id + "'";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            if (!rs.next()) return null;
            String name = rs.getString("name");
            String state = rs.getString("state");
            double min = rs.getDouble("min");
            double max = rs.getDouble("max");
            double average = rs.getDouble("average");
            double standardDeviation = rs.getDouble("standardDeviation");
            int rank = rs.getInt("rank");
            return new County(id, name, state, min, max, average, standardDeviation, rank);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DaoUtil.closeConnection(connection, statement, rs);
        }
    }

    @Override
    public List<County> selectAll() {
        String sql = "SELECT * FROM county";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            List<County> counties = new ArrayList<County>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String state = rs.getString("state");
                double min = rs.getDouble("min");
                double max = rs.getDouble("max");
                double average = rs.getDouble("average");
                double standardDeviation = rs.getDouble("standardDeviation");
                int rank = rs.getInt("rank");
                counties.add(new County(id, name, state, min, max, average, standardDeviation, rank));
            }
            return counties;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DaoUtil.closeConnection(connection, statement, rs);
        }
    }
    
}