import worldgame.Features;
import worldgame.MvcView;

/**
 * Mock Model for testing purpose.
 *
 */
public class MockView implements MvcView {

  private int uniqueCode;
  private StringBuilder log;

  /**
   * Build a mock view with unique id and game log when being used.
   * 
   * @param log        Game Log
   * @param uniqueCode Unique identfication for mocked model
   */
  public MockView(StringBuilder log, int uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public void setFeatures(Features f) {
    log.append(String.format("setFeatures Unique Code: %s\n", uniqueCode));

  }

  @Override
  public void refresh() {
    log.append(String.format("refresh Unique Code: %s\n", uniqueCode));

  }

  @Override
  public void changelog(String currentlog) {
    log.append(String.format("changelog Unique Code: %s\n", uniqueCode));

  }

  @Override
  public void showDialog(String message) {
    log.append(String.format("showDialog Unique Code: %s\n", uniqueCode));

  }

  @Override
  public int showConfirmationDiaglog(String message, String title) {
    log.append(String.format("showConfirmationDiaglog Unique Code: %s\n", uniqueCode));
    return 0;
  }

}
