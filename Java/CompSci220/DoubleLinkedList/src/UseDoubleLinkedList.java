/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jeff.boleng
 */
public class UseDoubleLinkedList {

  public static void main(String[] args) {

    DoubleLinkedList<String> myList = new DoubleLinkedList<String>();

    System.out.println("Right now myList is empty:  " + myList.isEmpty());

    myList.addToFront("Mary");
    myList.addToFront("had");
    myList.addToFront("a");
    myList.addToFront("little");
    myList.addToFront("lamb");
    myList.remove("a");
    myList.replace("little", "big");

    System.out.println("myList contains little:  " + myList.contains("little"));
    System.out.println("myList has " + myList.size() + " elements.");

    System.out.println("Here is myList:  " + myList.toString());

    DoubleLinkedList<Double> otherList = new DoubleLinkedList<Double>();
    System.out.println("Right now otherList is empty:  " + otherList.isEmpty());
    otherList.addToFront(3.14159);
    otherList.addToFront(2.719291929);
    otherList.addToFront(0.0);
    otherList.addToFront(42.0);
    otherList.addToFront(7.0);
    otherList.append(31337.0);

    System.out.println("otherList contains 42.0:  " + otherList.contains(42.0));
    System.out.println("otherList has " + otherList.size() + " elements.");

    System.out.println("Here is otherList:  " +  otherList.toString());
    
    DoubleLinkedList<String> replaceStuff = new DoubleLinkedList<String>();

    replaceStuff.addToFront("blah");
    replaceStuff.addToFront("blah");
    replaceStuff.addToFront("blah");
    replaceStuff.addToFront("blah");
    replaceStuff.addToFront("blah");
    
    System.out.println(replaceStuff);
    
    replaceStuff.replaceAll("blah", "lulz");
    
    System.out.println(replaceStuff);
    
    DoubleLinkedList<DoubleLinkedList<String>> forTheLulz = new DoubleLinkedList();
    forTheLulz.addToFront(myList);
    forTheLulz.addToFront(replaceStuff);
    System.out.println(forTheLulz);
    
    DoubleLinkedList<Integer> appendTest = new DoubleLinkedList();
    appendTest.append(1);
    appendTest.append(2);
    appendTest.append(3);
    appendTest.remove(2);
    System.out.println(appendTest);
  }
}
