package at.htlkaindorf._01_inventorymanagement.controller;

import at.htlkaindorf._01_inventorymanagement.pojos.Item;
import at.htlkaindorf._01_inventorymanagement.pojos.Type;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class AppController {
    @FXML
    public TextField tfCode;
    @FXML
    public TextField tfName;
    @FXML
    public TextField tfAmount;
    @FXML
    public ChoiceBox<Type> cbType;
    @FXML
    public ListView<Item> lvItems;
    @FXML
    public Button btAddItem;
    @FXML
    public Button btDeleteItem;
    @FXML
    public Button btAddType;
    @FXML
    public Button btDeleteType;
    @FXML
    public ListView<Type> lvTypes;
    @FXML
    public TextField tfSearchText;
    @FXML
    public ListView<Item> lvOutput;

    private DataController dataController;
    private Alert errorAlert;

    public void initialize() {
        dataController = new DataController();
        errorAlert = new Alert(Alert.AlertType.ERROR);

        btAddType.setOnAction(this::onAddType);
        btDeleteType.setOnAction(this::onDeleteType);
        btAddItem.setOnAction(this::onAddItem);
        btDeleteItem.setOnAction(this::onDeleteItem);
        tfSearchText.setOnKeyTyped(this::onSearch);
        lvOutput.setOnMouseClicked(this::onChangeAmountOfFilteredItem);
    }

    private void onAddType(ActionEvent event) {
        TextInputDialog idDialog = new TextInputDialog();
        idDialog.setTitle("TYPE ID");
        idDialog.setHeaderText("TYPE ID:");
        idDialog.setContentText("Please enter a new Type ID:");
        Optional<String> id = idDialog.showAndWait();

        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setTitle("TYPE Name");
        nameDialog.setHeaderText("TYPE Name:");
        nameDialog.setContentText("Please enter a new Type Name:");
        Optional<String> name = nameDialog.showAndWait();

        if (id.isPresent() && name.isPresent()) {
            try {
                dataController.addType(new Type(Long.parseLong(id.get()), name.get()));
                cbType.getItems().setAll(dataController.getTypesList());
                lvTypes.setItems(dataController.getTypesList());
            } catch (NumberFormatException nfe) {
                errorAlert.setContentText("Please fill in only correct values!");
                errorAlert.showAndWait();
            } catch (Exception e) {
                errorAlert.setContentText(e.getMessage());
                errorAlert.showAndWait();
            }
        }
    }

    private void onDeleteType(ActionEvent event) {
        dataController.removeType(lvTypes.getSelectionModel().getSelectedIndex());
        cbType.getItems().setAll(dataController.getTypesList());
        lvTypes.setItems(dataController.getTypesList());
    }

    private void onAddItem(ActionEvent event) {
        if (tfCode.getText().isBlank() || tfName.getText().isBlank() || cbType.getSelectionModel().isEmpty() || tfAmount.getText().isBlank()) {
            errorAlert.setContentText("Please fill in all fields!");
            errorAlert.showAndWait();
        } else {
            try {
                dataController.addItem(new Item(
                        cbType.getSelectionModel().getSelectedItem(),
                        Long.parseLong(tfCode.getText()),
                        tfName.getText(),
                        Integer.parseInt(tfAmount.getText())
                ));
                lvItems.setItems(dataController.getItemsList());
                lvOutput.setItems(dataController.getItemsList());
            } catch (NumberFormatException nfe) {
                errorAlert.setContentText("Please fill in only correct values!");
                errorAlert.showAndWait();
            } catch (Exception e) {
                errorAlert.setContentText(e.getMessage());
                errorAlert.showAndWait();
            }
        }
    }

    private void onDeleteItem(ActionEvent event) {
        dataController.removeItem(lvItems.getSelectionModel().getSelectedIndex());
        lvItems.setItems(dataController.getItemsList());
        lvOutput.setItems(dataController.getItemsList());
    }

    private void onSearch(KeyEvent event) {
        dataController.filterItems(tfSearchText.getText());
        lvOutput.setItems(dataController.getFilteredList());
    }

    private void onChangeAmountOfFilteredItem(MouseEvent mouseEvent) {
        if (lvOutput.getSelectionModel().getSelectedIndex() >= 0) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Amount");
            dialog.setHeaderText("Amount");
            dialog.setContentText("You can now change the amount to:");
            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {
                try {
                    dataController.changeAmountOfFiltered(
                            Integer.parseInt(result.get()), lvOutput.getSelectionModel().getSelectedIndex());
                    lvItems.setItems(dataController.getItemsList());
                    lvOutput.setItems(dataController.getItemsList());
                } catch (NumberFormatException nfe) {
                    errorAlert.setContentText("Please fill in a valid value!");
                    errorAlert.showAndWait();
                }
            }
        }
    }
}
