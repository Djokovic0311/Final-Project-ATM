package model;

import java.sql.Date;

public class Manager {
    private int AccountID;

    private void checkCustomer(Customer C){
        C.toString();
    }

    private void getDailyReport(Date date){
        //data will be fetched from DB by passing the date
    }

//    private void updateStock(Stock stock, int price){
//        Stock[] Stocks = checkStock();
//        for(int i =0;i<Stocks.length;i++){
//            if(Stocks[i].StockID = id){
//                Stocks[i].updateStockPrice(1333);
//            }
//        }
//    }

    public int getID() {
    	return AccountID;
    }
    private Stock[] checkStock(){

        return new Stock[0];
    }
}
