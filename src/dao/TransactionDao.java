package dao;

import model.CurrencyType;
import model.TransactionType;

public class TransactionDao {
    public void insertTransactionIntoDB(int customerID, int senderAccountId, int receiverAccountId, double amount, CurrencyType currencyType, TransactionType transactionType) {

    }
    // check whether an transaction exists
    public boolean transactionExist(int transactionID) {
        return false;
    }
}
