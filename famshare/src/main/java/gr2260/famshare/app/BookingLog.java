package gr2260.famshare.app;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BookingLog {

    private final Collection<Booking> bookinglog;

    public BookingLog()   {
        this.bookinglog = new ArrayList<>();
    }

    public BookingLog(Collection<Booking> bookingLog) {
        this.bookinglog = bookingLog;
    }

 

    public void addBooking(Booking booking) {
        bookinglog.add(booking);
        this.writeToFile();
    }

    public void removeBooking(Booking booking) {
        bookinglog.remove(booking);
        this.writeToFile();
    }

    public void writeToFile() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("bookinglog.json");
        try {
            mapper.writeValue(file, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Booking> readFromFile() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("bookinglog.json");
        try {
            BookingLog bookingLog = mapper.readValue(file, BookingLog.class);
            return new ArrayList<Booking>(bookingLog.bookinglog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
