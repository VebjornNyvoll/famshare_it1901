package famshare.apiservice;

import java.io.IOException;

import famshare.core.Booking;
import famshare.core.Calendar;
import famshare.json.*;

import org.springframework.stereotype.Service;


@Service
public class APICalendarService {

  private static final String filePath = "../fxui/src/main/resources/famshare/ui/calendar.json";
  public CalendarPersistance persistence = new CalendarPersistance();
  private final Calendar Calendar;

  public APICalendarService() throws IOException {
    Calendar = persistence.readCalendar(filePath);
  }
  
  protected Calendar getCalendar() throws IOException {
    return persistence.readCalendar(filePath);
  }

  protected boolean addBooking(Booking Booking) throws IOException {
    Calendar.addBooking(Booking);
    persistence.writeCalendar(Calendar, filePath);
    return true;
  }

  protected boolean removeBooking(int id) throws IOException {
    Calendar.removeBooking(id);
    persistence.writeCalendar(Calendar, filePath);
    return true;
  }

  protected Booking getBooking(int id) throws IOException {
    return Calendar.getBooking(id);
  }
}


    

