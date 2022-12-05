import java.util.List;
import java.util.Map;
/*
    This is the class for Manager's Account
    @author: Jiahang Li
    @version: 1.0
*/
public class ManagerAccount extends Account{
    // constructor with currency
    public ManagerAccount(int accountID, String ownerName, String pwd, AccountType type) {
        super(accountID, type, ownerName, pwd);
    }

    public ManagerAccount(int accountID, String ownerName, String pwd, AccountType type, Map<Currency, Double> balance) {
        this(accountID, ownerName, pwd, type);
        setBalance(balance);
    }
    @Override
    public boolean transfer(Account to, Currency currency, double amount) {
        return false;
    }
}
