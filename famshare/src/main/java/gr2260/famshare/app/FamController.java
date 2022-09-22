package gr2260.famshare.app;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class FamController {

    private Calendar calendar;
    private List<Item> itemObjectList;
    private List<Booking> bookingObjectList;
    private List<Integer> idList;
    private int idIndex;
    private User dummyUser = new User();

    public FamController() {
        idList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        idIndex = 0;
        bookingObjectList = new ArrayList<>();
        itemObjectList = new ArrayList<>();
    }

    // void initialize(URL location, ResourceBundle resources) {
    // updateItemList();
    // }

    @FXML
    private ListView<String> bookings, items;

    @FXML
    private DatePicker startDate, endDate;

    @FXML
    private Text exceptionText;

    @FXML
    private TextField description;

    public void setDummyItems() {

        Item cabin = new Item();
        cabin.setName("cabin");
        cabin.setID(1);
        Item car = new Item();
        car.setName("car");
        car.setID(2);
        Item boat = new Item();
        boat.setName("boat");
        boat.setID(3);

        itemObjectList = new ArrayList<>(Arrays.asList(cabin, car, boat));

    }

    public void updateItemList() {
        items.getItems().clear();
        for (int i = 0; i < itemObjectList.size(); i++) {
            items.getItems().add(i, itemObjectList.get(i).getName());
        }
    }

    public void updateBookingList() {
        bookings.getItems().clear();
        for (Booking booking : bookingObjectList) {
            bookings.getItems().add(booking.toString());
        }
    }

    @FXML
    void loadItems() {
        setDummyItems();
        updateItemList();
    }

    @FXML
    void book() {
        exceptionText.setText("");

        Booking tempBooking;
        LocalDate startD;
        LocalDate endD;

        int i = items.getSelectionModel().getSelectedIndex();

        try {
            startD = startDate.getValue();
            endD = endDate.getValue();

            tempBooking = new Booking(itemObjectList.get(i), dummyUser, startD, endD, 10);// temp dummy id

            bookingObjectList.add(tempBooking);
        } catch (Exception e) {
            exceptionText.setText(e.getMessage());
        }
        updateBookingList();
    }

}
