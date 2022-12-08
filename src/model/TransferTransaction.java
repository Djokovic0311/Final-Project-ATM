package model;

import java.util.Date;

/**
 This is the class for transfer transaction
 @author: Jiahang Li
 @version: 1.0
 */

public class TransferTransaction extends Transaction{
    // the account id from which the transfer is conducted
    private int fromAccountID;
    // the account id to which the transfer is conducted
    private int toAccountID;
    // tht user whom money is transferred to
    private int toUserID;

    // constructor for transfer transaction
    public TransferTransaction(String id, Date timestamp, double amount, String userID, TransactionType type,
                               int fromAccountID, int toAccountID, int toUserID) {
        super(id, timestamp, amount, userID, TransactionType.TRANSFER);
        this.fromAccountID = fromAccountID;
        this.toAccountID = toAccountID;
        this.toUserID = toUserID;
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

    public int getToUserID() {
        return toUserID;
    }

    public void setToUserID(int toUserID) {
        this.toUserID = toUserID;
    }

    @Override
    public String toString() {
        return "model.TransferTransaction{" +
                "fromAccountID=" + fromAccountID +
                ", toAccountID=" + toAccountID +
                ", toUserID=" + toUserID +
                '}';
    }
}
