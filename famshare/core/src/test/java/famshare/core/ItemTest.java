package famshare.core;

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
    itm.setDescription("This is a very cool item for important people");
    assertSame("This is a very cool item for important people", itm.getDescription());
  }

  @Test
  public void getAndSetIdTest() throws Exception {
    itm.setId(0);
    assertSame(0, itm.getId());
  }
}
