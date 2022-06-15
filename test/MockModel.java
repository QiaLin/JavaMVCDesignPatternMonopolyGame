
import java.awt.image.BufferedImage;
import worldgame.Item;
import worldgame.MyLuckyWorldGame;
import worldgame.Pet;
import worldgame.Player;
import worldgame.Space;

/**
 * Build a mock model with unique id and game log when being used.
 * 
 * @param log        Game Log
 * @param uniqueCode Unique identfication for mocked model
 */
public class MockModel implements MyLuckyWorldGame {
  private int uniqueCode;
  private StringBuilder log;

  /**
   * Build a mock model with unique id and game log when being used.
   * 
   * @param log        Game Log
   * @param uniqueCode Unique identfication for mocked model
   */
  public MockModel(StringBuilder log, int uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public void readTxt(String arg, String arg2) throws IllegalArgumentException {
    log.append(String.format("readTxt Input: %s %s Unique Code: %s\n", arg, arg2, uniqueCode));

  }

  @Override
  public String saveImage() {

    log.append(String.format("saveImage Unique Code: %s\n", uniqueCode));

    return log.toString();
  }

  @Override
  public String displayWorld() {
    log.append(String.format("displayWorld Unique Code: %s\n", uniqueCode));
    return log.toString();
  }

  @Override
  public Space getSpecificSpace(int roomindex) {
    uniqueCode = 114;
    log.append(
        String.format("getSpecificSpace Input: %s Unique Code: %s\n", roomindex, uniqueCode));
    return null;
  }

  @Override
  public Player getPlayer(int playerindex) {
    log.append(String.format("getPlayer Input: %s Unique Code: %s\n", playerindex, uniqueCode));
    return null;
  }

  @Override
  public String moveTc(int spindex) throws IllegalArgumentException {
    log.append(String.format("moveTc Input: %s Unique Code: %s\n", spindex, uniqueCode));
    return log.toString();
  }

  @Override
  public String movePlayer(Player player, int spindex) {
    log.append(
        String.format("movePlayer Input: %s %s Unique Code: %s\n", player.toString(), uniqueCode));
    return log.toString();
  }

  @Override
  public String displayPlayer(int index) {
    log.append(String.format("moveTc Input: %s  Unique Code: %s\n", index, uniqueCode));
    return log.toString();
  }

  @Override
  public String displayAllPlayerInWorld() {
    log.append(String.format("displayAllPlayerInWorld Unique Code: %s\n", uniqueCode));
    return log.toString();
  }

  @Override
  public String displayTc() {
    log.append(String.format("displayTc Unique Code: %s\n", uniqueCode));
    return log.toString();
  }

  @Override
  public int getPlayerTotalNum() {
    log.append(String.format("getPlayerTotalNum Unique Code: %s\n", uniqueCode));
    return 0;
  }

  @Override
  public String playerPickupItem(Player p, Item it) {
    log.append(String.format("playerPickupItem Input: %s %s Unique Code: %s\n", p.toString(),
        it.toString(), uniqueCode));
    return log.toString();
  }

  @Override
  public String playerLookAround(int playerindex) {
    log.append(
        String.format("playerLookAround Input: %s Unique Code: %s\n", playerindex, uniqueCode));
    return log.toString();
  }

  @Override
  public String movePet(int i) {
    log.append("movePet");
    uniqueCode = 5555;
    return null;
  }

  @Override
  public Pet getPet() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isWin() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public int getCarrieditemnum() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getTotalroomnum() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public String playerAttack(Player p, int itemindex) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String addPlayer(Player p, Space sp) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public BufferedImage getBuffImage() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getCurrentRun() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int getCurrentTurn() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void setInitGame(int i) {
    // TODO Auto-generated method stub

  }

  @Override
  public int getInitGame() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public boolean isEnd() {
    // TODO Auto-generated method stub
    return false;
  }
}
