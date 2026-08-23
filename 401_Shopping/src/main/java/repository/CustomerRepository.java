package repository;

import config.DatabaseConfig;
import exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerRepository {
    public void create() {
        String sql = """
                CREATE TABLE IF NOT EXISTS sh_customer (
                    id          SERIAL PRIMARY KEY NOT NULL,
                    name        VARCHAR(100)
                );
               """;

        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);
        } catch (SQLException e) {
            throw new DatabaseException("ERROR creating sh_customer!", e);
        }
    }
}
