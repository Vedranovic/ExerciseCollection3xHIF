package at.htlkaindorf.bookmanagement.controller;

import at.htlkaindorf.bookmanagement.interfaces.SortBy;
import at.htlkaindorf.bookmanagement.pojos.Author;
import at.htlkaindorf.bookmanagement.pojos.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class MainController {
    @FXML
    private ListView<Author> lvAuthors;
    @FXML
    private ListView<Book> lvBooks;
    @FXML
    private ListView<Book> lvBooksAndAuthors;
    @FXML
    private Button btOrderAuthorsBy;
    @FXML
    private Button btAssign;
    @FXML
    private Button btOrderBooksBy;
    @FXML
    private Button btClearAllMappings;
    @FXML
    private Button btExportMappings;

    private RadioButton radioButton;

    private Alert errorAlert;
    private TextInputDialog inputDialog;
    private DataController dataController;

    private void initialize() throws Exception {
        errorAlert = new Alert(Alert.AlertType.ERROR);
        inputDialog = new TextInputDialog();
        dataController = new DataController();

        btOrderAuthorsBy.setOnAction(this::onOrderAuthors);
        btAssign.setOnAction(this::onAssign);
        btOrderBooksBy.setOnAction(this::onOrderBooks);
        btClearAllMappings.setOnAction(this::onClear);
        btExportMappings.setOnAction(this::onExport);
    }

    private void onAssign(ActionEvent event) {
        dataController.assign(
                lvAuthors.getSelectionModel().getSelectedIndex(),
                lvBooks.getSelectionModel().getSelectedItems());
    }

    private void onClear(ActionEvent event) {
        dataController.clearAllMappings();
    }

    private void onExport(ActionEvent event) {

    }

    private void onOrderBooks(ActionEvent event) {
        inputDialog.setTitle("ORDER BY?");
        inputDialog.setHeaderText("ORDER BY?");
        inputDialog.setContentText("""
                Order authors by: firstname, lastname, birthyear, country;\
                 A for Ascending, D for Descending\
                 Format: ????-A|B""");
        Optional<String> result = inputDialog.showAndWait();

        if (result.isPresent()) {
            sortProperty(result.get(), (p, t) -> {
                try {
                    dataController.sortBooksBy(p, t);
                } catch (Exception e) {
                    errorAlert.setContentText(e.getMessage());
                    errorAlert.showAndWait();
                }
            });
        }
    }

    private void onOrderAuthors(ActionEvent event) {

    }

    private void sortProperty(String inputStr, SortBy sortBy) {
        String[] tokens = inputStr.split("-");

        if (tokens.length != 2) {
            errorAlert.setContentText("Please use the correct format!");
            errorAlert.showAndWait();
        } else {
            String property = tokens[0];
            String type = tokens[1];

            try {
                sortBy.sort(property, type);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
            }
        }
    }
}
