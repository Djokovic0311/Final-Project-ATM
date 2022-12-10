package model;

import java.util.Date;
/**
 This is the class for manager transaction
 @author: Jiahang Li
 @version: 1.0
 */
public class ManagerTransaction extends Transaction{
    // userID denotes to customer, since all managers see transactions
    public ManagerTransaction(int id, long timestamp, double amount, int userID) {
        super(id, timestamp, amount, userID, TransactionType.MANAGER);
    }
}
