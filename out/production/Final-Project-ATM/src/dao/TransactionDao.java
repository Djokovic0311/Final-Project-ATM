package dao;

import model.CurrencyType;
import model.Transaction;
import model.TransactionType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao {
    public void insertTransactionIntoDB(int customerID, int senderAccountId, int receiverAccountId, double amount,
                                        CurrencyType currencyType, TransactionType transactionType, long timestamp) {
        try {
            Connection conn = ConnectDao.connectToDb();
            String query1 = "SELECT COUNT(*) as count FROM Transactions;";
            PreparedStatement stmt1 = conn.prepareStatement(query1);
            ResultSet rs = stmt1.executeQuery();
            int recordNumber = 0;
            if (rs.next()) {
                recordNumber = rs.getInt(1);
            }
            int transactionID = recordNumber + 1;
            String query2 = "INSERT INTO Transactions (transactionID,customerID,accountID1,accountID2,currencyType,balance,transactionType,transactionTime) " +
                    "VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement stmt2 = conn.prepareStatement(query2);
            stmt2.setInt(1, transactionID);
            stmt2.setInt(2, customerID);
            stmt2.setInt(3, senderAccountId);
            stmt2.setInt(4, receiverAccountId);
            stmt2.setString(5, currencyType.toString());
            stmt2.setDouble(6, amount);
            stmt2.setString(7, transactionType.toString());
            stmt2.setDouble(8, (double) timestamp);
            stmt2.executeUpdate();
        } catch (Exception ignored) {}


    }
    // check whether a transaction exists
    public boolean transactionExist(int transactionID) {
        try {
            String query = "SELECT * FROM TRANSACTIONS WHERE transactionID = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, transactionID);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            return false;
        }
    }
    public List<Transaction> getTransactionsforCustomer(int customerID) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            String query = "SELECT * FROM TRANSACTIONS WHERE customerID = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                int transactionId = rs.getInt(1);
                int fromacc = rs.getInt(3);
                int toacc = rs.getInt(4);
                String currencyType = rs.getString(5);
                CurrencyType currencytype = CurrencyType.getTypeFromString(currencyType);
                int balance = rs.getInt(6);
                String type = rs.getString(7);
                TransactionType transactiontype = TransactionType.getTypeFromString(type);
                long timestamp = (long) rs.getDouble(8);
                Transaction T = new Transaction(transactionId,timestamp, balance,customerID,transactiontype,fromacc,toacc,currencytype);
                transactions.add(T);
            }
        } catch (Exception e) { return null; }
        if (transactions.isEmpty()) { return null; }
        return transactions;
    }

    public List<Transaction> getDailyTransactions(long timestamp){
        double time = (double) timestamp;
        List<Transaction> transactions = new ArrayList<>();
        try {
            String query = "SELECT * FROM TRANSACTIONS WHERE transactionTime = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDouble(1, time);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                int transactionId = rs.getInt(1);
                int customerID = rs.getInt(2);
                int fromacc = rs.getInt(3);
                int toacc = rs.getInt(4);
                String currencyType = rs.getString(5);
                CurrencyType currencytype = CurrencyType.getTypeFromString(currencyType);
                int balance = rs.getInt(6);
                String type = rs.getString(7);
                TransactionType transactiontype = TransactionType.getTypeFromString(type);
                Transaction T = new Transaction(transactionId,timestamp, balance,customerID,transactiontype,fromacc,toacc,currencytype);
                transactions.add(T);
            }
        } catch (Exception e) { return transactions; }
        return transactions;
    }
}
