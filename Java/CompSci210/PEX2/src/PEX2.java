	
/****************************************************************************
*   Copyright (C) 2011 Anthony Canino                                       *
*                                                                           *
*   This program is free software: you can redistribute it and/or modify    *
*   it under the terms of the GNU General Public License as published by    *
*   the Free Software Foundation, either version 3 of the License, or       *
*   (at your option) any later version.                                     *
*                                                                           *
*   This program is distributed in the hope that it will be useful,         *
*   but WITHOUT ANY WARRANTY; without even the implied warranty of          *
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the           *
*   GNU General Public License for more details.                            *
*                                                                           *
*   You should have received a copy of the GNU General Public License       *
*   along with this program.  If not, see <http://www.gnu.org/licenses/>.   *
****************************************************************************/

/**
 * Whac-a-mole!!!
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 15, 2011 at 1:27:29 PM
 *
 * Documentation Statement: I received no help on this assignment other 
 * than the web resources listed by the functions in which the web resources were used
 */

//  http://www.pacdv.com/sounds/mechanical_sounds.html - Gunshot sound
//  http://www.youtube.com/watch?v=evdkCuvbtfw - Background music
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PEX2 {

  static BufferedImage moleHead = null;
  static BufferedImage crosshair = null;
  static BufferedImage titleScreen = null;
  static BufferedImage menuScreen = null;
  static BufferedImage nameScreen = null;
  static BufferedImage difficultyScreen = null;
  static BufferedImage highScoresScreen = null;
  static BufferedImage[] explosion = null;
  static Color dirtBrown = null;
  static int[] screen = null;
  static int screenX = 0;
  static int screenY = 0;
  public static int imgX = 0;
  public static int imgY = 0;

  public static void main(String[] args) throws InterruptedException, IOException {
    loadGame();
    titleScreen();
    mainMenu();
  }
  
  public static void loadGame() {
    //  LOAD ALL THE THINGS!!!
    
    //  Screen Size
    screen = getScreenSize();
    screenX = screen[0] - 10;
    screenY = screen[1] - 75;

    //  Load Images
    moleHead = loadImage("res/MoleHead.png");
    crosshair = loadImage("res/Crosshairs.png");
    titleScreen = loadImage("res/TitleScreen.png");
    menuScreen = loadImage("res/MenuScreen.png");
    nameScreen = loadImage("res/ChangeNameScreen.png");
    difficultyScreen = loadImage("res/DifficultyScreen.png");
    highScoresScreen = loadImage("res/HighScoresScreen.png");
    explosion = new BufferedImage[13];
    String path = null;
    for (int counter = 0; counter < 13; counter++) {
      path = "res/boom/boom" + Integer.toString(counter) + ".png";
      explosion[counter] = loadImage(path);
    }
    
    //  Resize Images
    crosshair = resize(crosshair, screen[0] / 60);
    titleScreen = resize(titleScreen, screenX, screenY);
    menuScreen = resize(menuScreen, screenX, screenY);
    nameScreen = resize(nameScreen, screenX, screenY);
    difficultyScreen = resize(difficultyScreen, screenX, screenY);
    highScoresScreen = resize(highScoresScreen, screenX, screenY);

    //  Set up graphics
    globalVars.panel = new DrawingPanel(screenX, screenY);
    dirtBrown = new Color(205, 133, 63, 255);
    globalVars.g = globalVars.panel.getGraphics();
    globalVars.panel.setWindowTitle("Super Molehunter III");
    Font textFont = new Font("Dialog", Font.BOLD, 40);
    globalVars.g.setFont(textFont);

    //  START ALL THE THREADS!!!
    new soundEffects().start();
    new backgroundMusic().start();
    new userCrosshair(crosshair).start();
  }

  public static void titleScreen() throws InterruptedException {
    for (boolean goToMenu = false; goToMenu != true;) {
      if (globalVars.panel.mouseClickHasOccurred(DrawingPanel.LEFT_BUTTON)) {
        goToMenu = true;
      }
      globalVars.g.drawImage(titleScreen, null, 0, 0);
      globalVars.panel.copyGraphicsToScreen();
      Thread.sleep(50);
    }
  }

  public static void mainMenu() throws InterruptedException, IOException {
    String name = "ANTHONY";
    int mouseY = 0;
    for (;;) {
      globalVars.g.drawImage(menuScreen, null, 0, 0);
      mouseY = globalVars.panel.getMouseY();

      //  Execute Menu Action
      if (globalVars.panel.isMouseButtonDown(DrawingPanel.LEFT_BUTTON)) {
        mouseY = globalVars.panel.getMouseY();
        
        //  Play the game
        if (mouseY >= screen[1] / 3 && 
            mouseY <= (screen[1] / 3) + screen[1] / 10) {
          int userscore = playGame(name);
          highScores(userscore, name);
        } else {
          //  Change username
          if (mouseY >= (screen[1] / 3) + screen[1] / 10 && 
              mouseY <= (screen[1] / 3) + 2 * (screen[1] / 11)) {
            name = changeName();
          } else {
            //  Exit the game
            if (mouseY >= (screen[1] / 3) + 2 * (screen[1] / 11) && 
                mouseY <= (screen[1] / 3) + 3 * (screen[1] / 11)) {
              globalVars.panel.closeWindow();
              globalVars.quit = true;
              System.exit(0);
            }
          }
        }
      }
      globalVars.panel.copyGraphicsToScreen();
      Thread.sleep(50);
    }
  }

  public static void highScores(int userscore, String name) throws IOException, InterruptedException {
    String[] scores = readScores();
    if (scores != null) {
      int[] updatedScores = new int[scores.length + 1];
      String[] updatedNames = new String[scores.length + 1];
      String[] tmpStr = new String[1];
      for (int counter = 0; counter < scores.length; counter++) {
        tmpStr = scores[counter].split("/");
        updatedScores[counter] = Integer.parseInt(tmpStr[0]);
        updatedNames[counter] = tmpStr[1];
      }
      
      updatedScores[scores.length] = userscore;
      updatedNames[scores.length] = name;

      // Reverse insertion sort
      for (int counter = 1; counter < updatedScores.length; counter++) {
        int tempInt = updatedScores[counter];
        String tempName = updatedNames[counter];
        int counter2 = counter - 1;
        for (; counter2 >= 0 && updatedScores[counter2] < tempInt; counter2--) {
          updatedScores[counter2 + 1] = updatedScores[counter2];
          updatedNames[counter2 + 1] = updatedNames[counter2];
        }
        updatedScores[counter2 + 1] = tempInt;
        updatedNames[counter2 + 1] = tempName;
      }
      
      writeScores(updatedScores, updatedNames);
      drawHighScores(updatedScores, updatedNames);
    } else {
      int[] updatedScores = new int[]{userscore};
      String[] updatedNames = new String[]{name};
      writeScores(updatedScores, updatedNames);
      drawHighScores(updatedScores, updatedNames);
    }
  }

  public static void drawHighScores(int[] scores, String[] names) throws InterruptedException {
    Thread.sleep(200);
    globalVars.g.setColor(Color.red);
    for (boolean returnMenu = false; returnMenu != true;) {
      if (globalVars.panel.mouseClickHasOccurred(DrawingPanel.LEFT_BUTTON)) {
        returnMenu = true;
      }
      globalVars.g.drawImage(highScoresScreen, null, 0, 0);
      for (int counter = 0; counter < Math.min(10,scores.length); counter++) {
        globalVars.g.drawString(String.format("%3d  %s", scores[counter], names[counter]), 
                                (int) (screen[0] / 2.25), (screen[1] / 4) + counter * (screen[1] / 20));
      }
      globalVars.panel.copyGraphicsToScreen();
      Thread.sleep(50);
    }
  }

  public static int playGame(String name) throws InterruptedException {
    int[] reqScore = new int[]{10, 20, 30};
    int score = 0;
    int[] delayTime = new int[]{400, 300, 200};
    int roundDelay = 0;
    Thread.sleep(200);
    int difficulty = difficultyMenu();

    int[] difScale = new int[]{10, 20, 30};
    BufferedImage moleHeadGame = resize(moleHead, screen[0] / difScale[difficulty]);
    imgX = moleHeadGame.getWidth();
    imgY = moleHeadGame.getHeight();
    
    //  Build Hole Grid
    int[][] gridNum = new int[][]{{6, 5}, {8, 7}, {9, 8}};
    int[] gridX = new int[gridNum[difficulty][0]];
    int[] gridY = new int[gridNum[difficulty][1]];
    for (int counter = 0, X = screen[0] / 13, spacer = screen[0] / 13; 
         counter < gridNum[difficulty][0]; counter++, X += imgX + spacer) {
      gridX[counter] = X;
    }
    for (int counter = 0, Y = screen[1] / 12, spacer = screen[1] / 12; 
         counter < gridNum[difficulty][1]; counter++, Y += imgY + spacer) {
      gridY[counter] = Y;
    }

    //  Play the game
    for (int misses = 0; misses < 3 && score < reqScore[difficulty];) {
      int newX = (int) (Math.random() * (gridNum[difficulty][0]));
      int newY = (int) (Math.random() * (gridNum[difficulty][1]));
      globalVars.panel.setBackground(dirtBrown);

      if (playRound(moleHeadGame, gridX[newX], 
                                  gridY[newY], gridX, gridY, difficulty, score, misses, name)) {
        score += difficulty + 1;
      } else {
        score -= 1;
        misses++;
      }

      roundDelay = (int) ((Math.random() * 10) * 50) + delayTime[difficulty];
      for (int counter = 0; counter < roundDelay / 10; counter++) {
        drawHoles(gridX, gridY, score, misses, name);
        drawPanels(gridX, gridY);
        globalVars.panel.copyGraphicsToScreen();
        Thread.sleep(10);
      }
    }
    
    drawFinalScore(score);
    Thread.sleep(500);
    globalVars.g.setColor(Color.black);
    
    //  Wait for mouse before going to highscores
    for (boolean gotoHighscores = false; gotoHighscores != true;) {
      if (globalVars.panel.mouseClickHasOccurred(DrawingPanel.LEFT_BUTTON)) {
        gotoHighscores = true;
      }
      drawFinalScore(score);
      Thread.sleep(50);
    }
    return score;
  }

  public static void drawFinalScore(int score) {
    globalVars.panel.setBackground(dirtBrown);
    globalVars.g.drawString("Game over", (int) (screen[0] / 2.5), screen[1] / 3);
    globalVars.g.drawString("Your score is: " + score, 
                            (int) (screen[0] / 2.5), screen[1] / 3 + screen[1] / 20);
    globalVars.panel.copyGraphicsToScreen();
  }

  public static int difficultyMenu() throws InterruptedException {
    int mouseY = 0;
    for (boolean difSelected = false; difSelected != true;) {
      globalVars.g.drawImage(difficultyScreen, null, 0, 0);

      if (globalVars.panel.isMouseButtonDown(DrawingPanel.LEFT_BUTTON)) {
        mouseY = globalVars.panel.getMouseY();

        //  Easy
        if (mouseY >= (screen[1] / 4) + screen[1] / 15 && 
            mouseY <= (screen[1] / 3) + screen[1] / 16) {
          return 0;
        } else {
          //  Medium
          if (mouseY >= (screen[1] / 3) + screen[1] / 16 && 
              mouseY <= (screen[1] / 3) + 2 * (screen[1] / 16)) {
            return 1;
          } else {
            //  Hard
            if (mouseY >= (screen[1] / 3) + 2 * (screen[1] / 16) && 
                mouseY <= (screen[1] / 3) + 3 * (screen[1] / 16)) {
              return 2;
            }
          }
        }
      }
      globalVars.panel.copyGraphicsToScreen();
      Thread.sleep(50);
    }
    return 0;
  }

  public static String changeName() throws InterruptedException {
    String name = "";
    int keyCode = 0;
    globalVars.g.drawImage(nameScreen, null, 0, 0);
    globalVars.panel.copyGraphicsToScreen();
    globalVars.g.setColor(Color.red);
    for (boolean nameEntered = false; nameEntered != true;) {
      keyCode = globalVars.panel.getKeyHitCode();
      if (keyCode == 10) {
        nameEntered = true;
      } else {
        if (keyCode >= 65) {
          name = name + (char) keyCode;
          globalVars.g.drawString(name, (int) (screen[0] / 2.5), screen[1] / 3 + (screen[1] / 11));
          globalVars.panel.copyGraphicsToScreen();
        }
      }
      Thread.sleep(50);
    }
    return name;
  }

  public static void drawHoles(int[] gridX, int[] gridY, int score, int misses, String name) {
    globalVars.panel.setBackground(dirtBrown);
    globalVars.g.setColor(Color.black);
    String curScore = String.format("Current Score:%5s%d", " ", score);
    String curMisses = String.format("Current Misses:%3s%d/3", " ", misses);
    globalVars.g.drawString(curScore, 10, screen[1] / 25);
    globalVars.g.drawString(curMisses, 10, (int) 2 * (screen[1] / 25));
    globalVars.g.drawString("Username: " + name, (int) (screen[0] / 2.75), screen[1] / 25);
    
    //  DRAW ALL THE HOLES!!!
    for (int counter = 0; counter < gridY.length; counter++) {
      for (int counter2 = 0; counter2 < gridX.length; counter2++) {
        globalVars.g.fillOval(gridX[counter2] - (int) (imgX * .30), 
                              gridY[counter] + (int) (imgX * .5), 
                              (int) (imgX * 1.5), (int) (imgX * .50));
      }
    }
  }

  public static void drawPanels(int[] gridX, int[] gridY) {
    globalVars.g.setColor(dirtBrown);
    
    //  Draw panels to hide the mole below the hole
    for (int counter = 0; counter < gridY.length; counter++) {
      for (int counter2 = 0; counter2 < gridX.length; counter2++) {
        globalVars.g.fillRect(gridX[counter2] - (int) (imgX * .30), 
                              gridY[counter] + (int) (imgX * .6), 
                              (int) (imgX * 1.5), (int) (imgX * .65));
      }
    }
  }

  public static boolean hitTest(int X, int Y, int moleY) throws InterruptedException {
    int mouseX = 0;
    int mouseY = 0;
    if (globalVars.panel.mouseClickHasOccurred(DrawingPanel.LEFT_BUTTON)) {
      mouseX = globalVars.panel.getMouseClickX(DrawingPanel.LEFT_BUTTON);
      mouseY = globalVars.panel.getMouseClickY(DrawingPanel.LEFT_BUTTON);
      if (mouseX >= X && mouseX <= X + imgX && mouseY >= moleY && mouseY <= Y + imgY) {
        return true;
      }
    }
    return false;
  }

  public static void hitMoleAnimation(int X, int Y, 
                                             int[] gridX, int[] gridY, int difficulty, 
                                             int score, int misses, 
                                             String name) throws InterruptedException {
    double[][] position = new double[][]{{.2, 0}, {.05, .75}, {-.25, 1.5}};
    for (int counter = 0; counter < 13; counter++) {
      drawHoles(gridX, gridY, score, misses, name);
      drawPanels(gridX, gridY);
      globalVars.g.drawImage(explosion[counter], null, 
                                                 X + (int) (imgX * position[difficulty][0]), 
                                                 Y - (int) (imgY * position[difficulty][1]));
      globalVars.panel.copyGraphicsToScreen();
      Thread.sleep(50);
    }
  }

  public static boolean playRound(BufferedImage moleHeadGame, 
                                  int X, int Y, int[] gridX, int[] gridY,  int difficulty, 
                                  int score, int misses, String name) throws InterruptedException {
    int[] delayTime = new int[]{600, 500, 400};

    //  Mole coming out of hole
    for (int moleY = Y + imgY; moleY > Y; moleY -= 4) {
      if (hitTest(X, Y, moleY)) {
        hitMoleAnimation(X, Y, gridX, gridY, difficulty, score, misses, name);
        return true;
      }
      drawHoles(gridX, gridY, score, misses, name);
      globalVars.g.drawImage(moleHeadGame, null, X, moleY);
      drawPanels(gridX, gridY);
      globalVars.panel.copyGraphicsToScreen();
      Thread.sleep(10);
    }

    //  Mole stationary
    int roundDelay = (int) ((Math.random() * 10) * 50) + delayTime[difficulty];
    for (int counter = 0; counter < roundDelay / 10; counter++) {
      if (hitTest(X, Y, Y)) {
        hitMoleAnimation(X, Y, gridX, gridY, difficulty, score, misses, name);
        return true;
      }
      drawHoles(gridX, gridY, score, misses, name);
      globalVars.g.drawImage(moleHeadGame, null, X, Y);
      drawPanels(gridX, gridY);
      globalVars.panel.copyGraphicsToScreen();
      Thread.sleep(10);
    }

    //  Mole going into hole
    for (int moleY = Y; moleY < Y + imgY; moleY += 4) {
      if (hitTest(X, Y, moleY)) {
        hitMoleAnimation(X, Y, gridX, gridY, difficulty, score, misses, name);
        return true;
      }
      drawHoles(gridX, gridY, score, misses, name);
      globalVars.g.drawImage(moleHeadGame, null, X, moleY);
      drawPanels(gridX, gridY);
      globalVars.panel.copyGraphicsToScreen();
      Thread.sleep(10);
    }
    return false;
  }

  // http://www.roseindia.net/java/java-get-example/screen-dimensions.shtml
  public static int[] getScreenSize() {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension dim = toolkit.getScreenSize();
    int[] dimensions = new int[]{dim.width, dim.height};
    return dimensions;
  }

  // http://www.javalobby.org/articles/ultimate-image/
  public static BufferedImage resize(BufferedImage img, double scale) {
    int w = img.getWidth();
    int h = img.getHeight();
    int newW = (int) (w * (scale / 100));
    int newH = (int) (h * (scale / 100));

    BufferedImage dimg = dimg = new BufferedImage(newW, newH, img.getType());
    Graphics2D g = dimg.createGraphics();
    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
    g.dispose();
    return dimg;
  }

  // http://www.javalobby.org/articles/ultimate-image/
  public static BufferedImage resize(BufferedImage img, int newW, int newH) {
    int w = img.getWidth();
    int h = img.getHeight();
    BufferedImage dimg = dimg = new BufferedImage(newW, newH, img.getType());
    Graphics2D g = dimg.createGraphics();
    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
    g.dispose();
    return dimg;
  }

  // http://www.javalobby.org/articles/ultimate-image/
  public static BufferedImage loadImage(String ref) {
    BufferedImage bimg = null;
      
    try {
      InputStream is = PEX2.class.getResourceAsStream(ref);
//      ImageIO.read(is); 
//      bimg = ImageIO.read(new File(ref));
      bimg = ImageIO.read(is);
    } catch (Exception e) {
      System.out.println(e);
    }
    return bimg;
  }

  //  http://www.roseindia.net/java/beginners/java-read-file-line-by-line.shtml
  public static String[] readScores() throws IOException {
    String[] scores = new String[10];
    try {
      FileInputStream fstream = new FileInputStream("highscores.txt");
      DataInputStream in = new DataInputStream(fstream);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      int counter = 0;
      for (String strLine = null; (strLine = br.readLine()) != null && counter < 10; counter++) {
        scores[counter] = strLine;
      }
      in.close();
      String[] retScores = new String[counter];
      System.arraycopy(scores, 0, retScores, 0, counter);
      return retScores;
    } catch (IOException e) {
      return null;
    }
  }

  //  http://www.roseindia.net/java/beginners/java-write-to-file.shtml
  public static void writeScores(int[] scores, String[] names) throws IOException {
    FileWriter fstream = new FileWriter("highscores.txt");
    BufferedWriter out = new BufferedWriter(fstream);
    for (int counter = 0; counter < Math.min(10,scores.length); counter++) {
      out.write(String.format("%d/%s\r\n", scores[counter], names[counter]));
    }
    out.close();
  }
}

