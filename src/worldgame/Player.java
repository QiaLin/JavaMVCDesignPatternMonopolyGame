package worldgame;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents Player information for my lucky world. Create a Player class. Type
 * 0 is a computer player. Type 1 is a huma player.
 *
 */
public class Player {

  private final int num;
  private final String name;
  private int health;
  private int type;
  private Space currentSpace;
  private List<Item> carrieditems;

  /**
   * Constructs a {@code Player} object.
   * 
   * @param num  index of the player in player list
   * @param name name of the character
   * @param type 0 is robot 1 is human
   * @throws IllegalArgumentException for invalid health
   */
  public Player(int num, String name, int type) {
    if (health < 0) {
      throw new IllegalArgumentException("[Error] Invalid Health.");
    }
    if (name == null || "".equals(name)) {
      throw new IllegalArgumentException("[Error] Name cannot be null!");
    }
    if (type > 1 || type < 0) {
      throw new IllegalArgumentException("[Error] Type is invalid");
    }
    this.num = num;
    this.name = name;
    this.health = 50;
    this.type = type;
    this.carrieditems = new ArrayList<Item>();
  }

  /**
   * Add items to the item list of player's bag.
   * 
   * @param a itemobject {@code Item}
   */
  public void addcarrieditems(Item a) {
    if (a == null) {
      throw new IllegalArgumentException("[Error] Carried Item cannot be null!");
    }
    this.carrieditems.add(a);
  }

  /**
   * Remove items to the item list of player's bag.
   * 
   * @param a itemobject {@code Item}
   */
  public void removecarrieditems(Item a) {
    if (a == null) {
      throw new IllegalArgumentException("[Error] Carried Item cannot be null!");
    }
    this.carrieditems.remove(a);
  }

  /**
   * Get a item list of player's bag.
   * 
   * @return a item list
   */
  public List<Item> getCarrieditems() {
    // make a new copy of items and return to clients
    List<Item> copy = new ArrayList<Item>(carrieditems);
    return copy;
  }

  /**
   * Determine if the player can see another player in game.
   * 
   * @param another Another Player to see if they can see each other
   * @param pet     pet to determine if player can see each other
   * @return boolean true for can see false for can not see
   */
  public boolean canSee(Player another, Pet pet) {
    if (another == null) {
      throw new IllegalArgumentException("[Error] Carried Item cannot be null!");
    }
    boolean inPetNeighbor = false;
    boolean result = false;
    // in pet neighbor space
    for (Space petnb : pet.getCurrentSpace().getNeighbor()) {
      if (this.getCurrentSpace().getRoomindex() == petnb.getRoomindex()) {
        inPetNeighbor = true;
      }
      if (another.getCurrentSpace().getRoomindex() == petnb.getRoomindex()) {
        inPetNeighbor = true;
      }
    }

    // in the same room
    if (this.getCurrentSpace().getRoomindex() == another.getCurrentSpace().getRoomindex()) {
      result = true;
    }
    // neighbor
    for (Space a : this.getCurrentSpace().getNeighbor()) {
      if (another.getCurrentSpace().getRoomindex() == a.getRoomindex() && !inPetNeighbor) {
        result = true;
      }
    }

    return result;
  }

  /**
   * Set the current space for the player.
   * 
   * @param Space current space location of player
   */
  public void setCurrentSpace(Space currentPos) {
    if (currentPos == null) {
      throw new IllegalArgumentException("[Error] Player Space cannot be null!");
    }
    this.currentSpace = currentPos;
  }

  /**
   * Get the current health of the player.
   * 
   * @return int the HP for current player's health
   */
  public int getHealth() {
    return health;
  }

  /**
   * Get the current space for the player.
   * 
   * @return Space current space location of player
   */
  public Space getCurrentSpace() {
    return this.currentSpace;
  }

  /**
   * Get the index of the player.
   * 
   * @return int index of the player
   */
  public int getNum() {
    return num;
  }

  /**
   * Get the name of the player.
   * 
   * @return String name of the player
   */
  public String getName() {
    return name;
  }

  /**
   * Get the type of the player.
   * 
   * @return 0 for Computer Player, 1 for Human Player.
   */
  public int getType() {
    return type;
  }

  @Override
  public String toString() {
    return String.format(
        "Player [num=%s, name=%s, health=%s, type=%s, currentSpace=%s,carrieditems=%s]", num, name,
        health, type, currentSpace, carrieditems);
  }
}
