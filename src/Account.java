import java.util.Map;
import java.util.HashMap;

/*
    This is the abstract class for Account
    @author: Jiahang Li
    @version: 1.0
*/

import javax.security.auth.login.AccountException;
import java.util.Map;

public abstract class Account {
    // account ID
    private int accountID;
    // the owner's name of account
    private String ownerName;
    // account password
    private String password;
    // account type, ex: checking, saving...
    private AccountType type;
    // account status, activated or not
    private boolean activated;
    // current balance
    private Map<Currency, Double> balance;

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
    public Account(int accountID, String ownerName, String pwd, AccountType type, Currency currency, double balance) {
        this.accountID = accountID;
        this.ownerName = ownerName;
        this.password = pwd;
        this.type = type;
        this.activated = true;
        this.balance = new HashMap<>();
        this.balance.put(currency, balance);
    }

    
    // transfer money from one account to another, 
    // return true if the transfer is valid
    public abstract boolean transfer(Account to, Currency currency);

    // set the balance of a given currency
    public void setBalanceByCurrency(Currency currency, double amount)  {
        this.balance.put(currency, amount);
    }

    //get the balance of a given currency
    public double getBalanceByCurrency(Currency currency) {
        return this.balance.get(currency);
    }

    // add amount to a specific currency
    public void addToCurrency(Currency currency, double amount) {
        this.balance.put(currency, this.balance.getOrDefault(currency,0.0) + amount);
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

    public Map<Currency, Double> getBalance() {
        return balance;
    }

    public void setBalance(Map<Currency, Double> balance) {
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