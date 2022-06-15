package worldgame;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * A class for ReadOnlyViewPanel that contains panel using read-only model.
 * 
 */
public class ReadOnlyViewPanel extends JPanel {

  /**
   * JPanel Generated Serial ID.
   */
  private static final long serialVersionUID = -1835190622529726438L;

  private final ReadOnlyMyLuckyWorldGame model;

  /**
   * View Panel Constructor.
   * 
   * @param m the model
   */
  ReadOnlyViewPanel(ReadOnlyMyLuckyWorldGame m) {
    if (m == null) {
      throw new IllegalArgumentException(
          "ViewPanel Constructor Parameter ReadonlyTttModel cannot be null!");
    }
    model = m;

  }

  @Override
  public void paintComponent(Graphics g) {
    if (g == null) {
      throw new IllegalArgumentException(
          "ViewPanel paintComponent Parameter Graphics cannot be null!");
    }
    super.paintComponent(g);
    g.drawImage(model.getBuffImage(), 0, 0, this);
  }
}