class userCrosshair extends Thread {

  BufferedImage crosshair = null;

  userCrosshair(BufferedImage crosshair) {
    this.crosshair = crosshair;
  }

  public void run() {
    int mouseX = 0;
    int mouseY = 0;
    while (globalVars.quit == false) {
      mouseX = globalVars.panel.getMouseX();
      mouseY = globalVars.panel.getMouseY();
      globalVars.g.drawImage(crosshair, null, 
                                        mouseX - crosshair.getWidth() / 2, 
                                        mouseY - crosshair.getHeight() / 2);
    }
  }
}

class soundEffects extends Thread {

  public void run() {
    while (globalVars.quit == false) {
      if (globalVars.panel.isMouseButtonDown(DrawingPanel.LEFT_BUTTON)) {
        try {
          Thread.sleep(100);
        } catch (InterruptedException ex) {
        }
        if (!globalVars.panel.isMouseButtonDown(DrawingPanel.LEFT_BUTTON)) {
          new AePlayWave("res/bang_1.wav").start();
        }
      }
    }
  }
}

class backgroundMusic extends Thread {

  public void run() {
    Thread music = new AePlayWave("res/background_sound.wav");
    music.start();
    while (globalVars.quit == false) {
      if (!music.isAlive()) {
        music = new AePlayWave("res/background_sound.wav");
        music.start();
      } else {
        try {
          Thread.sleep(100);
        } catch (InterruptedException ex) {
        }
      }
    }
  }
}

