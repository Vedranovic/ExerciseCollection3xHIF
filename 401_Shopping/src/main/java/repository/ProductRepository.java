package repository;

import config.DatabaseConfig;
import exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductRepository {
    public void create() {
        String sql = """
                    CREATE TABLE IF NOT EXISTS sh_product (
                        id          SERIAL PRIMARY KEY NOT NULL,
                        name        VARCHAR(100),
                        description VARCHAR(1000),
                        category_id INTEGER,
                        FOREIGN KEY (category_id) REFERENCES sh_category
                    );
                   """;

        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);
        } catch (SQLException e) {
            throw new DatabaseException("ERROR creating sh_product!", e);
        }
    }
}
