package dao;

import model.CurrencyType;
import model.CustomerHeldStock;
import model.Loan;

import java.sql.*;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class LoanDao {

    // Get all the loan of this customer
    public List<Loan> getLoansByCustomerID(int customerID) {
        List<Loan> result = new ArrayList<>();
        try {
            String query = "SELECT * FROM Loans WHERE customerID = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int loanID = rs.getInt(1);
                String currencyType = rs.getNString(3);
                CurrencyType t = CurrencyType.getTypeFromString(currencyType);
                int amount = rs.getInt(4);
                long date = (long) rs.getDouble(5);
                Loan l = new Loan(loanID, customerID, amount, t, date);
                result.add(l);
            }
        } catch (Exception e) { return null; }
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

    // Add a new loan into the database
    public void insertLoan(int customerID, double amount, long loanDate, CurrencyType currencyType, int loanID){
        try {
            String query = "INSERT INTO Loans (loanID, customerID, currencyType, amount, loanTime) VALUES (?,?,?,?,?);";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, loanID);
            stmt.setInt(2, customerID);
            stmt.setString(3, currencyType.toString());
            stmt.setDouble(4, amount);
            stmt.setDouble(5, (double) loanDate);
            stmt.executeUpdate();
        } catch (Exception ignored) {}
    }


    // Retrieve the loan from database based on the loanID
    public Loan getLoanByID(int loanID){
        try {
            String query = "SELECT * FROM Loans WHERE loanID = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, loanID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int customerID = rs.getInt(2);
                String currencyType = rs.getNString(3);
                CurrencyType t = CurrencyType.getTypeFromString(currencyType);
                int amount = rs.getInt(4);
                long date = (long) rs.getDouble(5);
                return new Loan(loanID, customerID, amount, t, date);
            }
            return null;
        } catch (Exception e) { return null; }
    }

    // Update the amount of a loan, used when customer pay the loan
    public void updateLoan(int loanID, double amount) {
        try {
            String query = "UPDATE Loans SET amount = ? WHERE loanID = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDouble(1, amount);
            stmt.setInt(2, loanID);
            stmt.executeUpdate();
        } catch (Exception ignored) {}
    }

    // Get the remaining amount of a loan
    public double getLoanRemaining(int loanID){
        try {
            String query = "SELECT * FROM Loans WHERE loanID = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, loanID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(4);
            }
            return 0;
        } catch (Exception e) { return 0; }
    }

    // Remove a loan from the database
    public void deleteLoan(int loanID){
        try {
            String query = "DELETE FROM Loans WHERE loanID = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, loanID);
            stmt.executeUpdate();
        } catch (Exception ignored) {}
    }
}