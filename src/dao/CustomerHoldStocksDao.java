package dao;

import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerHoldStocksDao {

    // Check whether this account exist
    public boolean checkCustomerHolds(int stockID, int customerID) {
        try {
            String query = "SELECT * FROM CustomerHoldStocks WHERE stockID = ? AND customerID = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, stockID);
            stmt.setInt(2, customerID);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) { return false; }
    }


    // Update the information of a stock hold by customer, called when the customer buy or sell stock
    public void updateCustomerHeldStocks(int stockID, int customerID, double purchasedPrice, int quantity, long timestamp) { // Problem: buy same stock with different price
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
            Connection conn = ConnectDao.connectToDb();
            String query1 = "SELECT COUNT(*) FROM CustomerHoldStocks;";
            PreparedStatement stmt1 = conn.prepareStatement(query1);
            ResultSet rs = stmt1.executeQuery();
            int id = 1;
            if (rs.next()) {
                id = rs.getInt(1) + 1;
            }
            String query2 = "INSERT INTO CustomerHoldStocks (recordID, stockID, customerID, quantity, priceBought, timeBought)" +
                    "VALUES (?,?,?,?,?,?);";
            PreparedStatement stmt2 = conn.prepareStatement(query2);
            stmt2.setInt(1, id);
            stmt2.setInt(2, stockID);
            stmt2.setInt(3, customerID);
            stmt2.setInt(4, quantity);
            stmt2.setDouble(5, purchasedPrice);
            stmt2.setDouble(6, (double) timestamp);
            stmt2.executeUpdate();
        } catch (Exception ignored) {}
    }

    // get number of stock hold based on customerID and stockID
    public double[] getCustomerHeldStocksInfoByID(int stockID, int customerID){
        try {
            String query = "SELECT * FROM CustomerHoldStocks WHERE stockID = ? AND customerID = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, stockID);
            stmt.setInt(2, customerID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new double[]{rs.getInt(4), rs.getDouble(5)};
            }
            return new double[]{0, 0};
        } catch (Exception e) { return new double[]{0, 0}; }
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
                int stockID = rs.getInt(2);
                int quantity = rs.getInt(4);
                double price = rs.getDouble(5);
                long date = (long) rs.getDouble(6);
                CustomerHeldStock chs = new CustomerHeldStock(stockID, price, quantity, date);
                result.add(chs);
            }
        } catch (Exception e) { return result; }
        return result;
    }

}