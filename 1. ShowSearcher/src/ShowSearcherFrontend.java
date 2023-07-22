// --== CS400 Project One File Header ==--
// Name: <Luke Erickson>
// CSL Username: <lerickson>
// Email: <lerickson7@wisc.edu>
// Lecture #: <004 @4:00pm>
// Notes to Grader: <any optional extra notes to your grader>

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * ShowSearcherFrontend allows the user to interact with the show searcher database, search for
 * titles, and filter options
 * 
 * @author lukee
 *
 */
public class ShowSearcherFrontend implements IShowSearcherFrontend {

  Scanner scanner;
  IShowSearcherBackend backend;

  /**
   * Creates new ShowSearcherFrontend that reads input from System.in
   * 
   * @param backend
   */
  public ShowSearcherFrontend(IShowSearcherBackend backend) {
    this.backend = backend;
    scanner = new Scanner(System.in);
  }

  /**
   * Creates new ShowSearcherFrontend that reads input from a String
   * 
   * @param backend
   */
  public ShowSearcherFrontend(String text, IShowSearcherBackend backend) {
    this.backend = backend;
    scanner = new Scanner(text);
  }

  /**
   * Basic menu functionality, prompts the user to select what they would like to accomplish
   */
  @Override
  public void runCommandLoop() {

    System.out.println("Welcome to the Show Searcher App!");
    System.out.println("=================================");
    Boolean quit = false;

    // command loop
    while (!quit) {

      displayCommandMenu();
      String cmd = scanner.nextLine(); // read user command

      // search by title
      if (cmd.equals("t") || cmd.equals("T") || cmd.equals("1")) {
        // System.out.println("\n");
        titleSearch();
      }
      // search by year
      else if (cmd.equals("y") || cmd.equals("Y") || cmd.equals("2")) {
        // System.out.println("\n");
        yearSearch();
      }
      // filter providers
      else if (cmd.equals("f") || cmd.equals("F") || cmd.equals("3")) {
        // System.out.println("\n");
        filterMenu();
      }
      // quit
      else if (cmd.equals("q") || cmd.equals("Q") || cmd.equals("4")) {
        System.out.println("Now quitting");
        quit = true;
        return;
      }
      // invalid command
      else {
        System.out.println("\nWarning: Invalid command \"" + cmd + "\"");
      }

      // quit = true;

    }
  }

  /**
   * Basic menu, displaying options available to user
   */
  @Override
  public void displayCommandMenu() {
    // print menu and prompt user command line
    System.out.println("Command Menu:");
    System.out.println("\t1) Search by [T]itle Word");
    System.out.println("\t2) Search by [Y]ear First Produced");
    System.out.println("\t3) [F]ilter by Streaming Provider");
    System.out.println("\t4) [Q]uit");
    System.out.print("Choose a command from the menu above: ");

  }

  /**
   * Given a list of shows, displays them nicely with appropriate information
   */
  @Override
  public void displayShows(List<IShow> shows) {
    System.out.println("Found " + shows.size() + "/" + backend.getNumberOfShows() + " matches.");

    for (int i = 0; i < shows.size(); i++) {
      System.out.println(i + 1 + ". " + shows.get(i).getTitle());
      System.out.println("\t" + shows.get(i).getRating() + " (" + shows.get(i).getYear() + ") on: "
          + platformChecker(shows.get(i)));
    }
  }

  /**
   * Checks to see if a show is available given the provider filter options
   * 
   * @param show
   * @return name of provider
   */
  private String platformChecker(IShow show) {
    // check w/ data wrangler to see if i can feed strings in like this
    if (show.isAvailableOn("Netflix") && backend.getProviderFilter("Netflix"))
      return "Netflix";
    if (show.isAvailableOn("Hulu") && backend.getProviderFilter("Hulu"))
      return "Hulu";
    if (show.isAvailableOn("Prime Video") && backend.getProviderFilter("Prime Video"))
      return "Netflix";
    if (show.isAvailableOn("Disney+") && backend.getProviderFilter("Disney+"))
      return "Disney+";
    else
      return "[Show is not available to you]";
  }

  /**
   * Prompts the user to search by title
   */
  @Override
  public void titleSearch() {

    boolean quit = false;

    while (!quit) {
      System.out.println("Choose a word that you would like to search for: ");
      String cmd = scanner.nextLine(); // read user command
      //try {
        displayShows(backend.searchByTitleWord(cmd));
        // loop re-runs if command is invalid, does not re-run otherwise
        quit = true;
      //} catch (Exception e) {
        //System.out.println("\nWarning: Invalid command. Returning to main menu");
      //}
    }
  }

  @Override
  public void yearSearch() {
    boolean quit = false;

    while (!quit) {
      System.out.println("What year would you like to search for: ");
      try {
        int cmd = scanner.nextInt(); // read user command
        displayShows(backend.searchByYear(cmd));
        // loop re-runs if command is invalid, does not re-run otherwise
        quit = true;
      } catch (Exception e) {
        System.out.println("\nWarning: Invalid command. Returning to main menu");
        return;
      }
    }
  }

  /**
   * Allows user to view and toggle filters on different providers
   */
  private void filterMenu() {
    Boolean quit = false;

    while (!quit) {

      System.out.println("Providers that shows are being searched for:");
      System.out.println("\t1) _" + filterDisplayer("Netflix") + "_ [N]etflix");
      System.out.println("\t2) _" + filterDisplayer("Hulu") + "_ [H]ulu");
      System.out.println("\t3) _" + filterDisplayer("Prime Video") + "_ [P]rime Video");
      System.out.println("\t4) _" + filterDisplayer("Disney+") + "_ [D]isney+");
      System.out.println("\t5) [Q]uit toggling provider filters");
      System.out.println("Choose the provider that you'd like to toggle the filter for: ");

      String cmd = scanner.nextLine(); // read user command


      if (cmd.equals("n") || cmd.equals("N") || cmd.equals(1)) {
        backend.toggleProviderFilter("Netflix"); // toggle netflix
      } else if (cmd.equals("h") || cmd.equals("H") || cmd.equals(2)) {
        backend.toggleProviderFilter("Hulu"); // toggle hulu
      } else if (cmd.equals("p") || cmd.equals("P") || cmd.equals(3)) {
        backend.toggleProviderFilter("Prime Video"); // toggle amazon prime
      } else if (cmd.equals("d") || cmd.equals("D") || cmd.equals(4)) {
        backend.toggleProviderFilter("Disney"); // toggle disney
      } else if (cmd.equals("q") || cmd.equals("Q") || cmd.equals(5)) {
        System.out.println("Now quitting"); // quit filter menu
        quit = true;
        break;
      } else {
        System.out.println("Warning: Invalid command \"" + cmd + "\""); // invalid command
      }

    }


  }

  /**
   * Checks the status of a filter, and displays status appropriately to the user
   * 
   * @param provider
   * @return filter status
   */
  private String filterDisplayer(String provider) {
    if (backend.getProviderFilter(provider))
      return "x";
    else
      return "_";
  }

  // public static void main(String[] arg) {
  // ShowSearcherBackend backend = new ShowSearcherBackend();
  // ShowSearcherFrontend frontend1 = new ShowSearcherFrontend(backend);
  // frontend1.runCommandLoop();
  // }
}
