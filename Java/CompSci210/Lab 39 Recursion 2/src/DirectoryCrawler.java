
/**
 * Description: This program prompts the user for a file or directory name
 *              and shows a listing of all files and directories that can be
 *              reached from it (including subdirectories).
 *
 * @author  Dr. Brown, Fall 2011
 *
 * @version 1.0
 *
 */
import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;

public class DirectoryCrawler {
  static int filesDeleted = 0;
  //-------------------------------------------------------------------

  public static void main(String[] args) {
    // Allow the user to select a directory from a dialog box.
//    JFileChooser fc = new JFileChooser();
//    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//    int returnValue = fc.showOpenDialog(null);
//    
    int returnValue = 0;
    if (returnValue == 0) {
      //File myFile = fc.getSelectedFile();
      File myFile = new File("C:/Users/C14Anthony.Canino/Code-Archives/Lab 39 Recursion 2");
      listFileNames(myFile, 0);
    }
    System.out.println("\r\nFiles Deleted: " + filesDeleted);
  }

  //-------------------------------------------------------------------
  // Prints information for the given file/directory using the
  // given level of indentation
  public static void listFileNames(File file, int level) {
    // Print this file name
    if (file.isDirectory() || file.getName().matches(".*\\.class")) {
    printLeadingSpaces(level * 2);
    }
    if (file.isDirectory()) {
      System.out.println(file.getName());
    }
    if (file.getName().matches(".*\\.class")) {
      if (file.delete()) {
        System.out.println(file.getName() + " - Deleted");
        filesDeleted++;
      }
    }    

    if (file.isDirectory() && file.canRead()) {
      // The current file is a directory. List all of its files.
      File[] list = file.listFiles();
      for (int j = 0; j < list.length; j++) {
        listFileNames(list[j], level + 1);
      }
    }
  }

  //-------------------------------------------------------------------
  public static void printLeadingSpaces(int numberOfSpaces) {
    for (int j = 0; j < numberOfSpaces; j++) {
      System.out.print(" ");
    }
  }
}