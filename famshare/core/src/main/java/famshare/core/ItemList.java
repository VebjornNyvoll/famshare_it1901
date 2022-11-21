package famshare.core;

import java.util.ArrayList;
import java.util.List;

public class ItemList {

    private ArrayList<Item> items;

    public ItemList() {
        items = new ArrayList<>();
        Item cabin = new Item();
        cabin.setName("Cabin");
        cabin.setId(1);
        Item car = new Item();
        car.setName("Car");
        car.setId(2);
        Item boat = new Item();
        boat.setName("Boat");
        boat.setId(3);
        Item drill = new Item();
        drill.setName("Drill");
        drill.setId(4);
        Item bike = new Item();
        bike.setName("Bike");
        bike.setId(5);
        Item toolBox = new Item();
        toolBox.setName("Tool box");
        toolBox.setId(6);
        items.add(toolBox);
        items.add(bike);
        items.add(drill);
        items.add(boat);
        items.add(car);
        items.add(cabin);
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
