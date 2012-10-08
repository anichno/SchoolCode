
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

/**
 * Description: Uses the SimpleWeightedGraph class from http://www.jgrapht.org/
 * to represent a graph to play the Shannon Switching Game:
 * http://en.wikipedia.org/wiki/Shannon_switching_game
 *
 * Documentation Statement: We received no help on this assignment
 *
 * @author C14Anthony.Canino
 * @author C14Michael.Gallagher
 * @author Randall.Bower
 */
public class ShannonSwitchingGraph {

  private final int MINDISTANCE = 50;
  // Vertices are represented with the java.awt.Point class,
  // edges use the DefaultWeightedEdge class from JGraphT.
  private SimpleWeightedGraph<Point, DefaultWeightedEdge> graph;
  // The start and end vertices the players are trying to connect/cut.
  private Point start, end;
  // The dimensions of the graphic area where this graph will be displayed.
  private int width, height;
  // A random number generator used to create the random vertices and edges.
  private Random rand;
  private int numVerts;
  private DijkstraShortestPath<Point, DefaultWeightedEdge> pathSolver;
  
  /**
   * Constructs a new graph for the Shannon Switching Game such that all
   * vertices are within the given dimensions of a graphic display.
   *
   * @param width With of area on which this graph will be displayed.
   * @param height Height of area on which this graph will be displayed.
   */
  public ShannonSwitchingGraph(int width, int height) {
    // The dimensions of the graphic area on which this graph will be displayed.
    this.width = width;
    this.height = height;

    // Random number generated used in creating both vertices and edges.
    // Add a seed here to generate the same graph every time (for testing).
    this.rand = new Random();

    // Create the graph, vertices, and edges.
    this.graph = new SimpleWeightedGraph(DefaultWeightedEdge.class);
    createVertices();
    createEdges();
  }

  // Creates random vertices within the dimensions of the graphic area
  // on which this graph will be displayed while ensuring vertices are
  // spaced appropriately from the edges and each other.
  private void createVertices() {
    numVerts = (this.width * this.height) / 10000 * 2;
    LinkedList<Point> goodPoints = new LinkedList();

    this.start = new Point(50,50);
    this.end = new Point(this.width - 50, this.height - 50);
    goodPoints.add(this.start);
    goodPoints.add(this.end);

    for (int i = 0; i < numVerts; i++) {
      boolean pointAdded = false;
      while (!pointAdded) {
          //Creates vertex with random x and y-coordinates that are a certain
          //distance away from the edges of the screen.
        int xpos = 14 + this.rand.nextInt(this.width - 14 * 2); 
        int ypos = 14 + this.rand.nextInt(this.height - 14 * 2);
        Point candidatePoint = new Point(xpos, ypos);
        if (!goodPoints.isEmpty()) {
          boolean distGood = true;
          for (Point point : goodPoints) {
              //If the possible vertex distance is less than the mindistance
              //then the point is not a candidate to be created.
            if (candidatePoint.distance(point) < MINDISTANCE) {
              distGood = false;
            }
          }
          if (distGood) {
            goodPoints.add(candidatePoint);
            pointAdded = true;
          }
        } else {
          goodPoints.add(candidatePoint);
          pointAdded = true;
        }
      }
    }
    for (Point point : goodPoints) {
      this.graph.addVertex(point);
    }
  }

