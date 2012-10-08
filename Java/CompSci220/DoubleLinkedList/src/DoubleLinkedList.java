
/**
 * Implementation of a linked list
 * @param <E> 
 * @author jeff.boleng
 * @author anthony.canino & michael.gallagher
 */
//TODO finish tail
public class DoubleLinkedList<E> {

  /**
   * Individual node in the linked list
   */
  protected class Node {

    E value;
    Node next;
    Node prev;

    /**
     * default node constructor
     */
    protected Node() {
      this(null);
    }

    /**
     * Node constructor that accepts an element as a parameter.
     * @param s
     */
    protected Node(E s) {
      this.value = s;
      this.next = null;
      this.prev = null;
    }
  }
  Node head;
  Node tail;

  /**
   * Constructor for linked list, creates "head" node
   */
  public DoubleLinkedList() {
    this.head = null;
    this.tail = null;
  }

  /**
   * Returns whether the linked list is empty.
   * @return
   */
  public boolean isEmpty() {
    return (head == null);
  }

  /**
   * Returns the size of the linked list.
   * @return
   */
  public int size() {
    int count = 0;
    for (Node current = head; current != null; current = current.next) {
      count++;
    }
    return count;
  }

  /**
   * Returns if the parameter "s" is contained in the linked list.
   * @param s
   * @return
   */
  public boolean contains(E s) {
    return containsRec(s, head);
  }

  private boolean containsRec(E s, Node current) {
    if (current != null) {
      if (current.value.equals(s)) {
        return true;
      } else {

        return containsRec(s, current.next);
      }
    }
    return false;
  }

  /**
   * Adds an element to the front of the linked list at the node "head".
   * @param s
   */
  public void addToFront(E s) {
    Node temp = new Node(s);
    temp.next = head;
    temp.prev = null;
    if (temp.next != null) {
      temp.next.prev = temp;
    }
    head = temp;
  }

  /**
   * Adds an element to the end of the linked list.
   * @param s
   */
  public void append(E s) {
    Node last = null;
    for (Node current = head; current != null; current = current.next) {
      last = current;
    }
    Node temp = new Node(s);
    temp.prev = last;
    if (head == null) {
      head = temp;
    } else {
      last.next = temp;
      tail = temp;
    }
  }

  /**
   * Removes the desired element from the linked list.
   * @param s
   */
  public void remove(E s) {
    for (Node current = head; current != null; current = current.next) {
      if (current.value.equals(s)) { // note:  this works because String implements .equals
        // remove it
        if (current.prev != null) {
          current.prev.next = current.next;
          current.next.prev = current.prev;
          break;
        }
      }
    }
  }

  /**
   * Replaces an undesired element with a more desirable one.
   * @param element
   * @param newValue
   */
  public void replace(E element, E newValue) {
    for (Node current = head; current != null; current = current.next) {
      if (current.value.equals(element)) { // note:  this works because String implements .equals
        current.value = newValue;
        break;
      }
    }
  }

  /**
   * Replaces all elements in the linked list
   * @param element
   * @param newValue
   */
  public void replaceAll(E element, E newValue) {
    for (Node current = head; current != null; current = current.next) {
      if (current.value.equals(element)) { // note:  this works because String implements .equals
        current.value = newValue;
      }
    }
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    for (Node current = head; current != null; current = current.next) {
      if (current == head) {
        result.append("<head> <--> ");
      } else {
        result.append(" <--> ");
      }
      result.append(current.value);
    }
    return result.toString();
  }
}