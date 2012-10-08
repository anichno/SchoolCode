
import java.util.Scanner;


/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Jan 18, 2012 at 9:34:57 AM
 */
public class ClientCode {

  public static void main(String[] args) {
    NameAndAge[] people = new NameAndAge[100];
    int curIndex = 0;
    Scanner input = FileDialog.selectTextFile();
    
    while (input.hasNextLine()) {
      String line = input.nextLine();
      Scanner lineParse = new Scanner(line);
      people[curIndex].setName(lineParse.next());
      people[curIndex].setAge(lineParse.nextInt());
      curIndex++;
    }
    
    for (int i = 0; i < curIndex; i++) {
      System.out.println(people[i]);
    }
  }
}
