
import java.util.HashMap;
import java.util.HashSet;

public class LoanAccount extends Account{

    public LoanAccount(int accountID, AccountType type, String ownerName, String pwd)
    {
    	super(accountID, type, ownerName, pwd);
    	
    }

    public LoanAccount(int accountID, String ownerName, String pwd, AccountType type, Map<Currency, Double> balance) {
        this(accountID, ownerName, pwd, type);
        setBalance(balance);
    }
    
    public static String createLoanAccount(AccountType type, Customer customer, int startBalance){
        if (customer.getSavingAccounts()!=null){
            customer.getSavingAccount(super.accountID).withdraw(super.accountID, startBalance);//function not exist yet
            customer.createAccount(type, startBalance);}
    }

}

