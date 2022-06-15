package game.control;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.InputMismatchException;
import worldgame.Features;
import worldgame.MvcFrameView;
import worldgame.MvcView;
import worldgame.MyLuckyWorldGame;
import worldgame.MyLuckyWorldGameModel;
import worldgame.Player;

/**
 * The controller now implements the Features interface. This means each of
 * those functions will give control to the controller.
 */
public class MvcController implements Features {
  private MyLuckyWorldGame model;
  private MvcView view;
  private final String[] args1;

  /**
   * Constructor.
   * 
   * @param args args from command line
   * @param m    the model to use
   */
  public MvcController(String[] args, MyLuckyWorldGame m) {
    model = m;
    model.readTxt(args[0], args[1]);
    args1 = args;
    try {
      model.saveImage();
    } catch (IOException e) {
      System.out.println("[IOImageError]Save Image failed");
    }
  }

  /**
   * Mutator for the view.
   * 
   * @param v the view to use
   */
  public void setView(MvcView v) {
    if (v == null) {
      throw new IllegalArgumentException("MvcView is null");
    }
    view = v;
    // give the feature callbacks to the view
    view.setFeatures(this);
  }

  @Override
  public String processAddPlayerInput(String[] text) {
    if (text == null) {
      throw new IllegalArgumentException("text is null");
    }
    String log;
    try {
      int i = Integer.parseInt(text[1]);

      Player add = new Player(model.getPlayerTotalNum(), text[0], 1);
      log = model.addPlayer(add, model.getSpecificSpace(i));
    } catch (InputMismatchException e) {
      log = "Add Player failed!";

    }
    return log;
  }

  @Override
  public String processAddComputerPlayerInput() {
    Player add = new Player(model.getPlayerTotalNum(), "Computer", 0);
    String log = model.addPlayer(add, model.getSpecificSpace(0));
    return log;

  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public void restart() {
    MyLuckyWorldGame m = new MyLuckyWorldGameModel();
    // Create the model
    // Create the controller with the model
    MvcController controller = new MvcController(args1, m);
    // Create the view
    MvcView view = new MvcFrameView("MyLuckyWorld Game", m);
    // Set the view in the controller
    controller.setView(view);
  }

  @Override
  public BufferedImage loadBuffImage() {
    BufferedImage buffimg = model.getBuffImage();
    return buffimg;
  }

  @Override
  public String handleMove(int playernum, int spacenum) {
    if (playernum < 0 || spacenum < 0) {
      throw new IllegalArgumentException("playernum cannot less than 0");
    }
    return model.movePlayer(model.getPlayer(playernum), spacenum);

  }

  @Override
  public String handleInitGame() {
    if (model.getPlayerTotalNum() != 0) {
      model.setInitGame(1);
      return "Game start sucess! Play game...";
    } else {
      model.setInitGame(0);
      return "Game start failed! No player added!";
    }

  }

  @Override
  public String processPickUpInput(String itemindex) {
    if ("".equals(itemindex)) {
      throw new IllegalArgumentException("itemindex cannot be empty");
    }
    String log;
    try {
      int i = Integer.parseInt(itemindex);
      log = model.playerPickupItem(model.getPlayer(model.getCurrentRun()),
          model.getPlayer(model.getCurrentRun()).getCurrentSpace().getSpecficItemInCurrentSpace(i));
    } catch (InputMismatchException e) {
      log = "Pick up failed";

    }
    return log;
  }

  @Override
  public String processAttackInput(String itemindex) {
    if ("".equals(itemindex)) {
      throw new IllegalArgumentException("itemindex cannot be empty");
    }
    String log;
    int i = Integer.parseInt(itemindex);
    log = model.playerAttack(model.getPlayer(model.getCurrentRun()), i);
    return log;
  }

  @Override
  public void changemap(String filepath) {
    if ("".equals(filepath)) {
      throw new IllegalArgumentException("filepath cannot be empty");
    }
    args1[0] = filepath;

    // Create the model
    MyLuckyWorldGame m = new MyLuckyWorldGameModel();
    // Create the controller with the model
    MvcController controller = new MvcController(args1, m);
    // Create the view
    MvcView view = new MvcFrameView("MyLuckyWorld Game", m);
    // Set the view in the controller
    controller.setView(view);
  }

  @Override
  public String processplayerLookAround(int roomindex) {
    if (roomindex < 0) {
      throw new IllegalArgumentException("itemindex cannot be empty");
    }
    String log;
    log = model.playerLookAround(roomindex);
    return log;
  }

}
