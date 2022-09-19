package gr2260.famshare.app;

import javafx.fxml.FXML;

public class FamController {

    private Calendar calendar;

    public FamController(Calendar calendar) {
        this.calendar = calendar;
    }

    public boolean isAvailable() {
        if (calendar.isAvailable()) {
            return true;
        }
        return false;
    }

}
