package model;

import java.util.Hashtable;
import java.util.Map;
import java.util.HashMap;

/*
    This is the abstract class for model.Account
    @author: Jiahang Li
    @version: 1.0
*/

public abstract class Account {
    // account ID
    protected int accountID;
    protected int userID;
    protected AccountType type;
    protected Map<CurrencyType, Double> balance;

    // default constructor
    public Account() {
        this.balance = new HashMap<>();
    }

    // construct an account with parameters, no initial balance
    public Account(int accountID, int userID, AccountType type) {
        this.accountID = accountID;
        this.userID = userID;
        this.type = type;
        this.balance = initialBalance();
    }

    // construct an account with initial balance
    public Account(int accountID, int userID, AccountType type, CurrencyType currencyType, double balance) {
        this(accountID, userID, type);
        this.balance.put(currencyType, balance);
    }

    protected Map<CurrencyType, Double> initialBalance() {
        Map<CurrencyType, Double> balance = new Hashtable<>();
        balance.put(CurrencyType.USD, 0.0);
        balance.put(CurrencyType.EUR, 0.0);
        balance.put(CurrencyType.CNY, 0.0);
        return balance;
    }

    // override the toString
    @Override
    public String toString() {
        String str = "";
        String tabs = "\t";
        str += accountID + tabs;
        str += type + tabs;
        str += balance + tabs;
        return str;
    }

    // set the balance of a given currency
    public void setBalanceByCurrency(CurrencyType currencyType, double amount)  {
        this.balance.put(currencyType, amount);
    }

    //get the balance of a given currency
    public double getBalanceByCurrency(CurrencyType currencyType) {
        return this.balance.get(currencyType);
    }



    // getters and setters
    public int getAccountID() {
        return accountID;
    }

    public AccountType getType() {
        return type;
    }

    public Map<CurrencyType, Double> getBalance() {
        return balance;
    }

    public void setBalance(Map<CurrencyType, Double> balance) {
        this.balance = balance;
    }

    public int getUserID() {
        return userID;
    }

}