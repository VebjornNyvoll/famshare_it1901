package famshare.json;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import famshare.core.Calendar;

public class Persistance {

    private ObjectMapper mapper;
    private File file;

    public Persistance (File file) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new CalendarModule());
        this.file = file;
    }

    public void writeCalendar (Calendar calendar) throws StreamWriteException, DatabindException, IOException {
        mapper.writeValue(file, calendar);
    }

    public Calendar readCalendar () throws IOException {
        return mapper.readValue(file, Calendar.class);
    }
    
}
