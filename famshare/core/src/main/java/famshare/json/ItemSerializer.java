package famshare.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;


import famshare.core.Item;


public class ItemSerializer extends StdSerializer<Item> {

  public ItemSerializer() {
    this(null);
}

  public ItemSerializer(Class<Item> t) {
    super(t);
}
    

  @Override
  public void serialize(Item item, JsonGenerator jsonGen, SerializerProvider serializerProvider)
   throws IOException {
    jsonGen.writeStartObject();
    jsonGen.writeStringField("name", item.getName());
    jsonGen.writeStringField("description", item.getDescription());
    jsonGen.writeNumberField("itemID", item.getID());
    jsonGen.writeEndObject();
  }

}