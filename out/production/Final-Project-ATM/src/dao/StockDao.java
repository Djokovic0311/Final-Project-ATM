package dao;

import model.marketStock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StockDao {
    public double getPriceByID(int stockID) {
        try {
            String query = "SELECT * FROM StockMarket where stockID = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, stockID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                double price = rs.getDouble(2);
                return price;
            }
            return 0.0;
        } catch (Exception e) { return 0.0; }
    }

    // check stock existence first, if it exists, update stock price and return true;
    //else return false;
    public boolean updatePriceByID(int stockID, double price) {
        try {
            String query = "UPDATE StockMarket SET price = ? WHERE stockID= ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDouble(1, price);
            stmt.setInt(2, stockID);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) { return false; }
    }

    // check stock existence first, if it doesn't, insert stock  and return true;
    // else return false;
    public boolean insertIntoStock(int stockID, double price) {
        try {
            String query = "SELECT * FROM StockMarket where stockID = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, stockID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { // Already exist
                return false;
            } else {
                query = "INSERT INTO StockMarket (stockID, price) VALUES (?,?);";
                stmt = conn.prepareStatement(query);
                stmt.setInt(1, stockID);
                stmt.setDouble(2, price);
                stmt.executeUpdate();
                return true;
            }
        } catch (Exception e) { return false; }

    }
    public ArrayList<marketStock> getStocks(){
        ArrayList<marketStock> result = new ArrayList<>();
        try {
            String query = "SELECT * FROM StockMarket;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) { // Already exist
                int stockID = rs.getInt(1);
                double price = rs.getDouble(2);
                result.add(new marketStock(stockID, price, 1000));
            }
        } catch (Exception e) { return null; }
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

    public boolean checkStockByID (int stockID) {
        try {
            String query = "SELECT * FROM StockMarket where stockID = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, stockID);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) { return false; }
    }
}
