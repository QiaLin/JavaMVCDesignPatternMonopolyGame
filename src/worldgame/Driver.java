package worldgame;

import game.control.MvcController;

/**
 * 
 * Represents a Driver Class for MyLuckyWordGame.
 *
 */
public class Driver {
  /**
   * Execute and begin Game for clients.
   *
   * @param args args from jar terminal
   */
  public static void main(String[] args) {

    // Create the model
    MyLuckyWorldGame model = new MyLuckyWorldGameModel();
    // Create the controller with the model
    MvcController controller = new MvcController(args, model);
    // Create the view
    MvcView view = new MvcFrameView("MyLuckyWorld Game", model);
    // Set the view in the controller
    controller.setView(view);
  }

}
