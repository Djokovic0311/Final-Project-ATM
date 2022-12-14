package dao;

import model.CurrencyType;
import model.Transaction;
import model.TransactionType;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao {
    public void insertTransactionIntoDB(int customerID, int senderAccountId, int receiverAccountId, double amount,
                                        CurrencyType currencyType, TransactionType transactionType, long timestamp) throws SQLException {
        try {
            String query;
            User user;
            ResultSet rs;
            ResultSet countset;
            query = "SELECT COUNT(*) as count FROM TRANSACTIONS;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            countset = stmt.executeQuery();

            int recordNumber = 0;
            if (countset.next()) {
                recordNumber = countset.getInt(1);
            }
            int transactionid = recordNumber + 1;

            query = "INSERT INTO Transactions (transactionID,customerID,accountID1,accountID2,currencyType,balance,transactionType,transactionTime) VALUES(?,?,?,?,?,?,?,?);";
            stmt.setInt(1, transactionid);
            stmt.setInt(2, customerID);
            stmt.setInt(3, senderAccountId);
            stmt.setInt(4,receiverAccountId);
            stmt.setString(5,currencyType.toString());
            stmt.setDouble(6,amount);
            stmt.setString(7,transactionType.toString());
            stmt.setDouble(8,(double) timestamp);
            rs = stmt.executeQuery();


        }catch (Exception E){
            System.out.println("Insertion into DB Failed");
        }
    }
    // check whether an transaction exists
    public boolean transactionExist(int transactionID) throws SQLException {
        try {
            String query;
            User user;
            ResultSet rs;
            ResultSet countset;
            query = "SELECT * FROM TRANSACTIONS WHERE transactionID = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, transactionID);
            rs = stmt.executeQuery();

            int count = 0;
            if (rs.next()) {
                count = count + 1;
            }
            if (count == 0) {
                return false;
            } else {
                return true;
            }
        } catch(Exception E){
            return false;
        }
    }


    public List<Transaction> getTransactionsforCustomer(int customerID) throws SQLException {
        try {
            String query;
            User user;
            ResultSet rs;
            ResultSet countset;
            query = "SELECT * FROM TRANSACTIONS WHERE customerID = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, customerID);
            rs = stmt.executeQuery();


            List<Transaction> transactions = new ArrayList<>();
            if (rs.next()) {
                int transactionId = rs.getInt(0);
                String type = rs.getString(3);
                TransactionType transactiontype = TransactionType.getTypeFromString(type);
                int fromacc = rs.getInt(2);
                int toacc = rs.getInt(3);
                int balance = rs.getInt(4);
                String currencyType = rs.getString(4);
                CurrencyType currencytype = CurrencyType.getTypeFromString(currencyType);
                long timestamp = (long) rs.getDouble(7);

                Transaction T = new Transaction(transactionId, timestamp, balance, customerID, transactiontype, fromacc, toacc, currencytype);
                transactions.add(T);
            }

            return transactions;
        } catch (Exception E){
            System.out.println("An error occured");
            return null;
        }
    }
}

