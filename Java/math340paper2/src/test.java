
import java.util.ArrayList;
import java.util.LinkedList;


/**
 * <put a program description here>
 * 
 * @author anichno
 * 
 * @version 1.0 - Apr 25, 2012 at 5:38:04 PM
 */
public class test {

  public static void main(String[] args) {
    long time;
    int temp;
    Integer compareInt = new Integer(100);
    ArrayList<Integer> list = new ArrayList();
    for (int i = 0; i <= 10000000; i++) {
      list.add(i);
    }
    
    time = System.currentTimeMillis();
    for (int i = 0; i < 10000000; i++) {
      temp = list.get(10000000-i).compareTo(compareInt);
    }
    time = System.currentTimeMillis() - time;
    System.out.println(time);
    
    int[] listar = new int[10000001];
    for (int i = 0; i <= 10000000; i++) {
      listar[i] = i;
    }
    
    time = System.currentTimeMillis();
    for (int i = 0; i < 10000000; i++) {
      temp = listar[10000000-i];
    }
    time = System.currentTimeMillis() - time;
    System.out.println(time);
  }
}
