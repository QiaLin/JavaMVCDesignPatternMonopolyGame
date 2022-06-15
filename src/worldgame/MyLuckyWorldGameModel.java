package worldgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 * 
 * Implement a game model for my lucky world.
 *
 */
public class MyLuckyWorldGameModel implements MyLuckyWorldGame {
  private String myworldname;
  private TargetCharacter tc;
  private Pet pet;
  private int totalplayernum;
  private int totalroomnum;
  private int totalturnnum;
  private int currentTurn;
  private int currentRurn;
  private final int carrieditemnum;
  private final List<Space> spaces;
  private final List<Item> items;
  private final List<Player> players;
  private int initgame;

  /**
   * Constructs a {@code MyLuckyWordGameModel} object.
   *
   */
  public MyLuckyWorldGameModel() {
    this.myworldname = "";
    this.tc = null;
    this.pet = null;
    this.totalplayernum = 0;
    this.totalroomnum = 0;
    this.totalturnnum = 0;
    this.currentTurn = 0;
    this.initgame = 0;
    this.carrieditemnum = 3;
    this.spaces = new ArrayList<Space>();
    this.items = new ArrayList<Item>();
    this.players = new ArrayList<Player>();

  }

  @Override
  public void setInitGame(int i) {
    if (i < 0) {
      throw new IllegalArgumentException("init status cannot be less 0");
    }
    this.initgame = i;
  }

