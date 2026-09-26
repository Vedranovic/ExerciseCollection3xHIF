module at.htlkaindorf._01_inventorymanagement {
    requires javafx.controls;
    requires javafx.fxml;


    opens at.htlkaindorf._01_inventorymanagement.controller to javafx.fxml;
    exports at.htlkaindorf._01_inventorymanagement;
}