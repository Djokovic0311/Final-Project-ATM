package dao;

import model.*;
import utils.Utils;

import java.sql.*;
import java.util.*;

public class StockDao {
    public double getPriceByID(int stockID) throws SQLException {
        try {

            String query;

            ResultSet rs;
            query = "SELECT * FROM StockMarket where stockID = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, stockID);
            rs = stmt.executeQuery();
            while (rs.next()) {
                double price = rs.getDouble(1);
                return price;
            }

        } catch (Exception E) {
            return 0.0;
        }
        return 0.0;
    }

    // check stock existence first, if it exists, update stock price and return true;
    //else return false;
    public boolean updatePriceByID(int stockID, double price) throws SQLException {
        try {
            String query;

            ResultSet rs;
            query = "SELECT * FROM StockMarket where stockID = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, stockID);
            rs = stmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                count = count + 1;
            }

            if (count == 0) {
                return false;
            } else {
                //stock price will be updated
                query = "UPDATE StockMarket SET price = ? Where stockID= ?;";
                stmt = conn.prepareStatement(query);
                stmt.setDouble(1, price);
                stmt.setInt(1, stockID);
                rs = stmt.executeQuery();
                return true;
            }
        } catch (Exception E) {
            System.out.println("The database could not be updated");
            return false;
        }

    }

    // check stock existence first, if it doesnt, insert stock  and return true;
    // else return false;
    public boolean insertIntoStock(int stockID, double price) throws SQLException {
        try {
            String query;

            ResultSet rs;
            Connection conn = ConnectDao.connectToDb();
            query = "SELECT * FROM StockMarket where stockID = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, stockID);
            rs = stmt.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = count + 1;
            }
            if (count != 0) {
                System.out.println("Entry already present in database");
                return false;
            } else {
                query = "INSERT INTO StockMarket (stockID, price) VALUES (?,?);";
                stmt = conn.prepareStatement(query);
                stmt.setInt(1, stockID);
                stmt.setDouble(1, price);
                rs = stmt.executeQuery();
                return true;
            }

        } catch (Exception e) {
            System.out.println("Could not be added to the DB");
            return false;
        }

    }
}