package model;

public class marketStock extends Stock{
    private int marketPrice;

    public marketStock(int price){
        super(price);
    }
    public int getMarketPrice(){
        return this.marketPrice;
    }
}