

package dao;

import java.sql.*;

public class StockDao {
    public double getPriceByID(int stockID) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "108875556");
        Statement stmt = con.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery("SELECT * FROM StockMarket where stockID = " + stockID + ";");
        while(rs.next()){
            double price = rs.getDouble(1);
            return price;
        }
        return 0.0;
    }
    // check stock existence first, if it exists, update stock price and return true;
    //else return false;
    public boolean updatePriceByID(int stockID, double price) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "108875556");
        Statement stmt = con.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery("SELECT * FROM StockMarket where stockID = " + stockID + ";");

        int count = 0;
        while(rs.next()){
            count = count + 1;
        }

        if(count==0){
            return false;
        }else{
            //stock price will be updated
            rs = stmt.executeQuery("UPDATE StockMarket SET price =" + price + "Where stockID= stockID" + ";");
            return true;
        }

    }
    // check stock existence first, if it doesnt, insert stock  and return true;
    // else return false;
    public boolean insertIntoStock(int stockID, double price) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "108875556");
        Statement stmt = con.createStatement();
        ResultSet rs;
        rs = stmt.executeQuery("SELECT * FROM StockMarket where stockID = " + stockID + ";");
        int count = 0;
        while(rs.next()){
            count = count + 1;
        }
        if(count!=0){
            return false;
        }else{
            stmt.executeQuery("INSERT INTO StockMarket (stockID, price)" +
                    "VALUES ( " + stockID + ", " + price +  ");");
            return true;
        }
        
    }
}