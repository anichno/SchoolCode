
<<<<<<< HEAD
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <put a program description here>
 * 
 * @author anichno
 * 
 * @version 1.0 - Oct 24, 2011 at 6:32:43 PM
=======
/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Oct 25, 2011 at 2:08:07 PM
>>>>>>> 5dd997e21162b313a780f5f4e109252d59a332cf
 */
public class threadTest {

  public static void main(String[] args) throws InterruptedException {
<<<<<<< HEAD
    Runnable run = new threadfun();
    Thread fun = new Thread(run);
    
    while (true) {
      Thread.sleep(50);
    }
  }
  
  
}

class threadfun implements Runnable{
  private int thing = 0;
  public void addThing() {
    thing++;
  }
    public void run() {
      while (true) {
        try {
          System.out.println(thing);
          Thread.sleep(100);
        } catch (InterruptedException ex) {
          Logger.getLogger(threadTest.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
  }
=======
    int iterations = 0;
    while (true) {
      globalVars.overWriteMe = 0;
    Thread[] fun = new Thread[40];
    for (int counter = 0; counter < fun.length; counter++) {
        fun[counter] = new threadfun();
        fun[counter].start();
      }
    for (int counter = 0; counter < fun.length; counter++) {
      if (fun[counter].isAlive()) {
        counter = 0;
      }
        
    }
      //System.out.println(globalVars.overWriteMe);
      if (globalVars.overWriteMe != 5050*(fun.length)) {
        System.out.println(iterations);
        break;
      }
      iterations++;
    }
  }
}

class threadfun extends Thread {
    public void run() {
      for (int counter = 1; counter <= 100; counter++) {
      globalVars.overWriteMe += counter;
      }
    }
  }

class globalVars {
  public static int overWriteMe = 0;
}
>>>>>>> 5dd997e21162b313a780f5f4e109252d59a332cf
