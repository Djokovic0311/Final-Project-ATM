package model;
import java.util.Date;

public class customerHeldStock extends Stock {
    private long buyTime;
    private int buyPrice;

    public long getBuyTime(){
        return this.buyTime;
    }

    public int getBuyPrice(){
        return this.buyPrice;
    }


}