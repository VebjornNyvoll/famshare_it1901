package famshare.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import famshare.core.Booking;
import famshare.core.Calendar;
import famshare.core.Item;

public class FamshareControllerTest extends ApplicationTest {

    private FamController controller;

    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("FamshareTest.fxml"));
        final Parent root = loader.load();
        this.controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private Item famItem1, famItem2, famItem3;
    private Calendar calendar;

    @BeforeEach
    public void setupFamItems() {
        famItem1 = new Item();
        famItem1.setName("item1");
        famItem2 = new Item();
        famItem2.setName("item2");
        famItem3 = new Item();
        famItem3.setName("item3");
    }

    @Test
    public void testController() {
        assertNotNull(this.controller);
    }

    @Test
    public void testBookingView() {

    }

    @Test
    public void testItemView_initialItems() {
        // initial famshare items
        checkFamItemListView(famItem1, famItem2, famItem3);
    }

    @Test
    public void testBookItem() {
        final ListView<Item> itemListView = lookup("#itemView").query();
        itemListView.getSelectionModel().select(1);
        clickOn("#bookItemButton");
        // famItem2 is booked and added to bookingView
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
    private void checkItem(Item item, String itemName, String description, Integer id) {
        if (itemName != null) {
            assertEquals(itemName, item.getName());
        }
        if (description != null) {
            assertEquals(description, item.getDescription());
        }
        if (id != null) {
            assertEquals((int) id, item.getID());
        }
    }

    private void checkItems(Iterable<Item> items, Item... famItems) {
        int i = 0;
        for (Item item : items) {
            assertTrue(i < famItems.length);
            checkItem(item, item.getName(), item.getDescription(), item.getID());
            i++;
        }
        assertTrue(i == famItems.length);
    }

    // check bookings
    private void checkBooking(Booking booking, Boolean validDate, Integer id) {
        if (validDate != null) {
            assertEquals(validDate, booking.getStartDate().isBefore(booking.getEndDate()));
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
