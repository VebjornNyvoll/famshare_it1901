package famshare.ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import famshare.core.Booking;


import famshare.core.*;
import famshare.jsoncore.*;

public class FamController {

    
    private Calendar calendar;
    private User dummyUser = new User();
    private String filePath;
    private HTTPCaller httpCaller = new HTTPCaller();
    private int nextBookingId = 0;

    public FamController() throws IOException {
        dummyUser.setName("Dummy User");
    }

    public Calendar getCalendar() {
        return calendar;
    }


    @FXML
    private ListView<String> bookingView, itemView;

    @FXML
    private DatePicker startDate, endDate;

    @FXML
    private Text exceptionText;

    @FXML
    private Button bookButton, deleteButton, addItemButton;

    @FXML
    private TextField description, itemname;

    @FXML
    void initialize() throws IOException {
        // Load calendar from file if there is one
        try {
            calendar = httpCaller.getCalendarFromAPI();
            for (Booking b : calendar.getBookings()) {
                nextBookingId++;
            }
        } catch (Exception e) {
            calendar = new Calendar();
        }
        updateItemView();
        updateBookingView(); 
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
        for (Item item : calendar.getItemList().getItems()) {
            itemView.getItems().add(item.getName());
        }
    }

    public void updateBookingView() {
        bookingView.getItems().clear();
        for (Booking booking : calendar.getBookings()) {
            bookingView.getItems().add(booking.toString());
        }
        // Adds bookings in bookingview to json file
        // try {
        //     persistance.writeCalendar(calendar, filePath);
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }

    }

    @FXML
    public void book() {
        exceptionText.setText("");

        try {
            int i = itemView.getSelectionModel().getSelectedIndex();
            LocalDate startD = startDate.getValue();
            LocalDate endD = endDate.getValue();
            Booking newBooking = new Booking(calendar.getItemList().get(i), dummyUser, startD, endD, nextBookingId);
            nextBookingId++;
            calendar.addBooking(newBooking);
            httpCaller.postBookingToAPI(newBooking);
            updateBookingView();
            updateItemView();
            updateDisabledDates(itemView.getSelectionModel().getSelectedItem());
        } catch (Exception e) {
            exceptionText.setText(e.getMessage());
        }
    }

    @FXML
    void delete() {
        try {
            int i = bookingView.getSelectionModel().getSelectedIndex();
            int bookingId = Integer.parseInt(bookingView.getItems().get(i).split("bookingId:")[1]);
            exceptionText.setText(i + " " + bookingId);
            calendar.removeBooking(bookingId);
            httpCaller.deleteBookingFromAPI(bookingId);
            updateBookingView();
        } catch (Exception e) {
            exceptionText.setText(e.getMessage());
        }
    }
}
