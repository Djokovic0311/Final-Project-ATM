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

        ResultSet rs;
        rs = stmt.executeQuery("INSERT INTO Transactions (ID,userID,AccountID1,AccountID2,Balance,Transactiontype,currency,trasactionDate,stockID) " +
                "VALUES(" + customerID + ',' + senderAccountId + ',' + receiverAccountId + ',')
                "");

    }
    // check whether an transaction exists
    public boolean transactionExist(int transactionID) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "108875556");
        Statement stmt = con.createStatement();

        ResultSet rs;
        rs = stmt.executeQuery("SELECT * FROM TRANSACTIONS WHERE transactionID =" + transactionID + ";");
        int count = 0;
        if(rs.next()){
            count = count + 1;
        }
        if(count == 0){
            return false;
        }else{
            return true;
        }

    }
    public List<Transaction> getTransactionsforCustomer(int customerID) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "108875556");
        Statement stmt = con.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery("SELECT * FROM TRANSACTIONS WHERE userID =" + customerID + ";");

        List<Transaction> transactions = new ArrayList<>();
        if(rs.next()){
            int transactionId = rs.getInt(0);
            String type  = rs.getInt(3);
            int fromacc = rs.getInt(2);
            int toacc = rs.getInt(3);
            int balance = rs.getInt(4);
            String currencyType = rs.getInt(6);

            Transaction T = new Transaction(transactionId,timestamp,customerID,type,fromacc,toacc, int toAccountID, CurrencyType currencyType);
            transactions.add(T);
        }

    return transactions;
    }
 }

