module at.htlkaindorf.exa_201_inventorymanagement {
    requires javafx.controls;
    requires javafx.fxml;


    opens at.htlkaindorf.exa_201_inventorymanagement to javafx.fxml;
    exports at.htlkaindorf.exa_201_inventorymanagement;
    exports at.htlkaindorf.exa_201_inventorymanagement.controller;
    opens at.htlkaindorf.exa_201_inventorymanagement.controller to javafx.fxml;
}