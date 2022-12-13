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
                // What about the date?
                Loan l = new Loan(loanID, customerID, amount, t);
                result.add(l);
            }
        } catch (Exception e) { return null; }
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

    public void insertLoan(int customerID, double amount, long loanDate, CurrencyType currencyType, int loanID){
        try {
            Connection con = ConnectDao.connectToDb();
            Statement stmt = con.createStatement();
            // How to process the date?
//            stmt.executeQuery("INSERT INTO Loans (loanID, customerID, currencyType, amount, loanDate)" +
//                    "VALUES (" + loanDate + ", " + customerID + ", " + currencyType + " ," + amount + ", " + loanDate + ");");
        } catch (Exception ignored) {}
    }

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
                // What about the date?
                Loan l = new Loan(loanID, customerID, amount, t);
                return l;
            }
            return null;
        } catch (Exception e) { return null; }
    }

    public void updateLoan(int loanID, double amount) {
        try {
            Connection con = ConnectDao.connectToDb();
            Statement stmt = con.createStatement();
            stmt.executeQuery("UPDATE Loans SET amount = " + amount + " WHERE loanID = " + loanID + ";");
        } catch (Exception ignored) {}
    }
    public double getLoanRemaining(int loanID){
        try {
            Connection con = ConnectDao.connectToDb();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Loans WHERE loanID = " + loanID + ";");
            if (rs.next()) {
                // What about the date?
                return rs.getInt(3);
            }
            return 0;
        } catch (Exception e) { return 0; }
    }
    public void deleteLoan(int loanID){
        try {
            Connection con = ConnectDao.connectToDb();
            Statement stmt = con.createStatement();
            stmt.executeQuery("DELETE FROM Loans WHERE loanID = " + loanID + ";");
        } catch (Exception ignored) {}
    }
}
