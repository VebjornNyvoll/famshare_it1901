package famshare.core;

import static org.junit.Assert.assertSame;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookingTest {
    private Booking booking = null;
    private LocalDate date = null;

    @BeforeEach
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
    
    @Test
    public void setBookedObjectNullTest() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            booking.setBookedObject(null);
        });
    }

    @Test
    public void getSetBookerTest() {
        User usr = new User();
        booking.setBooker(usr);
        assertSame(usr, booking.getBooker());
    }

    @Test
    public void setBookerNullTest() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            booking.setBooker(null);
        });
    }

    @Test
    public void getSetStartDateTest() {
        booking.setStartDate(date);
        Assertions.assertEquals(date, booking.getStartDate());
    }

    @Test
    public void setStartDateTooEarlyTest() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            booking.setStartDate(date.minusDays(10));
        });
    }

    @Test
    public void getSetEndDateTest() {
        booking.setStartDate(date);
        booking.setEndDate(date.plusDays(10));
        Assertions.assertEquals(date.plusDays(10), booking.getEndDate());

    }

    @Test
    public void setEndDateBeforeStartDateTest() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            booking.setStartDate(date);
            booking.setEndDate(date.minusDays(10));
        });
    }
}