  // Randomly creates edges attempting to make the degree of each vertex between 3 and 5.
  // Must ensure no two edges overlap, so some vertices may end up with a smaller degree
  // (especially those near the corners of the graph ... which is why the start and end
  // vertices are not placed completely in the corners!)
  private void createEdges() {
    Set<Point> graphVertices = this.graph.vertexSet();
    //Creates a new priority queue called edge queue
    PriorityQueue<DefaultWeightedEdge> edgeQueue = new PriorityQueue(numVerts, new DistanceComparator(this.graph));

    for (Point p1 : graphVertices) {
      for (Point p2 : graphVertices) {
        if (p1.equals(p2)) {
          continue;
        }
        //Creates a new weighted edge
        DefaultWeightedEdge newEdge = this.graph.addEdge(p1, p2);
        if (newEdge != null) {
          edgeQueue.add(newEdge);
          this.graph.removeEdge(p1, p2);
        }
      }

      LinkedList<Line2D.Double> edgeList = new LinkedList();
      boolean candidateLineIsGood = true;
      while (this.graph.degreeOf(p1) < 3 && !edgeQueue.isEmpty()) {
          //The below code ensures that edges do not cross and makes the candidate
          //line a good candidate if it does not cross another edge
        DefaultWeightedEdge candidateEdge = edgeQueue.remove();
        Line2D.Double candidateLine = new Line2D.Double(this.graph.getEdgeSource(candidateEdge), this.graph.getEdgeTarget(candidateEdge));
        candidateLine.x1 = Math.min(candidateLine.x1, candidateLine.x2)+1;
        candidateLine.x2 = Math.max(candidateLine.x1, candidateLine.x2)-1;
        candidateLine.y1 = Math.min(candidateLine.y1, candidateLine.y2)+1;
        candidateLine.y2 = Math.max(candidateLine.y1, candidateLine.y2)-1;
        if (this.graph.degreeOf(this.graph.getEdgeTarget(candidateEdge)) < 5) {
          candidateLineIsGood = true;
          for (Line2D.Double existingLines : edgeList) {
            if (candidateLine.intersectsLine(existingLines)) {
              candidateLineIsGood = false;
              break;
            }
          }
          //if the edge is a good candidate then it is added to the edgeList
          if (candidateLineIsGood) {
            edgeList.add(candidateLine);
            this.graph.setEdgeWeight(this.graph.addEdge(this.graph.getEdgeSource(candidateEdge), this.graph.getEdgeTarget(candidateEdge)), 1.0);
          }
        }
      }
      edgeQueue.clear();
    }

    for (Point point : graphVertices) {
      if (this.graph.degreeOf(point) >= 5) {
        System.out.println(this.graph.degreeOf(point));
      }
    }
  }

  /**
   * Determines if the Short player wins the game.
   *
   * @return True if the Short player wins the game; false otherwise.
   */
  public boolean shortWins() {
    this.pathSolver = new DijkstraShortestPath(this.graph,this.start,this.end);
    return this.pathSolver.getPath() != null && this.pathSolver.getPath().getWeight() == 0.0;
  }

  /**
   * Determines if the Cut player wins the game.
   *
   * @return True if the Cut player wins the game; false otherwise.
   */
  public boolean cutWins() {
    this.pathSolver = new DijkstraShortestPath(this.graph,this.start,this.end);
    return this.pathSolver.getPath() == null;
  }

  /**
   * This method is called when a human is playing as the Short player. It
   * finds the edge in the graph closest to the given coordinates and if the
   * edge is within four pixels and has a weight greater than 0.0, it will be
   * locked by setting the edge weight to 0.0. If there is no edge within four
   * pixels of the given coordinates with weight greater than 0.0, no edge is
   * locked.
   *
   * @param x X-coordinate of the edge to lock.
   * @param y Y-coordinate of the edge to lock.
   * @return True if an edge is successfully locked; false otherwise.
   */
  public boolean lockEdgeAt(int x, int y) {
    Line2D.Double tempLine = new Line2D.Double();
    double minDist = Double.MAX_VALUE;
    DefaultWeightedEdge edgeToRemove = null;
    for (DefaultWeightedEdge edge : this.graph.edgeSet()) {
      tempLine.setLine(this.graph.getEdgeSource(edge), this.graph.getEdgeTarget(edge));
      double dist = tempLine.ptSegDist(x,y);
      if (dist < minDist) {
        minDist = dist;
        edgeToRemove = edge;
      }
    }
    if (edgeToRemove != null) {
      this.graph.setEdgeWeight(edgeToRemove, 0.0);
      return true;
    }
    return false;
  }

