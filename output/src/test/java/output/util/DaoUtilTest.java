package output.util;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;

@Ignore
public class DaoUtilTest {

    @Test
    public void testConnection() {
        Connection connection = null;
        connection = DaoUtil.getConnection();
        Assert.assertNotNull(connection);
        DaoUtil.closeConnection(connection);
    }

}