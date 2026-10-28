package at.htlkaindorf._04_bookmanagement.controller;

import at.htlkaindorf._04_bookmanagement.pojos.Author;
import at.htlkaindorf._04_bookmanagement.pojos.Book;
import at.htlkaindorf._04_bookmanagement.pojos.FileIO;
import at.htlkaindorf._04_bookmanagement.pojos.SortBy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class MainController {
    @FXML
    public VBox vbHeader;
    @FXML
    public ListView<Author> lvAuthors;
    @FXML
    public ListView<Book> lvBooks;
    @FXML
    public Button btOrderAuthorsBy;
    @FXML
    public Button btAssign;
    @FXML
    public Button btOrderBooksBy;
    @FXML
    public ListView<String> lvAuthorBooks;
    @FXML
    public Button btClearAllMapping;
    @FXML
    public Button btExportMappings;

    private DataController dataController;
    private TextInputDialog inputDialog;
    private Alert errorAlert;

    public void initialize() {
        dataController = new DataController();
        inputDialog = new TextInputDialog();
        errorAlert = new Alert(Alert.AlertType.ERROR);
        lvAuthors.setItems(FXCollections.observableArrayList(FileIO.readUniqueAuthors()));
        lvBooks.setItems(FXCollections.observableArrayList(FileIO.readUniqueBooks()));
        lvBooks.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvAuthorBooks.setItems(dataController.getBookAuthors());

        btOrderAuthorsBy.setOnAction(this::onOrderAuthors);
        btAssign.setOnAction(this::onAssign);
        btOrderBooksBy.setOnAction(this::onOrderBooks);
        btClearAllMapping.setOnAction(this::onClearAll);
        btExportMappings.setOnAction(this::onExport);
    }

    private void onAssign(ActionEvent event) {
        int index = lvAuthors.getSelectionModel().getSelectedIndex();
        ObservableList<Book> books = lvBooks.getSelectionModel().getSelectedItems();

        if (index >= 0 && books != null) {
            dataController.assign(index, books);
            lvAuthorBooks.setItems(dataController.getBookAuthors());
        }
    }

    private void onClearAll(ActionEvent event) {
        dataController.clearAllMappings();
        lvAuthorBooks.setItems(null);
    }

    private void onExport(ActionEvent event) {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File...");
        fileChooser.setInitialFileName("Untitled.json");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("JSON File", ".json")
        );

        File selected = fileChooser.showSaveDialog(vbHeader.getScene().getWindow());

        infoAlert.setTitle("Bestätigung");
        infoAlert.setHeaderText("Bestätigung");

        try {
            dataController.exportData(selected);
            infoAlert.setContentText("Data exported!");
            infoAlert.showAndWait();
        } catch (Exception e) {
            infoAlert.setContentText("Error Exporting!");
            infoAlert.showAndWait();
        }
    }

    private void onOrderBooks(ActionEvent event) {
        inputDialog.setTitle("ORDER BY?");
        inputDialog.setHeaderText("ORDER BY?");
        inputDialog.setContentText("Order books by: isbn, title, pages, year, genre;\nA for Ascending, D for Descending\nFormat: ????-A|D.");
        Optional<String> result = inputDialog.showAndWait();

        result.ifPresent(s -> sortProperty(s, (p, t) -> {
            try {
                dataController.sortBooksBy(p, t);
            } catch (Exception e) {
                errorAlert.setContentText(e.getMessage());
                errorAlert.showAndWait();
            }
        }));

        lvBooks.setItems(FXCollections.observableArrayList(dataController.getBooks()));
    }

    private void onOrderAuthors(ActionEvent event) {
        inputDialog.setTitle("ORDER BY?");
        inputDialog.setHeaderText("ORDER BY?");
        inputDialog.setContentText("Order authors by: firstname, lastname, birthyear, country;\nA for Ascending, D for Descending\nFormat: ????-A|D.");
        Optional<String> result = inputDialog.showAndWait();

        result.ifPresent(s -> sortProperty(s, (p, t) -> {
            try {
                dataController.sortAuthorsBy(p, t);
            } catch (Exception e) {
                errorAlert.setContentText(e.getMessage());
                errorAlert.showAndWait();
            }
        }));

        lvAuthors.setItems(FXCollections.observableArrayList(dataController.getAuthors()));
    }

    private void sortProperty(String inputStr, SortBy sortBy) {
        errorAlert.setTitle("Fehler");
        errorAlert.setHeaderText("Fehler");

        if (!inputStr.contains("-")) {
            errorAlert.setContentText("Please use the correct format!");
            errorAlert.showAndWait();
        } else {
            String[] tokens = inputStr.split("-");

            try {
                sortBy.sort(tokens[0], tokens[1]);
            } catch (Exception e) {
                errorAlert.setContentText("Please enter more data!");
                errorAlert.showAndWait();
            }
        }
    }
}
