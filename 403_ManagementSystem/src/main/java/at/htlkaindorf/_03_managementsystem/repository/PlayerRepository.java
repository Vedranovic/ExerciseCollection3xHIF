package at.htlkaindorf._03_managementsystem.repository;

import at.htlkaindorf._03_managementsystem.config.DatabaseConfig;
import at.htlkaindorf._03_managementsystem.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PlayerRepository {
    public void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS cl_player (
                    id          SERIAL PRIMARY KEY,
                    firstname   VARCHAR(100),
                    lastname    VARCHAR(100)
                );
                """;

        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);
        } catch (SQLException e) {
            throw new DatabaseException("Couldn't create table cl_player", e);
        }
    }
}
