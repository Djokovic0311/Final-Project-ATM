import java.util.Date;
import java.util.Map;

/*
    This is the class for Saving Account
    @author: Jiahang Li
    @version: 1.0
*/
public class SavingAccount extends Account{
    // interest in this saving account, which should be redeemed manually by customer
    private double interest;
    // lastest redeem record
    private Date lastRedeemDate;
    public SavingAccount(int accountID, String ownerName, String pwd, AccountType type) {
        super(accountID, type, ownerName, pwd);
        this.lastRedeemDate = new Date();
        this.interest = 0.0;
    }

    public SavingAccount(int accountID, String ownerName, String pwd, AccountType type, Map<Currency, Double> balance) {
        this(accountID, ownerName, pwd, type);
        setBalance(balance);
        this.lastRedeemDate = new Date();
        this.interest = 0.0;
    }

    @Override
    public boolean transfer(Account to, Currency currency, double amount) {
        boolean isValid = this.withdraw(currency, amount);
        if (isValid) {
            to.deposit(currency, amount);
            return true;
        }
        else return false;
    }

    //getters and setters

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public Date getLastRedeemDate() {
        return lastRedeemDate;
    }

    public void setLastRedeemDate(Date lastRedeemDate) {
        this.lastRedeemDate = lastRedeemDate;
    }
}
