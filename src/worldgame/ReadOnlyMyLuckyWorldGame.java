package worldgame;

import java.awt.image.BufferedImage;

/**
 * Read-Only Model for information hiding in my luckyworld game.
 */
public interface ReadOnlyMyLuckyWorldGame {

  /**
   * display info of the world.
   * 
   * @return String display of the world
   */
  String displayWorld();

  /**
   * get specific space.
   * 
   * @param roomindex Space
   * @return info of a space
   */
  Space getSpecificSpace(int roomindex);

  /**
   * get Player.
   *
   * @param playerindex the player index
   * @return player object
   */
  Player getPlayer(int playerindex);

  /**
   * player look around to show all player.
   * 
   * @param playerindex player index
   * @return String of all player
   */
  String playerLookAround(int playerindex);

  /**
   * displayPlayer.
   * 
   * @param index playerindex
   * @return String display
   */
  String displayPlayer(int index);

  /**
   * displayAllPlayerInWorld.
   * 
   * @return String display
   */
  String displayAllPlayerInWorld();

  /**
   * displayTargetCharacter.
   * 
   * @return String display
   */
  String displayTc();

  /**
   * getPlayerTotalNum.
   * 
   * @return int size of the player list
   */
  int getPlayerTotalNum();

  /**
   * Get the pet object.
   * 
   * @return pet object
   */
  Pet getPet();

  /**
   * Determine the current player if is win.
   * 
   * @return boolean True for player win False for player not win
   */
  boolean isWin();

  /**
   * Determine the maxmimum load number of carried item per player's bag.
   * 
   * @return int maxmimum load
   */
  int getCarrieditemnum();

  /**
   * Determine the total room number in this world.
   * 
   * @return int total room number
   */
  int getTotalroomnum();

  /**
   * get read-only Board Image in model.
   * 
   * @return BufferImage image to write to the board
   */
  BufferedImage getBuffImage();

  /**
   * get current run in model.
   * 
   * @return int current run
   */
  int getCurrentRun();

  /**
   * get current turn in model.
   * 
   * @return int current run
   */
  int getCurrentTurn();

  /**
   * get status of initializing the game.
   * 
   * @return i 1 means init sucess 0 means init game failure
   */
  int getInitGame();

  /**
   * get status of ending the game.
   * 
   * @return boolean true if game is end false if game is not end
   */
  boolean isEnd();

}
