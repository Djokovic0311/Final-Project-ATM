package model;
import java.sql.*;

import java.util.*;
import java.util.Date;

public class Manager {
    private int AccountID;

    private void checkCustomer(Customer C) {
        C.toString();
    }

    private void getDailyReport(Date date) {
        //data will be fetched from DB by passing the date


        try {

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "108875556");

            Statement stmt = con.createStatement();

            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM TRANSACTIONS WHERE date = " + date + "");

            List<Integer> transactionids = new ArrayList<>();
            List<Integer> userIDs = new ArrayList<>();
            List<Integer> accountIDs = new ArrayList<>();
            List<Integer> accountID2s = new ArrayList<>();
            List<Double> balances = new ArrayList<>();
            List<String> acctypes = new ArrayList<>();
            List<String> currencies = new ArrayList<>();
            List<Date> dates = new ArrayList<>();
            List<Integer> stockIDs = new ArrayList<>();

            //List<List<>> daily_report = new ArrayList<>();

            while (rs.next()) {
                int transactionId = rs.getInt(0);
                int userID = rs.getInt(1);
                int accountID = rs.getInt(3);
                int accountID2 = rs.getInt(4);
                double balance = rs.getDouble(5);
                String acctype = rs.getString(6);
                String currency = rs.getString(7);
                date = rs.getDate(8);
                int stockID = rs.getInt(9);

                transactionids.add(transactionId);
                userIDs.add(userID);
                accountIDs.add(accountID);
                accountID2s.add(accountID2);
                balances.add(balance);
                acctypes.add(acctype);
                currencies.add(currency);
                dates.add(date);
                stockIDs.add(stockID);

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private void updateStock(Stock stock, int new_price) {
        //Stock[] Stocks = checkStock();
        List<Stock> stocks = new Stock().Get_All_Stocks();
        for (int i = 0; i < stocks.size(); i++) {
            if (stocks.get(i).StockID == stock.StockID) {
                stock.updateStockPrice(new_price);
            }
        }
    }

    public List<Stock> Get_All_Stock() {
        List<Stock> stocks = new Stock().Get_All_Stocks();
        return stocks;
    }
}
