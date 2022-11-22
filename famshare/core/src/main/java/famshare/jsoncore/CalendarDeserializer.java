package famshare.jsoncore;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;

import famshare.core.Booking;
import famshare.core.Calendar;
import famshare.core.Item;
import famshare.core.User;


public class CalendarDeserializer extends StdDeserializer<Calendar> {
    
    public CalendarDeserializer() {
        this(null);
    }

    public CalendarDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Calendar deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        Calendar Calendar = new Calendar();
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);
        ArrayNode bookingsNode = (ArrayNode) node.get("calendar");
        
        
        //Gets the booking objects from json
        
        for (JsonNode booking : bookingsNode) {
            //Gets the booking object from json and adds it to the calendar
            Booking b = new Booking();
            //Gets the user object from json
            JsonNode userNode = booking.get("user");
            User user = new User();
            user.setName(userNode.get("name").asText());
            user.setId(userNode.get("id").asInt());
            user.addItem(1);
            b.setBooker(user);
            //Gets the item object from json
            JsonNode itemNode = booking.get("item");
            Item item = new Item();
            item.setName(itemNode.get("name").asText());
            item.setDescription(itemNode.get("description").asText());
            item.setId(itemNode.get("id").asInt());
            b.setBookedObject(item);
            //Gets the startdate from json
            JsonNode startDateNode = booking.get("startDate");
            String startDate = startDateNode.asText();
            LocalDate start = LocalDate.parse(startDate);
            //Gets the enddate as a date
            JsonNode endDateNode = booking.get("endDate");
            String endDate = endDateNode.asText();
            LocalDate end = LocalDate.parse(endDate);
            b.setDates(start, end);
            Calendar.addBooking(b);
        }
        return Calendar;
    }
}
