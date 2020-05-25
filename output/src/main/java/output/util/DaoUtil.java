package output.util;

import java.sql.*;

import static output.util.AppProperties.PROPERTIES;
import static output.dao.RegionDao.regionDao;
import static output.dao.StateDao.stateDao;
import static output.dao.CountyDao.countyDao;

public final class DaoUtil {

    private static final String URL = PROPERTIES.getProperty("DATABASE_URL");
    private static final String USER = PROPERTIES.getProperty("DATABASE_USER");
    private static final String PASSWORD = PROPERTIES.getProperty("DATABASE_PASSWORD");

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeConnection(Connection connection, Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeConnection(Connection connection, Statement statement, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean clearTables() {
        regionDao.deleteAll();
        stateDao.deleteAll();
        countyDao.deleteAll();
        return true;
    }

}