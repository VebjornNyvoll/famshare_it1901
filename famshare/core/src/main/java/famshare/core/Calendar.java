package famshare.core;

import java.util.ArrayList;

public class Calendar {
    // Class responsible for holding booking objects and performing operations on
    // those objects.
    private ArrayList<Booking> bookings;

    public Calendar() {
        bookings = new ArrayList<>();
    }

    public void removeBooking(int bookingId) {
        bookings.remove(bookingId);
    }

    public void addBooking(Booking booking) {
        if (isAvailable(booking)) {
            // Uses isAvailable() to ensure users cannot add invalid bookings.
            bookings.add(booking);
        } else {
            throw new IllegalStateException("Cannot add booking as one or more of the dates are unavailable.");
        }
    }

    public ArrayList<Booking> getBookings() {
        return new ArrayList<Booking>(bookings);
    }

    public boolean isAvailable(Booking newBooking) {
        if (newBooking.getAllDates() == null) {
            throw new NullPointerException("New booking is null");
        }

        // Goes through each booking in bookings, checks if the booked object is the
        // same as the new booking. If true, it then checks if there is any overlap
        // between the dates.
        // Returns false if there is an overlap and returns true if there is none.
        // Checks using itemID
        for (Booking booking : bookings) {
            if (booking.getBookedObject().getID() == newBooking.getBookedObject().getID()) {
                if (booking.getAllDates().stream().anyMatch(newBooking.getAllDates()::contains)) {
                    return false;
                }
            }
        }
        return true;
    }

}
