
import java.util.Date;
import java.util.Objects;

public class Loan{
    private double balance;
    private Customer customer;
    private String currency;
    private String id;

    public Loan(Customer customer,double balance,String currency, String id) {
        this.balance = balance;
        this.customer = customer;
        this.currency = currency;
        this.id = id;
    }


    public Loan(Customer customer, double balance, String currency) {
        this(customer,balance,currency,customer.getID() + new Date().hashCode());
    }

    public double getBalance() {
        return balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getCurrency() {
        return currency;
    }

    public String getId() {
        return id;
    }

    public void repay(double amount){
        this.balance = this.balance + amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public double getValue() {
        return balance;
    }

    @Override
    public String toString() {
        return customer.getName()+
                ", balance:" + balance;
    }
}
