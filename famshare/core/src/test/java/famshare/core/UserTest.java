package famshare.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

  private User usr = null;

  @Before
  public void init() {
    usr = new User();
  }

  @Test
  public void setAndGetNameTest() throws Exception {
    usr.setName("Ola Nordmann");
    assertSame("Ola Nordmann", usr.getName());
  }

  @Test
  public void setAndGetIdTest() throws Exception {
    usr.setId(0);
    assertEquals(0, usr.getId());
  }

  @Test
  public void addItemsTest() throws Exception {
    usr.addItem(0);
    usr.addItem(1);
    usr.addItem(993);

    assertEquals(new ArrayList<Integer>(Arrays.asList(0, 1, 993)), usr.getItems());
  }

  @Test
  public void removeItemsTest() throws Exception {
    usr.addItem(0);
    usr.addItem(1);
    usr.addItem(993);

    usr.removeItem(1);

    assertEquals(new ArrayList<Integer>(Arrays.asList(0, 993)), usr.getItems());
  }
}
