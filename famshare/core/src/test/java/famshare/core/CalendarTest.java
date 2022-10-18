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
    private Item itm2 = new Item();
    private LocalDate date = null;

    @BeforeEach
    public void init() {
        cal = new Calendar();
        System.out.println(cal.getBookings());
        bkn0.setBookingId(0);
        bkn1.setBookingId(1);
        bkn0.setBookedObject(itm);
        bkn1.setBookedObject(itm2);
        itm.setId(0);
        itm2.setId(1);

        date = LocalDate.now();
    }

    @Test
    public void addBookingValidDateTest() throws Exception {
        bkn0.setDates(date, date.plusDays(2));

        bkn1.setDates(date, date.plusDays(5));

        cal.addBooking(bkn0);
        cal.addBooking(bkn1);

        ArrayList<Booking> bookings = new ArrayList<Booking>();
        bookings.add(bkn0);
        bookings.add(bkn1);

        Assertions.assertEquals(bookings, cal.getBookings());
    }

    @Test
    public void addBookingInvalidDateTest() throws Exception {
        Assertions.assertThrows(NullPointerException.class, () -> {
            bkn0.setDates(null, null);
        });
       

        Assertions.assertThrows(NullPointerException.class, () -> {
            cal.addBooking(bkn0);
        });
    }

    @Test
    public void addBookingAlreadyBookedTest() throws Exception {
        bkn0.setDates(date, date.plusDays(2));
        itm2.setId(0);
        bkn1.setDates(date, date.plusDays(2));

        Assertions.assertThrows(IllegalStateException.class, () -> {
            cal.addBooking(bkn0);
            cal.addBooking(bkn1);
        });
    }

    @Test
    public void removeBookingTest() throws Exception {
        bkn0.setBookedObject(itm);
        bkn0.setDates(date, date.plusDays(2));

        bkn1.setBookedObject(itm);
        bkn1.setDates(date.plusDays(3), date.plusDays(5));

        cal.addBooking(bkn0);
        cal.addBooking(bkn1);

        cal.removeBooking(0);

        ArrayList<Booking> bookings = new ArrayList<Booking>();
        bookings.add(bkn1);

        Assertions.assertEquals(bookings, cal.getBookings());
    }
}
