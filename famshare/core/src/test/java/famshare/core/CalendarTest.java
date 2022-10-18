package famshare.core;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalendarTest {
    private Calendar cal = new Calendar();
    private Booking bkn0 = new Booking();
    private Booking bkn1 = new Booking();
    private Item itm = new Item();
    private LocalDate date = null;

    @BeforeEach
    public void init() {
        bkn0.setBookingId(0);
        bkn1.setBookingId(1);
        bkn0.setBookedObject(itm);
        bkn1.setBookedObject(itm);

        date = LocalDate.now();
    }

    @Test
    public void addBookingValidDateTest() throws Exception {
        bkn0.setStartDate(date);
        bkn0.setEndDate(date.plusDays(2));

        bkn1.setStartDate(date.plusDays(3));
        bkn1.setEndDate(date.plusDays(5));

        cal.addBooking(bkn0);
        cal.addBooking(bkn1);

        ArrayList<Booking> bookings = new ArrayList<Booking>();
        bookings.add(bkn0);
        bookings.add(bkn1);

        Assertions.assertEquals(bookings, cal.getBookings());
    }

    @Test
    public void addBookingInvalidDateTest() throws Exception {
        bkn0.setStartDate(null);
        bkn0.setEndDate(null);

        Assertions.assertThrows(NullPointerException.class, () -> {
            cal.addBooking(bkn0);
        });
    }

    @Test(expected = IllegalStateException.class)
    public void addBookingAlreadyBookedTest() throws Exception {
        bkn0.setStartDate(date);
        bkn0.setEndDate(date.plusDays(2));
        
        bkn1.setStartDate(date);
        bkn1.setEndDate(date.plusDays(2));

        Assertions.assertThrows(IllegalStateException.class, () -> {
            cal.addBooking(bkn0);
            cal.addBooking(bkn1);
        });
    }

    @Test
    public void removeBookingTest() throws Exception {
        bkn0.setBookedObject(itm);
        bkn0.setStartDate(date);
        bkn0.setEndDate(date.plusDays(2));

        bkn1.setBookedObject(itm);
        bkn1.setStartDate(date.plusDays(3));
        bkn1.setEndDate(date.plusDays(5));

        cal.addBooking(bkn0);
        cal.addBooking(bkn1);

        cal.removeBooking(0);

        ArrayList<Booking> bookings = new ArrayList<Booking>();
        bookings.add(bkn1);

        Assertions.assertEquals(bookings, cal.getBookings());
    }
}
