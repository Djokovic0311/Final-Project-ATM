package dao;

import model.CurrencyType;
import model.Transaction;
import model.TransactionType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao {
    public void insertTransactionIntoDB(int customerID, int senderAccountId, int receiverAccountId, double amount,
                                        CurrencyType currencyType, TransactionType transactionType, long timestamp) throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "108875556");

        Statement stmt = con.createStatement();


        ResultSet countset;
        countset = stmt.executeQuery("SELECT COUNT(*) as count FROM TRANSACTIONS");

        int recordNumber = 0;
        if (countset.next()) {
            recordNumber = countset.getInt(1);
        }
        int transactionid = recordNumber + 1;
        ResultSet rs;

        rs = stmt.executeQuery("INSERT INTO Transactions (transactionID,customerID,accountID1,accountID2,currencyType,balance,transactionType,transactionTime) " +
                "VALUES(" + transactionid + ", " +  customerID + ',' + senderAccountId + ',' + receiverAccountId + ',' +currencyType.toString() + "," + amount + "," + transactionType.toString() + "," + (double) timestamp+ ");");

    }
    // check whether an transaction exists
    public boolean transactionExist(int transactionID) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "108875556");
        Statement stmt = con.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery("SELECT * FROM TRANSACTIONS WHERE transactionID =" + transactionID + ";");
        return rs.next();
    }


    public List<Transaction> getTransactionsforCustomer(int customerID) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "108875556");
        Statement stmt = con.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery("SELECT * FROM TRANSACTIONS WHERE userID =" + customerID + ";");

        List<Transaction> transactions = new ArrayList<>();
        if(rs.next()){
            int transactionId = rs.getInt(0);
            String type  = rs.getString(3);
            TransactionType transactiontype = TransactionType.getTypeFromString(type);
            int fromacc = rs.getInt(2);
            int toacc = rs.getInt(3);
            int balance = rs.getInt(4);
            String currencyType = rs.getString(4);
            CurrencyType currencytype = CurrencyType.getTypeFromString(currencyType);
            long timestamp = (long)rs.getDouble(7);

            Transaction T = new Transaction(transactionId,timestamp, balance,customerID,transactiontype,fromacc,toacc,currencytype);
            transactions.add(T);
        }

        return transactions;
    }

    public List<Transaction> getDailyTransactions(long timestamp){
        return null;
    }
}