//  http://www.anyexample.com/programming/java/java_play_wav_sound_file.xml
enum Position {

  LEFT, RIGHT, NORMAL
};

class AePlayWave extends Thread {

  private String filename;
  private Position curPosition;
  private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb 

  public AePlayWave(String wavfile) {
    filename = wavfile;
    curPosition = Position.NORMAL;
  }

  public AePlayWave(String wavfile, Position p) {
    filename = wavfile;
    curPosition = p;
  }

  public void run() {

    URL soundFile = getClass().getClassLoader().getResource(filename);

    AudioInputStream audioInputStream = null;
    try {
      audioInputStream = AudioSystem.getAudioInputStream(soundFile);
    } catch (UnsupportedAudioFileException e1) {
      return;
    } catch (IOException e1) {
      return;
    }

    AudioFormat format = audioInputStream.getFormat();
    SourceDataLine auline = null;
    DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

    try {
      auline = (SourceDataLine) AudioSystem.getLine(info);
      auline.open(format);
    } catch (LineUnavailableException e) {
      return;
    } catch (Exception e) {
      return;
    }

    if (auline.isControlSupported(FloatControl.Type.PAN)) {
      FloatControl pan = (FloatControl) auline.getControl(FloatControl.Type.PAN);
      if (curPosition == Position.RIGHT) {
        pan.setValue(1.0f);
      } else {
        if (curPosition == Position.LEFT) {
          pan.setValue(-1.0f);
        }
      }
    }

    auline.start();
    int nBytesRead = 0;
    byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];

    try {
      while (nBytesRead != -1) {
        nBytesRead = audioInputStream.read(abData, 0, abData.length);
        if (nBytesRead >= 0) {
          auline.write(abData, 0, nBytesRead);
        }
      }
    } catch (IOException e) {
      return;
    } finally {
      auline.drain();
      auline.close();
    }
  }
}

class globalVars {

  public static DrawingPanel panel = null;
  public static Graphics2D g = null;
  public static boolean quit = false;
}