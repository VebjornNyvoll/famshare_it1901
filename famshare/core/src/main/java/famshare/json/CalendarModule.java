package famshare.json;

import com.fasterxml.jackson.databind.module.SimpleModule;

import famshare.core.Calendar;

@SuppressWarnings("serial")
public class CalendarModule extends SimpleModule {
    private static final String NAME = "CalendarModule";
    
    public CalendarModule() {
        super(NAME);
        addSerializer(Calendar.class, new CalendarSerializer());
        addDeserializer(Calendar.class, new CalendarDeserializer());

        
    }
}
