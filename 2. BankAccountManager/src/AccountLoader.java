// --== CS400 AccountLoader File Header ==--
// Name: <Luke Erickson>
// CSL Username: <lerickson>
// Email: <lerickson7@wisc.edu>
// Lecture #: <004 @4:00pm>
// Notes to Grader: <any optional extra notes to your grader>

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class AccountLoader implements IAccountLoader {

  /**
   * Basic method used to load in an XML file and store data into accounts
   */
  @Override
  public List<Account> loadAccounts(String filepath) throws FileNotFoundException {
    File accountXML = new File(filepath);
    if (!accountXML.isFile() || !accountXML.exists()) {
      throw new FileNotFoundException("Your XML file does not exist");
    }
    List<Account> accounts = new ArrayList<Account>();
    List<String> names = new ArrayList<String>();
    List<Integer> accountNumbers = new ArrayList<Integer>();
    List<Double> balances = new ArrayList<Double>();
    List<Double> credits = new ArrayList<Double>();
    List<String> securityAnswers = new ArrayList<String>();

    Scanner scan = new Scanner(accountXML);
    //int j = 0;

    // store data in their proper arrayLists
    while (scan.hasNextLine()) {
        //j++;
        String output = scan.nextLine().replace("\t", "");
        if(output.equals("<Names>")) {
          output = scan.nextLine().replace("\t", "");
          names.add(output);
        }
        else if(output.equals("<account-number>")) {
          output = scan.nextLine().replace("\t", "");
          accountNumbers.add(Integer.valueOf(output));
        }
        else if(output.equals("<balance>")) {
          output = scan.nextLine().replace("\t", "");
          balances.add(Double.valueOf(output));
        }
        else if(output.equals("<credit>")) {
          output = scan.nextLine().replace("\t", "");
          credits.add(Double.valueOf(output));
        }
        else if(output.equals("<Colors>")) {
          output = scan.nextLine().replace("\t", "");
          securityAnswers.add(output);
        }

        //System.out.println(output);

    }
    scan.close();
    
    //System.out.println(j);
    //System.out.println(names.size());

    // store all data in a list of accounts
    for (int i = 0; i < names.size(); i++) {
      //System.out.println(names.get(i));

      
      Account account = new Account(names.get(i), accountNumbers.get(i), balances.get(i),
          credits.get(i), securityAnswers.get(i));
      accounts.add(account);
    }
    //System.out.println(accounts.get(200).getBalance());

    return accounts;
  }

  
}
