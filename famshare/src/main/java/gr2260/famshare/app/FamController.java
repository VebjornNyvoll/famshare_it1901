package gr2260.famshare.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class FamController {

    private Calendar calendar;
    private List<Integer> idList;
    private int idIndex;

    public FamController(Calendar calendar) {
        this.calendar = calendar;
        idList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        idIndex = 0;
    }

    @FXML
    private ListView<String> bookings;

    @FXML
    private DatePicker startDate, endDate;

    @FXML
    private TextField item, username, description;

    public void book() {
        Item bookedObject = new Item();
        bookedObject.setName(item.getText());
        bookedObject.setDecsripion(description.getText());
        bookedObject.setID(idList.get(idIndex));

        User user = new User();
        user.setName(username.getText());
        user.addItem(idList.get(idIndex));

        Booking newBooking = new Booking(bookedObject, user, startDate.getValue(), endDate.getValue());

        isAvailable(newBooking);
    }

    public boolean isAvailable(Booking newBooking) {
        if (calendar.isAvailable(newBooking)) {
            // setter inn i lista etter ID-indeks, bare en midlertidig løsning
            bookings.getItems().set(idIndex, newBooking.toString());
            idIndex++;
            return true;
        }
        return false;
    }
}
