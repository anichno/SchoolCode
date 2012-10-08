
/**
 * Implementation of a Star Gazing program which provides easily accessible
 * information about stars to the user.  Constellation files are loaded
 * from the constellations directory.
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Oct 10, 2011 at 1:11:44 PM
 *
 * Documentation Statement: I received no help on this assignment other 
 * than the web resources listed by the functions in which the web resources were used
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PEX3 {

  static String[][] starStorage = null;
  static String[][] constelStore = null;
  static int[] fileInfo = null;
  static int screenSize = 0;
  static double[] starInfo = null;
  static DrawingPanel starPanel = null;
  static DrawingPanel infoPanel = null;
  static Graphics2D starG = null;
  static Graphics2D infoG = null;

  public static void main(String[] args) throws InterruptedException {
    loadStarWatcher();
    int nearStarIndex = 0;
    for (; !starPanel.mouseClickHasOccurred(DrawingPanel.LEFT_BUTTON);) {
      starPanel.setBackground(Color.black);
      infoPanel.setBackground(Color.white);
      nearStarIndex = drawStars();
      drawActiveInfo(starStorage[nearStarIndex]);
      starPanel.copyGraphicsToScreen();
      infoPanel.copyGraphicsToScreen();
      Thread.sleep(100);
    }
    starPanel.closeWindow();
    infoPanel.closeWindow();
  }

  //  Loads all the resources into memory
  public static void loadStarWatcher() {
    try {
      Scanner input = new Scanner(new File("stars.txt"));
      constelStore = loadConstellations();
      analyzeFile(input);
      int[] screenDim = getScreenSize();
      screenSize = Math.min(screenDim[0], screenDim[1]) - 75;
      System.out.println("Number of stars in file: " + fileInfo[0]);
      starInfo = analyzeStars();
      starPanel = new DrawingPanel(screenSize, screenSize);
      infoPanel = new DrawingPanel(500, 150);
      starPanel.setWindowTitle("Star Map");
      infoPanel.setWindowTitle("Active Star Information");
      starG = starPanel.getGraphics();
      infoG = infoPanel.getGraphics();
      Font textFont = new Font("Courier New", Font.PLAIN, 14);
      infoG.setFont(textFont);
    } catch (IOException error) {
      System.out.println("Error opening the file stars.txt" + error);
    }
  }

  //  Loads constellations into memory
  public static String[][] loadConstellations() {
    String[] constellFiles = null;
    String[] constellData = null;
    String[] constellName = null;
    final String constDirectory = "constellations";
    Scanner input = null;
    int maxLength = 0;
    int fileLength = 0;
    try {
      File dir = new File(constDirectory);
      constellFiles = dir.list();
      constelStore = new String[constellFiles.length][];
      constellData = new String[constellFiles.length];
      constellName = new String[constellFiles.length];
      System.out.println("Number of Constellations: " + constellFiles.length);
      if (constellFiles != null) {
        for (int counter = 0; counter < constellFiles.length; counter++) {
          input = new Scanner(new File(constDirectory + '/' + constellFiles[counter]));
          constellData[counter] = "";
          fileLength = 0;
          while (input.hasNextLine()) {
            constellData[counter] += input.nextLine() + '\n';
            fileLength++;
          }

          if (fileLength > maxLength) {
            maxLength = fileLength;
          }

          String temp = constellFiles[counter].substring(0, constellFiles[counter].length() - 4);
          String[] temp1 = temp.split("[A-Z]");
          String fileName = "";
          if (temp1.length > 2) {
            for (int builder = 0, curPos = 0, index = 1; builder < temp1.length - 1; builder++, curPos += temp1[index].length() + 1, index++) {
              fileName += temp.charAt(curPos) + temp1[index] + " ";
            }
          } else {
            fileName = temp;
          }
          fileName = fileName.trim();
          constellName[counter] = fileName;
        }

        for (int counter = 0; counter < constellFiles.length; counter++) {
          constelStore[counter] = new String[maxLength + 1];
          constelStore[counter][0] = constellName[counter];
          input = new Scanner(constellData[counter]);
          for (int counter2 = 1; input.hasNextLine(); counter2++) {
            constelStore[counter][counter2] = input.nextLine();
          }
        }
      }
    } catch (IOException error) {
      System.out.println("Error opening file" + error);
    }
    return constelStore;
  }

  //  Draws information about the active star
  public static void drawActiveInfo(String[] nearestStar) {
    int spacer = 20;
    String name = String.format("Name(s)%17s ", " :");
    for (int counter = 6; nearestStar[counter] != null && counter < fileInfo[1] + 5; counter++) {
      name += nearestStar[counter];
      if (nearestStar[counter + 1] != null) {
        name += ", ";
      }
    }
    String locationInfo = String.format("Location%16s %s, %s, %s", " :", nearestStar[0], nearestStar[1], nearestStar[2]);
    String henDnumInfo = String.format("Henry Draper Number%5s %s", " :", nearestStar[3]);
    String brightnessInfo = String.format("Star Brightness%9s %s", " :", nearestStar[4]);
    String harvRnumInfo = String.format("Harvard Revised Number%s %s", " :", nearestStar[5]);
    String constellationInfo = String.format("Constellation%11s", " :");
    String constellation = " ";
    if (nearestStar[6] != null) {
      constellation += detectConstellation(nearestStar, true);
      if (constellation.equals(" null")) {
        constellation = "";
      }
    }
    if (constellation.length() > 2) {
      constellationInfo += constellation.substring(0, constellation.length() - 2);
    }
    infoG.drawString(name, 10, spacer);
    infoG.drawString(locationInfo, 10, spacer * 2);
    infoG.drawString(henDnumInfo, 10, spacer * 3);
    infoG.drawString(brightnessInfo, 10, spacer * 4);
    infoG.drawString(harvRnumInfo, 10, spacer * 5);
    infoG.drawString(constellationInfo, 10, spacer * 6);
  }

  //  Detects whether or not the star is part of a constellation, if drawConst is true
  //  then the constellation is drawn to the screen
  public static String detectConstellation(String[] checkStar, boolean drawConst) {
    boolean next = false;
    String findConstMatch = null;
    String constString = "";
    for (int counter = 0; counter < constelStore.length; counter++) {
      for (int counter2 = 1; counter2 < constelStore[0].length && constelStore[counter][counter2] != null; counter2++) {
        for (int counter3 = 6; counter3 < fileInfo[1] + 6 && checkStar[counter3] != null; counter3++) {
          findConstMatch = String.format(".*%s.*", checkStar[counter3]);
          if (constelStore[counter][counter2].matches(findConstMatch)) {
            constString += constelStore[counter][0] + ", ";
            next = true;
            if (drawConst) {
              drawConstellation(constelStore[counter]);
            }
            break;
          }
        }
        if (next) {
          next = false;
          break;
        }
      }
    }
    if (constString.length() == 0) {
      constString = null;
    }
    return constString;
  }

  //  Scales the star the screen
  public static int scaleX(String starX) {
    int drawScale = (int) ((screenSize / 2 - 20) / starInfo[1]);
    return (int) (screenSize / 2 + (Double.valueOf(starX) * drawScale)) - 2;
  }

  //  Scales the star the screen
  public static int scaleY(String starY) {
    int drawScale = (int) ((screenSize / 2 - 20) / starInfo[1]);
    return (int) (screenSize / 2 - (Double.valueOf(starY) * drawScale)) - 2;
  }

  //  Draws the constellation to the screen
  public static void drawConstellation(String[] constellation) {
    int[][] star = new int[2][2];
    String[] starNames = null;

    starG.setColor(Color.orange);
    for (int constCounter = 1; constCounter < constellation.length - 1 && constellation[constCounter] != null; constCounter++) {
      starNames = constellation[constCounter].split(",");
      for (int starCounter = 0, starIndex = 0; starCounter < starStorage.length && starIndex < 2; starCounter++) {
        for (int fieldCounter = 6; fieldCounter < fileInfo[1] + 6 && starStorage[starCounter][fieldCounter] != null; fieldCounter++) {
          if (starStorage[starCounter][fieldCounter].equals(starNames[0]) || starStorage[starCounter][fieldCounter].equals(starNames[1])) {
            star[starIndex][0] = scaleX(starStorage[starCounter][0]);
            star[starIndex][1] = scaleY(starStorage[starCounter][1]);
            starIndex++;
          }
        }
      }
      starG.drawLine(star[0][0], star[0][1], star[1][0], star[1][1]);
    }
  }

  //  Draws the stars to the screen
  public static int drawStars() {
    int colorScale = (int) (205 / starInfo[0]);
    int mouseX = starPanel.getMouseX();
    int mouseY = starPanel.getMouseY();
    int starX = 0;
    int starY = 0;
    double distance = 0;
    double closeDist = 100;
    int closeStarX = 0;
    int closeStarY = 0;
    int closeStar = 0;
    for (int counter = 0; counter < starStorage.length; counter++) {
      if (Double.valueOf(starStorage[counter][4]) < 0) {
        continue;
      }
      starX = scaleX(starStorage[counter][0]);
      starY = scaleY(starStorage[counter][1]);
      if (starStorage[counter][fileInfo[1] + 6] != null) {
        starG.setColor(Color.green);
      } else {
        starG.setColor(new Color(255, 255, 255, (int) (Double.valueOf(starStorage[counter][4]) * colorScale) + 50));
      }
      starG.fillOval(starX, starY, 4, 4);
      distance = Math.sqrt((mouseX - starX) * (mouseX - starX) + (mouseY - starY) * (mouseY - starY));
      if (distance < closeDist) {
        closeDist = distance;
        closeStarX = starX;
        closeStarY = starY;
        closeStar = counter;
      }
    }
    starG.setColor(Color.RED);
    starG.fillOval(closeStarX - 5, closeStarY - 5, 10, 10);
    return closeStar;
  }

  //  Determines information about the star file
  public static void analyzeFile(Scanner input) {
    int max = 0;
    int lines = 0;
    String starFile = "";
    while (input.hasNextLine()) {
      String line = input.nextLine();
      starFile += line + '\n';
      String[] split = line.split(";");
      if (split.length > max) {
        max = split.length;
      }
      lines++;
    }
    fileInfo = new int[]{lines, max};
    loadStorage(new Scanner(starFile));
  }

  //  Loads the stars from the star file
  public static void loadStorage(Scanner input) {
    Scanner lineBreaker = null;
    String[][] storage = new String[fileInfo[0]][fileInfo[1] + 7];
    String line = null;
    String[] names = null;
    String tempName = null;
    int curLine = 0;
    while (input.hasNextLine()) {
      line = input.nextLine();
      lineBreaker = new Scanner(line);
      for (int counter = 0; counter < 6; counter++) {
        storage[curLine][counter] = lineBreaker.next();
      }
      if (lineBreaker.hasNextLine()) {
        line = lineBreaker.nextLine();
        names = line.split(";");
        for (int counter = 0; counter < names.length; counter++) {
          tempName = names[counter];
          tempName.replaceFirst(";", "");
          tempName = tempName.substring(1);
          storage[curLine][counter + 6] = tempName;
        }
        if (detectConstellation(storage[curLine], false) != null) {
          storage[curLine][fileInfo[1] + 6] = "";
        } else {
          storage[curLine][fileInfo[1] + 6] = null;
        }

      }
      curLine++;
    }
    starStorage = storage;
  }

  //  Analyzes the stars for basic information
  public static double[] analyzeStars() {
    double maxBrightness = 0;
    double maxDimension = 0;
    double brightness = 0;
    double dimension = 0;
    for (int counter = 0; counter < starStorage.length; counter++) {
      brightness = Double.valueOf(starStorage[counter][4]);
      dimension = Math.max(Math.abs(Double.valueOf(starStorage[counter][0])), Math.abs(Double.valueOf(starStorage[counter][1])));
      if (brightness > maxBrightness) {
        maxBrightness = brightness;
      }
      if (dimension > maxDimension) {
        maxDimension = dimension;
      }
    }
    return new double[]{maxBrightness, maxDimension};
  }

  // http://www.roseindia.net/java/java-get-example/screen-dimensions.shtml
  public static int[] getScreenSize() {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension dim = toolkit.getScreenSize();
    int[] dimensions = new int[]{dim.width, dim.height};
    return dimensions;
  }
}