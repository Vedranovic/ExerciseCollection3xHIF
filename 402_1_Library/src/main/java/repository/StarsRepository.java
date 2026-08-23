package repository;

import config.DatabaseConfig;
import exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StarsRepository {
    public void create() {
        String sql = """
                CREATE TABLE IF NOT EXISTS lb_stars (
                    id          SERIAL PRIMARY KEY,
                    value       VARCHAR(100)
                );
                """;

        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);
        } catch (SQLException e) {
            throw new DatabaseException("ERROR creating lb_stars!", e);
        }
    }
}
