package worldgame;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Represents Room information for my lucky world.
 *
 */
public class Space {
  private final int roomindex;
  private final String roomname;
  private final Rectangle location;
  private List<Space> neighbor;
  private List<Item> items;

  /**
   * Constructs a {@code Space} object.
   *
   * @param roomname  the title of the room
   * @param roomindex the room index of the space
   * @param location  the location of the space
   */
  public Space(int roomindex, String roomname, Rectangle location) {
    if (roomindex < 0) {
      throw new IllegalArgumentException("[Int Error in Space Class] Invalid roomindex.");
    }
    if (roomname == null || "".equals(roomname)) {
      throw new IllegalArgumentException("[String Error in Space Class] roomnamecannot be null!");
    }
    if (location == null) {
      throw new IllegalArgumentException(
          "[Rectangle Error in Space Class] location cannot be null!");
    }
    this.roomindex = roomindex;
    this.roomname = roomname;
    this.location = location;
    this.neighbor = new ArrayList<Space>();
    this.items = new ArrayList<Item>();
  }

  /**
   * setNeighbor to the Space.
   * 
   * @param neighbor ArrayList of Space
   */
  public void setNeighbor(List<Space> neighbor) {
    // make a new copy of neighbor
    List<Space> copy = new ArrayList<Space>(neighbor);
    // set the copy to this private class
    this.neighbor = copy;
  }

  /**
   * add Item to the space.
   * 
   * @param a item to add
   */
  public void addItemToSpace(Item a) {
    if (a == null) {
      throw new IllegalArgumentException(
          "[Item Error in Space Class] Item is null! addItemToSpace failed");
    }

    if (this.roomindex == a.getRoomindex()) {
      this.items.add(a);
    }
  }

  /**
   * add Item to the space.
   * 
   * @param a item to add
   */
  public void removeItemtoSpace(Item a) {
    if (a == null) {
      throw new IllegalArgumentException(
          "[Item Error in Space Class] Item is null! addItemToSpace failed");
    }

    if (this.roomindex == a.getRoomindex()) {
      this.items.remove(a);
    }
  }

  /**
   * getNeighbor.
   * 
   * @return a list of space neighbor
   */
  public List<Space> getNeighbor() {
    // make a new copy of neighbor and return to clients
    List<Space> copy = new ArrayList<Space>(neighbor);
    return copy;
  }

  /**
   * getNeighborDisplayWithInvisible with invisible room where pet is currently
   * occupied.
   * 
   * @param petspindex invisible room index
   * @return a string of space neighbor with invisible room
   */
  public String getNeighborDisplayWithInvisible(int petspindex) {
    if (petspindex < 0) {
      throw new IllegalArgumentException(
          "[Space getNeighborDisplayWithInvisible Error] petsindex cannot less than 0");
    }
    StringBuilder local = new StringBuilder();
    local.append(String.format("%s's Neighbor: ", this.getRoomname()));
    for (Space a : getNeighbor()) {
      if (a.getRoomindex() == petspindex) {
        local.append("[Room Index:hidden Room Name:hidden]");
      } else {
        local.append(a.toString());
      }
    }
    return local.toString();

  }

  /**
   * getNeighborDisplay.
   * 
   * @return a string of space neighbor
   */
  public String getNeighborDisplay() {
    String local = this.getRoomname() + "'s Neighbor: ";
    for (Space a : getNeighbor()) {
      local += String.format("[Room Index:%s Room Name:%s]", a.getRoomindex(), a.getRoomname());
    }
    return local;

  }

  /**
   * Display basic info of currentSpace including room index and room name.
   * 
   * @return a string of current space
   */
  public String getcurrentSpaceDisplay() {
    return String.format("[Room Index:%s Room Name:%s]", this.getRoomindex(), this.getRoomname());
  }

  /**
   * Display basic info of currentSpace including room index and room name. with
   * invisible room where pet is currently occupied.
   * 
   * @return a string of currentspace
   */
  public String getcurrentSpaceDisplayWithInvisible(int petspindex) {
    if (petspindex < 0) {
      throw new IllegalArgumentException(
          "[Space getcurrentSpaceDisplayWithInvisible Error] petsindex cannot less than 0");
    }
    if (this.getRoomindex() == petspindex) {
      return "[Room Index:hidden Room Name:hidden]";
    }
    return this.toString();
  }

  /**
   * getItems.
   * 
   * @return a list of items
   */
  public List<Item> getItems() {
    // make a new copy of items and return to clients
    List<Item> copy = new ArrayList<Item>(items);
    return copy;
  }

  /**
   * Return an item in current space.
   * 
   * @param itemindex itemindex in textfile
   * @return Item an item incurrent space
   */
  public Item getSpecficItemInCurrentSpace(int itemindex) {
    if (itemindex < 0) {
      throw new IllegalArgumentException("itemindex cannot be less than 0");

    }
    for (Item i : items) {
      if (i.getItemIndex() == itemindex) {
        return i;
      }

    }
    return null;
  }

  /**
   * display space items.
   * 
   * @return string of display items
   */
  public String displaySpaceItems() {
    StringBuilder sb = new StringBuilder();
    if (!items.isEmpty()) {
      for (Item it : items) {
        sb.append(it.toString());
      }
    } else {
      sb.append("No Items");
    }
    return sb.toString();
  }

  /**
   * Get the room index of the Space.
   * 
   * @return roomindex integer
   */
  public int getRoomindex() {
    return roomindex;
  }

  /**
   * Get the room name of the Space.
   * 
   * @return roomname String of room
   */
  public String getRoomname() {
    return roomname;
  }

  /**
   * location of the space.
   * 
   * @return getlocation Rectangle object
   */
  public Rectangle getLocation() {
    // make a new copy of items and return to clients
    Rectangle copy = new Rectangle(location);
    return copy;
  }

  @Override
  public String toString() {
    return String.format("[Room Index:%s Room Name:%s] %s", this.getRoomindex(), this.getRoomname(),
        this.displaySpaceItems());
  }
}
