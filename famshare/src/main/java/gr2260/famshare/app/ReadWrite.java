package gr2260.famshare.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class ReadWrite {

    public void saveBookings(List<Booking> bookings) throws FileNotFoundException { 
        File file = new File("bookings.json");
        PrintWriter pw = new PrintWriter(file);
        JSONObject obj = new JSONObject();
        for (Booking b : bookings) {
            obj.put("bookedObject", b.getBookedObject().getName());
            obj.put("description", b.getBookedObject().getDescription());
            obj.put("itemID", b.getBookedObject().getID());
            obj.put("booker", b.getBooker().getName());
            obj.put("startDate", b.getStartDate());
            obj.put("endDate", b.getEndDate());
            pw.println(obj.toJSONString());
        }
        pw.close();
    }

    public List<Booking> loadBookings() throws FileNotFoundException {
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader("bookings.json")) {
            List<Booking> bookings = new ArrayList<Booking>();
            Scanner sc = new Scanner(reader);
            while (sc.hasNextLine()) {
                JSONObject obj = (JSONObject) parser.parse(sc.nextLine());
                String bookedObject = (String) obj.get("bookedObject");
                String booker = (String) obj.get("booker");
                String startDate = (String) obj.get("startDate");
                String endDate = (String) obj.get("endDate");
                Item item = new Item();
                item.setName(bookedObject);
                User user = new User();
                user.setName(booker);
                Booking booking = new Booking();
                booking.setBookedObject(item);
                booking.setBooker(user);
                booking.setDates(LocalDate.parse(startDate), LocalDate.parse(endDate));
                bookings.add(booking);
            }
            sc.close();
            return bookings;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}