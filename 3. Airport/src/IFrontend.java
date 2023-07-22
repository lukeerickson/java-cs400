import javafx.stage.Stage;

public interface IFrontend {
    // Should take an instance of the Backend in the constructor and store in a private field
    // Should also take a List<Airport> objects in the constructor and store as a private field

    /*
       Helper method to display the Airport list as text -> Returns formatted list as a String
       for displaying to JavaFX window
     */
    public String displayAirportList();


    /*
    Will extend JavaFX Application Class and should implement the Start method
     */
    public void start(final Stage stage);


    /*
    Call to method when User clicks on Compute Route Button -> Should initiate Backend's
    calculateShortestPath()
     */
    public void computeRoute(String airport1, String airport2);


    /*
    Simple method that creates scene and launches application
    (Main Runner Application will call on this frontend method to run JavaFX application)
     */
     public void launch();
}
