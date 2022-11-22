package famshare.jsoncore;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import famshare.core.ItemList;

public class ItemListSerializer extends StdSerializer<ItemList> {

    public ItemListSerializer() {
      this(null);
  }
  
    public ItemListSerializer(Class<ItemList> t) {
      super(t);
  }
      
  
    @Override
    public void serialize(ItemList ItemList, JsonGenerator jsonGen, SerializerProvider serializerProvider)
     throws IOException {
      jsonGen.writeStartObject();
      //Writes the user object to the JSON
      jsonGen.writePOJOField("item", ItemList.getItems());
      //Writes the enddate
      jsonGen.writeEndObject();
    }
  
  }
