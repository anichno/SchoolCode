package pex4guibuilder;


/**
 * <put a program description here>
 * 
 * @author anichno
 * 
 * @version 1.0 - Nov 20, 2011 at 11:26:32 PM
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import pex4guibuilder.Connect4;
import pex4guibuilder.DrawingPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import pex4guibuilder.PEX4GuiBuilderView;

public class guiGame {
  //static Connect4 game = new Connect4(6, 7, 1, 1);
  private static javax.swing.JPanel BoardSizeFrame;
    private static javax.swing.JPanel FirstPlayerFrame;
    private static javax.swing.JPanel GameFrame;
    private static javax.swing.JPanel GameModeFrame;
    private static javax.swing.JPanel SettingsFrame;
    private static javax.swing.JButton btnStartGame;
    private static javax.swing.ButtonGroup btngrpFirstPlayer;
    private static javax.swing.ButtonGroup btngrpGameMode;
    private static javax.swing.JPanel gamePanel;
    private static javax.swing.JLabel lblBoardSize;
    private static javax.swing.JLabel lblColumns;
    private static javax.swing.JLabel lblFirstPlayer;
    private static javax.swing.JLabel lblGameMode;
    private static javax.swing.JLabel lblRows;
    private static javax.swing.JRadioButton rbtnComp;
    private static javax.swing.JRadioButton rbtnLocal;
    private static javax.swing.JRadioButton rbtnRed;
    private static javax.swing.JRadioButton rbtnRemote;
    private static javax.swing.JRadioButton rbtnYellow;
    private static javax.swing.JTextField txtColumns;
    private static javax.swing.JTextField txtRows;
    private static Connect4 game = null;

  public static void main(String[] args) {
    initComponents();
    playGame();
  }
  public static void playGame() {
    game.setRedName("Red Leader");
    game.setYellowName("Gold Leader");

    DrawingPanel panel = new DrawingPanel(600, 600);
    panel.setBackground(Color.blue);
    Graphics2D g = (Graphics2D) panel.getGraphics();
    drawGrid(g);
    Font newFont = new Font("Courier New", Font.BOLD, 40);
    g.setFont(newFont);
    g.setColor(Color.red);
    g.drawString("Turn: " + game.whoseTurn(), 20, 40);
    panel.copyGraphicsToScreen();
    while (!game.redWon() && !game.yellowWon() && !game.isTie()) {
      g.setColor(Color.blue);
      g.fillRect(0, 0, 600, 40);
      g.setColor(Color.red);
      if (game.whoseTurn().equals(game.getRedName()) || game.getGameMode() == 1) {
        game.nextMove(getMove(panel));
      } else {
        game.nextMove();
      }
      drawMove(g);
      g.drawString("Turn: " + game.whoseTurn(), 20, 40);
      panel.copyGraphicsToScreen();
    }
    g.setColor(Color.blue);
      g.fillRect(0, 0, 600, 40);
      g.setColor(Color.red);

    if (game.redWon()) {
      g.drawString("Winner: " + game.getRedName(), 20, 40);
    } else {
      if (game.yellowWon()) {
        g.drawString("Winner: " + game.getYellowName(), 20, 40);
      } else {
        g.drawString("Tie Game", 20, 40);
        System.out.println("Tie game.");
      }
    }
    panel.copyGraphicsToScreen();
  }

  public static int getMove(DrawingPanel panel) {
    int xClickLocation = 0;
    while (true) {
      if (panel.isMouseButtonDown(DrawingPanel.LEFT_BUTTON)) {
        while (panel.isMouseButtonDown(DrawingPanel.LEFT_BUTTON)) {
          panel.sleep(50);
        }
        xClickLocation = panel.getMouseX();
        for (int columns = 0, colSpacer = (600 / game.getColumns()) / 2; columns < game.getColumns(); columns++, colSpacer += 600 / (game.getColumns() + 1)) {
          if (xClickLocation > colSpacer && xClickLocation < colSpacer + 75) {
            return columns + 1;
          }
        }
      }
    }
  }

  public static void drawGrid(Graphics2D g) {
    Color oldColor = g.getColor();
    g.setColor(Color.white);
    for (int rows = 0, rowSpacer = (600 / (game.getRows())) / 2; rows < game.getRows(); rows++, rowSpacer += 600 / (game.getColumns() + 1)) {
      for (int columns = 0, colSpacer = (600 / game.getColumns()) / 2; columns < game.getColumns(); columns++, colSpacer += 600 / (game.getColumns() + 1)) {
        g.fillOval(colSpacer, rowSpacer, 75, 75);
      }
    }
    g.setColor(oldColor);
  }

  public static void drawMove(Graphics2D g) {
    int[] lastMove = game.getLastMove();
    int xPos = lastMove[1] * (600 / (game.getColumns() + 1)) + (600 / game.getColumns()) / 2;
    int yPos = lastMove[0] * (600 / (game.getColumns() + 1)) + (600 / game.getRows()) / 2;
    Color oldColor = g.getColor();

    if (game.whoseTurn().equals("Red") && game.RedisAt(lastMove[0], lastMove[1])) {
      g.setColor(Color.red);
    } else if (game.YellowisAt(lastMove[0], lastMove[1])) {
      g.setColor(Color.yellow);
    }

    g.fillOval(xPos, yPos, 75, 75);
    g.setColor(oldColor);
  }
  @Action
  public static void startGame() {
    int gameMode = 0;
    if (rbtnLocal.isSelected()) {
      gameMode = 1;
    }
    else if (rbtnRemote.isSelected()) {
      gameMode = 2;
    }
    else if (rbtnComp.isSelected()) {
      gameMode = 3;
    }
    int rows = Integer.parseInt(txtRows.getText());
    int columns = Integer.parseInt(txtColumns.getText());
    int firstPlayer = 0;
    if (rbtnRed.isSelected()) {
      firstPlayer = 1;
    }
    else if (rbtnYellow.isSelected()) {
      firstPlayer = 2;
    }
    game = new Connect4(rows,columns,gameMode,firstPlayer);
//    SettingsFrame.setVisible(false);
//    setComponent(GameFrame);
    playGame();
  }

  @Action
  public static void hideInvalidFields() {
    lblFirstPlayer.setEnabled(false);
    rbtnRed.setEnabled(false);
    rbtnYellow.setEnabled(false);
  }

  @Action
  public static void showValidFields() {
    lblFirstPlayer.setEnabled(true);
    rbtnRed.setEnabled(true);
    rbtnYellow.setEnabled(true);
  }
  
  private static void initComponents() {

        SettingsFrame = new javax.swing.JPanel();
        GameModeFrame = new javax.swing.JPanel();
        lblGameMode = new javax.swing.JLabel();
        rbtnLocal = new javax.swing.JRadioButton();
        rbtnRemote = new javax.swing.JRadioButton();
        rbtnComp = new javax.swing.JRadioButton();
        BoardSizeFrame = new javax.swing.JPanel();
        lblBoardSize = new javax.swing.JLabel();
        txtColumns = new javax.swing.JTextField();
        txtRows = new javax.swing.JTextField();
        lblColumns = new javax.swing.JLabel();
        lblRows = new javax.swing.JLabel();
        FirstPlayerFrame = new javax.swing.JPanel();
        lblFirstPlayer = new javax.swing.JLabel();
        rbtnRed = new javax.swing.JRadioButton();
        rbtnYellow = new javax.swing.JRadioButton();
        btnStartGame = new javax.swing.JButton();
        btngrpGameMode = new javax.swing.ButtonGroup();
        btngrpFirstPlayer = new javax.swing.ButtonGroup();
        GameFrame = new javax.swing.JPanel();
        gamePanel = new javax.swing.JPanel();

        SettingsFrame.setName("SettingsFrame"); // NOI18N
        SettingsFrame.setPreferredSize(new java.awt.Dimension(600, 600));

        GameModeFrame.setName("GameModeFrame"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(pex4guibuilder.PEX4GuiBuilderApp.class).getContext().getResourceMap(PEX4GuiBuilderView.class);
        lblGameMode.setText(resourceMap.getString("lblGameMode.text")); // NOI18N
        lblGameMode.setName("lblGameMode"); // NOI18N

        //javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(pex4guibuilder.PEX4GuiBuilderApp.class).getContext().getActionMap(PEX4GuiBuilderView.class, this);
        rbtnLocal.setAction(actionMap.get("showValidFields")); // NOI18N
        btngrpGameMode.add(rbtnLocal);
        rbtnLocal.setSelected(true);
        rbtnLocal.setText(resourceMap.getString("rbtnLocal.text")); // NOI18N
        rbtnLocal.setActionCommand(resourceMap.getString("rbtnLocal.actionCommand")); // NOI18N
        rbtnLocal.setName("rbtnLocal"); // NOI18N

        //rbtnRemote.setAction(actionMap.get("hideInvalidFields")); // NOI18N
        rbtnRemote.setAction(hideInvalidFields());
        btngrpGameMode.add(rbtnRemote);
        rbtnRemote.setText(resourceMap.getString("rbtnRemote.text")); // NOI18N
        rbtnRemote.setName("rbtnRemote"); // NOI18N

        btngrpGameMode.add(rbtnComp);
        rbtnComp.setText(resourceMap.getString("rbtnComp.text")); // NOI18N
        rbtnComp.setName("rbtnComp"); // NOI18N

        javax.swing.GroupLayout GameModeFrameLayout = new javax.swing.GroupLayout(GameModeFrame);
        GameModeFrame.setLayout(GameModeFrameLayout);
        GameModeFrameLayout.setHorizontalGroup(
            GameModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GameModeFrameLayout.createSequentialGroup()
                .addComponent(lblGameMode)
                .addContainerGap(75, Short.MAX_VALUE))
            .addGroup(GameModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(GameModeFrameLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(GameModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(rbtnComp)
                        .addComponent(rbtnLocal)
                        .addComponent(rbtnRemote))
                    .addContainerGap(14, Short.MAX_VALUE)))
        );
        GameModeFrameLayout.setVerticalGroup(
            GameModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GameModeFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGameMode)
                .addContainerGap(94, Short.MAX_VALUE))
            .addGroup(GameModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(GameModeFrameLayout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(rbtnLocal)
                    .addGap(6, 6, 6)
                    .addComponent(rbtnRemote)
                    .addGap(5, 5, 5)
                    .addComponent(rbtnComp)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        BoardSizeFrame.setName("BoardSizeFrame"); // NOI18N

        lblBoardSize.setText(resourceMap.getString("lblBoardSize.text")); // NOI18N
        lblBoardSize.setName("lblBoardSize"); // NOI18N

        txtColumns.setText(resourceMap.getString("txtColumns.text")); // NOI18N
        txtColumns.setName("txtColumns"); // NOI18N
        txtColumns.setPreferredSize(new java.awt.Dimension(20, 20));

        txtRows.setText(resourceMap.getString("txtRows.text")); // NOI18N
        txtRows.setName("txtRows"); // NOI18N
        txtRows.setPreferredSize(new java.awt.Dimension(20, 20));

        lblColumns.setText(resourceMap.getString("lblColumns.text")); // NOI18N
        lblColumns.setName("lblColumns"); // NOI18N

        lblRows.setText(resourceMap.getString("lblRows.text")); // NOI18N
        lblRows.setName("lblRows"); // NOI18N

        javax.swing.GroupLayout BoardSizeFrameLayout = new javax.swing.GroupLayout(BoardSizeFrame);
        BoardSizeFrame.setLayout(BoardSizeFrameLayout);
        BoardSizeFrameLayout.setHorizontalGroup(
            BoardSizeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BoardSizeFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BoardSizeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBoardSize)
                    .addGroup(BoardSizeFrameLayout.createSequentialGroup()
                        .addGroup(BoardSizeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblColumns)
                            .addComponent(lblRows))
                        .addGap(18, 18, 18)
                        .addGroup(BoardSizeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRows, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtColumns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BoardSizeFrameLayout.setVerticalGroup(
            BoardSizeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BoardSizeFrameLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lblBoardSize)
                .addGap(18, 18, 18)
                .addGroup(BoardSizeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblColumns)
                    .addComponent(txtColumns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BoardSizeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblRows)
                    .addComponent(txtRows, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        FirstPlayerFrame.setName("FirstPlayerFrame"); // NOI18N

        lblFirstPlayer.setText(resourceMap.getString("lblFirstPlayer.text")); // NOI18N
        lblFirstPlayer.setName("lblFirstPlayer"); // NOI18N

        btngrpFirstPlayer.add(rbtnRed);
        rbtnRed.setSelected(true);
        rbtnRed.setText(resourceMap.getString("rbtnRed.text")); // NOI18N
        rbtnRed.setName("rbtnRed"); // NOI18N

        btngrpFirstPlayer.add(rbtnYellow);
        rbtnYellow.setText(resourceMap.getString("rbtnYellow.text")); // NOI18N
        rbtnYellow.setName("rbtnYellow"); // NOI18N

        javax.swing.GroupLayout FirstPlayerFrameLayout = new javax.swing.GroupLayout(FirstPlayerFrame);
        FirstPlayerFrame.setLayout(FirstPlayerFrameLayout);
        FirstPlayerFrameLayout.setHorizontalGroup(
            FirstPlayerFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FirstPlayerFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FirstPlayerFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFirstPlayer)
                    .addComponent(rbtnRed)
                    .addComponent(rbtnYellow))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        FirstPlayerFrameLayout.setVerticalGroup(
            FirstPlayerFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FirstPlayerFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFirstPlayer)
                .addGap(17, 17, 17)
                .addComponent(rbtnRed)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtnYellow)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnStartGame.setAction(actionMap.get("startGame")); // NOI18N
        btnStartGame.setText(resourceMap.getString("btnStartGame.text")); // NOI18N
        btnStartGame.setName("btnStartGame"); // NOI18N

        javax.swing.GroupLayout SettingsFrameLayout = new javax.swing.GroupLayout(SettingsFrame);
        SettingsFrame.setLayout(SettingsFrameLayout);
        SettingsFrameLayout.setHorizontalGroup(
            SettingsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SettingsFrameLayout.createSequentialGroup()
                .addContainerGap(258, Short.MAX_VALUE)
                .addGroup(SettingsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SettingsFrameLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnStartGame))
                    .addComponent(FirstPlayerFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BoardSizeFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GameModeFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(175, 175, 175))
        );
        SettingsFrameLayout.setVerticalGroup(
            SettingsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SettingsFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(GameModeFrame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BoardSizeFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FirstPlayerFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnStartGame)
                .addGap(231, 231, 231))
        );

        GameFrame.setName("GameFrame"); // NOI18N
        GameFrame.setPreferredSize(new java.awt.Dimension(600, 600));

        gamePanel.setName("gamePanel"); // NOI18N

        javax.swing.GroupLayout gamePanelLayout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 446, Short.MAX_VALUE)
        );
        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 395, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout GameFrameLayout = new javax.swing.GroupLayout(GameFrame);
        GameFrame.setLayout(GameFrameLayout);
        GameFrameLayout.setHorizontalGroup(
            GameFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GameFrameLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(gamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );
        GameFrameLayout.setVerticalGroup(
            GameFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GameFrameLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(gamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(123, Short.MAX_VALUE))
        );

        //setComponent(SettingsFrame);
    }
}