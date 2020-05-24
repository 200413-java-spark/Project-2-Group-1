package output.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AppPropertiesTest {

    @Test
    public void GetPropertiesTest() {
        String user = AppProperties.PROPERTIES.getProperty("DATABASE_USER");
        assertEquals("mydb", user);
    }

}