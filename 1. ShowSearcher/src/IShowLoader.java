import java.util.List;
import java.io.FileNotFoundException;

/**
 * Instances of classes that implement this interface can be used to load a 
 * list of shows from a specified csv source file.
 * The following csv columns are used to load these show attributes:
 *   - Title: the complete title for a show
 *   - Year: the year that the show was first produced
 *   - Rotten Tomatoes: the review score (out of 100) for this show
 *   - Netflix: 1 = available on this service, other wise 0
 *   - Hulu: 1 = available on this service, other wise 0
 *   - Prime Video: 1 = available on this service, other wise 0
 *   - Disney+: 1 = available on this service, other wise 0
 */
public interface IShowLoader {

    /**
     * This method loads the list of songs described within a CSV file.
     * @param filepath is relative to executable's working directory
     * @return a list of show objects that were read from specified file
     */
    List<IShow> loadShows(String filepath) throws FileNotFoundException;

}