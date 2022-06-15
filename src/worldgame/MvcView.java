package worldgame;

/**
 * The interface for our view class.
 */
public interface MvcView {
  /**
   * Get the set of feature callbacks that the view can use.
   * 
   * @param f the set of feature callbacks as a Features object
   */
  void setFeatures(Features f);

  /**
   * repaint the view.
   * 
   */
  void refresh();

  /**
   * change the content of the game log.
   * 
   * @param currentlog the log to be changed
   */
  void changelog(String currentlog);

  /**
   * show the dialog.
   * 
   * @param currentlog the log to be showned
   */
  void showDialog(String message);

  /**
   * show Confirmation Diaglog.
   * 
   * @param message dialog to be shown
   * @param title   window title
   * @return answer yes is 1 no is 0
   */
  int showConfirmationDiaglog(String message, String title);

}
