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
                double price = rs.getDouble(1);
                return price;
            }
            return 0.0;
        } catch (Exception e) { return 0.0; }
    }

    // check stock existence first, if it exists, update stock price and return true;
    //else return false;
    public boolean updatePriceByID(int stockID, double price) {
        try {
            String query = "SELECT * FROM StockMarket where stockID = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, stockID);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                return false;
            } else {
                //stock price will be updated
                query = "UPDATE StockMarket SET price = ? Where stockID= ?;";
                stmt = conn.prepareStatement(query);
                stmt.setDouble(1, price);
                stmt.setInt(2, stockID);
                stmt.executeQuery();
                return true;
            }
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
    public ArrayList<marketStock> getStocks(){return new ArrayList<>();}
}
