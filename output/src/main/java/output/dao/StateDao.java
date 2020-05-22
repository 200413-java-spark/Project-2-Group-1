package output.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import output.tables.State;
import output.util.DaoUtil;

public class StateDao implements Dao<State> {

    @Override
    public int count() {
        int count = -1;
        String sql = "SELECT count(*) AS total FROM state";
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
    public boolean insert(State s) {
        // //update schema.sql to auto increment primary key? (use serial)
        // String state = s.state;
        // Integer leaseCount = s.leaseCount;
        // if(state.isEmpty() || state == null || leaseCount.equals(null) || leaseCount.intValue() < 0) {
        //     return false;
        // }
        // String sql = "insert into state(state, lease_count) values(?, ?)";
        // Connection connection = null;
        // PreparedStatement statement = null;
        // try {
        //     connection = DaoUtil.getConnection();
        //     statement = connection.prepareStatement(sql);
        //     statement.setString(1, state);
        //     statement.setInt(2, leaseCount);
        //     statement.execute();
        //     return true;
        // } catch (SQLException e) {
        //     e.printStackTrace();
        //     return false;
        // } finally {
        //     DaoUtil.closeConnection(connection, statement);
        // }
    
        return true;
    }

    @Override
    public boolean insertMany(List<State> states) {
        if(states.isEmpty() || states == null) {
            return false;
        }
        // String name = null;
        // String abbreviation = null;
        // String region = null;
        // double min = 0;
        // double max = 0;
        // double average = 0;
        // double standardDeviation = 0;
        // int rank = 0;
        String sql = "INSERT INTO state (name, abbreviation, region, min, max, average, standard_deviation, rank) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.prepareStatement(sql);
            for(State state : states) {
                if(state == null || state.name == null || state.name.isEmpty()) return false;
                statement.setString(1, state.name);
                statement.setString(2, state.abbreviation);
                statement.setString(3, state.region);
                statement.setDouble(4, state.min);
                statement.setDouble(5, state.max);
                statement.setDouble(6, state.average);
                statement.setDouble(7, state.standardDeviation);
                statement.setInt(8, state.rank);
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
    public boolean update(State e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean delete(State s) {
        // if(s == null || s.state.isEmpty() || s.state == null) {
        //     return false;
        // }
        // String sql = "delete from state where state.state = '" + s.state + "'";
        // Connection connection = null;
        // Statement statement = null;
        // try {
        //     connection = DaoUtil.getConnection();
        //     statement = connection.createStatement();
        //     statement.execute(sql);
        //     return true;
        // } catch (SQLException e) {
        //     e.printStackTrace();
        //     return false;
        // } finally {
        //     DaoUtil.closeConnection(connection, statement);
        // }

        return true;
    }

    @Override
    public boolean deleteAll() {
        String sql = "DELETE FROM state";
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
    public State select(State state) {
        if (state == null) return null;
        String sql = null;
        if (state.name == null || state.name.isEmpty()) {
            if (state.abbreviation == null || state.abbreviation.isEmpty()) return null;
            else sql = "SELECT * FROM state WHERE state.abbreviation = '" + state.abbreviation + "'";
        }
        else if (state.abbreviation == null || state.abbreviation.isEmpty()) {
            if (state.name == null || state.name.isEmpty()) return null;
            else sql = "SELECT * FROM state WHERE state.name = '" + state.name + "'";
        }
        else {
            sql = "SELECT * FROM state WHERE state.abbreviation = '" + state.abbreviation + "'";
        }
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            if(!rs.next()) return null;
            String name = rs.getString("name");
            String abbreviation = rs.getString("abbreviation");
            return new State(name, abbreviation);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DaoUtil.closeConnection(connection, statement, rs);
        }
    }

    @Override
    public List<State> selectAll() {
        // String sql = "select * from state";
        // Statement statement = connection.createStatement();
        // ResultSet rs = statement.executeQuery(sql);
        // while (rs.next()) { ... }
        return null;
    }

    @Override
    public int getId(State e) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public State selectById(int i) {
        // TODO Auto-generated method stub
        return null;
    }

}