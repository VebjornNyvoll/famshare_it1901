package famshare.json;

import famshare.core.Calendar;

import java.util.List;

import famshare.core.Booking;
import kong.unirest.GenericType;
import kong.unirest.Unirest;

public class HTTPCaller {
    
    public Calendar getCalendarFromAPI(){
        List<Booking> bookings = Unirest.get("http://localhost:8081/calendar")
        .asObject(new GenericType<List<Booking>>(){})
        .getBody();
        Calendar calendar = new Calendar();
        for (Booking booking : bookings) {
            calendar.addBooking(booking);
        }
        return calendar;
        
    }

    public void postCalendarToAPI(Calendar calendar){
        Unirest.post("http://localhost:8081/calendar")
        .header("Content-Type", "application/json")
        .body(calendar)
        .asEmpty();
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
