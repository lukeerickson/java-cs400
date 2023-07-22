import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BackendDeveloperTests {

  @Test
  void testGetNumberOfAccount() {
    accountBackend backend= new accountBackend();
    Account account1 = new Account("kyle", 651366, 200, 700, "green");
    Account account2 = new Account("tanner", 123456, 150, 650, "blue");
    Account account3 = new Account("emily", 52836, 1, 830, "orange");
    Account account4 = new Account("simon", 987654, 900, 400, "yellow");
    Account account5 = new Account("maggie", 764832, 20, 498, "brown");
    backend.addAccount(account1);
    backend.addAccount(account2);
    backend.addAccount(account3);
    backend.addAccount(account4);
    backend.addAccount(account5);
    
    assertEquals(5, backend.getNumberOfAccounts());
}
  
  @Test
  void testGetAccount() {
    accountBackend backend= new accountBackend();
    Account account1 = new Account("kyle", 651366, 200, 700, "green");
    Account account2 = new Account("tanner", 123456, 150, 650, "blue");
    Account account3 = new Account("emily", 52836, 1, 830, "orange");
    Account account4 = new Account("simon", 987654, 900, 400, "yellow");
    Account account5 = new Account("maggie", 764832, 20, 498, "brown");
    backend.addAccount(account1);
    backend.addAccount(account2);
    backend.addAccount(account3);
    backend.addAccount(account4);
    backend.addAccount(account5);
    assertEquals(200, backend.getAccount(651366).getBalance());
  }
  
  @Test
  void testTransferBalance() {
    accountBackend backend= new accountBackend();
    Account account1 = new Account("kyle", 651366, 200, 700, "green");
    Account account2 = new Account("tanner", 123456, 150, 650, "blue");
    Account account3 = new Account("emily", 52836, 1, 830, "orange");
    Account account4 = new Account("simon", 987654, 900, 400, "yellow");
    Account account5 = new Account("maggie", 764832, 20, 498, "brown");
    backend.addAccount(account1);
    backend.addAccount(account2);
    backend.addAccount(account3);
    backend.addAccount(account4);
    backend.addAccount(account5);
    backend.transferBalance(account1, account2, 50);
    
  }
  
  @Test
  void testUpdateBalance() {
    accountBackend backend= new accountBackend();
    Account account1 = new Account("kyle", 651366, 200, 700, "green");
    Account account2 = new Account("tanner", 123456, 150, 650, "blue");
    Account account3 = new Account("emily", 52836, 1, 830, "orange");
    Account account4 = new Account("simon", 987654, 900, 400, "yellow");
    Account account5 = new Account("maggie", 764832, 20, 498, "brown");
    backend.addAccount(account1);
    backend.addAccount(account2);
    backend.addAccount(account3);
    backend.addAccount(account4);
    backend.addAccount(account5);
    backend.updateBalance(account3, 450);
    assertEquals(451, account3.getBalance());
  }
  
  @Test
  void testUpdateCredit() {
    accountBackend backend= new accountBackend();
    Account account1 = new Account("kyle", 651366, 200, 700, "green");
    Account account2 = new Account("tanner", 123456, 150, 650, "blue");
    Account account3 = new Account("emily", 52836, 1, 830, "orange");
    Account account4 = new Account("simon", 987654, 900, 400, "yellow");
    Account account5 = new Account("maggie", 764832, 20, 498, "brown");
    backend.addAccount(account1);
    backend.addAccount(account2);
    backend.addAccount(account3);
    backend.addAccount(account4);
    backend.addAccount(account5);
    backend.updateCredit(account4, 20);
    assertEquals(420, account4.getCredit());
  }

}
