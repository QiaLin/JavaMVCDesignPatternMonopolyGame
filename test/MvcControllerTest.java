import static org.junit.Assert.assertEquals;

import game.control.MvcController;
import java.io.IOException;
import org.junit.Test;
import worldgame.MvcFrameView;
import worldgame.MvcView;
import worldgame.MyLuckyWorldGame;
import worldgame.MyLuckyWorldGameModel;

/**
 * Test cases for the MvcControllerTest controller, using mocks for readable and
 * appendable.
 */
public class MvcControllerTest {
  @Test
  public void testProcessInput() throws IOException {
    MyLuckyWorldGame model = new MyLuckyWorldGameModel();
    String[] args = new String[2];
    args[0] = "res/mansionm3sr.txt";
    args[1] = "5";
    MvcController controller = new MvcController(args, model);
    // Create the view
    MvcView view = new MvcFrameView("MyLuckyWorld Game", model);
    // Set the view in the controller
    controller.setView(view);
    // no error thrown
    controller.processAddComputerPlayerInput();

  }

  @Test
  public void testloadBuffImage() {
    MyLuckyWorldGame model = new MyLuckyWorldGameModel();
    String[] args = new String[2];
    args[0] = "res/mansionm3sr.txt";
    args[1] = "5";
    MvcController controller = new MvcController(args, model);
    // Create the view
    MvcView view = new MvcFrameView("MyLuckyWorld Game", model);
    // Set the view in the controller
    controller.setView(view);
    // no error thrown
    controller.loadBuffImage();
  }

  @Test
  public void testWithMockModel() throws IOException {
    StringBuilder log = new StringBuilder();
    MyLuckyWorldGame model = new MyLuckyWorldGameModel();
    MvcView view = new MockView(log, 123);
    String[] args = new String[2];
    args[0] = "res/mansionm3sr.txt";
    args[1] = "5";
    MvcController controller = new MvcController(args, model);
    controller.setView(view);
    StringBuilder expected = new StringBuilder();
    expected.append("setFeatures Unique Code: 123\n");
    assertEquals(expected.toString(), log.toString());
  }

  @Test
  public void testWithMockView() throws IOException {
    StringBuilder log = new StringBuilder();
    MyLuckyWorldGame model = new MockModel(log, 123);
    MvcView view = new MvcFrameView("abc", model);
    String[] args = new String[2];
    args[0] = "res/mansionm3sr.txt";
    args[1] = "5";
    MvcController controller = new MvcController(args, model);
    controller.setView(view);
    StringBuilder expected = new StringBuilder();
    expected.append("readTxt Input: res/mansionm3sr.txt 5 Unique Code: 123\n");
    expected.append("saveImage Unique Code: 123\n");
    assertEquals(expected.toString(), log.toString());
  }

}
