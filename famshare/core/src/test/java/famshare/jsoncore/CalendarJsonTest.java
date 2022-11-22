package famshare.jsoncore;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import com.fasterxml.jackson.databind.ObjectMapper;

import famshare.core.Booking;
import famshare.core.Calendar;
import famshare.core.Item;
import famshare.core.User;

public class CalendarJsonTest {

    private static ObjectMapper mapper;

    @BeforeAll
    public static void init() {
        mapper = new ObjectMapper();
        mapper.registerModule(new CalendarModule());
    }

    //Sets up a calendar object
    public Calendar setupCalendar() {
        Calendar cal = new Calendar();
        Booking bkn0 = new Booking();
        Booking bkn1 = new Booking();
        Item itm = new Item();
        Item itm2 = new Item();
        User usr = new User();
        // Sets names and ids for items
        itm.setId(0);
        itm2.setId(1);
        itm.setName("Item 1");
        itm2.setName("Item 2");
        itm2.setDescription("Item 2 description");
        itm.setDescription("Item 1 description");
        // Sets names and ids for users
        usr.setId(0);
        usr.setName("User 1");
        usr.addItem(0);
        // Sets booking ids
        bkn0.setBookingId(0);
        bkn1.setBookingId(1);
        //Sets users of booking
        bkn0.setBooker(usr);
        bkn1.setBooker(usr);
        //Sets items of booking
        bkn0.setBookedObject(itm);
        bkn1.setBookedObject(itm2);
        // Sets booking dates
        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDate end = LocalDate.of(2024, 1, 2);
        bkn0.setDates(start, end);
        bkn1.setDates(start, end);
        cal.addBooking(bkn0);
        cal.addBooking(bkn1);
        return cal;
    }

    @Test
    public void serializeCalendarTest() throws Exception {
        init();
        Calendar cal = setupCalendar();
        //Writes calendar as string
        String json = mapper.writeValueAsString(cal);
        //Checks if json string is correct currently using only contains
        assert json.contains("Item 1");
        assert json.contains("Item 2");
        assert json.contains("User 1");
        assert json.contains("2024-01-01");
        // Asserts not true
        assert !json.contains("Item 3");
        assert !json.contains("User 2");
    }

    @Test
    public void deserializeCalendarTest() throws Exception {
        init();
        Calendar cal = setupCalendar();
        //Writes calendar as string
        String json = mapper.writeValueAsString(cal);
        //Reads calendar from string
        Calendar cal2 = mapper.readValue(json, Calendar.class);
        //Checks if calendar is correct
        assert cal2.getBookings().size() == 2;
        assert cal2.getBookings().get(0).getBooker().getName().equals("User 1");
        assert cal2.getBookings().get(0).getBookedObject().getName().equals("Item 1");
        assert cal2.getBookings().get(1).getBooker().getName().equals("User 1");
        assert cal2.getBookings().get(1).getBookedObject().getName().equals("Item 2");
    }
}
