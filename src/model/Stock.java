package model;
public class Stock {
    protected int StockID;
    protected double price;
    protected int quantity;

    public Stock (int stockID, double price, int quantity) {
        this.StockID = stockID;
        this.price = price;
        this.quantity = quantity;
    }

    public int getStockID(){
        return this.StockID;
    }

    public double getPrice(){
        return this.price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
