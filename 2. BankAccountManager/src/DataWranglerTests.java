// --== CS400 Data Wrangler Tester File Header ==--
// Name: <Luke Erickson>
// CSL Username: <lerickson>
// Email: <lerickson7@wisc.edu>
// Lecture #: <004 @4:00pm>
// Notes to Grader: <any optional extra notes to your grader>

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;

class DataWranglerTests {

  /**
   * Makes sure that the file is read correctly
   * 
   * @throws FileNotFoundException
   */
  @Test
  void test1() throws FileNotFoundException {
    // make sure file is found when it should be found
    try {
      AccountLoader loader = new AccountLoader();
      List<Account> accounts = loader.loadAccounts("accounts.xml");
    }
    catch (FileNotFoundException e) {
      fail("File not found");
    }
    // make sure file isn't found when it shouldn't be found
    try {
      AccountLoader loader = new AccountLoader();
      List<Account> accounts = loader.loadAccounts("fake file lmao");
      fail("File found when it shouldn't have been");
    } catch(FileNotFoundException e) {
      // do nothing
    }
  }
  
  /**
   * Ensures that the Account class' compareTo() method works correctly
   * @throws FileNotFoundException 
   */
  @Test
  void test2() throws FileNotFoundException {

      AccountLoader loader = new AccountLoader();
      List<Account> accounts = loader.loadAccounts("accounts.xml");
      assertTrue(accounts.get(27).compareTo(accounts.get(28)) == -1);
      //System.out.println(accounts.get(0).getOwnerName());
      //System.out.println(accounts.get(4).getOwnerName());
      //assertTrue(accounts.get(73).compareTo(accounts.get(184)) == -1);


  }
  
  /**
   * Ensures that AccountLoader correctly stores account numbers
   * @throws FileNotFoundException 
   */
  @Test
  void test3() throws FileNotFoundException {

      AccountLoader loader = new AccountLoader();
      List<Account> accounts = loader.loadAccounts("accounts.xml");
      assertTrue(accounts.get(80).getAccountNumber() == 81);

  }
  
  /**
   * Ensures that AccountLoader correctly stores names
   * @throws FileNotFoundException 
   */
  @Test
  void test4() throws FileNotFoundException {
      AccountLoader loader = new AccountLoader();
      List<Account> accounts = loader.loadAccounts("accounts.xml");
      assertTrue(accounts.get(0).getOwnerName().equals("Emily"));
     
  }
  
  /**
   * Ensures that AccountLoader correctly stores balances
   * @throws FileNotFoundException 
   */
  @Test
  void test5() throws FileNotFoundException {

      AccountLoader loader = new AccountLoader();
      List<Account> accounts = loader.loadAccounts("accounts.xml");
      assertTrue(accounts.get(0).getBalance() == 254.69);

  }
  
  /**
   * Ensures that AccountLoader correctly stores credits
   * @throws FileNotFoundException 
   */
  @Test
  void test6() throws FileNotFoundException {
      AccountLoader loader = new AccountLoader();
      List<Account> accounts = loader.loadAccounts("accounts.xml");
      assertTrue(accounts.get(0).getCredit() == 146.68);

  }
  
  /**
   * Ensures that AccountLoader correctly stores security answers
   * @throws FileNotFoundException 
   */
  @Test
  void test7() throws FileNotFoundException {
      AccountLoader loader = new AccountLoader();
      List<Account> accounts = loader.loadAccounts("accounts.xml");
      assertTrue(accounts.get(0).getSecurityAnswer().equals("Green beige"));
      
  }
  
  /**
   * Ensures that AccountLoader and accountBackend work together as they should
   * @throws FileNotFoundException 
   */
  @Test
  void test8() throws FileNotFoundException {
      AccountLoader loader = new AccountLoader();
      List<Account> accounts = loader.loadAccounts("accounts.xml");
      accountBackend backend = new accountBackend();
      
      backend.addAccount(accounts.get(200));
      assertEquals(3278.99, backend.getAccount(201).getBalance());
      
  }
  
  /**
   * Ensures that AccountLoader and accountBackend work together as they should
   * @throws FileNotFoundException 
   */
  @Test
  void test9() throws FileNotFoundException {
      AccountLoader loader = new AccountLoader();
      List<Account> accounts = loader.loadAccounts("accounts.xml");
      accountBackend backend = new accountBackend();
      
      backend.addAccount(accounts.get(200));
      assertEquals("Grey white", backend.getAccount(201).getSecurityAnswer());
      
  }
  
  

}
