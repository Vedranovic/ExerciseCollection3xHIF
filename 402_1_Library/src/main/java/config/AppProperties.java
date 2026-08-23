package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProperties {
    public static final String DB_URL;
    public static final String DB_USER;
    public static final String DB_PASSWORD;

    public static final int DB_POOL_MAX_SIZE;
    public static final int DB_POOL_MIN_IDLE;

    static {
        Properties props = new Properties();

        try (InputStream inputStream = AppProperties.class.getResourceAsStream("/db.properties")) {
            if (inputStream == null) {
                throw new RuntimeException("db.properties could not be found!");
            }

            props.load(inputStream);

            DB_URL = props.getProperty("db.url");
            DB_USER = props.getProperty("db.user");
            DB_PASSWORD = props.getProperty("db.password");

            DB_POOL_MAX_SIZE = Integer.parseInt(props.getProperty("db.pool.maxSize"));
            DB_POOL_MIN_IDLE = Integer.parseInt(props.getProperty("db.pool.minIdle"));
        } catch (IOException ioe) {
            throw new RuntimeException("Error loading db.properties: ", ioe);
        }
    }
}
