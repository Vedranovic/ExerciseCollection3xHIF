package at.htlkaindorf._03_managementsystem.controller;

import at.htlkaindorf._03_managementsystem.models.Club;
import at.htlkaindorf._03_managementsystem.models.Match;
import at.htlkaindorf._03_managementsystem.models.Player;
import at.htlkaindorf._03_managementsystem.models.Trainer;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AppController {
    @FXML
    public ListView<Club> lvClubs;
    @FXML
    public ListView<Player> lvPlayers;
    @FXML
    public ListView<Trainer> lvTrainers;
    @FXML
    public ListView<Match> lvMatches;
    @FXML
    public Tab tbMatch;
    @FXML
    public Button btAddClub;
    @FXML
    public Button btDeleteClub;
    @FXML
    public Button btChangeClub;
    @FXML
    public Button btAddPlayer;
    @FXML
    public Button btDeletePlayer;
    @FXML
    public Button btChangePlayer;
    @FXML
    public Button btAddTrainer;
    @FXML
    public Button btDeleteTrainer;
    @FXML
    public Button btChangeTrainer;
    @FXML
    public Button btAddMatch;
    @FXML
    public Button btDeleteMatch;
    @FXML
    public Button btChangeMatch;
    @FXML
    public TextField tfClub;
    @FXML
    public TextField tfFirstField;
    @FXML
    public TextField tfSecondField;
    @FXML
    public TextField tfThirdField;

    private DataController dataController;
    private Alert errorAlert;

    public void initialize() throws IOException {
        dataController = new DataController();
        errorAlert = new Alert(Alert.AlertType.ERROR);
        dataController.setCSV();
        lvClubs.setItems(dataController.getClubs());
        lvClubs.setOnMouseClicked(this::onClub);
        btAddClub.setUserData("club");
        btDeleteClub.setUserData("club");
        btAddPlayer.setUserData("player");
        btDeletePlayer.setUserData("player");
        btAddTrainer.setUserData("trainer");
        btDeleteTrainer.setUserData("trainer");
        btAddMatch.setUserData("match");
        btDeleteMatch.setUserData("match");

        Button[] addButtons = {
                btAddClub,
                btAddPlayer,
                btAddTrainer,
                btAddMatch
        };

        Button[] deleteButtons = {
                btDeleteClub,
                btDeletePlayer,
                btDeleteTrainer,
                btDeleteMatch
        };

        Button[] changeButtons = {
                btChangePlayer,
                btChangeTrainer,
                btChangeMatch
        };

        for (Button button : addButtons) {
            button.setOnAction(this::onAdd);
        }

        for (Button button : deleteButtons) {
            button.setOnAction(this::onDelete);
        }

        for (Button button : changeButtons) {
            button.setOnAction(this::onChange);
        }

        tbMatch.setOnSelectionChanged(this::onMatchTab);
    }

    public void onAdd(ActionEvent event) {
        Button btn = (Button) event.getSource();
        String type = (String) btn.getUserData();

        switch (type) {
            case "club":
                if (tfClub.getText().isBlank()) {
                    errorAlert.setContentText("Please enter a club!");
                    errorAlert.showAndWait();
                } else {
                    lvClubs.getItems().add(
                            Club.builder()
                                    .name(tfClub.getText())
                                    .build()
                    );
                }
            break;
            case "player":
                if (isFilledOut()) {
                    lvPlayers.getItems().add(
                            Player.builder()
                                    .firstname(tfFirstField.getText())
                                    .lastname(tfSecondField.getText())
                                    .age(Integer.parseInt(tfThirdField.getText()))
                                    .build()
                    );
                }
            break;
            case "trainer":
                if (isFilledOut()) {
                    lvTrainers.getItems().add(
                            Trainer.builder()
                                    .firstname(tfFirstField.getText())
                                    .lastname(tfSecondField.getText())
                                    .age(Integer.parseInt(tfThirdField.getText()))
                                    .build()
                    );
                }
            break;
            case "match":
                if (isFilledOut()) {
                    lvMatches.getItems().add(
                            Match.builder()
                                    .club(Club.builder()
                                            .name(tfFirstField.getText())
                                            .build())
                                    .enemy(Club.builder()
                                            .name(tfSecondField.getText())
                                            .build())
                                    .startDate(LocalDate.parse(
                                            tfThirdField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                                    .build()
                    );
                }
            break;
        }
    }

    private boolean isFilledOut() {
        if (tfFirstField.getText().isBlank() || tfSecondField.getText().isBlank() || tfThirdField.getText().isBlank()) {
            errorAlert.setContentText("Please fill in the fields!");
            errorAlert.showAndWait();

            return false;
        }

        return true;
    }

    public void onDelete(ActionEvent event) {
        Button btn = (Button) event.getSource();
        String type = (String) btn.getUserData();

        switch (type) {
            case "club" -> lvClubs.getSelectionModel().getSelectedItems().remove(lvClubs.getSelectionModel().getSelectedItem());
            case "player" -> lvPlayers.getSelectionModel().getSelectedItems().remove(lvPlayers.getSelectionModel().getSelectedItem());
            case "trainer" -> lvTrainers.getSelectionModel().getSelectedItems().remove(lvTrainers.getSelectionModel().getSelectedItem());
            case "match" -> lvMatches.getSelectionModel().getSelectedItems().remove(lvMatches.getSelectionModel().getSelectedItem());
        }
    }

    public void onChange(ActionEvent event) {

    }

    public void onClub(Event event) {
        lvPlayers.setItems(dataController.setPlayers(lvClubs.getSelectionModel().getSelectedIndex()));
        lvTrainers.setItems(dataController.setTrainers(lvClubs.getSelectionModel().getSelectedIndex()));
        lvMatches.setItems(dataController.setMatches(lvClubs.getSelectionModel().getSelectedItem()));
    }

    public void onMatchTab(Event event) {
        if (tbMatch.isSelected()) {
            tfFirstField.setPromptText("Club");
            tfSecondField.setPromptText("Enemy");
            tfThirdField.setPromptText("Date");
        }

        tfFirstField.setPromptText("Firstname");
        tfSecondField.setPromptText("Lastname");
        tfThirdField.setPromptText("Age");
    }
}
