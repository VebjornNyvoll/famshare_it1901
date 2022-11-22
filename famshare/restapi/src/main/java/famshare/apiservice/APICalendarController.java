package famshare.apiservice;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;


import famshare.core.Booking;
import famshare.core.Calendar;
import famshare.core.ItemList;

@CrossOrigin
@CrossOrigin
@RestController
@RequestMapping(APICalendarController.CONTROLLER_PATH)
public class APICalendarController {

  public static final String CONTROLLER_PATH = "/calendar";

  private final APICalendarService APICalendar;

  @Autowired
  public APICalendarController(final APICalendarService APICalendar) {
    this.APICalendar = APICalendar;
  }

  @GetMapping
  protected Calendar getCalendar() throws IOException {
    return APICalendar.getCalendar();
  }

  @PostMapping(consumes = "text/plain")
  protected boolean addBooking(@RequestBody String bookingAsJsonString) throws IOException {
    ObjectMapper objMapper = new ObjectMapper();
    try {
      Booking booking = objMapper.readValue(bookingAsJsonString, Booking.class);
      APICalendar.addBooking(booking);
      APICalendar.addItem(booking.getBookedObject());
    } catch (Exception e) {
      System.out.println("Posted booking is on invalid format: " + e.getMessage() + " " + bookingAsJsonString);
      return false;
    }
    return true;
  }
  
  


  //Get individual booking to /calendar/{id}
  @GetMapping("/{id}")
  protected Booking getBooking(@PathVariable("id") int id) throws IOException {
    return APICalendar.getBooking(id);
  }
  

  @DeleteMapping(path = "/{id}")
  protected boolean removeBooking(@PathVariable("id") int id) throws IOException {
    System.out.println("Before: " + APICalendar.getCalendar().getBookings().size());
    System.out.println("Deleting booking with id: " + id);
    System.out.println("After: " + APICalendar.getCalendar().getBookings().size());
    APICalendar.removeBooking(id);
    return true;
  }
  
  @GetMapping(path = "/itemlist")
  protected ItemList getItemList() throws IOException {
    return APICalendar.getItemList();
  }
}
