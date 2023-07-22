/**
 * An instance of a class that implements the following interface can be used
 * to search and retrieve the database of Accounts within the BankApp.
 */
public interface IAccountBackend {
    /**
     *  Constructor instantiates a RBTHashTable object, and all other methods perform operations on
     *  hashtable
     */

    public void addAccount(Account account); // Adds Accounts into the RBT HashTable
    public int getNumberOfAccounts(); // Retrieve number of shows in HashTable
    public Account getAccount(int AccountNumber); // Search & Return account from HashTable
    public boolean updateBalance(Account account, double amount);
    public boolean updateCredit(Account account, double amount);
    /*
     *  Method to transfer balance from account 1 to account 2. This method will iterate through
     *  table, find account instances based
     */
    public boolean transferBalance(Account account1, Account account2, double balance);
}
