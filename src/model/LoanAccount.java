package model;

public class LoanAccount extends Account{


    @Override
    public boolean transfer(Account to, CurrencyType currencyType, double amount) {
        return false;
    }
}
