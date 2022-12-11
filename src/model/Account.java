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
    // account type, ex: checking, saving...
    protected AccountType type;
    // current balance
    protected Map<CurrencyType, Double> balance;

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

    // transfer money from one account to another, 
    // return true if the transfer is valid
    public boolean transfer(Account to, CurrencyType currencyType, double amount, double fee) {
        boolean isValid = this.withdraw(currencyType, amount);
        if (isValid) {
            to.deposit(currencyType, amount - fee);
            pay(currencyType, fee);
            return true;
        }
        return false;
    }

    // set the balance of a given currency
    public void setBalanceByCurrency(CurrencyType currencyType, double amount)  {
        this.balance.put(currencyType, amount);
    }

    //get the balance of a given currency
    public double getBalanceByCurrency(CurrencyType currencyType) {
        return this.balance.get(currencyType);
    }

    // deposit amount to a specific currency
    public void deposit(CurrencyType currencyType, double amount) {
        this.balance.put(currencyType, this.balance.getOrDefault(currencyType,0.0) + amount);
    }

    // withdraw amount to a specific currency, if not enough, return false
    public boolean withdraw(CurrencyType currencyType, double amount) {
        if(this.balance.containsKey(currencyType) && this.balance.get(currencyType) >= amount) {
            this.balance.put(currencyType, this.balance.getOrDefault(currencyType,0.0) - amount);
            return true;
        }
        else return false;
    }

    // getters and setters
    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public AccountType getType() {
        return type;
    }

    public void setBalance(Map<CurrencyType, Double> balance) {
        this.balance = balance;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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
//        str += activated + tabs;
        str += balance + tabs;
        return str;
    }

    public void pay(CurrencyType t, double amount) {
        ManagerAccount m = Database_Queries.get_manager_account();
        if (this.accountID != 0) {
            m.deposit(t, amount);
        } else {
            transfer(m, t, amount, 0);
        }
    }
}