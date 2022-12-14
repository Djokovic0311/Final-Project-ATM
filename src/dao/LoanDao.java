package dao;

import model.CurrencyType;
import model.CustomerHeldStock;
import model.Loan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class LoanDao {

    // Get all the loan of this customer
    public List<Loan> getLoansByCustomerID(int customerID) {
        List<Loan> result = new ArrayList<>();
        try {
            Connection con = ConnectDao.connectToDb();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Loans WHERE customerID = " + customerID + ";");
            while (rs.next()) {
                int loanID = rs.getInt(0);
                String currencyType = rs.getNString(2);
                CurrencyType t = CurrencyType.getTypeFromString(currencyType);
                int amount = rs.getInt(3);
                long date = (long) rs.getDouble(4);
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
            Connection con = ConnectDao.connectToDb();
            Statement stmt = con.createStatement();
            stmt.executeQuery("INSERT INTO Loans (loanID, customerID, currencyType, amount, loanDate)" +
                    "VALUES (" + loanID + ", " + customerID + ", " + currencyType + " ," + amount + ", " + (double) loanDate + ");");
        } catch (Exception ignored) {}
    }


    // Retrieve the loan from database based on the loanID
    public Loan getLoanByID(int loanID){
        try {
            Connection con = ConnectDao.connectToDb();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Loans WHERE loanID = " + loanID + ";");
            if (rs.next()) {
                int customerID = rs.getInt(1);
                String currencyType = rs.getNString(2);
                CurrencyType t = CurrencyType.getTypeFromString(currencyType);
                int amount = rs.getInt(3);
                long date = (long) rs.getDouble(4);
                return new Loan(loanID, customerID, amount, t, date);
            }
            return null;
        } catch (Exception e) { return null; }
    }

    // Update the amount of a loan, used when customer pay the loan
    public void updateLoan(int loanID, double amount) {
        try {
            Connection con = ConnectDao.connectToDb();
            Statement stmt = con.createStatement();
            stmt.executeQuery("UPDATE Loans SET amount = " + amount + " WHERE loanID = " + loanID + ";");
        } catch (Exception ignored) {}
    }

    // Get the remaining amount of a loan
    public double getLoanRemaining(int loanID){
        try {
            Connection con = ConnectDao.connectToDb();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Loans WHERE loanID = " + loanID + ";");
            if (rs.next()) {
                return rs.getInt(3);
            }
            return 0;
        } catch (Exception e) { return 0; }
    }

    // Remove a loan from the database
    public void deleteLoan(int loanID){
        try {
            Connection con = ConnectDao.connectToDb();
            Statement stmt = con.createStatement();
            stmt.executeQuery("DELETE FROM Loans WHERE loanID = " + loanID + ";");
        } catch (Exception ignored) {}
    }
}
