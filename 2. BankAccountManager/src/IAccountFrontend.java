import java.util.List;

/**
 * Instances of classes that implement this interface can be used to drive a
 * console-based text user interface for the BankApp.
 */
public interface IAccountFrontend {
    // constructor args (AccountBackend, AccountLoader) reads input from System.in
    // constructor args (String, AccountBackend, Account Loader) reads input from String

    /**
     * This method drives the entire read, eval, print loop (repl) for the
     * Bank App.  This loop will continue to run until the user
     * explicitly enters the quit command.
     */
    void runCommandLoop();

    // to help make it easier to test the functionality of this program,
    // the following helper methods will help support runCommandLoop():

    public void displayCommandMenu(); // prints command options to System.out

    public void displayAccount(Account account); // displays a list of shows

    public void accountSearch(); // reads word from System.in, displays results

    /**
     * This method is used to add a new account into the bank database. Ask for the new
     * account's Owner Name, Account Number & Routing Number. Parse from System.in, send to backend,
     * if backend returns false (account with account number exists), then return a message saying
     * addition failed / try again. If returned true, send results to data wrangler to be added into
     * XML file. Finally print success message saying that Account _____ was added to database.
     */
    public void accountAdd();

    /**
     * This method is used to transfer money from one account to another account object. Ask for two account
     * numbers and a Transfer amount. Pass all 3 int values to the Backend. If Backend returns false, print a failure
     * message claiming that the action failed, if returned true, print a success message.
     */
    public void transferBalance();

    /**
     * This method is used to remove an account from the database (XML & HashMap). Ask for the account number
     * to be removed, pass to backend. If backend returns a valid Account (Account object removed), then call on
     * datawranger method to remove account from xml file, finally print out Account object that was removed.
     * Else if backend returns null, print a failure message.
     */

    public void balanceUpdate();

    public void creditUpdate();
}