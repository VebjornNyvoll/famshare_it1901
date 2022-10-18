package famshare.core;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class CalendarTest {
    private Calendar cal = new Calendar();
    private Booking bkn0 = new Booking();
    private Booking bkn1 = new Booking();
    private Item itm = new Item();
    private LocalDate date = null;

    @Before
    public void init() {
        bkn0.setBookingId(0);
        bkn1.setBookingId(1);

        date = LocalDate.now();
    }

    @Test
    public void addBookingValidDateTest() throws Exception {
        bkn0.setBookedObject(itm);
        bkn0.setStartDate(date);
        bkn0.setEndDate(date.plusDays(2));

        bkn1.setBookedObject(itm);
        bkn1.setStartDate(date.plusDays(3));
        bkn1.setEndDate(date.plusDays(5));

        cal.addBooking(bkn0);
        cal.addBooking(bkn1);

        ArrayList<Booking> bookings = new ArrayList<Booking>();
        bookings.add(bkn0);
        bookings.add(bkn1);

        if (bookings != cal.getBookings()) {
            throw new Exception();
        }
    }

    @Test(expected = NullPointerException.class)
    public void addBookingInvalidDateTest() throws Exception {
        bkn0.setStartDate(null);
        bkn0.setEndDate(null);
        cal.addBooking(bkn0);
    }

    @Test(expected = IllegalStateException.class)
    public void addBookingAlreadyBookedTest() throws Exception {
        bkn0.setBookedObject(itm);
        bkn0.setStartDate(date);
        bkn0.setEndDate(date.plusDays(2));
        
        bkn1.setBookedObject(itm);
        bkn1.setStartDate(date);
        bkn1.setEndDate(date.plusDays(2));

        cal.addBooking(bkn0);
        cal.addBooking(bkn1);
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

        if (bookings != cal.getBookings()) {
            throw new Exception();
        }
    }
}
