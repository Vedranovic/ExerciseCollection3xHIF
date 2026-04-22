module at.htlkaindorf.bookmanagement {
    requires javafx.controls;
    requires javafx.fxml;


    opens at.htlkaindorf.bookmanagement to javafx.fxml;
    exports at.htlkaindorf.bookmanagement;
    exports at.htlkaindorf.bookmanagement.controller;
    opens at.htlkaindorf.bookmanagement.controller to javafx.fxml;
}