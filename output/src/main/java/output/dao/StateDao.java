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
        String sql = "select count(*) as total from state";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DaoUtil.closeConnection(connection, statement, rs);
            return count;
        }
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
        String sql = "insert into state(id, state) values(?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.prepareStatement(sql);
            int id = 1;
            for(State s : states) {
                String state = s.state;
                if(state.isEmpty() || state == null) {
                    return false;
                }
                statement.setInt(1, id++);
                statement.setString(2, state);
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
        String sql = "delete from state";
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
    public State select(State s) {
        if(s.state.isEmpty() || s == null) {
            return null;
        }
        String sql = "select * from state where state.state = '" + s.state + "'";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DaoUtil.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            if(!rs.next()) {
                return null;
            }
            int id = rs.getInt("id");
            String state = rs.getString("state");
            return new State(id, state);
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