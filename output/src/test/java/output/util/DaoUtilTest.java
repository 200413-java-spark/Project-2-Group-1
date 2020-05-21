package output.util;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

public class DaoUtilTest {
    
    @Test
    public void testConnection() {
        Connection connection = null;
        connection = DaoUtil.getConnection();
        Assert.assertNotNull(connection);
        DaoUtil.closeConnection(connection);
    }

}