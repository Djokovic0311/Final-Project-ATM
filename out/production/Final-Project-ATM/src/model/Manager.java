package model;

import java.sql.Date;

public class Manager extends User{
    private int AccountID;
    public Manager(String name, int ID, String type, String password){
        super(name,ID,type,password);
    }
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

    private Stock[] checkStock(){

        return new Stock[0];
    }
}
