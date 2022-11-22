package famshare.jsoncore;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import famshare.core.Booking;

public class BookingSerializer extends StdSerializer<Booking> {

  public BookingSerializer() {
    this(null);
}

  public BookingSerializer(Class<Booking> t) {
    super(t);
}
    

  @Override
  public void serialize(Booking Booking, JsonGenerator jsonGen, SerializerProvider serializerProvider)
   throws IOException {
    jsonGen.writeStartObject();
    //Writes the user object to the JSON
    jsonGen.writeObjectField("user", Booking.getBooker());
    //Writes the item object to the JSON
    jsonGen.writeObjectField("item", Booking.getBookedObject());
    //Writes the startdate
    jsonGen.writeStringField("startDate", Booking.getStartDate().toString());
    //Writes the enddate
    jsonGen.writeStringField("endDate", Booking.getEndDate().toString());
    jsonGen.writeEndObject();
  }

}