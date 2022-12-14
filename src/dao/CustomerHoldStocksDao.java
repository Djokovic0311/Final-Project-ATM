package dao;

import model.*;
import utils.ATMConstant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerHoldStocksDao {
    ATMConstant atmConstant = new ATMConstant();
    public boolean checkCustomerHolds(int stockID) { // What is this doing? If checking whether this customer have the stock then should provide customerID
        try {
            String query = "SELECT * FROM CustomerHoldStocks WHERE stockID = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, stockID);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) { return false; }
    }

    public void updateCustomerHeldStocks(int stockID, int customerID, double purchasedPrice, int quantity, long timestamp) { // Problem: buy same stock with different price
        int original_amount = getCustomerHeldStocksByID(stockID, customerID);
        try {
            String query = "UPDATE CustomerHoldStocks SET quantity = ?, priceBought = ?, timeBought = ?" +
                    "WHERE stockID = ? AND customerID = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, quantity + original_amount);
            stmt.setDouble(2, purchasedPrice);
            stmt.setDouble(3, (double) timestamp);
            stmt.setInt(4, stockID);
            stmt.setInt(5, customerID);
            stmt.executeUpdate();
        } catch (Exception ignored) {}
    }

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
        } catch (Exception e) { return null; }
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

}
