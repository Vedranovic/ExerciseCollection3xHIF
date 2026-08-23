package at.htlkaindorf._03_managementsystem;

import at.htlkaindorf._03_managementsystem.models.Club;
import at.htlkaindorf._03_managementsystem.models.Match;
import at.htlkaindorf._03_managementsystem.models.Player;
import at.htlkaindorf._03_managementsystem.models.Trainer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class FileAccess {
    private static final Path clubPath;
    private static final Path playerPath;
    private static final Path trainerPath;
    private static final Path matchPath;

    static {
        clubPath = Path.of(System.getProperty("user.dir"),
                "src", "main", "resources", "files", "clubs.csv");
        playerPath = Path.of(System.getProperty("user.dir"),
                "src", "main", "resources", "files", "players.csv");
        trainerPath = Path.of(System.getProperty("user.dir"),
                "src", "main", "resources", "files", "trainers.csv");
        matchPath = Path.of(System.getProperty("user.dir"),
                "src", "main", "resources", "files", "matches.csv");
    }

    public static List<Club> readClubs() throws IOException {
        try (Stream<String> stream = Files.lines(clubPath)) {
            return stream
                    .skip(1)
                    .map(Club::toClub)
                    .toList();
        }
    }

    public static List<Player> readPlayers() throws IOException {
        try (Stream<String> stream = Files.lines(playerPath)) {
            return stream
                    .skip(1)
                    .map(Player::toPlayer)
                    .toList();
        }
    }

    public static List<Trainer> readTrainers() throws IOException {
        try (Stream<String> stream = Files.lines(trainerPath)) {
            return stream
                    .skip(1)
                    .map(Trainer::toTrainer)
                    .toList();
        }
    }

    public static List<Match> readMatches() throws IOException {
        try (Stream<String> stream = Files.lines(matchPath)) {
            return stream
                    .skip(1)
                    .map(Match::toMatch)
                    .toList();
        }
    }
}
