// --== CS400 Project One File Header ==--
// Name: Utkarsh Sehgal
// CSL Username: sehgal
// Email: usehgal@wisc.edu
// Lecture #: 004
// Notes to Grader: <any optional extra notes to your grader>
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BackendDeveloperTests {
  public static void main(String[] args) {
    System.out.println("test 1:" + test1());
    System.out.println("test 2:" + test2());
    System.out.println("test 3:" + test3());
    System.out.println("test 4:" + test4());
    System.out.println("test 5:" + test5());
  }
  //test getProviderFilter()
  public static boolean test1() {
    ShowSearcherBackend test = new ShowSearcherBackend();
    try{
      //all of them should be true initially 
      if(!test.getProviderFilter("Netflix")) {
        return false;
      }
      
      if(!test.getProviderFilter("Hulu")) {
        return false;
      }
      
      if(!test.getProviderFilter("Prime")) {
        return false;
      }
      
      if(!test.getProviderFilter("Disney")) {
        return false;
      }
    } catch (Exception e) {
      System.out.println("A non expected exception has been thrown.");
      e.printStackTrace();
      return false;
    }
    return true; 
  }
  //test setProviderFilter
  public static boolean test2() {
    ShowSearcherBackend test = new ShowSearcherBackend();
    try{
      test.setProviderFilter("Netflix", false); // lets set all of the providers false and then use get to test if they have changed
      if(test.getProviderFilter("Netflix")) {
        return false;
      }
      test.setProviderFilter("Hulu", false);
      if(test.getProviderFilter("Hulu")) {
        return false;
      }
      test.setProviderFilter("Prime", false);
      if(test.getProviderFilter("Prime")) {
        return false;
      }
      test.setProviderFilter("Disney", false);
      if(test.getProviderFilter("Disney")) {
        return false;
      }
    } catch (Exception e) {
      System.out.println("A non expected exception has been thrown.");
      e.printStackTrace();
      return false;
    }
    return true; 
  }
  //test toggleProviderFilter
  public static boolean test3() {
    ShowSearcherBackend test = new ShowSearcherBackend();
    try{
      test.toggleProviderFilter("Netflix"); //they are all true initially so toggling them should make them all false
      if(test.getProviderFilter("Netflix")) {
        return false;
      }
      test.toggleProviderFilter("Hulu");
      if(test.getProviderFilter("Hulu")) {
        return false;
      }
      test.toggleProviderFilter("Prime");
      if(test.getProviderFilter("Prime")) {
        return false;
      }
      test.toggleProviderFilter("Disney");
      if(test.getProviderFilter("Disney")) {
        return false;
      }
    } catch (Exception e) {
      System.out.println("A non expected exception has been thrown.");
      e.printStackTrace();
      return false;
    }
    return true; 
  }
  //test searchByTitleWord()
  public static boolean test4() {
    ShowSearcherBackend test = new ShowSearcherBackend();
    LinkedList<NodeValue> wordOne = new LinkedList<NodeValue>(); //make my own hardcoded hashtable
    Show oneDance = new Show("one dance", 2000, 60, "N");
    Show oneStep = new Show("one step", 2000, 70, "NHPD");
    wordOne.add(new NodeValue("one", oneDance));
    wordOne.add(new NodeValue("one", oneStep));
    test.titleTable.nodes[0] = wordOne;
    try{
     List test1 = test.searchByTitleWord("one");//this should have all the shows with "one" and be sorted from highest rating to lowest rating
     if(!test1.get(0).equals(oneStep)) { // oneStep should be the one with the highest rating
       return false;
     }
     if(!test1.get(1).equals(oneDance)) {// oneStep should be the one with the lowest rating
       return false;
     }
     //lets test the provider feature
     test.toggleProviderFilter("N");//toggling netflix should make one dance not show up
     List test2 = test.searchByTitleWord("one");
     if(test2.size() != 1) {//we test this by seeing is the size of the shows list is 1 instead of 2
       return false;
     }
    } catch (Exception e) {
      System.out.println("A non expected exception has been thrown.");
      e.printStackTrace();
      return false;
    }
    return true; 
  }
  //test searchByYear()
  public static boolean test5() {
    ShowSearcherBackend test = new ShowSearcherBackend();
    LinkedList<NodeValue> year2000 = new LinkedList<NodeValue>();
    Show oneDance = new Show("one dance", 2000, 60, "N");
    Show oneStep = new Show("one step", 2000, 70, "NHPD");
    year2000.add(new NodeValue(2000, oneDance));
    year2000.add(new NodeValue(2000, oneStep));
    test.yearTable.nodes[0] = year2000;
    try{
      List test1 = test.searchByYear(2000);
      if(!test1.get(0).equals(oneStep)) {
        return false;
      }
      if(!test1.get(1).equals(oneDance)) {
        return false;
      }
      
      test.toggleProviderFilter("N");
      List test2 = test.searchByYear(2000);
      if(test2.size() != 1) {
        return false;
      }
    } catch (Exception e) {
      System.out.println("A non expected exception has been thrown.");
      e.printStackTrace();
      return false;
    }
    return true; 
  }
}
