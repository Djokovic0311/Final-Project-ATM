package model;

import model.Account;
import model.AccountType;
import model.Currency;

import java.util.Hashtable;
import java.util.Map;
/*
    This is the class for Manager's model.Account
    @author: Jiahang Li
    @version: 1.0
*/
public class ManagerAccount extends Account{
    // constructor with currency
    public ManagerAccount(int accountID, int userID, AccountType type) {
        super(accountID, userID, type);
        this.balance = initialBalance();
    }

    public ManagerAccount(int accountID, int userID, AccountType type, Map<CurrencyType, Double> balance) {
        this(accountID, userID, type);
        setBalance(balance);
    }
    @Override
    protected Map<CurrencyType, Double> initialBalance() {
        Map<CurrencyType, Double> balance = new Hashtable<>();
        balance.put(CurrencyType.USD, 1000000.0);
        balance.put(CurrencyType.EUR, 1000000.0);
        balance.put(CurrencyType.CNY, 1000000.0);
        return balance;
    }


    @Override
    public boolean transfer(Account to, CurrencyType currencyType, double amount) {
        return false;
    }
}
