import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import worldgame.Item;

/**
 * 
 * test Test Class.
 *
 */
public class ItemTest {
  private Item item;

  @Before
  public void setUp() {
    item = new Item(0, "abc", 5, 0);
  }

  @Test
  public void testgetDamage() {
    assertEquals(5, item.getDamage());

  }
}
