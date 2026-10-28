module at.htlkaindorf._04_bookmanagement {
    requires javafx.controls;
    requires javafx.fxml;


    opens at.htlkaindorf._04_bookmanagement.controller to javafx.fxml;
    exports at.htlkaindorf._04_bookmanagement;
}