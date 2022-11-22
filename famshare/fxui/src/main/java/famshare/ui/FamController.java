package famshare.ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import famshare.core.*;
import famshare.jsoncore.CalendarPersistance;

public class FamController {

    
    private Calendar calendar;
    private List<Item> itemObjectList;
    private User dummyUser = new User();
    private String filePath = "src/main/resources/famshare/ui/calendar.json";
    private CalendarPersistance persistance = new CalendarPersistance();
   

    public FamController() throws IOException {
        dummyUser.setName("Dummy User");
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public List<Item> getItemObjectList() {
        return new ArrayList<>(itemObjectList);
    }

    @FXML
    private ListView<String> bookingView, itemView;

    @FXML
    private DatePicker startDate, endDate;

    @FXML
    private Text exceptionText;

    @FXML
    private Button bookButton;

    @FXML
    private TextField description;

    public void setDummyItems() { // will load itemView from external file later

        Item cabin = new Item();
        cabin.setName("Cabin");
        cabin.setId(1);
        Item car = new Item();
        car.setName("Car");
        car.setId(2);
        Item boat = new Item();
        boat.setName("Boat");
        boat.setId(3);
        Item drill = new Item();
        drill.setName("Drill");
        drill.setId(4);
        Item bike = new Item();
        bike.setName("Bike");
        bike.setId(5);
        Item toolBox = new Item();
        toolBox.setName("Tool box");
        toolBox.setId(6);

        itemObjectList = new ArrayList<>(Arrays.asList(cabin, car, boat, drill, bike, toolBox));
    }

    @FXML
    void initialize() throws IOException {
        setDummyItems();
        // Load calendar from file if there is one
        try {
            calendar = persistance.readCalendar(filePath);  
        } catch (IOException e) {
            calendar = new Calendar();
        }
        updateItemView();
        updateBookingView();
        listenToItemView();
        
    }

    private void setDayCellFactories(List<LocalDate> bookedDates) {
        startDate.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                for (LocalDate localDate : bookedDates) {
                    if (date.equals(localDate)) {
                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;");
                    }
                }

            }
        });

        endDate.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                for (LocalDate localDate : bookedDates) {
                    if (date.equals(localDate)) {
                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;");
                    }
                }

            }
        });
    }

    public void listenToItemView() {
        itemView.getSelectionModel().selectedItemProperty()
                .addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    updateDisabledDates(newValue);
                });
    }

    private void updateDisabledDates(String value) {
        List<LocalDate> allBookedDates = new ArrayList<>();
        for (Booking booking : calendar.getBookings()) {
            if (booking.getBookedObject().getName().equals(value)) {
                allBookedDates.addAll(booking.getAllDates());
            }
        }
        setDayCellFactories(allBookedDates);
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
        // Adds bookings in bookingview to json file
        try {
            persistance.writeCalendar(calendar, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
            updateDisabledDates(itemObjectList.get(i).getName());
        } catch (Exception e) {
            exceptionText.setText(e.getMessage());
        }
    }
}
