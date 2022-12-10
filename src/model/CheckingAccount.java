package model;

import model.Account;
import model.AccountType;

import java.util.Map;
/**
    This is the class for Checking model.Account
    @author: Jiahang Li
    @version: 1.0
*/
public class CheckingAccount extends Account {

    // constructor with currency
    public CheckingAccount(int accountID, String ownerName, String pwd, AccountType type) {
        super(accountID, type, ownerName, pwd);
    }

    public CheckingAccount(int accountID, String ownerName, String pwd, AccountType type, Map<CurrencyType, Double> balance) {
        this(accountID, ownerName, pwd, type);
        setBalance(balance);
    }

    @Override
    public boolean transfer(Account to, CurrencyType currencyType, double amount) {
        boolean isValid = this.withdraw(currencyType, amount);
        if (isValid) {
            to.deposit(currencyType, amount);
            return true;
        }
        else return false;
    }
}
