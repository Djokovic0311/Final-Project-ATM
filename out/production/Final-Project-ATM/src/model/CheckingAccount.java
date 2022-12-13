package model;

import model.Account;
import model.AccountType;

import java.util.Map;
/**
    This is the class for Checking model.Account
    @author: Jiahang Li, Chen, Zhu
    @version: 2.0
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

    public CheckingAccount(int accountID, int userID, AccountType type, CurrencyType t, double balance) {
        this(accountID, userID, type);
        this.balance.put(t, balance);
    }

    public Map<CurrencyType, Double> getBalance() {
        return balance;
    }
}
