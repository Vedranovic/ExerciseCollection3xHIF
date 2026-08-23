package at.htlkaindorf._03_managementsystem.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(AppProperties.DB_URL);
        config.setUsername(AppProperties.DB_USER);
        config.setPassword(AppProperties.DB_PASSWORD);

        config.setMaximumPoolSize(AppProperties.DB_POOL_MAX_SIZE);
        config.setMinimumIdle(AppProperties.DB_POOL_MIN_IDLE);

        config.setPoolName("JDBC-Pool");
        config.setConnectionTimeout(5000);
        config.setLeakDetectionThreshold(10000);
        config.setMaxLifetime(1_800_000);

        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closePool() {
        dataSource.close();
    }
}