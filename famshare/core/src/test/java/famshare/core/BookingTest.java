package famshare.core;

import static org.junit.Assert.assertSame;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class BookingTest {
    private Booking booking = null;
    private LocalDate date = null;

    @Before
    public void init() {
        booking = new Booking();
        date = LocalDate.now();
    }

    @Test
    public void getSetBookingIDTest() {
        booking.setBookingId(0);
        assertSame(0, booking.getBookingId());
    }

    @Test
    public void getSetBookedObjectTest() {
        Item item = new Item();

        booking.setBookedObject(item);

        assertSame(item, booking.getBookedObject());
    }
    
    @Test(expected = IllegalStateException.class)
    public void setBookedObjectNullTest() {
        booking.setBookedObject(null);
    }

    @Test
    public void getSetBookerTest() {
        User usr = new User();
        booking.setBooker(usr);
        assertSame(usr, booking.getBooker());
    }

    @Test(expected = IllegalStateException.class)
    public void setBookerNullTest() {
        booking.setBooker(null);
    }

    @Test
    public void getSetStartDateTest() {
        booking.setStartDate(date);
        assertSame(date, booking.getStartDate());
    }

    @Test(expected = IllegalStateException.class)
    public void setStartDateTooEarlyTest() {
        booking.setStartDate(date.minusDays(10));
    }

    @Test
    public void getSetEndDateTest() {
        booking.setStartDate(date);
        booking.setEndDate(date.plusDays(10));
        assertSame(date.plusDays(10), booking.getEndDate());

    }

    @Test(expected = IllegalStateException.class)
    public void setEndDateBeforeStartDateTest() {
        booking.setStartDate(date);
        booking.setEndDate(date.minusDays(10));
    }
}
