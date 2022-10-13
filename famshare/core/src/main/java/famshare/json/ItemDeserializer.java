package famshare.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import famshare.core.Item;


public class ItemDeserializer extends StdDeserializer<Item> {
    
    public ItemDeserializer() {
        this(null);
    }

    public ItemDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Item deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        Item Item = new Item();
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);
        
        // try catch block
        JsonNode nameNode = node.get("name");
        String name = nameNode.asText();
        Item.setName(name);
        JsonNode descriptionNode = node.get("description");
        String description = descriptionNode.asText();
        Item.setDescription(description);
        JsonNode itemIDNode = node.get("itemID");
        int itemID = itemIDNode.asInt();
        Item.setID(itemID);
        return Item;
    }
}