  /**
   * This method is called when a human is playing as the Cut player. It finds
   * the edge in the graph closest to the given coordinates and if the edge is
   * within four pixels and has weight greater than 0.0, it is removed from
   * the graph. If there is no edge within four pixels of the given
   * coordinates with weight greater than 0.0, no edge is removed.
   *
   * @param x X-coordinate of the edge to cut.
   * @param y Y-coordinate of the edge to cut.
   * @return True if an edge is successfully cut; false otherwise.
   */
  public boolean cutEdgeAt(int x, int y) {
    Line2D.Double tempLine = new Line2D.Double();
    double minDist = Double.MAX_VALUE;
    DefaultWeightedEdge edgeToRemove = null;
    for (DefaultWeightedEdge edge : this.graph.edgeSet()) {
      tempLine.setLine(this.graph.getEdgeSource(edge), this.graph.getEdgeTarget(edge));
      double dist = tempLine.ptSegDist(x, y);
      if (dist < minDist) {
        minDist = dist;
        edgeToRemove = edge;
      }
    }
    if (edgeToRemove != null && this.graph.getEdgeWeight(edgeToRemove) == 1.0) {
      this.graph.removeEdge(edgeToRemove);
      return true;
    }
    return false;
  }

  /**
   * This method is called when the computer is playing as the Short player.
   * It will select an edge and lock it by setting the edge weight to 0.0.
   */
  public void makeShortMove() {
    this.pathSolver = new DijkstraShortestPath(this.graph,this.start,this.end);
    if (this.pathSolver.getPath() != null) {
      for (DefaultWeightedEdge edge : this.pathSolver.getPathEdgeList()) {
        if (this.graph.getEdgeWeight(edge) == 1.0) {
          this.graph.setEdgeWeight(edge, 0.0);
          return;
        }
      }
    }
  }

  /**
   * This method is called when the computer is playing as the Cut player. It
   * will select an edge and remove it from the graph.
   */
  public void makeCutMove() {
    this.pathSolver = new DijkstraShortestPath(this.graph,this.start,this.end);
    if (this.pathSolver.getPath() != null) {
      for (DefaultWeightedEdge edge : this.pathSolver.getPathEdgeList()) {
        if (this.graph.getEdgeWeight(edge) == 1.0) {
          this.graph.removeEdge(edge);
          return;
        }
      }
    }
  }

  /**
   * Draws this graph on a DrawingPanel. This method will only be used if the
   * GUI is done with a DrawingPanel object.
   *
   * @param panel The DrawingPanel object on which to draw this graph.
   */
  public void draw(DrawingPanel panel) {
    panel.setBackground(Color.WHITE); // Clears the drawing panel.
    panel.copyGraphicsToScreen();       // Updates the screen due to double buffering.
  }

  public void draw(Graphics g) {
    Color oldColor = g.getColor();
    
    for (Point point1 : this.graph.vertexSet()) {
      for (Point point2 : this.graph.vertexSet()) {
        if (this.graph.containsEdge(point1, point2)) {
          if (this.graph.getEdgeWeight(this.graph.getEdge(point1, point2)) == 0.0) {
            g.setColor(Color.green);
          }
          else {
            g.setColor(Color.blue);
          }
          g.drawLine((int) point1.getX()+5, (int) point1.getY()+5, (int) point2.getX()+5, (int) point2.getY()+5);
        }
      }
    }
    
    for (Point point : this.graph.vertexSet()) {
      if (point.equals(this.start) || point.equals(this.end)) {
        g.setColor(Color.red);
      }
      else {
        g.setColor(Color.yellow);
      }
      g.fillOval((int) point.getX(), (int) point.getY(), 10, 10);
    }

    g.setColor(oldColor);
  }


/**
   * Returns which edge is shorter when comparing edges
   */
  private class DistanceComparator implements Comparator<DefaultWeightedEdge> {

    private SimpleWeightedGraph<Point, DefaultWeightedEdge> graph;

    public DistanceComparator(SimpleWeightedGraph<Point, DefaultWeightedEdge> graph) {
      this.graph = graph;
    }

    @Override
    public int compare(DefaultWeightedEdge t, DefaultWeightedEdge t1) {
      Point p1s = this.graph.getEdgeSource(t);
      Point p1t = this.graph.getEdgeTarget(t);
      Point p2s = this.graph.getEdgeSource(t1);
      Point p2t = this.graph.getEdgeTarget(t1);

      Double dist1 = p1s.distance(p1t);
      Double dist2 = p2s.distance(p2t);

      return dist1.compareTo(dist2);
    }
  }
}
