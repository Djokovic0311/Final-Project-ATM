package model;/*
    This is the base class for transaction
       @author: Jiahang Li
    @version: 1.0
 */

import java.util.Date;

public class Transaction {

    // transaction id
    private  int ID;
    // time when transaction is made
    private long timestamp;
    // amount related to transaction
    private double amount;
    // person who made the transaction
    private int userID;
    private int fromAccountID;
    private int toAccountID;
    private CurrencyType currencyType;
    // transaction type chosen from enum
    private TransactionType type;

    public Transaction(int id, long timestamp, double amount, int userID, TransactionType type, int fromAccountID, int toAccountID, CurrencyType currencyType) {
        this.ID = id;
        this.timestamp = timestamp;
        this.amount = amount;
        this.userID = userID;
        this.currencyType = currencyType;
        this.fromAccountID = fromAccountID;
        this.toAccountID = toAccountID;

        this.type = type;
    }
    
    // getters and setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getuserID() {
        return userID;
    }

    public void setuserID(int userID) {
        this.userID = userID;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public int getFromAccountID() {
        return fromAccountID;
    }

    public void setFromAccountID(int fromAccountID) {
        this.fromAccountID = fromAccountID;
    }

    public int getToAccountID() {
        return toAccountID;
    }

    public void setToAccountID(int toAccountID) {
        this.toAccountID = toAccountID;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    @Override
    public String toString() {
        return getID() + "\t" +
                getTimestamp() + "\t" +
                getAmount() + "\t" +
                getuserID() + "\t" +
                getType();
    }
}
