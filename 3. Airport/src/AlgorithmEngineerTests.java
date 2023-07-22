import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AlgorithmEngineerTests {
  
  private ArrayList<Airport> locations;
  private AEShortestPath<Airport> graph;
  
  float x1 = (float) 0.0;
  float y1 = (float) 0.0;
  
  Airport A = new Airport("Arizona", "AZ", "CST", x1, y1);
  Airport B = new Airport("BananaVille", "BV", "CST", x1, y1);
  Airport C = new Airport("Connecticut", "CT", "EST", x1, y1);
  Airport D = new Airport("Donut", "DT", "DST", x1, y1);
  Airport E = new Airport("Epsilon Eggshells", "EE", "EST", x1, y1);

  /**
   * Perform all necessary set-up (and place-holders) to allow you to calculate distance between airports
   */
  @BeforeEach
  public void createGraph() {
    locations = new ArrayList<Airport>();
    graph = new AEShortestPath<>();
 
    locations.add(A);
    locations.add(B);
    locations.add(C);
    locations.add(D);
    locations.add(E);
    
    graph.insertVertex(A);
    graph.insertVertex(B);
    graph.insertVertex(C);
    graph.insertVertex(D);
    graph.insertVertex(E);
    
    graph.insertEdge(A, B, 1);
    graph.insertEdge(B, C, 2);
    graph.insertEdge(C, D, 3);
    graph.insertEdge(D, E, 4);
    graph.insertEdge(E, A, 5);
  }
  
  /**
   * Checks the distance/total weight cost from the airport A to E
   */
  @Test
  public void testPathCostAtoE() {
      assertTrue(graph.getPathCost(A, E) == 10);
  }
  
  /**
   * Checks the ordered sequence of data within vertices from the vertex 
   * A to B.
   */
  @Test
  public void testPathAtoB() {
      assertTrue(graph.shortestPath(A, B).toString().equals(
          "[City: Arizona\nState: AZ\nTime Zone: CST, City: BananaVille\nState: BV\nTime Zone: CST]"));
  }
  
  /**
   * Makes sure that the shortest distance from A to E is calculated correctly (w/ delay)
   */
  @Test
  public void testTotalPathWeight() {
    assertTrue(graph.totalPathWeight(A, E) >= 10);
    assertTrue(graph.totalPathWeight(A, E) <= 11);

  }
 
  
  /**
  * Checks that the matrix accurately displays the shortest distance from A to B
  */
 @Test
 public void testMatrixAtoB() {
   Double[][] distances = new Double[5][5];
   distances = graph.pathMatrix(locations);
   
   assertTrue(distances[0][1] >= 1);
   assertTrue(distances[0][1] <= 1.1);

 }
 
 /**
 * Checks that the matrix accurately displays the distance from E to E if you were to go thru all airports
 */
@Test
public void testMatrixEtoE() {
  Double[][] distances = new Double[5][5];
  distances = graph.pathMatrix(locations);
  
  assertTrue(distances[4][4] == 0);

}


}
