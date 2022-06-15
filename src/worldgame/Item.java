package worldgame;

/**
 * 
 * Represents Item information for my lucky world.
 *
 */
public class Item {
  private final int itemIndex;
  private final String name;
  private final int damage;
  private final int roomindex;

  /**
   * Constructs a {@code Item} object.
   * 
   * @param itemIndex index of the itemm
   * @param name      Item Name
   * @param damage    item caused damage
   * @param roomindex room index of the item
   */
  public Item(int itemIndex, String name, int damage, int roomindex) {
    if (itemIndex < 0) {
      throw new IllegalArgumentException("[Int Error in Item Class] Invalid itemIndex.");
    }
    if (name == null || "".equals(name)) {
      throw new IllegalArgumentException("[String Error in Item Class] roomnamecannot be null!");
    }
    if (damage < 0) {
      throw new IllegalArgumentException("[Int Error in Item Class] Invalid damage.");
    }

    if (roomindex < 0) {
      throw new IllegalArgumentException("[Int Error in Item Class] Invalid itemIndex.");
    }

    this.itemIndex = itemIndex;
    this.name = name;
    this.damage = damage;
    this.roomindex = roomindex;
  }

  /**
   * Get the item index.
   * 
   * @return itemindex item index
   */
  public int getItemIndex() {
    return itemIndex;
  }

  /**
   * Get the item name.
   * 
   * @return name item name
   */
  public String getName() {
    return name;
  }

  /**
   * Get the damage.
   * 
   * @return damage damage
   */
  public int getDamage() {
    return damage;
  }

  /**
   * Get the item of current Room index.
   * 
   * @return roomindex integer
   */
  public int getRoomindex() {
    return roomindex;
  }

  @Override
  public String toString() {
    return String.format("[Item Index:%d Item Name:%s Damage:%s]", this.getItemIndex(),
        this.getName(), this.getDamage());
  }

}
