package model;

import model.Transaction;
import model.TransactionType;

import java.util.Date;

/**
 This is the class for deposit transaction
 @author: Jiahang Li
 @version: 1.0
 */
public class WithdrawTransaction extends Transaction {
    public WithdrawTransaction(int ID, long timestamp, double amount, int userID) {
        super(ID, timestamp, amount, userID, TransactionType.WITHDRAW);
    }
}
