package model;/*
    This is the base class for transaction
       @author: Jiahang Li
    @version: 1.0
 */

import java.util.Date;

public class Transaction {

    // transaction id
    private  String ID;
    // time when transaction is made
    private Date timestamp;
    // amount related to transaction
    private double amount;
    // person who made the transaction
    private String userID;
    // transaction type chosen from enum
    private TransactionType type;

    public Transaction(String id, Date timestamp, double amount, String userID, TransactionType type) {
        this.ID = id;
        this.timestamp = timestamp;
        this.amount = amount;
        this.userID = userID;
        this.type = type;
    }
    
    // getters and setters
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getuserID() {
        return userID;
    }

    public void setuserID(String userID) {
        this.userID = userID;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return getID() + "\t" +
                getTimestamp().toString() + "\t" +
                getAmount() + "\t" +
                getuserID() + "\t" +
                getType();
    }
}
