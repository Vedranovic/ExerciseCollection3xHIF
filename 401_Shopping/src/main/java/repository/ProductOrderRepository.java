package repository;

import config.DatabaseConfig;
import exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductOrderRepository {
    public void create() {
        String sql = """
                CREATE TABLE IF NOT EXISTS sh_product_order (
                        product_id      INTEGER NOT NULL,
                        order_id        INTEGER NOT NULL,
                        quantity        INTEGER,
                        PRIMARY KEY (product_id, order_id),
                        FOREIGN KEY (product_id) REFERENCES sh_product(id),
                        FOREIGN KEY (order_id) REFERENCES sh_order(id)
                );
               """;

        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);
        } catch (SQLException e) {
            throw new DatabaseException("ERROR creating sh_product_order!", e);
        }
    }
}
