package worldgame;

import javax.swing.ImageIcon;

/**
 * 
 * Represents TargetCharacter information for my lucky world.
 *
 */
public class TargetCharacter {
  private final String name;
  private int health;
  private Space currentSpace;
  private ImageIcon tcimage;
  private final int beginhealth;

  /**
   * Constructs a {@code TargetCharacter} object.
   * 
   * @param name   name of the character
   * @param health health of the character
   * @throws IllegalArgumentException for invalid health
   */
  public TargetCharacter(String name, int health) throws IllegalArgumentException {
    if (health < 0) {
      throw new IllegalArgumentException("[Error] Invalid Health.");
    }
    if (name == null || "".equals(name)) {
      throw new IllegalArgumentException("[Error] Name cannot be null!");
    }
    this.name = name;
    this.beginhealth = health;
    this.health = health;
    this.currentSpace = null;
    this.tcimage = new ImageIcon("res/tc.jpg");

  }

  /**
   * Get the current space for the Target Character.
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
    this.currentSpace = currentPos;
  }

  /**
   * Set the health after damage caused to TargetCharacter.
   * 
   * @param decreasedhealth damaged health
   */
  public void decreaseHealth(int decreasedhealth) {
    this.health = this.health - decreasedhealth;
  }

  /**
   * Get the name of TargetCharacter.
   * 
   * @return Space current space location of player
   */
  public String getName() {
    return name;
  }

  /**
   * Get the health of TargetCharacter.
   * 
   * @return int health of TargetCharacter
   */
  public int getHealth() {
    return health;
  }

  /**
   * Get the initial health of TargetCharacter.
   * 
   * @return int begin health of TargetCharacter
   */
  public int getBeginHealth() {
    return beginhealth;
  }

  /**
   * Get the image icon of TargetCharacter.
   * 
   * @return ImageIcon image of the targetcharacter
   */
  public ImageIcon getImageIcon() {

    return tcimage;
  }

  @Override
  public String toString() {
    return String.format("TargetCharacter [name=%s, health=%s, \ncurrentSpace=%s%s]", name, health,
        currentSpace.getRoomname(), currentSpace.displaySpaceItems());
  }

}
