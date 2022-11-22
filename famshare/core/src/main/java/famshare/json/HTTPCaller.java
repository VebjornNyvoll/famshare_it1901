package famshare.json;

import java.lang.reflect.Array;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import famshare.core.Booking;
import famshare.core.Calendar;
import famshare.core.Item;
import famshare.core.ItemList;
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
            
            JSONObject userObj = bookingObj.getJSONObject("user");

            User user = new User();
            user.setName(userObj.getString("name"));
            user.setId(userObj.getInt("id"));
            booking.setBooker(user);

            JSONObject itemObj = bookingObj.getJSONObject("item");
            Item item = new Item();
            item.setName(itemObj.getString("name"));
            item.setId(itemObj.getInt("id"));
            item.setDescription(itemObj.getString("description"));
            booking.setBookedObject(item);
            if(!calendar.containsItem(item)) {
                calendar.addItem(item);
            }
            calendar.addBooking(booking);
        }
        return calendar;
    }

    public void postBookingToAPI(Booking booking){
        String serializedBooking = null;
        try {
            serializedBooking = new ObjectMapper().writeValueAsString(booking);
        } catch (JsonProcessingException e) {
            System.out.println("Could not serialize booking " + "Booking id is: " + booking.getBookingId() + " " + e.getMessage());
        }
        if(serializedBooking != null){
            Unirest.post("http://localhost:8081/calendar")
            .header("Content-Type", "text/plain")
            .body(serializedBooking)
            .asJson();
        }
    }

    //Post item

    public String getStringFromAPI(){
        return Unirest.get("http://localhost:8081/testfunction")
        .queryString("name", "test")
        .asString()
        .getBody();
    }

    public void deleteBooking(int bookingId){
        Unirest.delete("http://localhost:8081/{id}")
        .routeParam("id", Integer.toString(bookingId))
        .body(bookingId);

        System.out.println("Booking with id " + bookingId + " deleted");
    }

    public HTTPCaller() {
    }
}
