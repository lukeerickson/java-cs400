import java.util.List;

public class BankApp {
    public static void main(String[] args) throws Exception{
        IAccountLoader loader = new AccountLoader(); // new AccountLoader
        List<Account> accounts = loader.loadAccounts("accounts.xml");
        IAccountBackend backend = new accountBackend(); // new AccountBackend
        for (Account account : accounts){
            //System.out.println(account.getAccountNumber());
            backend.addAccount(account);
        }
        IAccountFrontend frontend = new AccountFrontend(backend); // new AccountFrontend(backend, loader)
        frontend.runCommandLoop();
    }
}
