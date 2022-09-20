package gr2260.famshare.app;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


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
       
    }

    public void removeBooking(Booking booking) {
        bookinglog.remove(booking);
        
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
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            return List.of(mapper.readValue(new File("bookinglog.json"), BookingLog.class).bookinglog);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    public Collection<Booking> getBookinglog() {
        return this.bookinglog;
    }


   
    


    public static void main(String[] args) {
        BookingLog bookingLog = new BookingLog();
        Item i1 = new Item();
        i1.setName("Item1");
        i1.setID(1);
        i1.setDecsripion("test");
        User u1 = new User();
        u1.setName("User1");
        u1.setId(1);
        u1.addItem(i1.getID());
        LocalDate startDate = LocalDate.of(2020, 12, 1);
        LocalDate endDate = LocalDate.of(2020, 12, 5);
        Booking b1 = new Booking(i1, u1, startDate, endDate);
        System.out.println(b1);
        bookingLog.addBooking(b1);
        bookingLog.writeToFile();
    }
    
}
