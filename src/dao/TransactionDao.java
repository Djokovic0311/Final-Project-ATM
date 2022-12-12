package dao;

import model.CurrencyType;
import model.Transaction;
import model.TransactionType;

import java.util.List;

public class TransactionDao {
    public void insertTransactionIntoDB(int customerID, int senderAccountId, int receiverAccountId, double amount,
                                        CurrencyType currencyType, TransactionType transactionType, long timestamp) {

    }
    // check whether an transaction exists
    public boolean transactionExist(int transactionID) {
        return false;
    }
    public List<Transaction> getTransactionsforCustomer(int customerID) {
        return null;
    }
}
