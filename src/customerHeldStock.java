import java.util.Date;

public class customerHeldStock extends Stock {
    private Date buyTime;
    private int buyPrice;

    public Date getBuyTime(){
        return this.buyTime;
    }

    public int getBuyPrice(){
        return this.buyPrice;
    }


}
