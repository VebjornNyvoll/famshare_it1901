package famshare.json;

import java.lang.reflect.Array;
import java.time.LocalDate;

import famshare.core.Booking;
import famshare.core.Calendar;
import famshare.core.Item;
import famshare.core.User;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;


public class HTTPCaller {
    
    public Calendar getCalendarFromAPI(){
        JSONObject rootObj = Unirest.get("http://localhost:8081/calendar")
        .asJson()
        .getBody()
        .getObject();

        // TODO: Move this to CalendarDeserializer
        JSONArray calendarArray = rootObj.getJSONArray("bookings");
        Calendar calendar = new Calendar();
        for (int i = 0; i < calendarArray.length(); i++) {
            JSONObject bookingObj = calendarArray.getJSONObject(i);
            Booking booking = new Booking();
            booking.setBookingId(bookingObj.getInt("bookingId"));

            LocalDate startDate = LocalDate.parse(bookingObj.getString("startDate"));
            LocalDate endDate = LocalDate.parse(bookingObj.getString("endDate"));
            booking.setDates(startDate, endDate);
            
            JSONObject userObj = bookingObj.getJSONObject("booker");

            User user = new User();
            user.setName(userObj.getString("name"));
            user.setId(userObj.getInt("id"));
            booking.setBooker(user);

            JSONObject itemObj = bookingObj.getJSONObject("bookedObject");
            Item item = new Item();
            item.setName(itemObj.getString("name"));
            item.setId(itemObj.getInt("id"));
            item.setDescription(itemObj.getString("description"));
            booking.setBookedObject(item);

            calendar.addBooking(booking);
        }
        return calendar;

    }

    public void postBookingToAPI(Booking booking){
        Unirest.post("http://localhost:8081/calendar")
        .header("Content-Type", "application/json")
        .body(booking)
        .asJson();
    }

    public String getStringFromAPI(){
        return Unirest.get("http://localhost:8081/testfunction")
        .queryString("name", "test")
        .asString()
        .getBody();
    }

    public HTTPCaller() {
    }
}
