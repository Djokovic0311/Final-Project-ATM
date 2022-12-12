package model;
public class Stock {
    protected int StockID;
    protected int price;
    protected int quantity;

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
}
