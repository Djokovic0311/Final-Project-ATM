package model;

import model.Account;
import model.AccountType;
import model.Currency;

import java.util.Map;
/*
    This is the class for Manager's model.Account
    @author: Jiahang Li
    @version: 1.0
*/
public class ManagerAccount extends Account{
    // constructor with currency
    public ManagerAccount(int accountID, String ownerName, AccountType type) {
        super(accountID, type, ownerName);
    }

    public ManagerAccount(int accountID, String ownerName, AccountType type, Map<CurrencyType, Double> balance) {
        this(accountID, ownerName, type);
        setBalance(balance);
    }
    @Override
    public boolean transfer(Account to, CurrencyType currencyType, double amount) {
        return false;
    }
}
