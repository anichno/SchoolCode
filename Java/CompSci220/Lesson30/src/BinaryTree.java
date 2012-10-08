/** * Description: This class represents a Binary Search Tree All nodes in the
 * tree satisfy the property that the value in the node is larger than all * values in nodes in the left sub-tree and smaller than all values in nodes * in the right sub-tree. 
* @author Randall.Bower */ 
public class BinaryTree<T extends Comparable> 
private class Node<T> T item; Node<T> left; Node<T> right; 
) 
// A node reference for the root of the tree is the only attribute needed. // Note: Since object references are set to null by default, there is no // constructor needed to create a new, empty, binary tree. private Node root; 
/** * Inserts an item into this BinaryTree, ensuring the property * that all values to the left of a given node are smaller and * all values to the right of a given node are larger. 
* @param item The item to be inserted. *1 public void insert( T item ) this.root = this.insert( this.root, item ); ) 
// Recursive helper method for insert. private Node<T> insert( Node<T> curr, T item ) // All nodes are inserted into a tree as a leaf. if( curr == null ) // Create the new leaf node and return it to the previous call to // insert that is waiting to set the parent's left or right child. curr = new Node<T>f item ); 
return curr; 
/** * Determines if this binary tree contains the given * @parmn item The item to be searched for. * @return True if this tree contains the itemfalse otherwise. 
// Recursive helper method for contains. private boolean contains( Node<T> curr, T item ) if( curr == null ) // Reached a left ... 
/** * Removes the given item from the tree. 
* @param item The item to be removed. */ public void remove( T item ) this.root = this.remove( this.root, item ); ) 
// Recusrive helper method for remove. private Node<T> remove( Node<T> curr, T item ) 
return curr; ) 
/** * Finds the smallest item in this binary tree. * @return The smallest item in this binary tree null if the tree is empty. public T smallest() 
) 
// Helper method for smallest. Not recursive and not necessary to find // the smallest item in the entire tree, but used internally to find the // smallest item in a sub-tree. private T smallest( Node<T> curr ) 
) 
/** * Finds the largest item in this binary tree. * @return The largest item in this binary tree; null if the tree is empty. 
) 
// Helper method for largest. Not recursive and not necessary to find // the largest item in the entire tree, but used internally to find the // largest item in a sub-tree. private T largest( Node<T> curr ) The rightmost item is largest. while( curr.right I= null ) curr = curr.right; 
) 
) 
) 
) 
) 
public Node( T item ) 
this.item = item; this.left = null; // Not really necessary. this.right = null; // Not really necessary. 
else if( item.compareTo( curr.item ) < 0) 
// Item is smaller than the item in the current node, so go left. curr.left = insert( curr.left, item ); else // Item is larger than the item in the current node, so go right. curr.right = insert( curr.right, item ); 
*/ ublic boolean contains( T item ) 
return this.contains( this.root, item ); 
return false; // ... item is not in the tree. 
else if( item.compareTo( curr.item ) < 3 ) // Item is smaller than current item, 
return this.contains( curr.left, item ); // look left. 
else if( item.compareTo( curr.item ) > D ) // Item is larger than current item, 
return this.contains( curr.right, item ); // look right. 
else // Item must be equal to the current item, 
return true, // Found it! 
// The base case is curr == null, but there's nothing to do // so the code tests the opposite before continuing. if( curr I= null ) f( item.compareTo( curr.item ) < 0 ) // Item smaller, look left. 
curr.left = this.remove( curr.left, item ); 
else if( item.compareTo( curr.item ) > 0. ) // Item is larger, look right. curr.right = this.remove( curr.right, item ); 
else // Found it! 
// Note: This first test will also be true if curr is a leaf and curr // will be set to null, which is the desired behavior for a leaf node. if( curr.left == null ) // Only a right child, curr = curr.right; // so move it up. 
else if( curr.right == null ) // Only a left child, curr = curr.left; // so move it up. 
else // Must be two children. // Repace the item in the current node with the smallest node in the right sub-tree. curr.item = this.smallest( curr.right ); // Now remove the item copied into curr from the right sub-tree so we don't have two copies of it. curr.right = this.remove( curr.right, curr.item ); 
return this.smallest( this.root ); 
// The leftmost item is smallest. while( curr.left != null ) curr = curr.left; return curr.item; 
*/ ublic T largest() 
return this.largest( this.root ); 
return curr.item; 
/** * Returns a string containing a breadth-first traversal of nodes in this tree. * @return A string containing a breadth-first traversal of nodes in this tree. */ public String breadth() // The StringBuilder class is a more efficient way to append to a string. StringBuilder sb = new stringBuilder(); if( this.root I= null ) // Nodes are placed in a queue when their parents are visited. // FIFO removal from the queue results in all nodes at a given // level being visited before any nodes at a lower level. java.util.Queue<Node> queue = new java.util.LinkedList(); queue.add( this.root ); while( lqueue.isEmpty() ) 
Node curr = queue.remove(); sb.append( curr.item ); sb.append( " " ); if( curr.left I= null ) queue.add( curr.left ); 
if( curr.right != null ) queue.add( curr.right ); 
return sb.toString().trim(); 
/** * Determines if the tree is empty. * @return True if this tree contains no elements; false otherwise. */ public boolean isEmpty() 
return this.root == null; 
/** * Determines of two binary trees contain the same items AND are structurally equal. * @parmn obj The BinaryTree to compare this BinaryTree to. * @return True if the trees are equal; false otherwise. @Override public boolean equals( Object obj ) 
if( obj instanceof BinaryTree ) return this.equals( this.root, ((BinaryTree)obj).root ); else return false; 
// Recursive helper method for equals. private boolean equals( Node<T> one, Node<T> two ) 
return ( one == null && two == null ) II ( one I= null && two I= null 66 one.item.equals( two.item ) 66 this.equals( one.left, two.left ) && this.equals( one.right, two.right ) ); 
/** * Returns the size of the tree (the number of nodes in the tree). 
* @return The size of this tree. */ public int size() return this.size( this.root 
// Recursive helper method for size. private int size( Node<T> curr ) 
return curr null o 0 : I + this.size( ourr.left ) + this. size( curr.right 

* Returns the height of the tree; the number of branches between the root * and the deepest leaf. The height of the root is zero and the height of * an empty tree is -1. 
* @return The height of this tree. public int height() return this.height( this.root ); 
// Recursive helper method for height. private int height( Node<T> curr ) return curr == null -I : I + Math.max his.height( curr.left ). this.height( curr.right ) ); 
* Creates and returns a string with an in-order traversal of this tree. 
* @return An in-order traversal of this tree. ✓ie public String inOrder() return this.inOrder( this.root ).tri ); 
// Recursive helper method for inOrder. private String inOrder( Node<T> curr ) 
* Creates and returns a string with a pre-order traversal of this tree. 
* @return A pre-order traversal of this tree. public String preOrder() return this.preOrder( this.root ).trim() 
// Recursive helper method for preOrder. private String preOrder( Node<T> curr ) 
if( curr == null ) 
else return this.inOrder( curr.left ) + curr.item + " " + this.inOrder( curr.right ); 
if( curr == null ) 
else return curr.item + " " + this.preOrder( curr.left + this.preOrder( curr.right ); 
[kr * Creates and returns a string with a post-order traversal of this tree. * @return A post-order traversal of this tree. public String postOrder() return this.postOrder( this.root ).trim 
// Recursive helper method for postOrder. private String postOrder( Node<T> curr ) 
if( curr == null ) 
else return this.postOrder( curr.left ) + this.postOrder( curr.right ) + curr.itam + 
ja-A-* Creates and returns a string representation of this binary tree, which * is an in-order traversal of the tree's nodes. 
* @return A string containing an in-order traversal of this binary tree's nodes. @Override public String toString() return this . toString ( this . root rim(); 
// Recursive helper method for toString. private String toStrin 4( Node<T> curr ) 
if( curr == null ) 
else return toStrin 4( curr.left ) + curr.item + " " + tostring( curr.right ); 
/** * A handy way to view the contents of a tree; when viewing, touch * your left ear to your left shoulder and it looks like a tree! public void display() this . disp laY( this . root, 0 ); 
// Recursive helper method for display. private void display( Node<T> curr, int depth ) if( curr == null ) return 
else 
return 
return 
return 
return 
this.displa Y( curr.right, depth + ); this.display( depth * curr.item ); this.disp laYi curr.left, depth +  
for( int i = i < n; i++ ) 
System.out.print( " " ); 
System.out.println( item ); 
// Optionally save the tree image. if( save ) 
filename = "Tree.jpg" 
filename += 
panel.saveGraphics( filename ); 
panel.waitForKeyHit(); panel.closeWindow(); 
tree.insert( data[ i ] ); 
// Displays an item preceeded by spaces to show depth in the tree. private void display( int n, T item ) 
/** * Draws this tree in a DrawingPanel window. public void draw( boolean save ) // Calculate the height of the tree and then the maximum number of leaves if the // tree is full. Use these values to determine the size of the DrawingPanel. int height = this.height(); int leaves = (int)Math.pow( 2, height ); // Room for all the leaves plus one NODE_WIDTH between them and one-half NODE_SIZE margin on the edges. // Use Math.max to ensure there's room for a tree with less than two levels. int windowWidth = Math.max( ( leaves - I ) * NODE_SIZE * 2, NODE_SIZE * 5 ); // Room for each level plus some one-half NODE_SIZE margin plus one NODE_SIZE at the top for the expression. // Use Math.max to ensure there's room for a tree with less than two levels. int windowHeight = Math.max( height + I ) * 2 * NODE_SIZE + NODE_SIZE, NODE_SIZE * 3 ); DrawingPanel panel = new DrawingPanel( windowWidth, windowHeight ); 
// Make the DrawingPanel pretty. panel.setWindowTitle( "Press any key to close window..." ); panel.setBackground( ava.awt.color.wHITE ); java.awt.Graphics2D g = panel.getGraphics(); g.setFont( new java.awt.Font( ava.awt.Font.sERIF, java.a Font.BOLD, NODE_SIZE * 2 / 3 ) ); java.awt.FontMetrics fm = g.getFontmetrics(); 
// Draw the tree contents across the top of the DrawingPanel. g.drawStrin g( this.toString(), windowWidth / 2 - fm.stringWidth( this.toStrin g() ) / 2, NODE_SIZE ); 
// Draw the lines first, between the center of the Node circles, so the Node // circles will be drawn on top of the lines and everything will look nice. this . drawiines( gr this.root, 0, windowWidth, NODE_SIZE * 2, windowWidth / 2, NODE_SIZE * 2 ); this . drawnodes( gr this.root, 0, windowWidth, NODE_SIZE * 2 ); panel.copyGraphicsToScreen(); 
String filename = javax.swing.SOptionPane.showInputDialog( "Enter file name if( filename == null II filename.length() == 0 ) 
) else if( !filename.endswith( ".jpg" ) ) ( 
// This node size is large enough to look nice with two-digit numbers. // Three digit numbers are acceptable, but anything more looks bad. private final static int NODE_SIZE = 32; 
// Nodes are drawn separately (after) the lines so the nodes can be // drawn on top of the lines and everything looks nice. // The leftx and rightx parameters are the boundaries between which // the sub-tree rooted at the current node must be drawn. private void drawNodes( java.awt.Graphics2D g, Node curr, int leftx, int rightx, int y ) ( if( curr I= null ) 
// Calculate the center of the available drawing area. int x = ( leftx + rightx ) / 2; // Fill in the background of the node to draw over the ends of the lines. g.setColor( ava.awt.Color.WHITE ); g.fillOval( x - NODE_SIZE / 2, y - NODE_SIZE / 2, NODE_SIZE, NODE_SIZE ); // Draw the outline of the node. g.setColor( ava.awt.Color.BLACK ); g.drawOval( x - NODE_SIZE / 2, y - NODE_SIZE / 2, NODE_SIZE, NODE_SIZE ); // Draw the contents of the node, centered within the node. java.awt.FontMetrics fm = g.getFontmetrics(); g.drawStrin 4( curr.item.toString(), x - fm.stringWidth( curr.item.tostring() ) / 2, y + NODE_SIZE / 4 ); // Recursively draw the children. drawNodes( g, curr.left, leftx, x, y + NODE_SIZE * 2 ); drawNodes( g, curr.right, x, rightx, y + NODE_SIZE * 2 ); 
// Lines are drawn separately (before) the nodes so the lines // drawn from the center point of the nodes but then be drawn // the node itself resulting in everything looking all pretty // Drawing lines requires x and y information for the current // but also x and y position for the parent node. private void drawLines( java.awt.Graphics2D g, Node curr, int int parentx, int parenty ) 
BinaryTree<Integer> tree = new BinaryTree(); 
int[] data = ( 42, 37, 40, 12, 25, 67, 88, 75, SO, 55, 99 1; for( int i = 0; i < data.length; i++ ) ( 
System.out.println( "inOrder; " + tree . inOrder () ) ; System.out.println( "preOrder: + tree.preOrder() ); System.out.println( "postCrder: " + tree.postOrder() ); tree.display(); 
// Change the parameter to true to save the tree image. tree.draw( false ); 
can be over by and stuff. node, 
/** * A binary tree would not normally have a main method, but it is handy * here to demonstrate basic functionality. * @param args Command line arguments; ignored. */ public static void main( String[] args ) ( 
leftx, int rightx, int y, 
if( curr null ) ( // Calculate the center of the available drawing area. int x = ( leftx + rightx ) / 2; // Draw lines from the center of this node to the center of the parent node. g.setColor( ava.awt.color.BLACK ); g.drawLine( x, y, parentx, parenty ); // Recursively draw the children. drawLines( g, curr.left, leftx, x, y + NODE_SIZE * 2, x, y ); drawLines( g, curr.right, x, rightx, y + NODE_SIZE * 2, x, y ); 
