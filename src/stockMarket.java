
public class stockMarket extends Transaction{

    public boolean buyStock(String stockID, Customer customer) {
    	String info;
        if(customer.getBalance() - super.getAmount() < 2500){//getAmount():Transaction amount
           info = "Your saving account should remain at least $2500.";
        }else if ( super.getAmount()< 1000){
            info = "Your should transfer at least $1000 at once.";
        }else if (getAmount() > customer.getBalance()){
            info =  "You only have $"+customer.getBalance()+".";
        }else{
            customer.withdraw(customer.getID(), getAmount());
            insertBuyStockTransaction(this);//database
            BuyStockTransaction bst = new BuyStockTransaction();//based on BuyStockTransaction.java
        }
        return true;
    }
    
    public boolean sellStock(String stockID, Customer customer){
    	String info;
        if (getStockPool().containsKey(stockID)){
            CustomerStock cs = getAccount().getStockPool().get(getStock().getName());
            if (cs.getQuantity() < getStock().getQuantity()){
                return false;
            }else{
                cs.remove(getStock());
                if (cs.getQuantity() == 0){
                    StockProfit profit = new StockProfit(getStock());
                    getAccount().getStockPool().remove(cs.getName());
                    removeCustomerStock(cs);
                }
                nsertStockTransaction(this);
                return true;
            }
        }else{
            return true;
        }
    }
    
    
}
