import java.util.List;

public class AirportApp {
    IAirportLoader loader = new AirportLoader()
    List<Airport> airports = loader.loadAirports(); // List of all airport objects made by DW
    IBackend backend = new Backend(airports)
    backend.addNodes(airports); // Populate graph with airports
    backend.addEdges(); // Generate graph edges
    IFrontend frontend = new frontend(backend, airports); // Pass backend and airport list to frontend
    frontend.launch(); // run frontend's JavaFX application
}
