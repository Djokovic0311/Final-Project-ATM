package model;

import java.util.Date;

/**
 This is the class for transfer transaction
 @author: Jiahang Li
 @version: 1.0
 */

public class TransferTransaction extends Transaction{
    // tht user whom money is transferred to


    // constructor for transfer transaction
    public TransferTransaction(int id, long timestamp, double amount, int userID,
                               int fromAccountID, int toAccountID, CurrencyType currencyType) {
        super(id, timestamp, amount, userID, TransactionType.TRANSFER,fromAccountID,toAccountID, currencyType);

    }


}
