package famshare.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class ItemTest {
  private Item itm = null;

  @BeforeEach
  public void init() {
    itm = new Item();
  }

  @Test
  public void getAndSetNameTest() throws Exception {
    itm.setName("Cool curvateous cabin");
    Assertions.assertEquals("Cool curvateous cabin", itm.getName());
  }

  @Test
  public void getAndSetDescriptionTest() throws Exception {
    itm.setDescription("This is a very cool item for important people");
    Assertions.assertEquals("This is a very cool item for important people", itm.getDescription());
  }

  @Test
  public void getAndSetIdTest() throws Exception {
    itm.setId(0);
    Assertions.assertEquals(0, itm.getId());
  }
}
