package model;

public class CustomerHeldStock extends Stock {
    private long buyTime;

    public CustomerHeldStock(int stockID, double price, int quantity) {
        super(stockID, price, quantity);
    }

    public long getBuyTime(){
        return this.buyTime;
    }


}