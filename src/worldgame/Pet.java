package worldgame;

/**
 * 
 * Represents Pet information for my lucky world.
 *
 */
public class Pet {
  private final String name;
  private Space currentSpace;

  /**
   * Constructs a {@code Pet} object.
   * 
   * @param name name of the character
   * @throws IllegalArgumentException for invalid health
   */
  public Pet(String name) throws IllegalArgumentException {
    if (name == null || "".equals(name)) {
      throw new IllegalArgumentException("[Error] Name cannot be null!");
    }
    this.name = name;
    this.currentSpace = null;

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
   * Set the current space for the player.
   * 
   * @return Space current space location of player
   */
  public void setCurrentSpace(Space currentPos) {
    if (currentPos == null) {
      throw new IllegalArgumentException("[Error] Pet Space cannot be null!");

    }
    this.currentSpace = currentPos;
  }

  /**
   * Get the pet name.
   * 
   * @return String name of th pet
   */
  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return String.format("Pet [name=%s,\n currentSpace=%s%s]", name, currentSpace.getRoomname(),
        currentSpace.displaySpaceItems());
  }

}
