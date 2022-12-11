package model;

import java.util.Map;

public class LoanAccount extends Account{


    public LoanAccount(int accountID, int userID, AccountType type) {
        super(accountID, type, userID);
    }

    public LoanAccount(int accountID, int userID, AccountType type, double balance) {
        super(accountID, type, userID);
        this.balance.put(CurrencyType.USD, balance);
    }

    public double getBalance() {
        double balance = this.balance.get(CurrencyType.USD);
        return balance;
    }


    @Override
    public boolean transfer(Account to, CurrencyType currencyType, double amount) {
        return false;
    }
}
