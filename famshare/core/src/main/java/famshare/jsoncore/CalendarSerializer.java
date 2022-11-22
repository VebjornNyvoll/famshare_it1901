package famshare.jsoncore;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import famshare.core.Calendar;

public class CalendarSerializer extends StdSerializer<Calendar> {

  public CalendarSerializer() {
    this(null);
}

  public CalendarSerializer(Class<Calendar> t) {
    super(t);
}
    

  @Override
  public void serialize(Calendar Calendar, JsonGenerator jsonGen, SerializerProvider serializerProvider)
   throws IOException {
    jsonGen.writeStartObject();
    //Writes the calendar object
    jsonGen.writeObjectField("calendar", Calendar.getBookings());
    jsonGen.writeEndObject();
  }

}
