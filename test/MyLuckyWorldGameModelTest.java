
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import worldgame.MyLuckyWorldGameModel;
import worldgame.Pet;
import worldgame.Player;

/**
 * 
 * test MyLuckyWordGameModel Class.
 *
 */
public class MyLuckyWorldGameModelTest {
  private MyLuckyWorldGameModel model;

  @Before
  public void setUp() {
    model = new MyLuckyWorldGameModel();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMove() {
    // Throw error when move is not non-negative
    model.moveTc(-1);
  }

  @Test
  public void testCanSeeInSameSpaceWithoutPet() {
    model.readTxt("res/mansion.txt", "1");
    Pet pet = new Pet("MockPet");
    pet.setCurrentSpace(model.getSpecificSpace(19));
    Player p1 = new Player(0, "abc", 1);
    p1.setCurrentSpace(model.getSpecificSpace(0));
    Player p2 = new Player(1, "a", 1);
    p2.setCurrentSpace(model.getSpecificSpace(0));
    assertTrue(p1.canSee(p2, pet));
  }

  @Test
  public void testCanSeeInSameSpaceWithPet() {
    model.readTxt("res/mansion.txt", "3");
    Pet pet = new Pet("MockPet");
    pet.setCurrentSpace(model.getSpecificSpace(0));
    Player p1 = new Player(0, "abc", 1);
    p1.setCurrentSpace(model.getSpecificSpace(0));
    Player p2 = new Player(1, "a", 1);
    p2.setCurrentSpace(model.getSpecificSpace(0));
    assertTrue(p1.canSee(p2, pet));
  }

  @Test
  public void testCanSeeInNeighborWithoutPet() {
    model.readTxt("res/mansion.txt", "3");
    Pet pet = new Pet("MockPet");
    pet.setCurrentSpace(model.getSpecificSpace(19));
    Player p1 = new Player(0, "abc", 1);
    p1.setCurrentSpace(model.getSpecificSpace(0));
    Player p2 = new Player(1, "a", 1);
    p2.setCurrentSpace(model.getSpecificSpace(1));
    assertTrue(p1.canSee(p2, pet));
  }

  @Test
  public void testCanNotSeeInNeighborWithPetInNeighbor() {
    model.readTxt("res/mansion.txt", "3");
    Pet pet = new Pet("MockPet");
    pet.setCurrentSpace(model.getSpecificSpace(3));
    Player p1 = new Player(0, "abc", 1);
    p1.setCurrentSpace(model.getSpecificSpace(0));
    Player p2 = new Player(1, "a", 1);
    p2.setCurrentSpace(model.getSpecificSpace(1));
    assertFalse(p1.canSee(p2, pet));
  }

  @Test
  public void testCanNotSeeWithPetInSameRoomButAnotherAtNeighbor() {
    model.readTxt("res/mansion.txt", "3");
    Pet pet = new Pet("MockPet");
    pet.setCurrentSpace(model.getSpecificSpace(0));
    Player p1 = new Player(0, "abc", 1);
    p1.setCurrentSpace(model.getSpecificSpace(0));
    Player p2 = new Player(1, "a", 1);
    p2.setCurrentSpace(model.getSpecificSpace(1));
    assertFalse(p1.canSee(p2, pet));
  }

  @Test
  public void testCanNotSeeWithPetInSameRoomButAnotherAtNeighbor2() {
    model.readTxt("res/mansion.txt", "3");
    Pet pet = new Pet("MockPet");
    pet.setCurrentSpace(model.getSpecificSpace(0));
    Player p1 = new Player(0, "abc", 1);
    p1.setCurrentSpace(model.getSpecificSpace(1));
    Player p2 = new Player(1, "a", 1);
    p2.setCurrentSpace(model.getSpecificSpace(0));
    assertFalse(p1.canSee(p2, pet));
    System.out.println(pet.toString());
  }

}
