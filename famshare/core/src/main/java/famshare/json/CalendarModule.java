package famshare.json;

import com.fasterxml.jackson.databind.module.SimpleModule;

import famshare.core.Booking;
import famshare.core.Calendar;
import famshare.core.Item;
import famshare.core.ItemList;

@SuppressWarnings("serial")
public class CalendarModule extends SimpleModule {
    private static final String NAME = "CalendarModule";
    
    public CalendarModule() {
        super(NAME);
        addSerializer(Calendar.class, new CalendarSerializer());
        addDeserializer(Calendar.class, new CalendarDeserializer());

        addSerializer(Booking.class, new BookingSerializer());
        addDeserializer(Booking.class, new BookingDeserializer());

        addSerializer(Item.class, new ItemSerializer());
        addDeserializer(Item.class, new ItemDeserializer());

        addSerializer(ItemList.class, new ItemListSerializer());
        addDeserializer(ItemList.class, new ItemListDeserializer());
    }
}
