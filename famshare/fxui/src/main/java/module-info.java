module famshare.ui {
    requires famshare.core;
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires javafx.graphics;


    opens famshare.ui to javafx.fxml, javafx.graphics;
}
