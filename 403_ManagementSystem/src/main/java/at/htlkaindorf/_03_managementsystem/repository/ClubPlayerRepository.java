package at.htlkaindorf._03_managementsystem.repository;

import at.htlkaindorf._03_managementsystem.config.DatabaseConfig;
import at.htlkaindorf._03_managementsystem.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ClubPlayerRepository {
    public void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS cl_clubPlayer(
                    club_id     INTEGER REFERENCES cl_club(id),
                    player_id   INTEGER REFERENCES cl_player(id),
                    startDate   DATE,
                    endDate     DATE,
                
                    PRIMARY KEY (club_id, player_id, startDate)
                );
                """;

        try (Connection connection = DatabaseConfig.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);
        } catch (SQLException e) {
            throw new DatabaseException("Couldn't create table cl_clubPlayer", e);
        }
    }
}
