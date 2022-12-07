

import java.util.HashMap;

public class SecurityAccount extends Account{
    public SecurityAccount() {
        this.activated = false;
        this.balance = new HashMap<>();
    }

    public SecurityAccount(int accountID, String ownerName, String pwd, AccountType type) {
        super(accountID, type, ownerName, pwd);
        this.activated = false;
        this.balance = new HashMap<>();
    }

    public HashMap<String, Double> getProfit(customerStock cs) {
                return target.getValue();
    }

    public HashMap<String, Double> getUnrealizedProfit(customerStock cs) {
                return (cs.getPrice()- cs.getBuyPrice()) * cs.getQuantity();
            
    }


    public HashMap<String, Double> getStockAmount(customerStock cs) {
                return cs.getCurrentPrice() * cs.getQuantity();
    }

    public static String createAccount(Customer customer, double startBalance){
        if (customer.getSavingAccounts()!=null){
            customer.getSavingAccount(super.accountID).withdraw(super.accountID, startBalance);//function not exist yet
            customer.createAccount(SECURITY, startBalance = this.startBalance);
            }
    }

}
