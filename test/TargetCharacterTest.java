import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import worldgame.TargetCharacter;

/**
 * 
 * test TargetCharacter Class.
 *
 */
public class TargetCharacterTest {
  private TargetCharacter tc;

  @Before
  public void setUp() {
    tc = new TargetCharacter("ac", 10);
  }

  @Test
  public void testgetHealth() {
    assertEquals(10, tc.getHealth());

  }
}
