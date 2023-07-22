import java.util.ArrayList;

/**
 * Uses the shortest path algorithm and the total weights of the edges to calculate the shortest
 * path between airports
 */
public interface IAEShortestPath<T> extends GraphADT<T>{

    /**
     * Calculates the distance between connected airports using their coordinates. It also adds a
     * random delay to the paths of up to 10% of their normal trip time.
     *
     * @param start the starting airport
     * @param end the ending airport
     * @return
     */
    public Double totalPathWeight(Airport start, Airport end);
    
    
    /**
     * Given a list of airports, calculates the shortest distance between each one and represents
     * this is in a matrix
     * 
     * @param locations list of airports to calculate distances between
     * @return 2d array of distances
     */
    public Double[][] pathMatrix(ArrayList<Airport> locations);
}
