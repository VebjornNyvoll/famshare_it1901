package famshare.core;

import java.util.ArrayList;

public class User {

  private String name;
  private int id;
  // Should Users item be represented by a list of IDs, or a list of item-objects?
  // For now it's set up as a list of IDs
  private ArrayList<Integer> items = new ArrayList<Integer>();

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public ArrayList<Integer> getItems() {
    return items;
  }

  public void addItem(int itemId) {
    this.items.add(itemId);
  }

  public void removeItem(int itemId) {
    // Should maybe encapsulate in while loop to make sure any duplicate instances get removed
    // Ideally this wouldn't happen to begin with but y'know
    this.items.remove(itemId);
  }

}
