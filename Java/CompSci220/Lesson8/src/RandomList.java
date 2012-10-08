import java.util.*;

public class RandomList 
{
    public static void main (String[ ] args) 
    {
      new RandomList ().run();
     } // method main

     public void run()
     {
        final int SEED = 111;
      
        List<Integer> randList = new ArrayList<Integer>();
      
        Random r = new Random (SEED);
      
        // Insert 10 random integers, in the range 0...99, into randList:
        for (int i = 0; i < 10; i++)
             randList.add (r.nextInt(100)); // insertion
      
        // Print out randList:
        System.out.println (randList);  
      
        // See if 22 is in randList:
        if (randList.contains (22))
             System.out.println ("Yes, 22 is in randList.");
        else
             System.out.println ("No, 22 is not in randList.");
      
        // Print out the Integer at index 3:
        System.out.println (randList.get (3) + " is at index 3");
      
        // Remove the Integer at index 6:
        randList.remove (6);
      
        // Insert a new random Integer at index 5:
        randList.add (5, r.nextInt (100));

        // Print out randList.
        System.out.println (randList);
      
        // Remove all even Integers:
        Iterator<Integer> itr = randList.iterator();
        while (itr.hasNext())        
             if (itr.next() % 2 == 0)
                 itr.remove();
               
         // Print out randList;
         System.out.println (randList);
 } // method run

} // class RandomList
