package model;
import java.util.List;
import java.util.ArrayList;

public class Stock {
    protected int StockID;
    protected int price;

    protected List<Stock> Stock_list = new ArrayList<>();

    public List<Stock> Get_All_Stocks(){
        return this.Stock_list;
    }

    public Stock(){
        //Constructor used to get all the stocks
    }

    public Stock(int price){
        int created_id = Stock_list.size() + 1;
        this.StockID = created_id;
        this.price = price;
    }

    public int getStockID(){
        return this.StockID;
    }

    public int getPrice(){
        return this.price;
    }

    public void updateStockPrice(int price) {
        this.price = price;
    }
}