  @Override
  public boolean isEnd() {
    if (this.getCurrentRun() == this.totalturnnum) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public int getInitGame() {
    return this.initgame;
  }

  @Override
  public int getCurrentRun() {
    return this.currentRurn;
  }

  @Override
  public int getCurrentTurn() {

    return this.currentTurn;
  }

  @Override
  public int getTotalroomnum() {
    return totalroomnum;
  }

  @Override
  public int getCarrieditemnum() {
    return carrieditemnum;
  }

  @Override
  public Pet getPet() {
    return this.pet;
  }

  @Override
  public boolean isWin() {
    if (this.tc.getHealth() <= 0) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public String displayWorld() {
    // initialize game
    StringBuilder a = new StringBuilder();
    a.append(String.format("-------- Welcome to %s--------\n", myworldname));
    a.append(String.format("Target Character is %s with Health: %s. Pet is %s.\n",
        this.tc.getName(), this.tc.getHealth(), this.pet.getName()));
    a.append(String.format("Total room num is %s\nAll room is following:\n", this.totalroomnum));
    for (Space sp : spaces) {
      a.append(String.format("%s\n", sp.getcurrentSpaceDisplay()));
    }
    return a.toString();

  }

  @Override
  public Space getSpecificSpace(int roomindex) {
    if (roomindex < 0 && roomindex > this.getTotalroomnum()) {
      throw new IllegalArgumentException("invalid getSpecificSpace");

    }
    Space a = this.spaces.get(roomindex);
    return a;
  }

  @Override
  public String displayPlayer(int index) {
    if (index < 0) {
      throw new IllegalArgumentException("invalid displayPlayer");
    }
    StringBuilder a = new StringBuilder(players.get(index).toString());
    a.append("\n");
    return a.toString();
  }

  @Override
  public String movePet(int i) throws IllegalArgumentException {
    if (i < 0) {
      throw new IllegalArgumentException("invalid move");
    }
    // set the moved position to pet
    this.pet.setCurrentSpace(spaces.get(i));
    return String.format("Pet %s move to %s\n", pet.getName(), pet.getCurrentSpace().toString());
  }

  @Override
  public String moveTc(int i) throws IllegalArgumentException {
    if (i < 0) {
      throw new IllegalArgumentException("invalid move");
    }
    if (i > this.totalroomnum) {
      throw new IllegalArgumentException("invalid move");
    }
    int move = i % totalroomnum;
    /*
     * if (spaces.get(i).getNeighbor().contains(tc.getCurrentSpace())) { throw new
     * IllegalArgumentException("Cannot move to a space is not neighbor"); }
     */
    // set the moved position to character
    this.tc.setCurrentSpace(spaces.get(move));
    return String.format("TargetCharacter %s move to %s\n", tc.getName(),
        tc.getCurrentSpace().toString());
  }

  @Override
  public String movePlayer(Player p, int i) {
    if (p == null) {
      throw new IllegalArgumentException("Model movePlayer Player cannot be null!");
    }
    if (i < 0) {
      throw new IllegalArgumentException("negative space, invalid move");
    }
    if (i > this.totalroomnum) {
      throw new IllegalArgumentException("invalid move");
    }

    StringBuilder sb = new StringBuilder();

    for (Space nb : this.getSpecificSpace(i).getNeighbor()) {
      if (p.getCurrentSpace().getRoomindex() == nb.getRoomindex()) {

        // Computer Player
        if (p.getType() == 0) {
          Space previous = p.getCurrentSpace();
          p.setCurrentSpace(previous.getNeighbor().get(0));
          Space currentspace = p.getCurrentSpace();
          sb.append(String.format("Computer Player %s move to %s\n", p.getNum(),
              currentspace.toString()));
          // Computer Player move pet
          // sb.append(String.format("Computer Player %s make %s", p.getNum(), movePet(i +
          // 1)));
          // Computer Player pick up item
          for (Item a : currentspace.getItems()) {
            if (a.getDamage() > 0) {
              sb.append(playerPickupItem(p, a));
            }
          }

        } else {
          // Human Player
          // set the moved position to player
          p.setCurrentSpace(spaces.get(i));
          sb.append(String.format("Player %s Move to %s\n", p.getNum(),
              p.getCurrentSpace().getRoomname()));

        }
      }
    }

    // increment turn and run after every single move
    this.currentTurn += 1;
    if (this.currentTurn == this.getPlayerTotalNum()) {
      this.currentTurn = 0;
      this.currentRurn += 1;
      sb.append(moveTc(currentRurn));
      sb.append(movePet(currentRurn));
    }

    return sb.toString();

  }

  @Override
  public String playerAttack(Player p, int itemindex) {
    if (p == null) {
      throw new IllegalArgumentException("Model playerAttack Player cannot be null!");
    }
    if (itemindex < 0) {
      throw new IllegalArgumentException("Model playerAttack itemindex cannot be less than 0");
    }

    StringBuilder sb = new StringBuilder();
    // boolean check to see if can attack
    int canattack = 1; // ok to attack in default
    for (Player pl : players) {
      // if cansee then not ok to attack
      if (p.canSee(pl, this.pet) && p.getNum() != pl.getNum()) {
        canattack = -1; // not ok to attack
        if (p.getType() == 1) {
          sb.append(String.format("Player %s %s can see you. Cannot attack!\n", pl.getNum(),
              pl.getName()));
        }
      }
    }
    // if target character in the same room can attack
    if (canattack == 1
        && p.getCurrentSpace().getRoomindex() == tc.getCurrentSpace().getRoomindex()) {
      canattack = 1;
    } else {
      canattack = -1;
      if (p.getType() == 1
          && p.getCurrentSpace().getRoomindex() != tc.getCurrentSpace().getRoomindex()) {
        sb.append("Cannot attack! You are not at the same room as TargetCharacter!\n");
      }
    }
    // boolean check can attack end
    if (canattack == 1) {

      // human player Computer Player
      if (p.getCarrieditems().isEmpty()) {
        tc.decreaseHealth(1);
        sb.append(String.format("Player %s %s poking him in the eye caused 1 damege to %s.\n",
            p.getNum(), p.getName(), this.tc.getName()));
        sb.append(String.format("TargetCharacter %s has %s health left!\n", this.tc.getName(),
            this.tc.getHealth()));
      } else {
        if (p.getType() == 1) {
          // Human Player
          for (Item i : p.getCarrieditems()) {
            if (itemindex == i.getItemIndex()) {
              tc.decreaseHealth(i.getDamage());
              sb.append(String.format("Player %s %s using %s caused %s damege to %s.\n", p.getNum(),
                  p.getName(), i.getName(), i.getDamage(), this.tc.getName()));
              p.removecarrieditems(i);
              sb.append(String.format("After attacked Current player has item %s\n",
                  p.getCarrieditems().toString()));
              sb.append(String.format("TargetCharacter %s has %s health left!\n", this.tc.getName(),
                  this.tc.getHealth()));
              break;

            } else {
              sb.append("No such item in bag please try again!");
              break;
            }
          }
        } else if (p.getType() == 0) {
          // Computer Player
          Item maxelement = Collections.max(p.getCarrieditems(),
              Comparator.comparingInt(Item::getDamage));
          // retrieve the maximum damage
          int maxDamage = maxelement.getDamage();
          tc.decreaseHealth(maxDamage);
          sb.append(String.format("Player %s %s using %s caused %s damege to %s.\n", p.getNum(),
              p.getName(), maxelement.getName(), maxelement.getDamage(), this.tc.getName()));
          sb.append(String.format("TargetCharacter %s has %s health left!\n", this.tc.getName(),
              this.tc.getHealth()));
        }
      }
    }
    // increment turn and run after every single move
    this.currentTurn += 1;
    if (this.currentTurn == this.getPlayerTotalNum()) {
      this.currentTurn = 0;
      this.currentRurn += 1;
      sb.append(moveTc(currentRurn));
      sb.append(movePet(currentRurn));
    }

    return sb.toString();

  }

  @Override
  public String playerPickupItem(Player p, Item it) {
    if (p == null) {
      throw new IllegalArgumentException("Model playerPickupItem Player cannot be null!");
    }
    if (it == null) {
      throw new IllegalArgumentException("Model playerPickupItem Item cannot be less than 0");
    }
    StringBuilder a = new StringBuilder();
    Space currentspace = p.getCurrentSpace();
    if (!currentspace.getItems().isEmpty() && (p.getCarrieditems().size() < carrieditemnum)) {
      a.append(String.format("Current space has items: %s \n", currentspace.toString()));
      p.addcarrieditems(it);
      currentspace.removeItemtoSpace(it);
      a.append(String.format("Player %s %s pickup item: %s which has %d damage\n", p.getName(),
          p.getNum(), it.getName(), it.getDamage()));
      a.append(
          String.format("After picking Current space has %s\n", currentspace.displaySpaceItems()));
    } else if (p.getCarrieditems().size() >= carrieditemnum) {
      a.append(String.format("Player %s %s bag for items is full now!\n", p.getName(), p.getNum(),
          it.getName(), it.getDamage()));
    }
    return a.toString();
  }

  @Override
  public String playerLookAround(int playerindex) {
    if (playerindex < 0) {
      throw new IllegalArgumentException("Model playerAttack itemindex cannot be less than 0");
    }
    StringBuilder log = new StringBuilder();
    Player p = players.get(playerindex);

    log.append(String.format("Player %d can go: %s\n", p.getNum(), p.getCurrentSpace()
        .getNeighborDisplayWithInvisible(this.pet.getCurrentSpace().getRoomindex())));
    if (p.getCurrentSpace().getRoomindex() == tc.getCurrentSpace().getRoomindex()) {
      log.append(String.format("TargetCharacter %s\n", tc.toString()));
    }
    if (p.getCurrentSpace().getRoomindex() == pet.getCurrentSpace().getRoomindex()) {
      log.append(String.format("Pet %s\n", pet.toString()));
    }

    for (Player pl : players) {
      if (pl.getCurrentSpace().getRoomindex() == p.getCurrentSpace().getRoomindex()
          && pl.getNum() != playerindex) {
        log.append(
            String.format("Player %s %s  carried items: %s health: %s is also in this space.\n",
                pl.getNum(), pl.getName(), pl.getCarrieditems().toString(), pl.getHealth()));
      }

      if (isNeighbor(pl.getCurrentSpace().getLocation(), p.getCurrentSpace().getLocation())
          && (pl.getCurrentSpace().getRoomindex() != p.getCurrentSpace().getRoomindex())) {
        log.append(String.format("Player %s %s  carried items: %s health: %s is in neighbor %s \n",
            pl.getNum(), pl.getName(), pl.getCarrieditems().toString(), pl.getHealth(),
            pl.getCurrentSpace()
                .getcurrentSpaceDisplayWithInvisible(this.pet.getCurrentSpace().getRoomindex())));

      }

    }
    if (isNeighbor(tc.getCurrentSpace().getLocation(), p.getCurrentSpace().getLocation())
        && (tc.getCurrentSpace().getRoomindex() != p.getCurrentSpace().getRoomindex())) {
      log.append(String.format("TargetCharacter %s\n", tc.toString()));
    }

    if (isNeighbor(pet.getCurrentSpace().getLocation(), p.getCurrentSpace().getLocation())
        && (pet.getCurrentSpace().getRoomindex() != p.getCurrentSpace().getRoomindex())) {
      log.append(String.format("Pet %s\n", pet.toString()));
    }

    return log.toString();
  }

  /**
   * helper function to determine if two space neighbors.
   * 
   * @param r1 location of the space
   * @param r2 location of another space
   * @return boolean True if they are
   */
  private boolean isNeighbor(Rectangle r1, Rectangle r2) {
    if (r1 == null || r2 == null) {
      throw new IllegalArgumentException("Model isNeighbor Rectangle cannot be null!");
    }
    int x1 = r1.x;
    int y1 = r1.y;
    int x2 = r1.x + r1.width;
    int y2 = r1.y + r1.height;
    int x3 = r2.x;
    int y3 = r2.y;
    int x4 = r2.x + r2.width;
    int y4 = r2.y + r2.height;
    if ((Math.max(x1, x3) <= Math.min(x2, x4)) && (Math.max(y1, y3) <= Math.min(y2, y4))) {
      return true;
    }
    return false;
  }

  /**
   * helper function to determine if two space are overlapped.
   * 
   * @param r1 location of the space
   * @param r2 location of another space
   * @return boolean True if they are
   */
  private boolean isOverlapped(Rectangle r1, Rectangle r2) {
    if (r1 == null || r2 == null) {
      throw new IllegalArgumentException("Model isNeighbor Rectangle cannot be null!");
    }
    int x1 = r1.x;
    int y1 = r1.y;
    int x2 = r1.x + r1.width;
    int y2 = r1.y + r1.height;
    int x3 = r2.x;
    int y3 = r2.y;
    int x4 = r2.x + r2.width;
    int y4 = r2.y + r2.height;
    if ((Math.max(x1, x3) < Math.min(x2, x4)) && (Math.max(y1, y3) < Math.min(y2, y4))) {
      return true;
    }
    return false;
  }

  private List<Space> createNeighbor(Space input) {
    if (input == null) {
      throw new IllegalArgumentException("createNeighbor input Space cannot be null!");
    }
    List<Space> neighbors = new ArrayList<Space>();
    for (Space sp : spaces) {
      if (sp != input && isNeighbor(input.getLocation(), sp.getLocation())) {
        neighbors.add(sp);
      }
    }
    return neighbors;
  }

  @Override
  public BufferedImage getBuffImage() {

    // create a BufferedImage for mentioned image types.
    int imageWidth = 700;
    int imageHeight = 700;
    BufferedImage buffImg = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
    // create a graphics2d object which can be used to draw into the buffered image
    Graphics2D g2d = buffImg.createGraphics();

    // draw a string
    g2d.setColor(Color.yellow);
    g2d.setFont(new Font("TimesRoman", Font.BOLD, 40));
    // g2d.drawString(this.myworldname, 20, 100);

    for (Space a : spaces) {
      // loop variables
      Rectangle r1 = a.getLocation();
      String recRoomName = a.getRoomname();

      // draw scaled Version of rectangle object
      g2d.setColor(Color.YELLOW);
      g2d.drawRect((int) r1.getX(), (int) r1.getY(), (int) r1.getWidth(), (int) r1.getHeight());
      g2d.setFont(new Font("TimesRoman", Font.PLAIN, 12));
      g2d.drawString(recRoomName, ((int) r1.getX()), (int) r1.getY() + 20);
      if (tc.getCurrentSpace().getRoomindex() == a.getRoomindex()) {
        // draw targetcharacter
        g2d.drawImage(tc.getImageIcon().getImage(), ((int) r1.getX() + (int) r1.getWidth() - 30),
            (int) r1.getY(), null);
      }
      // drawPlayer
      int i = 0;
      if (!players.isEmpty()) {
        for (Player p : players) {
          if (p.getCurrentSpace() != null
              && p.getCurrentSpace().getRoomindex() == a.getRoomindex()) {
            g2d.setColor(Color.green);
            g2d.drawOval((int) r1.getX() + (20 * i), (int) r1.getY() + (int) r1.getHeight() - 20,
                20, 20);
            g2d.setFont(new Font("TimesRoman", Font.PLAIN, 15));
            g2d.drawString(Integer.toString(p.getNum()), (int) r1.getX() + (20 * i) + 5,
                (int) r1.getY() + (int) r1.getHeight() - 5);
            i++;
          }
        }
      }
    }

    return buffImg;

  }

  @Override
  public String saveImage() throws IOException {
    StringBuilder log = new StringBuilder();
    log.append("-------- Creating an image! -------\n");
    BufferedImage img = this.getBuffImage();
    // write the image file
    File f = new File("res/m2samplemap.png");
    try {
      ImageIO.write(img, "png", f);

    } catch (IOException e) {
      log.append("IOException");
      System.exit(0);
    }

    log.append(f.getAbsolutePath() + " created successfully!\n");
    return log.toString();
  }

  @Override
  public void readTxt(String arg1, String arg2) throws IllegalArgumentException {
    if (arg1 == null || arg2 == null) {
      throw new IllegalArgumentException(
          "[String Error in MyLuckyWordGameModel Class] args cannot be null!");
    }
    System.out.println("-------- Reading txt! -------");
    // Read the file using Scanner reader
    File textFile = new File(arg1);
    this.totalturnnum = Integer.valueOf(arg2);
    Scanner sr = null;
    try {
      sr = new Scanner(textFile);
    } catch (FileNotFoundException e1) {
      System.out.println("The text file is not found! [exit]");
      System.exit(0);

    }
    sr.nextInt();
    sr.nextInt();
    this.myworldname = sr.nextLine().strip();

    // read initial character info and save
    int health = sr.nextInt();
    String targetCharactername = sr.nextLine().strip();
    this.tc = new TargetCharacter(targetCharactername, health);

    // read initial petname info and save
    String petname = sr.nextLine().strip();
    this.pet = new Pet(petname);

    // Read total room num info
    int totalRoomsNum = sr.nextInt();
    this.totalroomnum = totalRoomsNum;
    if (totalRoomsNum < 2) {
      throw new IllegalArgumentException("Invalid total room num");
    }
    for (int i = 0; i < totalRoomsNum; i++) {
      int recCol = sr.nextInt();
      int recRow = sr.nextInt();
      int recRightCol = sr.nextInt();
      int recRightRow = sr.nextInt();

      // if right coordinate is equal to left corrdinate, the size is zero
      // so the initial rectangle size should be one
      int recLength = recRightRow - recRow + 1;
      int recWidth = recRightCol - recCol + 1;
      if (recRow < 0 || recCol < 0 || recRightRow < 0 || recRightCol < 0) {
        throw new IllegalArgumentException("Coordinate Cannot be zero!");

      }
      if (recRow > recRightRow || recCol > recRightCol) {
        throw new IllegalArgumentException(
            "Cannot draw the rectangle. Left top (row,col) is greater than Right bot!");
      }
      int scale = 17;
      int imagepos = 30;
      // create a rectangle object
      Rectangle r1 = new Rectangle(recRow * scale + imagepos, recCol * scale + imagepos,
          recLength * scale, recWidth * scale);
      // write rec info to space
      String recRoomName = sr.nextLine().strip();
      spaces.add(new Space(i, recRoomName, r1));
    }
    // throw error if two spaces are overlapped
    int secEle = 1;
    for (Space sp : spaces) {
      if (this.isOverlapped(sp.getLocation(), spaces.get(secEle).getLocation())) {
        throw new IllegalArgumentException("Two spaces overlapped, cannot create world");
      }
      secEle += 1;
      if (secEle > spaces.size() - 1) {
        break;
      }
    }

    // Read item info and save to a list
    this.moveTc(0);
    this.movePet(0);
    int totalItemNum = 0;
    if (sr.hasNextInt()) {
      totalItemNum = sr.nextInt();
      if (totalItemNum < 2) {
        throw new IllegalArgumentException("Invalid total item num");
      }
    }
    for (int i = 0; i < totalItemNum; i++) {
      int roomindex = sr.nextInt();
      int damage = sr.nextInt();
      String name = sr.nextLine().strip();
      items.add(new Item(i, name, damage, roomindex));
    }
    if (!items.isEmpty()) {
      for (Item it : items) {
        for (Space sp : spaces) {
          sp.addItemToSpace(it);
        }
      }
    }
    // read neightbor info and save
    for (Space sp : spaces) {
      sp.setNeighbor(this.createNeighbor(sp));
    }

    // close the scanner
    sr.close();
  }

  @Override
  public String addPlayer(Player p, Space sp) {
    if (p == null || sp == null) {
      throw new IllegalArgumentException(
          "[addPlayer Error in MyLuckyWordGameModel Class] p cannot be null!");
    }
    if (this.totalplayernum > 10) {
      return "Add Player failed. playernum >10\n";
    } else {
      players.add(p);
      // initial the space for player
      if (p.getType() == 1) {
        p.setCurrentSpace(sp);
        return String.format("Player %d %s successfully added!\n", p.getNum(), p.getName());
      } else {
        p.setCurrentSpace(sp);
        return String.format("Computer Player %d %s successfully added!\n", p.getNum(),
            p.getName());
      }
    }
  }

  @Override
  public Player getPlayer(int playerindex) {
    if (playerindex < 0) {
      throw new IllegalArgumentException(
          "[getPlayer Error in MyLuckyWordGameModel Class] playerindex cannot be less than 0");
    }
    return players.get(playerindex);

  }

  @Override
  public int getPlayerTotalNum() {
    this.totalplayernum = players.size();
    return this.totalplayernum;

  }

  @Override
  public String displayTc() {
    StringBuilder sb = new StringBuilder();
    sb.append(tc.toString());
    return sb.toString();
  }

  @Override
  public String displayAllPlayerInWorld() {
    StringBuilder sb = new StringBuilder();
    for (Player pl : players) {
      sb.append(pl.toString());
      sb.append(" can go: ");
      sb.append(pl.getCurrentSpace().getNeighborDisplay());
      sb.append("\n");
    }
    return sb.toString();
  }
}
