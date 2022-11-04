package famshare.apiservice;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import famshare.core.Booking;
import famshare.core.Calendar;
import famshare.core.Item;
import famshare.core.User;
import famshare.json.CalendarModule;

import java.time.LocalDate;

public class APICalendar {
    private Calendar calendar;
    private final long id;


    public void setFilePath(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new CalendarModule());
        try {
            this.calendar = mapper.readValue(new File(filePath), Calendar.class);
        } catch (JsonMappingException e) {
            Calendar testCalendar = new Calendar();
            Item testItem = new Item();
            testItem.setId(1);
            testItem.setName("testItemName");
            testItem.setDescription("really a description");
            User testUser = new User();
            testUser.setId(101);
            testUser.setName("testUserName");
            Booking testBooking = new Booking();
            testBooking.setBookedObject(testItem);
            testBooking.setBooker(testUser);
            testBooking.setBookingId(1001);
            testBooking.setDates(LocalDate.now(), LocalDate.now().plusDays(1));
            testCalendar.addBooking(testBooking);
            this.calendar = testCalendar;
        }
    }


    public APICalendar(long id, String filepath) throws IOException {
         {
            this.id = id;
            setFilePath(filepath);
    }
    }

    public long getId() {
        return id;
    }

    public Calendar getCalendar() {
        return calendar;
    }


}


    

