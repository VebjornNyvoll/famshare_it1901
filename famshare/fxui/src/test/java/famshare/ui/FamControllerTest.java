package famshare.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import famshare.core.Booking;
import famshare.core.Calendar;
import famshare.core.Item;

public class FamControllerTest extends ApplicationTest {

    private FamController controller;
    private List<Item> famItemTestList;
    private Calendar calendarTest = new Calendar();
    // private String testFilePath =
    // "src/test/resources/famshare/ui/calendarTest.json";

    @Override
    public void start(Stage stage) throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("famshare.fxml"));
        final Parent parent = fxmlLoader.load();
        this.controller = fxmlLoader.getController();
        // this.controller.setFilePath(testFilePath);
        stage.setScene(new Scene(parent));
        stage.show();
    }

    // @BeforeEach
    // public void setupFamTestItems() {

    // Item cabin = new Item();
    // cabin.setName("Cabin");
    // cabin.setID(1);
    // Item car = new Item();
    // car.setName("Car");
    // car.setID(2);
    // Item boat = new Item();
    // boat.setName("Boat");
    // boat.setID(3);
    // Item drill = new Item();
    // drill.setName("Drill");
    // drill.setID(4);
    // Item bike = new Item();
    // bike.setName("Bike");
    // bike.setID(5);
    // Item toolBox = new Item();
    // toolBox.setName("Tool box");
    // toolBox.setID(6);

    // famItemTestList = new ArrayList<>(Arrays.asList(cabin, car, boat, drill,
    // bike, toolBox));
    // }

    @Test
    public void testFamController() {
        assertNotNull(this.controller);
    }

    // public void book() {
    // System.out.println("lol");
    // }

    @Test
    public void testBookingView_initial() {
        final ListView<Booking> bookingListView = lookup("#bookingView").query();
        assertEquals(2, bookingListView.getItems().size());
    }

    @Test
    public void testItemView_initial() {
        // initial famshare items
        final ListView<String> itemListView = lookup("#itemView").query();
        assertEquals(6, itemListView.getItems().size());
        assertEquals("Cabin", itemListView.getItems().get(0));
        assertEquals("Tool box", itemListView.getItems().get(5));

        // checkFamItemListView(famItemTestList);
    }

    @Test
    public void testBookItem() {
        final ListView<String> itemListView = lookup("#itemView").query();
        itemListView.getSelectionModel().select(0); // select item

        // booking without dates; exception in UI
        clickOn("#bookItemButton");

        assertNotNull(getExceptionText());
        checkNumOfBookings(2);

        // booking with dates
        clickOn("#startDate");
        write("21.12.2022");
        clickOn("#endDate");
        write("26.12.2022");
        itemListView.getSelectionModel().select(0); // select item
        clickOn("#bookItemButton");

        // assertEquals("", getExceptionText());

    }

    public String getExceptionText() {
        return lookup("#exceptionText").queryText().getText();
    }

    public void checkNumOfBookings(int expected) {
        final ListView<Booking> bookingListView = lookup("#bookingView").query();
        assertEquals(expected, bookingListView.getItems().size());
    }

    // check listView
    public void checkFamItemListView(Item... items) {
        final ListView<Item> itemListView = lookup("#itemView").query();
        checkItems(itemListView.getItems(), items);
    }

    public void checkFamBookingsListView(Booking... bookings) {
        final ListView<Booking> bookingListView = lookup("#bookingView").query();
        checkBookings(bookingListView.getItems(), bookings);
    }

    // check items
    private void checkItem(Item item, String itemName, Integer id) {
        if (itemName != null) {
            assertEquals(itemName, item.getName());
        }
        if (id != null) {
            assertEquals((int) id, item.getID());
        }
    }

    private void checkItems(Iterable<Item> items, Item... famItems) {
        int i = 0;
        for (Item item : items) {
            assertTrue(i < famItems.length);
            checkItem(item, item.getName(), item.getID());
            i++;
        }
        assertTrue(i == famItems.length);
    }

    // check bookings
    private void checkBooking(Booking booking, Boolean validDate, Integer id) {
        if (validDate != null) {
            assertEquals(validDate,
                    booking.getStartDate().isBefore(booking.getEndDate()));
        }
        if (id != null) {
            assertEquals((int) id, booking.getBookingId());
        }
    }

    private void checkBookings(Iterable<Booking> bookings, Booking... famBookings) {
        int i = 0;
        for (Booking booking : bookings) {
            assertTrue(i < famBookings.length);
            checkBooking(booking, true, booking.getBookingId());
            i++;
        }
        assertTrue(i == famBookings.length);
    }

}
