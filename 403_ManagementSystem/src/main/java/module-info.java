module at.htlkaindorf._03_managementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.zaxxer.hikari;
    requires java.sql;
    requires static lombok;


    opens at.htlkaindorf._03_managementsystem.controller to javafx.fxml;
    exports at.htlkaindorf._03_managementsystem;
}