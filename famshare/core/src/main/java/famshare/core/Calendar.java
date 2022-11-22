package famshare.core;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import famshare.jsoncore.CalendarDeserializer;
import famshare.jsoncore.CalendarSerializer;

@JsonDeserialize(using = CalendarDeserializer.class)
@JsonSerialize(using = CalendarSerializer.class)
public class Calendar {
    // Class responsible for holding booking objects and performing operations on
    // those objects.
    private ArrayList<Booking> bookings;
    private ItemList itemList;

    public Calendar() {
        bookings = new ArrayList<>();
        itemList = new ItemList();
    }

    public Booking getBooking(int id) {
        // Returns a booking object with the given id.
        for (Booking b : bookings) {
            if (b.getBookingId() == id) {
                return b;
            }
        }
        return null;
    }

    public void removeBooking(int bookingId) {
        for (Booking booking : bookings) {
            if(booking.getBookingId() == bookingId) {
                System.out.println(booking);
                bookings.remove(booking);
                System.out.println(bookings);
                return;
            }
        }
    }

    public void addBooking(Booking booking) {
        if(isAvailable(booking)){
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
            if (booking.getBookedObject().getId() == newBooking.getBookedObject().getId()) {
                if (booking.getAllDates().stream().anyMatch(newBooking.getAllDates()::contains)) {
                    return false;
                }
            }
        }
        return true;
    }


    //Gets itemlist from calendar
    public ItemList getItemList() {
        return this.itemList;
    }

    //Sets itemlist in calendar
    public void setItemList(ItemList itemList) {
        this.itemList = itemList;
    }

    
    //Adds item to itemlist
    public void addItem(Item item) {
        itemList.addItem(item);
    }

    //Checks if item is in itemlist
    public boolean containsItem(Item item) {
        return itemList.getItems().contains(item);
    }


}
