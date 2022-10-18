package famshare.core;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

  private User usr = null;

  @BeforeEach
  public void init() {
    usr = new User();
  }

  @Test
  public void setAndGetNameTest() throws Exception {
    usr.setName("Ola Nordmann");
    Assertions.assertEquals("Ola Nordmann", usr.getName());
  }

  @Test
  public void setAndGetIdTest() throws Exception {
    usr.setId(0);
    Assertions.assertEquals(0, usr.getId());
  }

  @Test
  public void addItemsTest() throws Exception {
    usr.addItem(0);
    usr.addItem(1);
    usr.addItem(993);

    Assertions.assertEquals(new ArrayList<Integer>(Arrays.asList(0, 1, 993)), usr.getItems());
  }

  @Test
  public void removeItemsTest() throws Exception {
    usr.addItem(0);
    usr.addItem(1);
    usr.addItem(993);

    usr.removeItem(1);

    Assertions.assertEquals(new ArrayList<Integer>(Arrays.asList(0, 993)), usr.getItems());
  }
}
