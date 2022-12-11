package dao;

public class CustomerHoldStocksDao {
    public boolean checkCustomerHolds(int stockID) {
        return false;
    }

    public void updateCustomerHeldStocks(int stockID, int customerID, double purchasedPrice, int quantity, long timestamp) {

    }

    public void insertNewHeldStock(int stockID, int customerID, double purchasedPrice, int quantity, long timestamp) {}
    public int getCustomerHeldStocksByID(int stockID, int customerID){
        return 0;
    }

    public void removeCustomerHeldStock(int stockID, int customerID){

    }
}
