package repository;

import config.DatabaseConfig;
import exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderRepository {
    public void create() {
        String sql = """
                    CREATE TABLE IF NOT EXISTS sh_order (
                        id             SERIAL PRIMARY KEY NOT NULL,
                        customer_id    INTEGER,
                        FOREIGN KEY (customer_id) REFERENCES sh_customer,
                        order_date     DATE
                    );
                   """;

        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);
        } catch (SQLException e) {
            throw new DatabaseException("ERROR creating sh_order!", e);
        }
    }
}
