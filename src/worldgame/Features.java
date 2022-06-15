package worldgame;

import java.awt.image.BufferedImage;

/**
 * This interface represents a set of features that the program offers. Each
 * feature is exposed as a function in this interface. This function is used
 * suitably as a callback by the view, to pass control to the controller. How
 * the view uses them as callbacks is completely up to how the view is designed
 * (e.g. it could use them as a callback for a button, or a callback for a
 * dialog box, or a set of text inputs, etc.)
 *
 * <p>
 * Each function is designed to take in the necessary data to complete that
 * functionality.
 */

public interface Features {
  /**
   * Exit the program.
   */
  void exitProgram();

  /**
   * handle User click the board to Move.
   */
  String handleMove(int playernum, int spacenum);

  /**
   * handle User init the game.
   */
  String handleInitGame();

  /**
   * load images in the game.
   * 
   * @return BufferedImage images
   */
  BufferedImage loadBuffImage();

  /**
   * process Input of Add a player.
   * 
   * @param text input
   * @return log String
   */
  String processAddPlayerInput(String[] text);

  /**
   * process Input of Add a Computer player.
   * 
   * @return log String
   */
  String processAddComputerPlayerInput();

  /**
   * process Input of pick up item.
   * 
   * @param itemindex item to pick up
   * @return log String
   */
  String processPickUpInput(String itemindex);

  /**
   * process Input of attack the targetcharacter.
   * 
   * @param itemindex item to attack
   * @return log String
   */
  String processAttackInput(String itemindex);

  /**
   * restart the game.
   */
  void restart();

  /**
   * change map and get file path from user and restart the game.
   */
  void changemap(String filepath);

  String processplayerLookAround(int currentTurn);

}
