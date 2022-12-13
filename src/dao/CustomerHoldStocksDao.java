package dao;

import model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerHoldStocksDao {
    public boolean checkCustomerHolds(int stockID) { // What is this doing? If checking whether this customer have the stock then should provide customerID
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","108875556");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CustomerHoldStocks WHERE stockID = " + stockID + ";");
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (Exception e) { return false; }
    }

    public void updateCustomerHeldStocks(int stockID, int customerID, double purchasedPrice, int quantity, long timestamp) { // Problem: buy same stock with different price
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","108875556");
            Statement stmt = con.createStatement();
            stmt.executeQuery("INSERT INTO CustomerHoldStocks (stockID, customerID, stockNumber, priceBought, timeBought)" +
                    "VALUES (" + stockID + ", " + customerID + ", " + quantity + " ," + purchasedPrice + ", " + (double) timestamp + ")" +
                    "ON DUPLICATE KEY UPDATE stockNumber = " + quantity + ";");
        } catch (Exception ignored) {}
    }

    public void insertNewHeldStock(int stockID, int customerID, double purchasedPrice, int quantity, long timestamp) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","108875556");
            Statement stmt = con.createStatement();
            stmt.executeQuery("INSERT INTO CustomerHoldStocks (stockID, customerID, quantity, priceBought, timeBought)" +
                    "VALUES (" + stockID + ", " + customerID + ", " + quantity + " ," + purchasedPrice + ", " + (double) timestamp + ");");
        } catch (Exception ignored) {}
    }
    public int getCustomerHeldStocksByID(int stockID, int customerID){ // Why return int?
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","108875556");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CustomerHoldStocks WHERE stockID = " + stockID + " customerID = " + customerID + ";");
            if (rs.next()) {
                return rs.getInt(2);
            }
            return 0;
        } catch (Exception e) { return 0; }
    }

    public void removeCustomerHeldStock(int stockID, int customerID){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","108875556");
            Statement stmt = con.createStatement();
            stmt.executeQuery("DELETE FROM CustomerHoldStocks WHERE stockID = " + stockID + "AND customerID = " + customerID + ";");
        } catch (Exception ignored) {}
    }
    public ArrayList<CustomerHeldStock> getStocks(int customerID) {
        ArrayList<CustomerHeldStock> result = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","108875556");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CustomerHoldStocks WHERE customerID = " + customerID + ";");
            while (rs.next()) {
                int stockID = rs.getInt(0);
                int quantity = rs.getInt(2);
                double price = rs.getDouble(3);
                // What about the date?
                CustomerHeldStock chs = new CustomerHeldStock(stockID, price, quantity);
                result.add(chs);
            }
        } catch (Exception e) { return null; }
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

}
