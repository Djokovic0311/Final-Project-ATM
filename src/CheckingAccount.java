import java.util.List;
import java.util.Map;

public class CheckingAccount extends Account{

    // constructor with currency
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
