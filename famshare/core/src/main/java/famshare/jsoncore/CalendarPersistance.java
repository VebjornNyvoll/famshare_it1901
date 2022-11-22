package famshare.jsoncore;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

import famshare.core.Calendar;

public class CalendarPersistance {

    private ObjectMapper mapper = new ObjectMapper();

    public CalendarPersistance () {
        mapper.registerModule(new CalendarModule());
    }

    public void writeCalendar (Calendar calendar, String filepath) throws IOException {
        mapper.writeValue(new File(filepath), calendar);
    }

    public Calendar readCalendar (String filepath) throws IOException {
        return mapper.readValue(new File(filepath), Calendar.class);
    }
    
}
