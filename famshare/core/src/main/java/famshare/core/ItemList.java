package famshare.core;

import java.util.ArrayList;
import java.util.List;

public class ItemList {

    private ArrayList<Item> items;

    public ItemList() {
        items = new ArrayList<Item>();
        
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(int itemId) {
        items.remove(itemId);
    }

    public ArrayList<Item> getItems() {
        return new ArrayList<Item>(items);
    }

    public Item getItem(int id) {
        return items.get(id);
    }

    //Set items
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public Item get(int i) {
        return items.get(i);
    }

    //Returns size
    public int size() {
        return items.size();
    }

    //Returns true if list is empty
    public boolean isEmpty() {
        return items.isEmpty();
    }



}
