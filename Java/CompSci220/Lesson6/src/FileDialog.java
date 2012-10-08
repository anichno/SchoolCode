import javax.swing.*;
import java.util.*;
import javax.swing.filechooser.*;
import java.io.*;

/**
 *
 * @author jeff.boleng
 */
public class FileDialog {

    public static Scanner selectTextFile() {
      do
      {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Text/Java Files", "txt", "java");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        try
        {
          if (returnVal == JFileChooser.APPROVE_OPTION)
          {
            return new Scanner(chooser.getSelectedFile());
          } else {
            return null;
          }
        } catch (FileNotFoundException e) {
          JOptionPane.showMessageDialog(null, "Invalid File!", "error",
                  JOptionPane.ERROR_MESSAGE);
        }
      } while (true);
    }
}
