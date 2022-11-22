package famshare.json;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import famshare.core.ItemList;
import famshare.core.Item;
import famshare.core.User;

public class ItemListDeserializer extends StdDeserializer<ItemList> {
    
    public ItemListDeserializer() {
        this(null);
    }

    public ItemListDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ItemList deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        ItemList ItemList = new ItemList();
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);
        //Gets the item object from json    
        JsonNode itemNode = node.get("item");
        Item item = new Item();
        item.setName(itemNode.get("name").asText());
        item.setDescription(itemNode.get("description").asText());
        item.setId(itemNode.get("itemID").asInt());

        return ItemList;
    }
}
