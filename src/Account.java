/*
    This is the abstract class for Account
    @author: Jiahang Li
    @version: 1.0
*/

public abstract class Account {
    // account ID
    protected int accountID;
    // account type, ex: checking, saving...
    protected String type;
    // account status, activated or not
    protected boolean activated;
    // current balance
    protected double balance;

    // default constructor
    public Account() {
        this.activated = true;
        this.balance = 0;
    }

    // construct an account with parameters
    public Account(int accountID, String type) {
        this.accountID = accountID;
        this.type = type;
        this.activated = true;
        this.balance = 0;
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
    
    // transfer money from one account to another, 
    // return true if the transfer is valid
    public abstract boolean transfer(Account to, Currency currency);

    // getters and setters
    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}