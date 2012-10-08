
/**
 * Description: This program draws a fractal curve (recursively)
 *
 * @author  Dr. Brown
 *
 * @version 1.0   Fall 2011
 *
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class FractalCurve implements ChangeListener {

  public static final int WINDOW_SIZE = 600;
  public static final int MAX_LEVEL_OF_RECURSION = 12;
  public static final int MAX_HEIGHT = 20;
  //
  private JSpinner levelSpinner;
  private JSpinner heightSpinner;
  private FractalPanel centerPanel;

  //-------------------------------------------------------------------
  public static void main(String[] args) {
    new FractalCurve();
  }

  //-------------------------------------------------------------------
  public FractalCurve() {
    // Create the window
    JFrame frame = new JFrame();
    frame.setLayout(new BorderLayout());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(WINDOW_SIZE, WINDOW_SIZE);
    frame.setTitle("Fractal Curve");

    // Create a graphics panel to draw the fractal curve in.
    centerPanel = new FractalPanel(); // see class below
    centerPanel.setLayout(null);
    frame.add(centerPanel, BorderLayout.CENTER);

    // The NORTH panel holds the control spinners.
    JPanel topPanel = new JPanel(new FlowLayout());
    frame.add(topPanel, BorderLayout.NORTH);

    JLabel label = new JLabel("Level of recursion:");
    levelSpinner = new JSpinner(new SpinnerNumberModel(8, 1, MAX_LEVEL_OF_RECURSION, 1));
    levelSpinner.addChangeListener(this);

    JLabel label2 = new JLabel("Fractional height: 1/");
    heightSpinner = new JSpinner(new SpinnerNumberModel(4, 2, MAX_HEIGHT, 1));
    heightSpinner.addChangeListener(this);

    topPanel.add(label);
    topPanel.add(levelSpinner);
    topPanel.add(label2);
    topPanel.add(heightSpinner);

    // Make the window visible.
    frame.setVisible(true);
  }

  //-------------------------------------------------------------------
  @Override
  public void stateChanged(ChangeEvent event) {
    centerPanel.repaint();
  }

  //===================================================================
  // Inner class for the drawing panel
  class FractalPanel extends JPanel {

    private double heightDenominator;

    //-----------------------------------------------------------------
    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      // Calculate the initial points of a straight horizontal line
      Dimension panelSize = getSize();
      int xOffset = panelSize.width / 30; // 1/30 of panel width
      int yCenter = panelSize.height / 2;
      Point p1 = new Point(xOffset, yCenter);
      Point p2 = new Point(panelSize.width - xOffset, yCenter);

      // Draw the Koch Curve
      heightDenominator = (double) (Integer) heightSpinner.getValue();
      int levelOfRecursion = (Integer) levelSpinner.getValue();

      drawEdge(g, p1, p2, levelOfRecursion);
    }

    //-----------------------------------------------------------------
    public void drawEdge(Graphics g, Point p1, Point p2, int level ) {
      if (level <= 1) {
        // Base case; the maximum number of recursive calls has been reached
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
      } else {
        // Calculate 2 interior points between p1 and p2
        int dx = p2.x - p1.x;
        int dy = p2.y - p1.y;
        double dxOnLine = dx / 3.0;
        double dyOnLine = dy / 3.0;
        double dxOffLine = dx / heightDenominator;
        double dyOffLine = dy / heightDenominator;
        // Note: the perpendicular bisector has a slope of -dx/dy;
        Point p3 = new Point((int) Math.round(p1.x + dxOnLine + dyOffLine),
                             (int) Math.round(p1.y + dyOnLine - dxOffLine) );
        Point p4 = new Point((int) Math.round(p2.x - dxOnLine - dyOffLine),
                             (int) Math.round(p2.y - dyOnLine + dxOffLine) );

        // Draw the edges
        drawEdge(g, p1, p3, level - 1 );
        drawEdge(g, p3, p4, level - 1 );
        drawEdge(g, p4, p2, level - 1 );
      }
    }
  }
}
