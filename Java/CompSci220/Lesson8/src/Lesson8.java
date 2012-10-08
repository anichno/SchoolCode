
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Jan 24, 2012 at 9:29:06 AM
 */
public class Lesson8 {

  public static void main(String[] args) {
    final int SEED = 1337;
    final int SIZE = 100000;
    long time = 0;
      
        List<Integer> randList = new ArrayList<Integer>();
      
        Random r = new Random (SEED);
      
        for (int i = 0; i < SIZE; i++)
             randList.add (r.nextInt(100)); // insertion
      
        time = System.currentTimeMillis();
        randList.get (SIZE/2);
        time = System.currentTimeMillis()-time;
        System.out.println("Array List get: " + time);
      
        // Remove all even Integers:
        time = System.currentTimeMillis();
        Iterator<Integer> itr = randList.iterator();
        while (itr.hasNext())        
             if (itr.next() % 2 == 0)
                 itr.remove();
               
        time = System.currentTimeMillis()-time;
         System.out.println ("Array List removals: " + time);
         
         // Linked List
         randList = new LinkedList<Integer>();
         
         for (int i = 0; i < SIZE; i++)
             randList.add (r.nextInt(100)); // insertion
      
        time = System.currentTimeMillis();
        randList.get (SIZE/2);
        time = System.currentTimeMillis()-time;
        System.out.println("Linked List get: " + time);
      
        // Remove all even Integers:
        time = System.currentTimeMillis();
        itr = randList.iterator();
        while (itr.hasNext())        
             if (itr.next() % 2 == 0)
                 itr.remove();
               
        time = System.currentTimeMillis()-time;
         // Print out randList;
         System.out.println ("Linked List remove: " + time);
  }
}
