package pex4guibuilder;

/**
 * <put a program description here>
 * 
 * @author anichno
 * 
 * @version 1.0 - Nov 13, 2011 at 9:17:51 PM
 */
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class GameCanvas extends Canvas {

  private BufferedImage img = null;
  private Graphics2D offscreenGraphics = null;
  private int width = 0;
  private int height = 0;
  
  public GameCanvas() {
    super();
  }
  
  public void setupGameCanvas() {
//    this.width = this.getWidth();
//    this.height = this.getHeight();
    this.width = 300;
    this.height = 300;
    img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    offscreenGraphics = img.createGraphics();
  }
  @Override
  public void paint(Graphics g) {
    if (img != null) {
      //super.paint(g);
      g.fillOval(100, 100, 50, 50);
      this.saveGraphics("blah1.jpg");
      System.out.println(g.drawImage(img, 0, 0, width, height, this));
      this.saveGraphics("blah2.jpg");
      //this.imageUpdate(img, 0, 0, 0, width, height);
      System.out.println("hi");
    }
  }

  public void setImage(BufferedImage img) {
    this.img = img;
  }
  
  @Override
  public Graphics2D getGraphics() {
    return offscreenGraphics;
  }
  
  public void saveGraphics(String filename)
	 {
      String extension = filename.substring(filename.lastIndexOf(".") + 1);

      // write file
      try
		{
        ImageIO.write(img, extension, new java.io.File(filename));
      }
		catch (java.io.IOException e)
		{
        System.err.println("Unable to save image:\n" + e);
      }
    }
}