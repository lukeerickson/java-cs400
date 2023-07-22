import java.util.List;

/**
 * Instances of classes that implement this interface can be used to drive a
 * console-based text user interface for the ShowSearcher App.
 */
public interface IShowSearcherFrontend {

    // constructor args (IShowSearcherBackend) reads input from System.in
    // constructor args (String, IShowSearcherBackend) reads input from String

    /**
     * This method drives the entire read, eval, print loop (repl) for the
     * Song Search App.  This loop will continue to run until the user 
     * explicitly enters the quit command. 
     */
    void runCommandLoop();

    // to help make it easier to test the functionality of this program, 
    // the following helper methods will help support runCommandLoop():

    public void displayCommandMenu(); // prints command options to System.out

    public void displayShows(List<IShow> shows); // displays a list of shows

    public void titleSearch(); // reads word from System.in, displays results
    
    public void yearSearch(); // reads year from System.in, displays results
}