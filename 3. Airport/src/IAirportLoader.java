import java.util.List;
import java.io.FileNotFoundException;
/**
 * Instances of classes implementing this interface can be used to load
 * a list of Airports from a specified JSON source file.
 * JSON File Attributes:
 * - City name where airport is located
 * - State abbreviations where airport is located
 * - Time zone of airport
 * - latitude
 * - longitude
 */
public interface IAirportLoader {

    /**
     * This method loads the list of airports described within a JSON file
     *
     * @param filepath is relative to executable's working directory
     * @return a list of account objects that were read from specified file
     */
    public List<Airport> loadAirports(String filepath, int numberOfDestinations) throws FileNotFoundException;
}
