package famshare.json;

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

import famshare.core.Booking;
import famshare.core.Item;
import famshare.core.User;




public class ReadWrite {
    private String filePath = "src/main/resources/famshare/ui/bookings.json";

    public void saveBookings(List<Booking> bookings) throws FileNotFoundException { 
        File file = new File(filePath);
        PrintWriter pw = new PrintWriter(file);
        JSONObject obj = new JSONObject();
        for (Booking b : bookings) {
            obj.put("bookedObject", b.getBookedObject().getName());
            obj.put("booker", b.getBooker().getName());
            obj.put("startDate", b.getStartDate().toString());
            obj.put("endDate", b.getEndDate().toString());
            pw.println(obj.toJSONString());
        }
        pw.close();
    }

    public List<Booking> loadBookings() throws FileNotFoundException {
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(filePath)) {
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
                Booking booking = new Booking(item, user, LocalDate.parse(startDate), LocalDate.parse(endDate), 4);
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