import java.util.List;
import java.util.Map;

public class CheckingAccount extends Account{
    // map key: one of 5 typea of currency(type, rate)
    private Map<Currency, Double> balance;

    // constructor with currency list
    public CheckingAccount(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public boolean deposit(Currency currency) {
        
    }
    @Override
    public boolean transfer(Account to, Currency currency) {
        return false;
    }
}
