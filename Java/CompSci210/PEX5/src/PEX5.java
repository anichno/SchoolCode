
/**
 * Event driven graphical drawing window which uses shapes built 
 * with a defined inheritance hierarchy
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Dec 1, 2011 at 10:54:35 AM
 * 
 * Documentation Statement:  Framework for Shapes borrowed from 
 * Doctor Carlisle.
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class PEX5 implements MouseListener,
                             ActionListener,
                             MouseMotionListener,
                             PopupMenuListener {

  final int SHAPE_RECT = 0;
  final int SHAPE_CIRCLE = 1;
  final int SHAPE_ELLIPSE = 2;
  final int SHAPE_SQUARE = 3;
  final int SHAPE_TRIANGLE = 4;
  final int MODE_MOVE = 0;
  final int MODE_RESIZE = 1;
  final int MODE_DELETE = 2;
  final int LEFT_MOUSE_BUTTON = 1;
  final int RIGHT_MOUSE_BUTTON = 3;
  JFrame frame;
  JMenuBar menuBar;
  JMenu shapeMenu;
  JMenuItem shapeMenuRect;
  JMenuItem shapeMenuCircle;
  JMenuItem shapeMenuEllipse;
  JMenuItem shapeMenuSquare;
  JMenuItem shapeMenuTriangle;
  JMenu colorMenu;
  JMenuItem colorMenuRed;
  JMenuItem colorMenuBlue;
  JMenuItem colorMenuGray;
  JMenuItem colorMenuYellow;
  JMenuItem colorMenuGreen;
  JMenuItem colorMenuBlack;
  JMenu modeMenu;
  JMenuItem modeMenuMove;
  JMenuItem modeMenuResize;
  JMenuItem modeMenuDelete;
  JPopupMenu colorPopupMenu;
  JMenuItem colorPopupMenuRed;
  JMenuItem colorPopupMenuBlue;
  JMenuItem colorPopupMenuGray;
  JMenuItem colorPopupMenuYellow;
  JMenuItem colorPopupMenuGreen;
  JMenuItem colorPopupMenuBlack;
  static JPanel sketchPad = null;
  static Graphics2D g = null;
  static int lastIndex;
  static Shape[] userShapes = null;
  static Shape selectionCircle = null;
  static int curShape;
  static int clickedShape;
  static boolean shapeMovable;
  static boolean shapeResizable;
  static Color curColor = null;
  static int selectionMode;
  static Point tempTriP1 = null;
  static Point tempTriP2 = null;
  static boolean makeTriangle;
  static int curTriPoint;
  static Shape tempTriangle = null;

  public static void main(String[] args) throws InterruptedException {
    userShapes = new Shape[100];
    lastIndex = 0;
    clickedShape = -1;
    shapeMovable = false;
    shapeResizable = false;
    curColor = Color.red;
    selectionMode = 0;
    makeTriangle = false;
    curTriPoint = 0;

    new PEX5();
    g = (Graphics2D) sketchPad.getGraphics();

  }

  public PEX5() {
    frame = new JFrame();
    frame.setForeground(Color.WHITE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocation(10, 50);
    frame.setSize(600, 600);
    frame.setTitle("PEX 5");
    frame.setLayout(new BorderLayout());
    frame.setResizable(false);

    menuBar = new JMenuBar();

    shapeMenu = new JMenu("Shapes");
    shapeMenuRect = new JMenuItem("Rectangle");
    shapeMenuCircle = new JMenuItem("Circle");
    shapeMenuEllipse = new JMenuItem("Ellipse");
    shapeMenuSquare = new JMenuItem("Square");
    shapeMenuTriangle = new JMenuItem("Triangle");

    shapeMenu.add(shapeMenuRect);
    shapeMenuRect.addActionListener(this);
    shapeMenu.add(shapeMenuCircle);
    shapeMenuCircle.addActionListener(this);
    shapeMenu.add(shapeMenuEllipse);
    shapeMenuEllipse.addActionListener(this);
    shapeMenu.add(shapeMenuSquare);
    shapeMenuSquare.addActionListener(this);
    shapeMenu.add(shapeMenuTriangle);
    shapeMenuTriangle.addActionListener(this);

    colorMenu = new JMenu("Colors");
    colorMenuRed = new JMenuItem("Red");
    colorMenuBlue = new JMenuItem("Blue");
    colorMenuGray = new JMenuItem("Gray");
    colorMenuYellow = new JMenuItem("Yellow");
    colorMenuGreen = new JMenuItem("Green");
    colorMenuBlack = new JMenuItem("Black");

    colorMenu.add(colorMenuRed);
    colorMenuRed.addActionListener(this);
    colorMenu.add(colorMenuBlue);
    colorMenuBlue.addActionListener(this);
    colorMenu.add(colorMenuGray);
    colorMenuGray.addActionListener(this);
    colorMenu.add(colorMenuYellow);
    colorMenuYellow.addActionListener(this);
    colorMenu.add(colorMenuGreen);
    colorMenuGreen.addActionListener(this);
    colorMenu.add(colorMenuBlack);
    colorMenuBlack.addActionListener(this);

    modeMenu = new JMenu("Mode");
    modeMenuMove = new JMenuItem("Move");
    modeMenuResize = new JMenuItem("Resize");
    modeMenuDelete = new JMenuItem("Delete");

    modeMenu.add(modeMenuMove);
    modeMenuMove.addActionListener(this);
    modeMenu.add(modeMenuResize);
    modeMenuResize.addActionListener(this);
    modeMenu.add(modeMenuDelete);
    modeMenuDelete.addActionListener(this);

    colorPopupMenu = new JPopupMenu("Change Color");
    colorPopupMenuRed = new JMenuItem("Red");
    colorPopupMenuBlue = new JMenuItem("Blue");
    colorPopupMenuGray = new JMenuItem("Gray");
    colorPopupMenuYellow = new JMenuItem("Yellow");
    colorPopupMenuGreen = new JMenuItem("Green");
    colorPopupMenuBlack = new JMenuItem("Black");

    colorPopupMenu.add(colorPopupMenuRed);
    colorPopupMenuRed.addActionListener(this);
    colorPopupMenu.add(colorPopupMenuBlue);
    colorPopupMenuBlue.addActionListener(this);
    colorPopupMenu.add(colorPopupMenuGray);
    colorPopupMenuGray.addActionListener(this);
    colorPopupMenu.add(colorPopupMenuYellow);
    colorPopupMenuYellow.addActionListener(this);
    colorPopupMenu.add(colorPopupMenuGreen);
    colorPopupMenuGreen.addActionListener(this);
    colorPopupMenu.add(colorPopupMenuBlack);
    colorPopupMenuBlack.addActionListener(this);
    colorPopupMenu.addPopupMenuListener(this);

    menuBar.add(shapeMenu);
    menuBar.add(colorMenu);
    menuBar.add(modeMenu);

    frame.add(menuBar, BorderLayout.NORTH);

    sketchPad = new JPanel();
    frame.add(sketchPad, BorderLayout.CENTER);
    sketchPad.setBackground(Color.white);
    sketchPad.addMouseListener(this);
    sketchPad.addMouseMotionListener(this);

    frame.setVisible(true);
  }

  public static void redrawScreen() {
    new ScreenDraw(g, userShapes, selectionCircle,
                   tempTriangle, false).start();
  }

  //  Wait before drawing, use for the popup box
  public static void redrawScreen(boolean delay) {
    new ScreenDraw(g, userShapes, selectionCircle,
                   tempTriangle, delay).start();
  }

  public static int detectClick(int mouseX, int mouseY) {
    for (int i = 0; i < userShapes.length; i++) {
      if (userShapes[i] != null) {
        if (userShapes[i].clicked(mouseX, mouseY)) {
          return i;
        }
      }
    }
    return -1;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (makeTriangle && curTriPoint == 2) {
      userShapes[lastIndex] = new Triangle(tempTriP1,
                                           tempTriP2, e.getPoint(),
                                           curColor);
      lastIndex++;
      curTriPoint = 0;
      makeTriangle = false;
      tempTriangle = null;
      redrawScreen();
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
    clickedShape = detectClick(e.getX(), e.getY());
    if (e.getButton() == LEFT_MOUSE_BUTTON) {
      if (clickedShape != -1) {
        if (selectionMode == MODE_MOVE) {
          shapeMovable = true;
          shapeResizable = false;
        } else {
          if (selectionMode == MODE_RESIZE) {
            shapeMovable = false;
            shapeResizable = true;
          } else {
            if (selectionMode == MODE_DELETE) {
              userShapes[clickedShape] = null;
            }
          }
        }
        if (userShapes[clickedShape] != null) {
          selectionCircle = new Circle(
                  (int) userShapes[clickedShape].getCenter().getX(),
                  (int) userShapes[clickedShape].getCenter().getY(),
                  5, Color.white);
        }

      } else {
        selectionCircle = null;
        if (curShape == SHAPE_RECT) {
          userShapes[lastIndex] = new Rectangle(e.getX(),
                                                e.getY(), 30, 70,
                                                curColor);
        } else {
          if (curShape == SHAPE_CIRCLE) {
            userShapes[lastIndex] = new Circle(e.getX(),
                                               e.getY(), 50,
                                               curColor);
          } else {
            if (curShape == SHAPE_ELLIPSE) {
              userShapes[lastIndex] = new Ellipse(e.getX(),
                                                  e.getY(), 30, 70,
                                                  curColor);
            } else {
              if (curShape == SHAPE_SQUARE) {
                userShapes[lastIndex] = new Square(e.getX(),
                                                   e.getY(), 50,
                                                   curColor);
              } else {
                if (curShape == SHAPE_TRIANGLE && curTriPoint == 0) {
                  makeTriangle = true;
                  tempTriP1 = e.getPoint();
                  curTriPoint++;
                }
              }
            }
          }
        }

        clickedShape = lastIndex;
        lastIndex++;
      }
      redrawScreen();
    } else {
      if (e.getButton() == RIGHT_MOUSE_BUTTON && clickedShape != -1) {
        selectionCircle = new Circle(
                (int) userShapes[clickedShape].getCenter().getX(),
                (int) userShapes[clickedShape].getCenter().getY(),
                5, Color.white);
        colorPopupMenu.show(sketchPad, e.getX(), e.getY());
        redrawScreen();
      }
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (makeTriangle && curTriPoint == 1) {
      tempTriP2 = e.getPoint();
      curTriPoint++;
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    //  Do nothing
    return;
  }

  @Override
  public void mouseExited(MouseEvent e) {
    //  Do nothing
    return;
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == shapeMenuRect) {
      curShape = SHAPE_RECT;
    } else {
    if (ae.getSource() == shapeMenuCircle) {
      curShape = SHAPE_CIRCLE;
    } else {
    if (ae.getSource() == shapeMenuEllipse) {
      curShape = SHAPE_ELLIPSE;
    } else {
    if (ae.getSource() == shapeMenuSquare) {
      curShape = SHAPE_SQUARE;
    } else {
    if (ae.getSource() == shapeMenuTriangle) {
      curShape = SHAPE_TRIANGLE;
    } else {
    if (ae.getSource() == colorMenuRed) {
      curColor = Color.red;
    } else {
    if (ae.getSource() == colorMenuBlue) {
      curColor = Color.blue;
    } else {
    if (ae.getSource() == colorMenuGray) {
      curColor = Color.gray;
    } else {
    if (ae.getSource() == colorMenuYellow) {
      curColor = Color.yellow;
    } else {
    if (ae.getSource() == colorMenuGreen) {
      curColor = Color.green;
    } else {
    if (ae.getSource() == colorMenuBlack) {
      curColor = Color.black;
    } else {
    if (ae.getSource() == colorPopupMenuRed) {
      userShapes[clickedShape].setColor(Color.red);
      redrawScreen();
    } else {
    if (ae.getSource() == colorPopupMenuBlue) {
      userShapes[clickedShape].setColor(Color.blue);
      redrawScreen();
    } else {
    if (ae.getSource() == colorPopupMenuGray) {
      userShapes[clickedShape].setColor(Color.gray);
      redrawScreen();
    } else {
    if (ae.getSource() == colorPopupMenuYellow) {
      userShapes[clickedShape].setColor(Color.yellow);
      redrawScreen();
    } else {
    if (ae.getSource() == colorPopupMenuGreen) {
      userShapes[clickedShape].setColor(Color.green);
      redrawScreen();
    } else {
    if (ae.getSource() == colorPopupMenuBlack) {
      userShapes[clickedShape].setColor(Color.black);
      redrawScreen();
    } else {
    if (ae.getSource() == modeMenuMove) {
      selectionMode = MODE_MOVE;
    } else {
    if (ae.getSource() == modeMenuResize) {
      selectionMode = MODE_RESIZE;
    } else {
    if (ae.getSource() == modeMenuDelete) {
      selectionMode = MODE_DELETE;
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    if (shapeMovable && clickedShape != -1
        && userShapes[clickedShape] != null) {
      userShapes[clickedShape].move(e.getX(), e.getY());
      selectionCircle = new Circle(
              (int) userShapes[clickedShape].getCenter().getX(),
              (int) userShapes[clickedShape].getCenter().getY(),
              5, Color.white);
    } else {
      if (shapeResizable && clickedShape != -1
          && userShapes[clickedShape] != null) {
        userShapes[clickedShape].resize(e.getX(), e.getY());
        selectionCircle = new Circle(
                (int) userShapes[clickedShape].getCenter().getX(),
                (int) userShapes[clickedShape].getCenter().getY(),
                5, Color.white);
      } else {
        if (makeTriangle && curTriPoint == 1) {
          tempTriangle = new Triangle(tempTriP1,
                                      e.getPoint(),
                                      new Point(e.getX() + 4,
                                                e.getY() + 4),
                                      curColor);
        }
      }
    }
    redrawScreen();
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    if (makeTriangle && curTriPoint == 2) {
      tempTriangle = new Triangle(tempTriP1, tempTriP2,
                                  e.getPoint(), curColor);
      redrawScreen();
    }
  }

  @Override
  public void popupMenuWillBecomeVisible(PopupMenuEvent pme) {
    //  Do nothing
    return;
  }

  @Override
  public void popupMenuWillBecomeInvisible(PopupMenuEvent pme) {
    //  Redraw screen with delay
    redrawScreen(true);
  }

  @Override
  public void popupMenuCanceled(PopupMenuEvent pme) {
    //  Do nothing
    return;
  }
}

class ScreenDraw extends Thread {

  private Shape[] userShapes = null;
  private Shape selectionCircle = null;
  private Shape tempTriangle = null;
  private Graphics2D g = null;
  private int delay;

  public ScreenDraw(Graphics2D g, Shape[] userShapes,
                    Shape selectionCircle,
                    Shape tempTriangle,
                    boolean delay) {
    this.g = g;
    this.userShapes = userShapes;
    this.selectionCircle = selectionCircle;
    this.tempTriangle = tempTriangle;
    if (delay) {
      this.delay = 5;
    }
  }

  public void run() {
    try {
      Thread.sleep(delay);
    } catch (InterruptedException ex) {
    }
    g.setColor(Color.white);
    g.fillRect(0, 0, 600, 600);
    for (int i = 0; i < userShapes.length; i++) {
      if (userShapes[i] != null) {
        userShapes[i].draw(g);
      }
    }
    if (selectionCircle != null) {
      selectionCircle.draw(g);
    }
    if (tempTriangle != null) {
      tempTriangle.draw(g);
    }
  }
}