package dao;

import model.marketStock;

import java.util.ArrayList;

public class StockDao {
    public double getPriceByID(int stockID) {
        return 0.0;
    }
    // check stock existence first, if it exists, update stock price and return true;
    //else return false;
    public boolean updatePriceByID(int stockID, double price){
        return true;
    }
    // check stock existence first, if it doesnt, insert stock  and return true;
    // else return false;
    public boolean insertIntoStock(int stockID, double price){
        return true;
    }

    public ArrayList<marketStock> getStocks(){return new ArrayList<>();}
}
