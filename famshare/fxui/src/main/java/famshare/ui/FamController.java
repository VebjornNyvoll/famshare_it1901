package famshare.ui;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import famshare.core.Booking;
import famshare.core.Calendar;
import famshare.core.Item;
import famshare.core.User;
import famshare.json.ReadWrite;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class FamController {

    private Calendar calendar;
    private List<Item> itemObjectList;
    private User dummyUser = new User();
    private ReadWrite rw = new ReadWrite();

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
        Item drill = new Item();
        drill.setName("Drill");
        drill.setID(4);
        Item bike = new Item();
        bike.setName("Bike");
        bike.setID(5);
        Item toolBox = new Item();
        toolBox.setName("Tool box");
        toolBox.setID(6);

        itemObjectList = new ArrayList<>(Arrays.asList(cabin, car, boat, drill, bike, toolBox));
    }

    @FXML
    void initialize() throws FileNotFoundException {
        setDummyItems();
        updateItemView();
        List<Booking> bookings = rw.loadBookings();
        for (Booking b : bookings) {
            calendar.addBooking(b);
        }
        updateBookingView();
        //Reads from file and adds bookings to calendar
        // listenToItemView();
        listenToItemView();
      
        
    }
    
    private void setDayCellFactories(List<LocalDate> bookedDates){
        startDate.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty ){
                super.updateItem(date, empty);

                for (LocalDate localDate : bookedDates) {
                    if(date.equals(localDate)){
                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;");
                    }
                }
                
            }});

            endDate.setDayCellFactory(param -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty ){
                    super.updateItem(date, empty);
    
                    for (LocalDate localDate : bookedDates) {
                        if(date.equals(localDate)){
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                    
                }});
    }
    

    public void listenToItemView(){
        itemView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            updateDisabledDates(newValue);
        });
    }

    private void updateDisabledDates(String value){
        List<LocalDate> allBookedDates = new ArrayList<>();
            for (Booking booking : calendar.getBookings()) {
                if(booking.getBookedObject().getName().equals(value)){
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
        //Adds bookings in bookingview to json file
        try {
            rw.saveBookings(calendar.getBookings());
        } catch (Exception e) {
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
