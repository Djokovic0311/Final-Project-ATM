package model;

import java.util.Map;
import java.util.HashMap;

/*
    This is the abstract class for model.Account
    @author: Jiahang Li
    @version: 1.0
*/

public abstract class Account {
    // account ID
    private int accountID;
    // the owner's name of account
    private String ownerName;
    private int userID;
    // account password
    private String password;
    // account type, ex: checking, saving...
    private AccountType type;
    // account status, activated or not
    private boolean activated;
    // current balance
    private Map<CurrencyType, Double> balance;

    // default constructor
    public Account() {
        this.activated = true;
        this.balance = new HashMap<>();
    }

    // construct an account with parameters, no initial balance
    public Account(int accountID, AccountType type, String ownerName, String pwd) {
        this.accountID = accountID;
        this.ownerName = ownerName;
        this.password = pwd;
        this.type = type;
        this.activated = true;
        this.balance = new HashMap<>();
    }

    // construct an account with initial balance
    public Account(int accountID, String ownerName, String pwd, AccountType type, CurrencyType currencyType, double balance) {
        this.accountID = accountID;
        this.ownerName = ownerName;
        this.password = pwd;
        this.type = type;
        this.activated = true;
        this.balance = new HashMap<>();
        this.balance.put(currencyType, balance);
    }

    
    // transfer money from one account to another, 
    // return true if the transfer is valid
    public abstract boolean transfer(Account to, CurrencyType currencyType, double amount);

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

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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

    // override the toString
    @Override
    public String toString() {
        String str = "";
        String tabs = "\t";
        str += accountID + tabs;
        str += type + tabs;
        str += activated + tabs;
        str += balance + tabs;
        return str;
    }
}