package famshare.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import famshare.jsoncore.BookingDeserializer;
import famshare.jsoncore.BookingSerializer;


@JsonDeserialize(using = BookingDeserializer.class)
@JsonSerialize(using = BookingSerializer.class)
public class Booking {
    // Class responsible for holding start and end date of a booking, as well as
    // which user created said booking.
    private Item bookedObject;
    private User booker;
    private LocalDate startDate;
    private LocalDate endDate;
    private int bookingId;
    private List<LocalDate> allDates;

    public Booking() {
    }

    // Temporary constructor for testing purposes
    public Booking(Item bookedObject, User booker, LocalDate startDate, LocalDate endDate, int bookingId) {
        setBookedObject(bookedObject);
        setBooker(booker);
        setDates(startDate, endDate);
        setBookingId(bookingId);
    }

    // Sets start and end dates and fills allDates list with all dates between start
    // and end date.
    public void setDates(LocalDate startDate, LocalDate endDate) {
        setStartDate(startDate);
        setEndDate(endDate);
        Stream<LocalDate> dates = startDate.datesUntil(endDate.plusDays(1));
        this.allDates = dates.collect(Collectors.<LocalDate>toList());
    }

    public void setBookingId(int id) {
        this.bookingId = id;
    }

    public int getBookingId() {
        return this.bookingId;
    }

    public void setBookedObject(Item bookedObject) {
        if (bookedObject == null) {
            throw new IllegalStateException("bookedObject cannot be null");
        }
        this.bookedObject = bookedObject;
    }

    public void setBooker(User booker) {
        if (booker == null) {
            throw new IllegalStateException("booker cannot be null");
        }
        this.booker = booker;
    }

    // Ensures that the startDate is not before the current date by checking users
    // localtime.
    private void setStartDate(LocalDate startDate) {
        if (startDate.isBefore(LocalDate.now())) {
            // throw new IllegalStateException("Can't book dates that have passed!");
        }
        this.startDate = startDate;
    }

    private void setEndDate(LocalDate endDate) {
        if (this.startDate == null || endDate.isBefore(startDate)) {
            throw new IllegalStateException(
                    "endDate must be set after startDate and must be a date equal to or later than startDate");
        }
        this.endDate = endDate;
    }

    public Item getBookedObject() {
        return this.bookedObject;
    }

    public User getBooker() {
        return this.booker;
    }

    public LocalDate getStartDate() {
        return LocalDate.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth());
    }

    public LocalDate getEndDate() {
        return LocalDate.of(endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth());
    }

    public List<LocalDate> getAllDates() {
        if (this.allDates == null) {
            throw new IllegalStateException(this.toString() + " has no dates");
        }
        List<LocalDate> allDatesCopy = new ArrayList<>(allDates);
        return allDatesCopy;
    }

    @Override
    public String toString() {
        return bookedObject.getName() + "; " + booker.getName() + "; " + getStartDate() + " - "
                + getEndDate() + " bookingId:" + bookingId;
    }


}
