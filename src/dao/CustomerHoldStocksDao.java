package dao;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerHoldStocksDao {

    // Check whether this account exist
    public boolean checkCustomerHolds(int stockID) {
        try {
            String query = "SELECT * FROM CustomerHoldStocks WHERE stockID = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, stockID);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) { return false; }
    }


    // Update the information of a stock hold by customer, called when the customer buy or sell stock
    public void updateCustomerHeldStocks(int stockID, int customerID, double purchasedPrice, int quantity, long timestamp) { // Problem: buy same stock with different price
        int original_amount = getCustomerHeldStocksByID(stockID, customerID);
        try {
            String query = "UPDATE CustomerHoldStocks SET quantity = ?, priceBought = ?, timeBought = ?" +
                    "WHERE stockID = ? AND customerID = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, quantity);
            stmt.setDouble(2, purchasedPrice);
            stmt.setDouble(3, (double) timestamp);
            stmt.setInt(4, stockID);
            stmt.setInt(5, customerID);
            stmt.executeUpdate();
        } catch (Exception ignored) {}
    }


    // Insert a new stock inside the database of customer hold stocks
    public void insertNewHeldStock(int stockID, int customerID, double purchasedPrice, int quantity, long timestamp) {
        try {
            String query = "INSERT INTO CustomerHoldStocks (stockID, customerID, quantity, priceBought, timeBought)" +
                    "VALUES (?,?,?,?,?);";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, stockID);
            stmt.setInt(2, customerID);
            stmt.setInt(3, quantity);
            stmt.setDouble(4, purchasedPrice);
            stmt.setDouble(5, (double) timestamp);
            stmt.executeUpdate();
        } catch (Exception ignored) {}
    }

    // get number of stock hold based on customerID and stockID
    public int getCustomerHeldStocksByID(int stockID, int customerID){ // Why return int?
        try {
            String query = "SELECT * FROM CustomerHoldStocks WHERE stockID = ? AND customerID = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, stockID);
            stmt.setInt(2, customerID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(3);
            }
            return 0;
        } catch (Exception e) { return 0; }
    }

    // Remove a customer hold stock when the customer sell it out totally
    public void removeCustomerHeldStock(int stockID, int customerID){
        try {
            String query = "DELETE FROM CustomerHoldStocks WHERE stockID = ? AND customerID = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, stockID);
            stmt.setInt(2, customerID);
            stmt.executeUpdate();
        } catch (Exception ignored) {}
    }
    public ArrayList<CustomerHeldStock> getStocks(int customerID) {
        ArrayList<CustomerHeldStock> result = new ArrayList<>();
        try {
            String query = "SELECT * FROM CustomerHoldStocks WHERE customerID = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int stockID = rs.getInt(1);
                int quantity = rs.getInt(3);
                double price = rs.getDouble(4);
                long date = (long) rs.getDouble(5);
                CustomerHeldStock chs = new CustomerHeldStock(stockID, price, quantity, date);
                result.add(chs);
            }
        } catch (Exception e) { return result; }
        return result;
    }

}