module gr2260.famshare.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens gr2260.famshare.app to javafx.graphics, javafx.fxml;
}
