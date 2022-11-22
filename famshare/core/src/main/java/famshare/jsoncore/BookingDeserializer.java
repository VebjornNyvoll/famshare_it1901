package famshare.json;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import famshare.core.Booking;
import famshare.core.Item;
import famshare.core.User;

public class BookingDeserializer extends StdDeserializer<Booking> {
    
    public BookingDeserializer() {
        this(null);
    }

    public BookingDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Booking deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        Booking Booking = new Booking();
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);

        //Gets the user object from json
        JsonNode userNode = node.get("user");
        User user = new User();
        user.setName(userNode.get("name").asText());
        user.setId(userNode.get("id").asInt());
        user.addItem(1);
        Booking.setBooker(user);
        
        //Gets the item object from json
        JsonNode itemNode = node.get("item");
        Item item = new Item();
        item.setName(itemNode.get("name").asText());
        item.setDescription(itemNode.get("description").asText());
        item.setId(itemNode.get("itemID").asInt());
        Booking.setBookedObject(item);

        //Gets the date from json
        JsonNode startDateNode = node.get("startDate");
        //Gets the startdate as a date
        String startDate = startDateNode.asText();
        LocalDate start = LocalDate.parse(startDate);
        Booking.setStartDate(start);
        //Gets the endate as a date
        JsonNode endDateNode = node.get("endDate");
        String endDate = endDateNode.asText();
        LocalDate end = LocalDate.parse(endDate);
        Booking.setEndDate(end);

        return Booking;
    }
}
