
/**
 * prints the twelve days song using embedded loops
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 10, 2011 at 12:31:10 PM
 */
public class twelve_days {

  public static void main(String[] args) {
    int a, b = 0;

    for (a = 0; a < 12; a++) {
      System.out.print("On the ");
      day(a);
      System.out.println(" day of Christmas,");
      System.out.println("my true love sent to me");

      for (b = a; b >= 0; b--) {
        lyric(b);
      }
      System.out.println();
    }
  }

  public static void day(int a) {
    switch (a) {
      case 0:
        System.out.print("first");
        break;
      case 1:
        System.out.print("second");
        break;
      case 2:
        System.out.print("third");
        break;
      case 3:
        System.out.print("fourth");
        break;
      case 4:
        System.out.print("fifth");
        break;
      case 5:
        System.out.print("sixth");
        break;
      case 6:
        System.out.print("seventh");
        break;
      case 7:
        System.out.print("eighth");
        break;
      case 8:
        System.out.print("nineth");
        break;
      case 9:
        System.out.print("tenth");
        break;
      case 10:
        System.out.print("eleventh");
        break;
      case 11:
        System.out.print("twelfth");
        break;
    }
  }

  public static void lyric(int b) {
    switch (b) {
      case 0:
        System.out.println("a partridge in a pear tree.");
        break;
      case 1:
        System.out.println("two turtle doves, and");
        break;
      case 2:
        System.out.println("three French hens,");
        break;
      case 3:
        System.out.println("four calling birds,");
        break;
      case 4:
        System.out.println("five golden rings,");
        break;
      case 5:
        System.out.println("six geese a-laying,");
        break;
      case 6:
        System.out.println("seven swans a-swimming,");
        break;
      case 7:
        System.out.println("eight maids a-milking,");
        break;
      case 8:
        System.out.println("nine ladies dancing,");
        break;
      case 9:
        System.out.println("ten lords a-leaping,");
        break;
      case 10:
        System.out.println("eleven pipers piping,");
        break;
      case 11:
        System.out.println("Twelve drummers drumming,");
        break;
    }
  }
}
