package famshare.apiservice;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import famshare.core.Booking;
import famshare.core.Calendar;

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

  @PostMapping
  protected boolean addBooking(@RequestBody Booking Booking) throws IOException {
    APICalendar.addBooking(Booking);
    return true;
  }

  //Get individual booking to /calendar/{id}
  @GetMapping("/{id}")
  protected Booking getBooking(@PathVariable("id") int id) throws IOException {
    return APICalendar.getBooking(id);
  }
  

  @DeleteMapping(path = "/{id}")
  protected boolean removeBooking(@PathVariable("id") int id) throws IOException {
    APICalendar.removeBooking(id);
    return true;
  }
}
