package model;



import java.util.Date;
import java.util.Map;
/**
    This is the class for Checking model.Account
    @author: Jiahang Li
    @version: 1.0
*/
public class CheckingAccount extends Account {

    // constructor with currency
    public CheckingAccount(int accountID, int userID, AccountType type) {
        super(accountID, userID, type);
    }

    public CheckingAccount(int accountID, int userID, AccountType type, Map<CurrencyType, Double> balance) {
        this(accountID, userID, type);
        setBalance(balance);
    }

    public CheckingAccount(int accountID, int userID, AccountType type, double balance) {
        this(accountID, userID, type);
        this.balance.put(CurrencyType.USD, balance);
    }

    public Map<CurrencyType, Double> getBalance() {
        return balance;
    }

}
