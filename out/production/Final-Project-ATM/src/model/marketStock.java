package model;

public class marketStock extends Stock{
    private int marketPrice;
    public marketStock (int stockID, double price, int quantity) {
        super(stockID,price,quantity);
        this.StockID = stockID;
        this.price = price;
        this.quantity = quantity;
    }
    public int getMarketPrice(){
        return this.marketPrice;
    }
}