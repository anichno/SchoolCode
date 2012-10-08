
/**
 * First column widest
 * 
 * @author anichno
 * 
 * @version 1.0 - Nov 8, 2011 at 9:41:29 PM
 */
import java.awt.Container;
import java.awt.GridLayout;

public class myGridLayout extends GridLayout {

  private int totRows = 0;
  private int totColumns = 0;

  public myGridLayout(int rows, int columns) {
    super(rows, columns);
    this.totRows = rows;
    this.totColumns = columns;
  }

  @Override
  public void layoutContainer(Container parent) {
    int width = parent.getWidth();
    int height = parent.getHeight();
    int numComponents = parent.getComponentCount();
    int widgetWidth = 0;
    for (int row = 0, rowSpacer = 0, i = 0; row < totRows && i < numComponents; row++, rowSpacer += height / totRows) {
      for (int column = 0, colSpacer = 0; column < totColumns && i < numComponents; column++, i++) {
        if (column == 0) {
          widgetWidth = (width / 2);
        } else {
          widgetWidth = ((width / 2) / (totColumns - 1));
        }
        parent.getComponent(i).setBounds(colSpacer, rowSpacer, widgetWidth, height / totRows);
        colSpacer += widgetWidth;
      }
    }

  }
}
