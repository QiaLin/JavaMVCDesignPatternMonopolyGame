package worldgame;

import java.io.IOException;

/**
 * Actual model to play. A single game of My Lucky World Game, played on a map
 * with multiple players, with the object of the game to achieve move the player
 * into different rooms and die if no deaths {@link Player}a player.
 * 
 *
 */
public interface MyLuckyWorldGame extends ReadOnlyMyLuckyWorldGame {
  /**
   * Read info from a text file.
   * 
   * @param arg1 the file location which is the first argument of the main
   * @throws IllegalArgumentException for text content failed.
   */
  void readTxt(String arg1, String arg2) throws IllegalArgumentException;

  /**
   * set initialize the game.
   * 
   * @param i 1 means init sucess 0 means init game failure
   */
  void setInitGame(int i);

  /**
   * Draw Map and Save an Image to a PNG file and initialize game.
   * 
   * @return String of game log
   * @throws IOException save image failed
   */
  String saveImage() throws IOException;

  /**
   * add Player.
   *
   * @param p  the player to add
   * @param sp initial space
   * @return String of game log
   */

  String addPlayer(Player p, Space sp);

  /**
   * player pickup the item.
   * 
   * @param p  Player object
   * @param it Item object
   * @return String of game log
   */
  String playerPickupItem(Player p, Item it);

  /**
   * action of player attack the target character.
   * 
   * @param p         Player object to attack
   * @param itemindex item used to attack the target character
   * @return String game log
   */
  String playerAttack(Player p, int itemindex);

  /**
   * player move the pet.
   * 
   * @param i index of moving the pet to another space
   * @return String of game log
   */
  String movePet(int i);

  /**
   * Execute a move in the position specified by the given space.
   *
   * @param spindex the space index of the intended move
   * @return String gamelog
   * @throws IllegalArgumentException the position is otherwise invalid
   */
  String moveTc(int spindex) throws IllegalArgumentException;

  /**
   * Execute a move in the position specified by the given space.
   *
   * @param player  the Player object
   * @param spindex int
   * @return String gamelog
   */
  String movePlayer(Player player, int spindex);
}
