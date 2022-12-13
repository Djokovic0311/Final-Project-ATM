package model;
import java.util.Date;

public class customerHeldStock extends Stock {
    private Date buyTime;
    private int buyPrice;

    public customerHeldStock(int price){
        super(price);
    }

    public Date getBuyTime(){
        return this.buyTime;
    }

    public int getBuyPrice(){
        return this.buyPrice;
    }


}