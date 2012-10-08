
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * <put a program description here>
 * 
 * @author anichno
 * 
 * @version 1.0 - Apr 24, 2012 at 7:51:01 PM
 */
public class Math340Main {

  public static void main(String[] args) {
    String[] datasetFiles = new File("datasets").list();
    String[] setNames = new String[]{"Random", "Reversed", "Almost", "Sorted"};
    String[] sortNames = new String[]{"bubble", "optimized bubble", "insertion", "quick", "optimized quick", "merge", "optimized merge", "java's"};
    long time;
    int[][] testcaseFile = null;
    int[] testcase = null;
    for (int i = 3; i < datasetFiles.length; i++) {
      System.out.println("Reading Test Case " + (i + 1));
      testcaseFile = readTestCase("datasets/wa2dataset" + (i + 1) + ".csv");
      System.out.println("Test case " + (i + 1) + " is " + testcaseFile[0].length + " long");
      System.out.println();

      for (int j = 0; j < setNames.length; j++) {
        System.out.println("***" + setNames[j] + " Sorted***");
        for (int k = 0; k < sortNames.length; k++) {
          testcase = new int[testcaseFile[j].length];
          System.arraycopy(testcaseFile[j], 0, testcase, 0, testcaseFile[j].length);
          ArrayList<Integer> testcaseList = new ArrayList(testcaseFile[j].length);
          for (int index = 0; index < testcaseFile[j].length; index++) {
            testcaseList.add(testcase[index]);
          }
          
          time = System.currentTimeMillis();
          System.out.print("Sorting Test Case " + (i + 1) + " using " + sortNames[k] + " sort...");
          switch (k) {
            case 0:
              bubbleSort(testcase);
              break;
            case 1:
              bubbleSortOptimized(testcase);
              break;
            case 2:
              insertionSort(testcase);
              break;
            case 3:
              testcaseList = quickSort(testcaseList);
              break;
            case 4:
              testcase = quickSortOptimized(testcase, testcase.length);
              break;
            case 5:
              testcaseList = mergeSort(testcaseList);
              break;
            case 6:
              testcase = mergeSortOptimized(testcase);
              break;
            case 7:
              Arrays.sort(testcase);
              break;
          }
          time = System.currentTimeMillis() - time;
          boolean sortedCorrect = false;
          switch(k) {
            case 3:
              sortedCorrect = validateTestCaseList(testcaseList, testcaseFile[3]);
              break;
            case 5:
              sortedCorrect = validateTestCaseList(testcaseList, testcaseFile[3]);
              break;
            default:
              sortedCorrect = validateTestCase(testcase, testcaseFile[3]);
              break;
          }
          System.out.printf("%10s%s%10s%s\r\n", " ", "\tTook " + time + " ms to sort", " ", "\tSorted correctly: " + sortedCorrect);
        }
      }
      System.out.println("--------End Test Case " + (i + 1) + "--------\r\n");
    }
  }

  public static int[][] readTestCase(String fileName) {
    ArrayList<ArrayList<Integer>> fileArr = new ArrayList();
    for (int i = 0; i < 4; i++) {
      fileArr.add(new ArrayList(10000));
    }
    try {
      Scanner file = new Scanner(new File(fileName));
      file.nextLine();
      while (file.hasNextLine()) {
        String line = file.nextLine();
        Scanner lineParser = new Scanner(line);
        lineParser.useDelimiter(",");
        lineParser.next();
        for (int i = 0; lineParser.hasNext(); i++) {
          fileArr.get(i).add(lineParser.nextInt());
        }
      }
    } catch (FileNotFoundException ex) {
      System.out.println("Bad file");
    }
    int[][] returnFileArr = new int[fileArr.size()][];
    for (int i = 0; i < fileArr.size(); i++) {
      returnFileArr[i] = new int[fileArr.get(i).size()];
      for (int j = 0; j < fileArr.get(i).size(); j++) {
        returnFileArr[i][j] = fileArr.get(i).get(j).intValue();
      }
    }
    return returnFileArr;
  }

  public static boolean validateTestCase(int[] testcase, int[] sortedCase) {
    for (int i = 0; i < testcase.length; i++) {
      if (testcase[i] != sortedCase[i]) {
        return false;
      }
    }
    return true;
  }
  
  public static boolean validateTestCaseList(ArrayList<Integer> testcase, int[] sortedCase) {
    for (int i = 0; i < testcase.size(); i++) {
      if (!testcase.get(i).equals(sortedCase[i])) {
        System.out.println(testcase.get(i) + " : " + sortedCase[i]);
        return false;
      }
    }
    return true;
  }

  public static void bubbleSort(int[] intAry) {
    for (int i = 0; i < intAry.length - 1; i++) {
      for (int j = 0; j < intAry.length - 1; j++) {
        if (intAry[j] > intAry[j + 1]) {
          int temp = intAry[j];
          intAry[j] = intAry[j + 1];
          intAry[j + 1] = temp;
        }
      }
    }
  }

