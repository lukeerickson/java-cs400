import java.util.List;

public interface IBackend {
    // Instantiate instance of AE Shortest Path Class
    // Create a private array of Airport[] to hold all instances of Airport objects (Hashtable would actually be better)
    // There should only be 50 Airports in the array


    /*
     * Given a list of airport objects, iterate through list and pass each airport
     * to the AE insertVertex() Method AND Add to private airport[] field
     *
     * Return false if any errors occurred / exceptions thrown, else return true
     */
    public boolean addNodes(List<Airport> airportList);


    /*
    Creates airport network using randomly generated edges
    Using the private airport[] field that contains all the airports in graph,
    use a random generator to generate 2 random indices in the range of the range of
    the airport[] field size. Use these 2 random indices to get the airport objects and
    pass both objects to the AE insertEdge() method. Before calling AE method, make sure
    random indices / objects are not the same (Self-Loops in airport network).
    REPEAT 75 times (50 airports, should have a more connected network).
    Use the AE's totalPathWeight() for edge cost.
     */
    public boolean addEdges();


    /*
    Take in two Strings (Airport names) and return the shortest path determined by the AE's shortestPath().
    Need to get the correct Airport objects stored in the Airport[] private field (use the String inputs).
    Pass airport objects to AE and return list output.
    */
    public List<Airport> calculateShortestPath(String airport1, String airport2);


    /*
    Method to determine if an airport is in the graph. Should iterate through Airport[] field and check if any airport
    is the same as the input String. (Frontend will call on method to compare with user input).
    */
    public boolean containsAirport(String airport);
}
