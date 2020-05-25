package output.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class AppProperties {

    public static final Properties PROPERTIES = loadProperties();

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try {
            InputStream input = ClassLoader.getSystemClassLoader().getResourceAsStream("app.properties");
//            properties.load(new FileInputStream("src/main/resources/app.properties"));
            properties.load(input);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}