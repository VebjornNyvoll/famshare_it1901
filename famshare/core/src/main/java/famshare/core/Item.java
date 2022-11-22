package famshare.core;


public class Item {
  private String name;
  private String description;
  private int itemID;

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDescription() {
    return this.description;
  }

  public void setId(int id) {
    this.itemID = id;
  }

  public int getId() {
    return this.itemID;
  }

}
