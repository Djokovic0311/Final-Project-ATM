package model;

public class CustomerHeldStock extends Stock {
    private long buyTime;

    public CustomerHeldStock(int stockID, double price, int quantity, long time) {
        super(stockID, price, quantity);
        this.buyTime = time;
    }

    public long getBuyTime(){
        return this.buyTime;
    }


}