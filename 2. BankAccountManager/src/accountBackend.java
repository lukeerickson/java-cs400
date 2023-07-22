
public class accountBackend implements IAccountBackend{
  protected RedBlackTree<Account> tree = new RedBlackTree<Account>();

  @Override
  public void addAccount(Account account) {
    tree.insert(account);
  }

  @Override
  public int getNumberOfAccounts() {
    
    return tree.size;
  }

  @Override
  public Account getAccount(int AccountNumber) {
   Account head = (Account) tree.root.data;
   RedBlackTree.Node<Account> currHead = tree.root;
   
   while(head != null) {
     if(head.getAccountNumber() == AccountNumber)
       return head;
   
   else if (head.getAccountNumber() < AccountNumber) {
     currHead = currHead.rightChild;
     head = currHead.data;
   }
     
   else {
     currHead = currHead.leftChild;
     head = currHead.data;
   }
   }
   
    return null;
  }

  @Override
  public boolean transferBalance(Account account1, Account account2, double balance) {
    if((account1.getBalance() - balance) < 0 || balance < 0) {
      return false;
    }
    else {
      account1.setBalance(account1.getBalance() - balance);
      account2.setBalance(account2.getBalance() + balance);
      return true;
    }
  }

  @Override
  public boolean updateBalance(Account account, double amount) {
      account.setBalance(account.getBalance() + amount);
      return true;
  }



  @Override
  public boolean updateCredit(Account account, double amount) {
    if(account.getCredit() + amount < 300 || account.getCredit() + amount > 850) {
      return false;
    }
    else {
      account.setCredit(account.getCredit() + amount);
      return true;
    }
  }

  

}
