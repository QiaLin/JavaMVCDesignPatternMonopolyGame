import static org.junit.Assert.assertEquals;

import java.awt.Rectangle;
import org.junit.Before;
import org.junit.Test;
import worldgame.Space;

/**
 * 
 * test Space Class.
 *
 */
public class SpaceTest {
  private Space space;

  @Before
  public void setUp() {
    space = new Space(0, "roomname", new Rectangle(4, 5));
  }

  @Test
  public void testgetRectangle() {
    assertEquals(new Rectangle(4, 5), space.getLocation());
  }
}
