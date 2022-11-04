package famshare.apiservice;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import famshare.apiservice.APICalendar;
import famshare.core.Booking;
import famshare.core.Calendar;
import famshare.core.Item;
import famshare.core.User;


@RestController
public class APICalendarController {
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/calendar")
    public Calendar calendar(@RequestParam(value = "name", defaultValue = "World") String name) {
        APICalendar apiCalendar;
        try {
            apiCalendar = new APICalendar(counter.incrementAndGet(), "src/main/resources/calendar.json");
            return apiCalendar.getCalendar();
        } catch (IOException e) {
            // TODO: Remove this, it's just for debugging
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            Calendar testCalendar = new Calendar();
            Item testItem = new Item();
            testItem.setId(1);
            testItem.setName(exceptionAsString);
            testItem.setDescription("It really did!");
            User testUser = new User();
            testUser.setId(101);
            testUser.setName("BROKE_IN_CONTROLLER");
            Booking testBooking = new Booking();
            testBooking.setBookedObject(testItem);
            testBooking.setBooker(testUser);
            testBooking.setBookingId(1001);
            testBooking.setDates(LocalDate.now(), LocalDate.now().plusDays(1));
            testCalendar.addBooking(testBooking);
            return testCalendar;
            // This is just to make clear what has gone wrong, when something has. Will be removed later.
        }
        
    }
}
