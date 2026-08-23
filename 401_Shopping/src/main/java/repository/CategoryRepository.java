package repository;

import config.DatabaseConfig;
import exceptions.DatabaseException;
import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    public void create() {
        String sql = """
                CREATE TABLE IF NOT EXISTS sh_category (
                    id          SERIAL PRIMARY KEY NOT NULL,
                    name        VARCHAR(100)
                );
               """;

        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);
        } catch (SQLException e) {
            throw new DatabaseException("ERROR creating sh_category!", e);
        }
    }

    public List<Category> findAll() {
        String sql = """
                SELECT  id, name
                FROM    sh_category;
                """;
        List<Category> categories = new ArrayList<>();

        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                categories.add(new Category(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                ));
            }

            return categories;
        } catch (SQLException e) {
            throw new DatabaseException("ERROR finding all categories!", e);
        }
    }

    public Category findById(long id) {
        String sql = """
                SELECT  id, name
                FROM    sh_category
                WHERE   id = ?;
                """;

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Category(
                            resultSet.getLong("id"),
                            resultSet.getString("name"));
                }
            }

            return null;
        } catch (SQLException e) {
            throw new DatabaseException("ERROR finding category with id: " + id, e);
        }
    }

    public Category insert(Category category) {
        String sql = """
                INSERT INTO sh_category(name) VALUES (?) RETURNING id;
                """;

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, category.getName());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                   category.setId(resultSet.getLong("id"));
                }
            }

            return category;
        } catch (SQLException e) {
            throw new DatabaseException("ERROR inserting the category!", e);
        }
    }

    public void update(Category category) {
        String sql = """
                UPDATE  sh_category
                SET     name = ?
                WHERE   id = ?;
                """;

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, category.getName());
            statement.setLong(2, category.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("ERROR updating sh_categories!", e);
        }
    }

    public void delete(long id) {
        String sql = """
                DELETE FROM sh_category
                WHERE id = ?;
                """;

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("ERROR deleting category at id: " + id, e);
        }
    }
}
