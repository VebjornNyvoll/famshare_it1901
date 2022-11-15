// package famshare.ui;

// import javafx.collections.ObservableList;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.scene.control.DatePicker;
// import javafx.scene.control.ListView;
// import javafx.stage.Stage;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertNull;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.io.IOException;
// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.assertj.core.api.Assertions;
// import org.assertj.core.internal.bytebuddy.asm.Advice.Local;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.testfx.framework.junit5.ApplicationTest;
// import famshare.core.Booking;
// import famshare.core.Calendar;
// import famshare.core.Item;

// public class FamControllerTest extends ApplicationTest {

//     private FamController controller;
//     private Calendar calendar;
//     private String testFilePath =
//     "src/test/resources/famshare/ui/calendar.json";

//     @Override
//     public void start(Stage stage) throws IOException {
//         final FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("famshare.fxml"));
//         final Parent parent = fxmlLoader.load();
//         this.controller = fxmlLoader.getController();
//         this.calendar = this.controller.getCalendar(); // unsure about this encapsulation..
//         this.controller.setFilePath(testFilePath);
//         stage.setScene(new Scene(parent));
//         stage.show();
//     }

//     @Test
//     public void testFamController() {
//         assertNotNull(this.controller);
//         assertNotNull(this.calendar);
//     }

//     @Test
//     public void testBookingView_initial() {
//         checkNumOfBookings(calendar.getBookings().size());
//     }

//     @Test
//     public void testItemView_initial() {
//         initial famshare items
//         final ListView<String> itemListView = lookup("#itemView").query();
//         assertEquals(itemListView.getItems().size(), controller.getItemObjectList().size());
//         assertEquals("Cabin", itemListView.getItems().get(0));
//         assertEquals("Tool box", itemListView.getItems().get(5));
//     }

//     booking without dates; exception in UI
//     @Test
//     public void testBookWithoutDates() {
//         final ListView<String> itemListView = lookup("#itemView").query();
//         itemListView.getSelectionModel().select(0); // select item
//         clickOn("#bookItemButton");
//         assertNotNull(getExceptionText()); // should assertThrows
//         checkNumOfBookings(2);

//     }

//     @Test
//     public void testBookWithInvalidDates() {
//         final ListView<String> itemListView = lookup("#itemView").query();
//         itemListView.getSelectionModel().select(0); // select item
//         clickOn("#startDate");
//         write("21.12.2022");
//         clickOn("#endDate");
//         write("15.12.2022");
//         clickOn("#bookItemButton");
//     }

//     booking with dates
//     @Test
//     public void testBookWithDates() {
//         final ListView<String> itemListView = lookup("#itemView").query();
//         itemListView.getSelectionModel().select(0); // select item

//         javafx.scene.control.DatePicker startDate = lookup("#startDate");
//         javafx.scene.control.DatePicker endDate = lookup("#endDate");

//         try {
//         startDate.setValue(LocalDate.of(2022, 12, 21));
//         endDate.setValue(LocalDate.of(2022, 12, 26));

//         } catch (IllegalArgumentException | NullPointerException e) {
//         e.printStackTrace();
//         }

//         clickOn("#startDate");
//         write("21.12.2022");
//         clickOn("#endDate");
//         write("26.12.2022");

//         clickOn("#bookItemButton");
//         assertEquals("", getExceptionText());
//         final ListView<Booking> bookingListView = lookup("#bookingView").query();

//     }

//     public String getExceptionText() {
//         return lookup("#exceptionText").queryText().getText();
//     }

//     public void checkNumOfBookings(int expected) {
//         final ListView<Booking> bookingListView = lookup("#bookingView").query();
//         assertEquals(expected, bookingListView.getItems().size());
//     }

//     public List<String> getBookingItemNames(List<Booking> bookings) {
//         List<String> bookingItemNames = new ArrayList<>();
//         for (Booking b : bookings) {
//             bookingItemNames.add(b.getBookedObject().getName());
//         }
//         return bookingItemNames;
//     }

// }
