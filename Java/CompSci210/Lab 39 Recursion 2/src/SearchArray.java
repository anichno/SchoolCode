
/**
 * Description: Search an array for a value.
 *
 * @author  Dr. Brown
 *
 * @version 1.0   Fall 2011
 *
 */
public class SearchArray {
  //-------------------------------------------------------------------

  public static void main(String[] args) {

    int[] list = {34, 56, 12, 18, 4, 9, 23, 92, 7};

    System.out.println("found 56 at position " + search(list, 56));
    System.out.println("found  4 at position " + search(list, 4));
    System.out.println("found  2 at position " + search(list, 2));

    System.out.println("-------------");

    System.out.println("found 56 at position " + search2(list, 0, 56));
    System.out.println("found  4 at position " + search2(list, 0, 4));
    System.out.println("found  2 at position " + search2(list, 0, 2));

  }

  //-------------------------------------------------------------------
  // A non-recursive (iterative) search method that returns the position
  // of a value in an array. If the item is not found, returns -1.
  public static int search(int[] list, int value) {
    for (int j = 0; j < list.length; j++) {
      if (list[j] == value) {
        return j;
      }
    }

    return -1;
  }

  //-------------------------------------------------------------------
  // A recursive search method.
  public static int search2(int[] list, int startIndex, int value) {
    if (startIndex < list.length) {
      if (list[startIndex] == value) {
        return startIndex;
      } else {
        return search2(list, startIndex + 1, value);
      }
    } else {
      return -1;
    }
  }
}
