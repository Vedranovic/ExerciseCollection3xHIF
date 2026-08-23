package at.htlkaindorf._03_managementsystem.controller;

import at.htlkaindorf._03_managementsystem.FileAccess;
import at.htlkaindorf._03_managementsystem.models.Club;
import at.htlkaindorf._03_managementsystem.models.Match;
import at.htlkaindorf._03_managementsystem.models.Player;
import at.htlkaindorf._03_managementsystem.models.Trainer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.io.IOException;
import java.util.Objects;

@Getter
public class DataController {
    private ObservableList<Club> clubs;
    private ObservableList<Player> players;
    private ObservableList<Trainer> trainers;
    private ObservableList<Match> matches;

    public DataController() {
        clubs = FXCollections.observableArrayList();
        players = FXCollections.observableArrayList();
        trainers = FXCollections.observableArrayList();
        matches = FXCollections.observableArrayList();
    }

    public void setCSV() throws IOException {
        clubs.setAll(FileAccess.readClubs());
        players.setAll(FileAccess.readPlayers());
        trainers.setAll(FileAccess.readTrainers());
        matches.setAll(FileAccess.readMatches());
    }

    public ObservableList<Player> setPlayers(int index) {
        ObservableList<Player> clubPlayers = FXCollections.observableArrayList();
        Long id = clubs.get(index).getId();

        players.forEach(player -> {
            if (Objects.equals(player.getClubId(), id)) {
                clubPlayers.add(player);
            }
        });

        return clubPlayers;
    }

    public ObservableList<Trainer> setTrainers(int index) {
        ObservableList<Trainer> clubTrainer = FXCollections.observableArrayList();
        Long id = clubs.get(index).getId();

        trainers.forEach(trainer -> {
            if (Objects.equals(trainer.getClubId(), id)) {
                clubTrainer.add(trainer);
            }
        });

        return clubTrainer;
    }

    public ObservableList<Match> setMatches(Club club) {
        ObservableList<Match> clubMatch = FXCollections.observableArrayList();

        matches.forEach(match -> {
            if (Objects.equals(match.getClub().getName(), club.getName())) {
                clubMatch.add(match);
            }
        });

        return clubMatch;
    }
}
