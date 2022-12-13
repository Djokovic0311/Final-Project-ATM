package model;

import java.util.Date;
import java.util.Map;

/**
 This is the class for sell stock transaction
 @author: Jiahang Li
 @version: 1.0
 */
public class SellStockTransaction extends Transaction{
    // amount of each stock purchased
    private Map<String, Integer> stockMap;
    private int stockID;
    public SellStockTransaction(int id, long timestamp, double amount, int userID, Map<String, Integer> sellInfo,int fromAccountID, int toAccountID, CurrencyType currencyType) {
        super(id, timestamp, amount, userID, TransactionType.SELLSTOCK,fromAccountID,toAccountID,currencyType );
        this.stockMap = sellInfo;
    }

    // getters and setters

    public Map<String, Integer> getstockMap() {
        return stockMap;
    }

    public void setstockMap(Map<String, Integer> stockMap) {
        this.stockMap = stockMap;
    }

    public int getStockID() {
        return stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());
        stringBuilder.append(",");
        for (Map.Entry<String, Integer> record : stockMap.entrySet()) {
            stringBuilder.append(record.getKey()).append(",").append(record.getValue()).append(",");
        }
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        return stringBuilder.toString();
    }
}
