/**
 * Draws nifty panels and randomizes their colors
 * Every two seconds it then snuffs out the life of a random panel
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Oct 11, 2011 at 10:48:06 AM
 */
import java.awt.Color;

public class ManyPanels {
  
  final static int NUM_PANELS = 6;

  public static void main(String[] args) {
    DrawingPanel[] manyPanels = new DrawingPanel[NUM_PANELS];
    int activePanels = NUM_PANELS;
    for (int counter = 0; counter < manyPanels.length; counter++) {
      manyPanels[counter] = new DrawingPanel(300, 300);
      manyPanels[counter].setWindowTitle("Window: " + counter);
      manyPanels[counter].setBackground(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
      manyPanels[counter].copyGraphicsToScreen();
    }

    for (int counter = 0;; counter++) {
      if (counter == 5) {
        counter = 0;
      }
      if (manyPanels[counter].mouseClickHasOccurred(DrawingPanel.LEFT_BUTTON)) {
        break;
      }
    }

    long time = System.currentTimeMillis();
    while (activePanels > 0) {
      if ((System.currentTimeMillis() - time) % 200 <= 0) {
        changeColors(manyPanels);
      }
      if ((System.currentTimeMillis() - time) >= 2000) {
        killPanel(manyPanels);
        activePanels--;
        time = System.currentTimeMillis();
      }
    }
  }

  public static void changeColors(DrawingPanel[] manyPanels) {
    int select = 0;
    while (true) {
      select = (int) (Math.random() * NUM_PANELS);
      if (manyPanels[select] != null) {
        manyPanels[select].setBackground(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
        manyPanels[select].copyGraphicsToScreen();
        return;
      }
    }
  }

  public static void killPanel(DrawingPanel[] manyPanels) {
    int select = 0;
    while (true) {
      select = (int) (Math.random() * NUM_PANELS);
      if (manyPanels[select] != null) {
        manyPanels[select].closeWindow();
        manyPanels[select] = null;
        return;
      }
    }
  }
}
