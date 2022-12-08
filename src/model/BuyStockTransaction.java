package model;

import java.util.Date;
import java.util.Map;

/**
 This is the class for buy stock transaction
 @author: Jiahang Li
 @version: 1.0
 */
public class BuyStockTransaction extends Transaction {
    // amount of each stock purchased
    private Map<String, Integer> stockMap;

    public BuyStockTransaction(String id, Date timestamp, double amount, String userID, Map<String, Integer> buyInfo) {
        super(id, timestamp, amount, userID, TransactionType.BUYSTOCK);
        this.stockMap = buyInfo;
    }

    // getters and setters

    public Map<String, Integer> getstockMap() {
        return stockMap;
    }

    public void setstockMap(Map<String, Integer> stockMap) {
        this.stockMap = stockMap;
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
