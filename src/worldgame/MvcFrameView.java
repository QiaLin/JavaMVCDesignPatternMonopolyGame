package worldgame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Implementation of the Frame of the view.
 */
public class MvcFrameView extends JFrame implements MvcView {
  private static final long serialVersionUID = -2179965453492637485L;

  private JLabel jl;
  private JLabel gamerules;
  private JLabel gamelog;
  private JPanel pic;
  private ReadOnlyMyLuckyWorldGame model;
  private JMenu jmenu;
  private JMenu jmenu2;
  private JMenuItem jm00;
  private JMenuItem jm01;
  private JMenuItem jm02;
  private JMenuItem jm03;
  private JMenuItem jm20;
  private JMenuItem jm21;

  /**
   * MvcFrameView Implementation Constructor.
   * 
   * @param caption the caption to use
   * @param m       Read-Only Model MyLuckyWorldGame
   */
  public MvcFrameView(String caption, ReadOnlyMyLuckyWorldGame m) {
    super(caption);
    this.model = m;

    // Welcome word
    jl = new JLabel("Welcome to Lucky World Game!", JLabel.CENTER);
    jl.setPreferredSize(new Dimension(680, 30));
    jl.setFont(new Font("Arial", Font.BOLD, 24));
    jl.setForeground(Color.yellow);
    jl.setBounds(0, 0, 680, 40);
    this.add(jl);

    // Welcome word

    gamelog = new JLabel("Game log: ", JLabel.LEFT);
    gamelog.setPreferredSize(new Dimension(680, 40));
    gamelog.setFont(new Font("Arial", Font.BOLD, 20));
    gamelog.setForeground(Color.yellow);
    gamelog.setBounds(0, 71, 680, 30);
    this.add(gamelog);

    // gamerules
    StringBuilder sb = new StringBuilder();
    sb.append("<html>Game Rules<br>");
    sb.append("Click the menu Begin Game to begin game<br>");
    sb.append("Click the board to move<br>");
    sb.append("Press \"l\" to lookaround current space<br>");
    sb.append("Press \"k\" to attack the Target Character<br>");
    sb.append("Press \"p\" to pick item to current space<br>");
    sb.append("Kill the TargetCharacter with devil pic to win<br>");
    sb.append("</html>");
    gamerules = new JLabel(sb.toString(), JLabel.LEFT);
    gamerules.setPreferredSize(new Dimension(400, 200));
    gamerules.setFont(new Font("Arial", Font.BOLD, 15));
    gamerules.setForeground(Color.yellow);
    gamerules.setBounds(600, 71, 400, 200);
    this.add(gamerules);
    // Menubar
    JMenuBar jb = new JMenuBar();
    this.add(jb);
    jb.setBounds(0, 40, 1000, 30);
    jb.setBackground(Color.decode("#65991a"));
    jmenu = new JMenu("Begin Game");
    jmenu.setForeground(Color.white);
    jb.add(jmenu);
    jm00 = new JMenuItem("Add a Human Player");
    jm01 = new JMenuItem("Add a Computer Player");
    jm02 = new JMenuItem("Exit Game");
    jm03 = new JMenuItem("Start Game");

    jmenu.add(jm00);
    jmenu.add(jm01);
    jmenu.add(jm02);
    jmenu.add(jm03);
    jmenu2 = new JMenu("Load New File");
    jmenu2.setForeground(Color.white);
    jb.add(jmenu2);
    jm20 = new JMenuItem("Restart with this map");
    jm21 = new JMenuItem("Restart with your choosed map");
    jmenu2.add(jm20);
    jmenu2.add(jm21);

    // load the image to a Jpaenl
    pic = new ReadOnlyViewPanel(model);
    pic.setPreferredSize(new Dimension(700, 1000));
    // pic.setBounds(0, 100, 700, 1000);
    pic.setBackground(Color.BLACK);

    // add scrollbar
    JScrollPane scrollPane = new JScrollPane(pic);
    scrollPane.setBounds(0, 100, 600, 680);
    this.add(scrollPane);

    this.getContentPane().setBackground(Color.BLACK);
    this.setPreferredSize(new Dimension(1000, 900));
    this.setLayout(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    pack();
    setLocationRelativeTo(null);
    setVisible(true);

  }

  private String performAttackInput() {
    StringBuilder sb = new StringBuilder();
    if (!model.getPlayer(model.getCurrentTurn()).getCarrieditems().isEmpty()) {
      sb.append("You can use following to attack:");
      for (Item i : model.getPlayer(model.getCurrentTurn()).getCarrieditems()) {
        sb.append(i.toString());
        sb.append("\n");
      }
      sb.append("Enter Item index to Attack:");
      String result = JOptionPane.showInputDialog(this, sb.toString());
      return result;
    } else {
      return "0";
    }
  }

  private String[] performAddPlayerInput() {

    String result = JOptionPane.showInputDialog(this, "Enter Player name:");
    String result1 = JOptionPane.showInputDialog(this,
        "Enter Initial space index(only less than space number is allowed):");
    String[] a = { result, result1 };
    if (result == null || result1 == null) {
      changelog("Add Player Failed");

    }
    return a;
  }

  private String performPickUpInput() {
    StringBuilder sb = new StringBuilder();
    sb.append(model.getPlayer(model.getCurrentTurn()).getCurrentSpace().displaySpaceItems());
    sb.append(model.getPlayer(model.getCurrentTurn()).getCarrieditems().toString());
    sb.append("Enter Item Index:");

    String result = JOptionPane.showInputDialog(this, sb.toString());
    if (result == null) {
      changelog("Pick Failed");

    }
    return result;
  }

  @Override
  public int showConfirmationDiaglog(String message, String title) {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("[Run: %s Turn: %s ] ", model.getCurrentRun(), model.getCurrentTurn()));
    sb.append(message);
    int answer = JOptionPane.showConfirmDialog(this, sb.toString(), title,
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (answer == JOptionPane.YES_OPTION) {
      return 1;
    } else {
      return 0;
    }

  }

  @Override
  public void showDialog(String message) {
    StringBuilder sb = new StringBuilder();
    sb.append(message);

    JOptionPane.showConfirmDialog(this, sb.toString(), "Game log", JOptionPane.DEFAULT_OPTION);

  }

  @Override
  public void changelog(String currentlog) {
    StringBuilder sb = new StringBuilder();
    sb.append("<html>Game log: ");

    sb.append(currentlog);
    sb.append("</html>");
    gamelog.setText(sb.toString());

  }

  /**
   * Accept the set of callbacks from the controller, and hook up as needed to
   * various things in this view.
   * 
   * @param f the set of feature callbacks as a Features object
   */
  @Override
  public void setFeatures(Features f) {
    // add HumanPlayer listener
    jm00.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String[] a = performAddPlayerInput();
        String log = f.processAddPlayerInput(a);
        changelog(log);
      }

    });

    // add ComputerPlayer listener
    jm01.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String log = f.processAddComputerPlayerInput();
        changelog(log);
      }

    });

    // end game
    jm02.addActionListener(l -> f.exitProgram());

    // init game
    jm03.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        String log = f.handleInitGame();
        changelog(log);
        refresh();
      }

    });

    // reload game
    jm20.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        f.restart();
      }

    });

    // reload game
    jm21.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser("res/");
        fc.showOpenDialog(null);
        File f1 = fc.getSelectedFile();
        if (f1 != null) {
          String filepath = f1.getPath();
          f.changemap(filepath);
        }

      }
    });

    // move game
    pic.addMouseListener(new MouseListener() {

      @Override
      public void mouseClicked(MouseEvent e) {

        int yesorno = 0;
        int roomnum = 0;
        int playernum = 0;
        playernum = model.getCurrentTurn();
        if (!model.isEnd() && !model.isWin()) {
          for (int i = 0; i < model.getTotalroomnum(); i++) {
            if (model.getSpecificSpace(i).getLocation().contains(e.getPoint())) {
              if (model.getInitGame() == 1) {
                String message = String.format("Do %s %s want to move %s?",
                    model.getPlayer(playernum).getNum(), model.getPlayer(playernum).getName(),
                    model.getSpecificSpace(i).getRoomname());
                yesorno = showConfirmationDiaglog(message, "move");
                roomnum = model.getSpecificSpace(i).getRoomindex();
              } else {
                String message = String.format("Do you want to move %s?",
                    model.getSpecificSpace(i).getRoomname());
                showConfirmationDiaglog(message, "move");
                showDialog("Game hasn't start, cannot move!Click Begin Game to start Game!");

              }
            }
          }
          if (yesorno == 1) {
            String log = f.handleMove(playernum, roomnum);
            showDialog(log);
            changelog(String.format("Current Turn: %s,Current Run: %s",
                model.getPlayer(model.getCurrentTurn()).getName(), model.getCurrentRun()));
            refresh();

          }

        } else if (model.isEnd()) {
          changelog("Run out of Runs! All Players lose! Doctor Lucky Escape!");
        } else if (model.isWin()) {
          changelog(String.format("Player %s %s Wins! Doctor Lucky Died",
              model.getPlayer(model.getCurrentTurn()).getNum(),
              model.getPlayer(model.getCurrentTurn()).getName()));

        }
      }

      @Override
      public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

      }

      @Override
      public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

      }

      @Override
      public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

      }

      @Override
      public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

      }

    });
    this.addKeyListener(new KeyListener() {

      @Override
      public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'p' && model.getInitGame() == 1 && !model.isEnd() && !model.isWin()) {
          String itemindex = performPickUpInput();
          String log = f.processPickUpInput(itemindex);
          showDialog(log);
        }
        if (e.getKeyChar() == 'k' && model.getInitGame() == 1 && !model.isEnd() && !model.isWin()) {
          String itemindex = performAttackInput();
          String log = f.processAttackInput(itemindex);
          showDialog(log);
          if (model.isWin()) {
            changelog(String.format("Player %s %s Wins! Doctor Lucky Died",
                model.getPlayer(model.getCurrentTurn()).getNum(),
                model.getPlayer(model.getCurrentTurn()).getName()));
          }

          if (model.isEnd()) {
            changelog("Run out of Runs! All Players lose! Doctor Lucky Escape!");
          }
        }
        if (e.getKeyChar() == 'l' && model.getInitGame() == 1 && !model.isEnd() && !model.isWin()) {
          String log1 = f.processplayerLookAround(model.getCurrentTurn());
          showDialog(log1);
        }

      }

      @Override
      public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

      }

      @Override
      public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

      }

    });
  }

  @Override
  public void refresh() {
    this.repaint();
    pic.repaint();

  }

}
