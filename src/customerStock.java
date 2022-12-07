
public class customerStock extends Stock{

    private int quantity;
    private Customer customer;
    private double profit;
    private double currentPrice;
    private double buyPrice;
    public customerStock(int stockID, Customer customer, int quantity, double buyprice, double currentPrice, double profit) {
        super(stock.getID, stock.getPrice());
        this.quantity = quantity;
        this.customer = customer;
        this.buyPrice = buyprice;
        this.profit = profit;
        this.currentPrice = currentPrice;
    }

    public customerStock() {
    }

    public double getBuyPrice() {
    	return buyPrice;
    }
    
    
    public void decrease(CustomerStock stock){
        assert stock.getID() == (super.getID());
        quantity = quantity - stock.quantity;
    }
    

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getProfit() {
        return profit;
    }
    
    public void setPrice(double price) {
        this.currentPrice = price;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getprice() {
        return currentPrice;
    }

}