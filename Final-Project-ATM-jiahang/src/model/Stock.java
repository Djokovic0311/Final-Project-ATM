package model;
public class Stock {
    protected int StockID;
    protected int price;
    protected int quantity;

    public Stock(int stockID, int price, int quantity) {
    	this.StockID = stockID;
    	this.price = price;
    	this.quantity = quantity;
    }
    
    public int getStockID(){
        return this.StockID;
    }

    public int getPrice(){
        return this.price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void setPrice(int price){
    	this.price = price;
    }
}
