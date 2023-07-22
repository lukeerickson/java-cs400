import java.util.List;
import java.util.Scanner;

public class AccountFrontend implements IAccountFrontend {
  
  Scanner scanner;
  IAccountBackend backend;
  
  public AccountFrontend(IAccountBackend backend) {
    this.backend = backend;
    scanner = new Scanner(System.in);
  }
  
  public AccountFrontend(String text, IAccountBackend backend) {
    this.backend = backend;
    scanner = new Scanner(text);
  }
  
  @Override
  public void runCommandLoop() {
    System.out.println("=================================");
    System.out.println("Welcome to the Banker App!");
    System.out.println("=================================");
    Boolean quit = false;

    // command loop
    while (!quit) {
      
      displayCommandMenu();
      String cmd = scanner.nextLine(); // read user command

      // search by account number
      if (cmd.equals("s") || cmd.equals("S") || cmd.equals("1")) {
        // System.out.println("\n");
        accountSearch();
      }
      // add account
      else if (cmd.equals("a") || cmd.equals("A") || cmd.equals("2")) {
        // System.out.println("\n");
        accountAdd();
      }
      // update balance
      else if (cmd.equals("b") || cmd.equals("B") || cmd.equals("3")) {
        // System.out.println("\n");
        balanceUpdate();
      }
      // update credit
      else if (cmd.equals("c") || cmd.equals("C") || cmd.equals("4")) {
        // System.out.println("\n");
        creditUpdate();
      }
      // transfer balance from account to another account
      else if (cmd.equals("t") || cmd.equals("T") || cmd.equals("5")) {
        transferBalance();
      }
      // quit
      else if (cmd.equals("q") || cmd.equals("Q") || cmd.equals("5")) {
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

  @Override
  public void displayCommandMenu() {
    // print menu and prompt user command line
    System.out.println("Command Menu:");
    System.out.println("\t1) [S]earch Account Database");
    System.out.println("\t2) [A]dd Account to Database");
    System.out.println("\t3) [B]alance Update to Account");
    System.out.println("\t4) [C]redit Update to Account");
    System.out.println("\t5) [T]ransfer Balance Between Accounts");
    System.out.println("\t6) [Q]uit");
    System.out.print("Choose a command from the menu above: ");
    
  }

  
  @Override
  public void displayAccount(Account account) {
    System.out.println("Account: #" + account.getAccountNumber() + ", Owner: " + account.getOwnerName() + ", Balance: " + account.getBalance() + ", Credit: " + account.getCredit());
    
  }

  @Override
  public void accountSearch() {
    boolean quit = false;

    while (!quit) {
      System.out.println("Enter Account Number :");
      int cmd = scanner.nextInt(); // read user command
      try {
        displayAccount(backend.getAccount(cmd));
        // loop re-runs if command is invalid, does not re-run otherwise
        quit = true;
      } catch (Exception e) {
        System.out.println("\nWarning: Invalid command. Returning to main menu");
      }
    }
    scanner.nextLine();
    
  }

  @Override
  public void accountAdd() {
    boolean quit = false;

    while (!quit) {
      System.out.println("Enter Account Number: ");
      int actNum = scanner.nextInt(); // read user command
      //System.out.println();
      System.out.println("Enter Account Owner: ");
      String actName = scanner.nextLine();
      //System.out.println();
      System.out.println("Enter Account Balance: ");
      double actBal = scanner.nextDouble();
      //System.out.println();
      System.out.println("Enter Account Balance: ");
      double actCred = scanner.nextDouble();
      System.out.println("Enter Your Favorite Color: ");
      String answer = scanner.nextLine();
      Account account = new Account(actName, actNum, actBal, actCred, answer);
      try {
        backend.addAccount(account);
        // loop re-runs if command is invalid, does not re-run otherwise
        System.out.println("");
        quit = true;
      } catch (Exception e) {
        System.out.println("\nWarning: Invalid command. Returning to main menu");
      }
    }
    scanner.nextLine();
    
  }

  @Override
  public void transferBalance() {
    boolean quit = false;

    while (!quit) {
      System.out.println("Enter Account Number 1:");
      int act1 = scanner.nextInt(); // read user command
      System.out.println("Enter Account Number 2: ");
      int act2 = scanner.nextInt();
      System.out.println("Enter Transfer Amount: ");
      int amt = scanner.nextInt();
      try {
        backend.transferBalance(backend.getAccount(act1), backend.getAccount(act2), amt);
        // loop re-runs if command is invalid, does not re-run otherwise
        System.out.println("Succefully Transfered " + amt + "$" + "from Account #" + act1 + " to Account #" + act2);
        quit = true;
      } catch (Exception e) {
        System.out.println("\nWarning: Invalid command. Returning to main menu");
      }
    }
    scanner.nextLine();
    
  }

  @Override
  public void balanceUpdate() {
    boolean quit = false;

    while (!quit) {
      System.out.println("Enter Account Number 1:");
      int act = scanner.nextInt(); // read user command
      scanner.nextLine();
      System.out.println("Enter Amount: ");
      String output = scanner.nextLine();
      double amt = Double.valueOf(output);
      try {
        backend.updateBalance(backend.getAccount(act), amt);
        // loop re-runs if command is invalid, does not re-run otherwise
        System.out.println("Succefully added balance by" + amt + "$" + "to Account #" + act);
        quit = true;
      } catch (Exception e) {
        System.out.println("\nWarning: Invalid command. Please retry");
      }
    }
    scanner.nextLine();
    
  }
  
  @Override
  public void creditUpdate() {
    boolean quit = false;

    while (!quit) {
      System.out.println("Enter Account Number 1:");
      int act = scanner.nextInt(); // read user command
      scanner.nextLine();
      System.out.println("Enter Amount: ");
      String output = scanner.nextLine();
      double amt = Double.valueOf(output);
      System.out.println(act);
      try {
        backend.updateCredit(backend.getAccount(act), amt);
        // loop re-runs if command is invalid, does not re-run otherwise
        System.out.println("Succefully updated credit by " + amt + "$" + "to Account #" + act);
        quit = true;
      } catch (Exception e) {
        System.out.println("\nWarning: Invalid command. Please retry");
      }
    }
    scanner.nextLine();
    
  }

}
