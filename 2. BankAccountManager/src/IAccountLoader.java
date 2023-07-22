// --== CS400 IAccountLoader Tester File Header ==--
// Name: <Luke Erickson>
// CSL Username: <lerickson>
// Email: <lerickson7@wisc.edu>
// Lecture #: <004 @4:00pm>
// Notes to Grader: <any optional extra notes to your grader>

import java.util.List;
import java.io.FileNotFoundException;

/**
 * Instances of Classes implementing this interface can be used to load
 * a list of Accounts from a specified XML source file.
 * XML File Attributes:
 * - Account Number
 * - Owner Name
 * - Balance
 * - Credit
 * - Answer to security question
 */
public interface IAccountLoader {
    /**
     * This method loads the list of Accounts described within a XMl file
     *
     * @param filepath is relative to executable's working directory
     * @return a list of Account objects that were read from specified file
     */
    public List<Account> loadAccounts(String filepath) throws FileNotFoundException;


}