module famshare.ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires famshare.core;

    opens gr2260.famshare.app to javafx.graphics, javafx.fxml;
}
