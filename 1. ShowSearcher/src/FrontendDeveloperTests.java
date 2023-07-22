// --== CS400 Project One File Header ==--
// Name: <Luke Erickson>
// CSL Username: <lerickson>
// Email: <lerickson7@wisc.edu>
// Lecture #: <004 @4:00pm>
// Notes to Grader: <any optional extra notes to your grader>

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Tests to make sure that methods in ShowSearcherFrontend work as expected
 * 
 * @author lukee
 *
 */
public class FrontendDeveloperTests {
  /**
   * @return true if ShowSearcherFrontend's constructor and quit functionality work as expected
   */
  public static boolean test1() {
    try {
      ShowSearcherBackend backend = new ShowSearcherBackend();
      ShowSearcherFrontend frontend1 = new ShowSearcherFrontend(backend);
      ShowSearcherFrontend frontend2 = new ShowSearcherFrontend("q", backend);

      TextUITester tester = new TextUITester("", frontend2);
      tester.run();
      String output = tester.checkOutput();

      // System.out.println(output);

      // check to see if the quit function works
      if (!output.startsWith("Welcome to the Show Searcher App!"))
        return false;
      if (!output.contains("Now quitting"))
        return false;

    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * @return true if the titleSearch() functionality works as expected
   */
  public static boolean test2() {
    try {
      ShowSearcherBackend backend = new ShowSearcherBackend();
      ShowSearcherFrontend frontend1 = new ShowSearcherFrontend(backend);
      ShowSearcherFrontend frontend2 = new ShowSearcherFrontend("1\ndad\nq\n", backend);

      TextUITester tester = new TextUITester("", frontend2);
      tester.run();
      String output = tester.checkOutput();

      // System.out.println(output);

      // check to see if titleSearch() works
      if (!output.contains("Choose a word that you would like to search for:"))
        return false;
      if (!output.contains("Found"))
        return false;
      // check that user is returned to the command menu
      if (!output.contains("Command Menu"))
        return false;

    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * @return true if yearSearch() functionality works as expected
   */
  public static boolean test3() {
    try {
      ShowSearcherBackend backend = new ShowSearcherBackend();
      ShowSearcherFrontend frontend1 = new ShowSearcherFrontend(backend);
      ShowSearcherFrontend frontend2 = new ShowSearcherFrontend("2\n2000\nq\n", backend);

      TextUITester tester = new TextUITester("", frontend2);
      tester.run();
      String output = tester.checkOutput();

      // System.out.println(output);

      // check to see if the quit function works
      if (!output.contains("What year would you like to search for:"))
        return false;
      if (!output.contains("Found"))
        return false;
      // check that user is returned to the command menu
      if (!output.contains("Command Menu"))
        return false;

    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * @return true if yearSearch() correctly handles improper input
   */
  public static boolean test4() {
    try {
      ShowSearcherBackend backend = new ShowSearcherBackend();
      ShowSearcherFrontend frontend1 = new ShowSearcherFrontend(backend);
      // fix this later
      ShowSearcherFrontend frontend2 = new ShowSearcherFrontend("2\nx\nq\n", backend);

      TextUITester tester = new TextUITester("", frontend2);
      tester.run();
      String output = tester.checkOutput();

      // System.out.println(output);

      // check to see if the quit function works
      if (!output.contains("Warning: Invalid command"))
        return false;
      // check that user is returned to command menu
      if (!output.contains("Command Menu"))
        return false;

    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * @return true if filterMenu() functionality works as expected
   */
  public static boolean test5() {
    try {
      ShowSearcherBackend backend = new ShowSearcherBackend();
      ShowSearcherFrontend frontend1 = new ShowSearcherFrontend(backend);
      ShowSearcherFrontend frontend2 = new ShowSearcherFrontend("f\nn\nq\nq\n", backend);

      TextUITester tester = new TextUITester("", frontend2);
      tester.run();
      String output = tester.checkOutput();

      // System.out.println(output);

      // check to see if the quit function works
      if (!output.contains("Choose the provider that you'd like to toggle the filter for:"))
        return false;
      if (!output.contains("Providers that shows are being searched for:"))
        return false;
      // check that user is returned to command menu
      if (!output.contains("Command Menu"))
        return false;

    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * @return true if filterMenu() correctly handles improper input
   */
  public static boolean test6() {
    try {
      ShowSearcherBackend backend = new ShowSearcherBackend();
      ShowSearcherFrontend frontend1 = new ShowSearcherFrontend(backend);
      ShowSearcherFrontend frontend2 = new ShowSearcherFrontend("f\nburger\nn\nq\nq\n", backend);

      TextUITester tester = new TextUITester("", frontend2);
      tester.run();
      String output = tester.checkOutput();

      // System.out.println(output);

      // check to see if the quit function works
      if (!output.contains("Choose the provider that you'd like to toggle the filter for:"))
        return false;
      if (!output.contains("Warning: Invalid command \"burger\""))
        return false;
      // check that user is returned to command menu
      if (!output.contains("Command Menu"))
        return false;

    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * @return true if invalid input in the command menu is handled properly
   */
  public static boolean test7() {
    try {
      ShowSearcherBackend backend = new ShowSearcherBackend();
      ShowSearcherFrontend frontend1 = new ShowSearcherFrontend(backend);
      ShowSearcherFrontend frontend2 = new ShowSearcherFrontend("mango\nq", backend);

      TextUITester tester = new TextUITester("", frontend2);
      tester.run();
      String output = tester.checkOutput();

      // System.out.println(output);

      // check to see if the invalid command function works
      if (!output.contains("Warning: Invalid command \"mango\""))
        return false;
      // check that user is returned to command menu
      if (!output.contains("Command Menu"))
        return false;

    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * @return tests filter functionality of BackendDeveloper's role
   */
  public static boolean test8() {
    try {
      ShowSearcherBackend backend = new ShowSearcherBackend();

      // makes sure that the filter is initially on
      if (!backend.getProviderFilter("Netflix"))
        return false;
      // makes sure that setProviderFilter() works correctly
      backend.setProviderFilter("Netflix", false);
      if (backend.getProviderFilter("Netflix"))
        return false;
      // make sure that toggleProviderFilter() works properly
      backend.toggleProviderFilter("Netflix");
      if (!backend.getProviderFilter("Netflix"))
        return false;

    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * @return tests add and search functionality of BackendDeveloper's role
   */
  public static boolean test9() {
    try {
      ShowSearcherBackend backend = new ShowSearcherBackend();
      // make hash table
      LinkedList<NodeValue> linkedList = new LinkedList<NodeValue>();
      // make 2 show objects to be added to hash table
      Show fruitBanana = new Show("fruit banana", 1980, 1, "Netflix");
      Show fruitApple = new Show("fruit apple", 1940, 2, "Hulu");
      linkedList.add(new NodeValue("fruit", fruitBanana));
      linkedList.add(new NodeValue("fruit", fruitApple));
      backend.titleTable.nodes[0] = linkedList;

       // should contain all shows w/ fruit in the name
        List list1 = backend.searchByTitleWord("fruit");
       // fruitBanana should have a higher rating and appear at list1.get(0)
       if(!list1.get(0).equals(fruitBanana)) { 
         return false;
       }
       if(!list1.get(1).equals(fruitApple)) {
         return false;
       }
       //lets test the provider feature
       backend.toggleProviderFilter("Hulu");
       List list2 = backend.searchByTitleWord("fruit");
       if(list2.size() != 1) //we test this by seeing is the size of the shows list is 1 instead of 2
         return false;

      } catch (Exception e) {
        return false;
      }
      return true; 
  }

  public static void main(String[] args) {
    System.out.println("test1: " + test1());
    System.out.println("test2: " + test2());
    System.out.println("test3: " + test3());
    System.out.println("test4: " + test4());
    System.out.println("test5: " + test5());
    System.out.println("test6: " + test6());
    System.out.println("test7: " + test7());
    System.out.println("test8: " + test8());
    System.out.println("test9: " + test9());
  }
}
