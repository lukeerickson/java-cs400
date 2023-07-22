// --== CS400 Account File Header ==--
// Name: <Luke Erickson>
// CSL Username: <lerickson>
// Email: <lerickson7@wisc.edu>
// Lecture #: <004 @4:00pm>
// Notes to Grader: <any optional extra notes to your grader>

import java.lang.IllegalArgumentException;


public class Account implements Comparable<Account> {
    // Account Instance Variables
    private final String ownerName;
    private final int accountNumber;
    private double balance;
    private double credit;
    private final String securityAnswer;

    /**
     * Constructor of Account objects
     *
     * @param name -> Name of Account Owner
     * @param account -> Key for Account
     */
    public Account(String name, int account, double balance, double credit, String securityAnswer){
        this.ownerName = name;
        this.accountNumber = account;
        this.balance = balance;
        this.credit = credit;
        this.securityAnswer = securityAnswer;
    }

    /**
     * Method to retrieve Account Name
     *
     * @return Account Owner's Name (String)
     */
    public String getOwnerName() {
        return (this.ownerName);
    }

    /**
     * Method to retrieve Account Number
     *
     * @return Account Key Number (int)
     */
    public int getAccountNumber() {
        return (this.accountNumber);
    }

    /**
     * Method to retrieve account balance
     *
     * @return Account balance (int)
     */
    public double getBalance(){
        return (this.balance);
    }

    /**
     * Method to set account balance
     *
     * @param balance: New Account balance (int)
     */
    public void setBalance(double balance){
        this.balance = balance;
    }
    
    /**
     * Method to retrieve account balance
     *
     * @return Account balance (int)
     */
    public double getCredit(){
        return (this.credit);
    }

    /**
     * Method to set account credit
     *
     * @param credit: New Account balance (int)
     */
    public void setCredit(double credit){
        this.credit = credit;
    }
    
    /**
     * Method to get security answer
     * 
     * @return security answer
     */
    public String getSecurityAnswer() {
      return this.securityAnswer;
    }

    /**
     * Method to compare two Account Objects by Account Number
     *
     * @param account -> Account object to be compared with
     * @return 1 if "this" is GREATER than account, 0 if equal, and -1 if "this" is LESS than account (int)
     */
    public int compareTo(Account account) throws IllegalArgumentException{
        if (account == null) {
            throw new IllegalArgumentException("Passed Account was Non-Existent");
        }

        return (Integer.compare(this.accountNumber, account.getAccountNumber()));
    }
}