package model;

import java.util.Date;

/**
    This is the class for deposit transaction
    @author: Jiahang Li
    @version: 1.0
 */
public class DepositTransaction extends Transaction {
    public DepositTransaction(int ID, long timestamp, double amount, int userID,int fromAccountID, int toAccountID, CurrencyType currencyType) {
        super(ID, timestamp, amount, userID, TransactionType.DEPOSIT,fromAccountID,toAccountID,currencyType);
    }
}