  public static void bubbleSortOptimized(int[] intAry) {
    boolean swapped;
    do {
      swapped = false;
      for (int j = 0; j < intAry.length - 1; j++) {
        if (intAry[j] > intAry[j + 1]) {
          int temp = intAry[j];
          intAry[j] = intAry[j + 1];
          intAry[j + 1] = temp;
          swapped = true;
        }
      }
    } while (swapped);
  }

  public static void insertionSort(int[] intAry) {
    for (int j = 1; j < intAry.length; j++) {
      int i = 0;
      while (intAry[j] > intAry[i]) {
        i++;
      }
      int m = intAry[j];
      for (int k = 0; k < (j - i); k++) {
        intAry[j - k] = intAry[j - k - 1];
      }
      intAry[i] = m;
    }
  }

  public static ArrayList<Integer> quickSort(ArrayList<Integer> intAry) {
    if (intAry.size() > 1) {
      ArrayList<Integer> L1 = new ArrayList(intAry.size());
      ArrayList<Integer> L2 = new ArrayList(intAry.size());
      int pivot = intAry.remove(intAry.size() / 2);
      for (int i = 0; i < intAry.size(); i++) {
        if (intAry.get(i) < pivot) {
          L1.add(intAry.get(i));
        } else {
          L2.add(intAry.get(i));
        }
      }
      L1 = quickSort(L1);
      L2 = quickSort(L2);
      intAry.clear();
      intAry.addAll(L1);
      intAry.add(pivot);
      intAry.addAll(L2);
    }
    return intAry;
  }

  public static int[] quickSortOptimized(int[] intAry, int arrLen) {
    int list1last = 0;
    int list2last = 0;
    if (arrLen > 1) {
      int pivot = intAry[arrLen / 2];
      intAry[arrLen / 2] = intAry[0];
      intAry[0] = pivot;
      int[] list1 = new int[arrLen];
      int[] list2 = new int[arrLen];
      for (int i = 1; i < arrLen; i++) {
        if (intAry[i] <= pivot) {
          list1[list1last++] = intAry[i];
        } else {
          list2[list2last++] = intAry[i];
        }
      }
      list1 = quickSortOptimized(list1, list1last);
      list2 = quickSortOptimized(list2, list2last);
      System.arraycopy(list1, 0, intAry, 0, list1last);
      intAry[list1last] = pivot;
      System.arraycopy(list2, 0, intAry, list1last + 1, list2last);
    }
    return intAry;
  }
  
  public static ArrayList<Integer> mergeSort(ArrayList<Integer> intAry) {
    if (intAry.size() > 1) {
      int m = intAry.size()/2;
      ArrayList<Integer> L1 = new ArrayList(intAry.size());
      ArrayList<Integer> L2 = new ArrayList(intAry.size());
      for (int i = 0; i < m; i++) {
        L1.add(intAry.get(i));
      }
      for (int i = m; i < intAry.size(); i++) {
        L2.add(intAry.get(i));
      }
      intAry = merge(mergeSort(L1),mergeSort(L2));
    }
    return intAry;
  }
  
  public static ArrayList<Integer> merge(ArrayList<Integer> arr1, ArrayList<Integer> arr2) {
    ArrayList<Integer> combined = new ArrayList(arr1.size()+arr2.size());
    while (!arr1.isEmpty() && !arr2.isEmpty()) {
      if (arr2.get(0) > arr1.get(0)) {
        combined.add(arr1.remove(0));
      }
      else {
        combined.add(arr2.remove(0));
      }
      if (arr1.isEmpty() || arr2.isEmpty()) {
        if (arr1.isEmpty()) {
          combined.addAll(arr2);
        }
        else {
          combined.addAll(arr1);
        }
      }
    }
    return combined;
  }

  public static int[] mergeSortOptimized(int[] intAry) {
    if (intAry.length > 1) {
      int m = intAry.length / 2;
      int[] L1 = new int[m];
      System.arraycopy(intAry, 0, L1, 0, m);
      int[] L2 = new int[(int) Math.ceil(intAry.length / 2d)];
      System.arraycopy(intAry, m, L2, 0, intAry.length - m);
      intAry = mergeOptimized(mergeSortOptimized(L1), mergeSortOptimized(L2));
    }
    return intAry;
  }

  public static int[] mergeOptimized(int[] arr1, int[] arr2) {
    int[] combined = new int[arr1.length + arr2.length];
    for (int arr1index = 0, arr2index = 0, combinedlast = 0; arr1index < arr1.length && arr2index < arr2.length;) {
      if (arr2[arr2index] > arr1[arr1index]) {
        combined[combinedlast++] = arr1[arr1index++];
      } else {
        combined[combinedlast++] = arr2[arr2index++];
      }
      if (arr1index == arr1.length || arr2index == arr2.length) {
        if (arr1index == arr1.length) {
          System.arraycopy(arr2, arr2index, combined, combinedlast, arr2.length - arr2index);
        } else {
          System.arraycopy(arr1, arr1index, combined, combinedlast, arr1.length - arr1index);
        }
      }
    }
    return combined;
  }
}
