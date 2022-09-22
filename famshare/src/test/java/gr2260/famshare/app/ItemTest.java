package gr2260.famshare.app;

import static org.junit.Assert.assertSame;
import org.junit.Before;
import org.junit.Test;

public class ItemTest {
  private Item itm = null;

  @Before
  public void init() {
    itm = new Item();
  }

  @Test
  public void getAndSetNameTest() throws Exception {
    itm.setName("Cool curvateous cabin");
    assertSame("Cool curvateous cabin", itm.getName());
  }

  @Test
  public void getAndSetDescriptionTest() throws Exception {
    itm.setDecsripion("This is a very cool item for important people");
    assertSame("This is a very cool item for important people", itm.getDescription());
  }

  @Test
  public void getAndSetIdTest() throws Exception {
    itm.setID(0);
    assertSame(0, itm.getID());
  }
}
