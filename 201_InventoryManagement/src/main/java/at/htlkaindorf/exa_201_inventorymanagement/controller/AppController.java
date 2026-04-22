package at.htlkaindorf.exa_201_inventorymanagement.controller;

import at.htlkaindorf.exa_201_inventorymanagement.pojos.Item;
import at.htlkaindorf.exa_201_inventorymanagement.pojos.Type;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class AppController {
    @FXML
    private TextField tfCode;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfAmount;
    @FXML
    private TextField tfSearch;
    @FXML
    private ChoiceBox<Type> cbType;
    @FXML
    private ListView<Item> lvItems;
    @FXML
    private ListView<Type> lvTypes;
    @FXML
    private ListView<Item> lvResult;
    @FXML
    private Button btAddItem;
    @FXML
    private Button btDeleteItem;
    @FXML
    private Button btAddType;
    @FXML
    private Button btDeleteType;

    private DataController dataController;
    private Alert errorAlert;
    private TextInputDialog inputDialog;

    public void initialize() {
        dataController = new DataController();
        errorAlert = new Alert(Alert.AlertType.ERROR);
        inputDialog = new TextInputDialog();
        lvResult.setItems(dataController.getItemsList());

        tfSearch.setOnKeyTyped(this::onSearch);
        btAddType.setOnAction(this::onAddType);
        btDeleteType.setOnAction(this::onDeleteType);
        btAddItem.setOnAction(this::onAddItem);
        btDeleteItem.setOnAction(this::onDeleteItem);
        lvResult.setOnMouseClicked(this::onChangeAmount);
    }

    private void onAddType(ActionEvent event) {
        inputDialog.setTitle("TYPE ID");
        inputDialog.setHeaderText("TYPE ID");
        inputDialog.setContentText("Please enter a new Type ID:");
        Optional<String> result = inputDialog.showAndWait();

        if (result.isPresent()) {
            inputDialog.setTitle("TYPE Name");
            inputDialog.setHeaderText("TYPE Name");
            inputDialog.setContentText("Please enter a new Type Name:");
            Optional<String> secondResult = inputDialog.showAndWait();

            if (secondResult.isPresent()) {
                try {
                    dataController.addType(new Type(Integer.parseInt(result.get()), secondResult.get()));
                    lvTypes.setItems(dataController.getTypesList());
                    cbType.setItems(dataController.getTypesList());
                } catch (Exception e) {
                    errorAlert.setContentText(e.getMessage());
                    errorAlert.showAndWait();
                }
            }
        }
    }

    private void onDeleteType(ActionEvent event) {
        if (lvTypes.getSelectionModel().getSelectedIndex() >= 0) {
            dataController.removeType(lvTypes.getSelectionModel().getSelectedIndex());
            cbType.setItems(dataController.getTypesList());
        }
    }

    private void onAddItem(ActionEvent event) {
        try {
            long code = Long.parseLong(tfCode.getText());
            String name = tfName.getText();
            int amount = Integer.parseInt(tfAmount.getText());
            Type type = cbType.getValue();

            dataController.addItem(new Item(type, code, name, amount));
            lvItems.setItems(dataController.getItemsList());
        } catch (NumberFormatException nfe) {
            errorAlert.setContentText("Please enter correct values!");
            errorAlert.showAndWait();
        } catch (Exception e) {
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
    }

    private void onDeleteItem(ActionEvent event) {
        if (lvItems.getSelectionModel().getSelectedIndex() >= 0) {
            dataController.removeItem(lvItems.getSelectionModel().getSelectedIndex());
        }
    }

    private void onSearch(KeyEvent event) {
        String searchText = tfSearch.getText();

        dataController.filterItems(searchText);
    }

    private void onChangeAmount(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            inputDialog.setTitle("Amount");
            inputDialog.setHeaderText("Amount");
            inputDialog.setContentText("You can now change the amount to:");
            Optional<String> result = inputDialog.showAndWait();

            if (result.isPresent()) {
                try {
                    int amount = Integer.parseInt(result.get());

                    dataController.changeAmountOfFilteredItems(amount, lvResult.getSelectionModel().getSelectedIndex());
                    lvResult.setItems(dataController.getFilteredItemList());
                    lvItems.setItems(dataController.getItemsList());
                } catch (NumberFormatException nfe) {
                    errorAlert.setContentText("Enter a valid number!");
                    errorAlert.showAndWait();
                }
            }
        }
    }
}
