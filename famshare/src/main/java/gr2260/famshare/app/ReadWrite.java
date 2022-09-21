package gr2260.famshare.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONObject;


public class ReadWrite {

    public void saveBookings(List<Booking> bookings) throws FileNotFoundException { 
        File file = new File("bookings.json");
        PrintWriter pw = new PrintWriter(file);
        JSONObject obj = new JSONObject();
        for (Booking b : bookings) {
            obj.put("bookedObject", b.getBookedObject().getName());
            obj.put("booker", b.getBooker().getName());
            obj.put("startDate", b.getStartDate());
            obj.put("endDate", b.getEndDate());
            pw.println(obj.toJSONString());
        }
        pw.close();
    }

    public String loadBookings() throws FileNotFoundException {
        File file = new File("bookings.json");
        Scanner sc = new Scanner(file);
        String json = "";
        while (sc.hasNextLine()) {
            json += sc.nextLine();
        }
        sc.close();
        return json;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ReadWrite rw = new ReadWrite();
        List<Booking> bookings = new ArrayList<>();
        Item i1 = new Item();
        i1.setName("Item1");
        i1.setID(1);
        i1.setDecsripion("test");
        User u1 = new User();
        u1.setName("User1");
        u1.setId(1);
        u1.addItem(i1.getID());
        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.of(2020, 1, 2);
        Booking b1 = new Booking(i1, u1, startDate, endDate);
        bookings.add(b1);
        rw.saveBookings(bookings);
        String json = rw.loadBookings();
        System.out.println(json);
    }
}