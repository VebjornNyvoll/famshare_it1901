package gr2260.famshare.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class FamController {

    private Calendar calendar;
    private List<Item> itemObjectList;
    private User dummyUser = new User();

    public FamController() {
        calendar = new Calendar();
        dummyUser.setName("Dummy User");
    }

    @FXML
    private ListView<String> bookingView, itemView;

    @FXML
    private DatePicker startDate, endDate;

    @FXML
    private Text exceptionText;

    @FXML
    private TextField description;

    public void setDummyItems() { // will load itemView from external file later

        Item cabin = new Item();
        cabin.setName("Cabin");
        cabin.setID(1);
        Item car = new Item();
        car.setName("Car");
        car.setID(2);
        Item boat = new Item();
        boat.setName("Boat");
        boat.setID(3);

        itemObjectList = new ArrayList<>(Arrays.asList(cabin, car, boat));
    }

    public void updateItemView() {
        itemView.getItems().clear();
        for (int i = 0; i < itemObjectList.size(); i++) {
            itemView.getItems().add(i, itemObjectList.get(i).getName());
        }
    }

    public void updateBookingView() {
        bookingView.getItems().clear();
        for (Booking booking : calendar.getBookings()) {
            bookingView.getItems().add(booking.toString());
        }
    }

    @FXML
    void loadItems() {
        setDummyItems();
        updateItemView();
    }

    @FXML
    void book() {
        exceptionText.setText("");

        try {
            int i = itemView.getSelectionModel().getSelectedIndex();
            LocalDate startD = startDate.getValue();
            LocalDate endD = endDate.getValue();
            Booking newBooking = new Booking(itemObjectList.get(i), dummyUser, startD, endD, 10);// temp dummy id

            calendar.addBooking(newBooking);
            updateBookingView();

        } catch (Exception e) {
            exceptionText.setText(e.getMessage());
        }
    }
}
