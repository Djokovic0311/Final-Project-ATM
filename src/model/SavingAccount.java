package model;

import model.Account;
import model.AccountType;
import model.Currency;
import utils.Utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/*
    This is the class for Saving model.Account
    @author: Jiahang Li, Chen Zhu
    @version: 2.0
*/
public class SavingAccount extends Account{ // Warning: Date part is not complete
    private long lastRedeemDate;
    public SavingAccount(int accountID, int userID, AccountType type) {
        super(accountID, userID, type);
        this.lastRedeemDate = Utils.getTimestamp();
    }

    public SavingAccount(int accountID, int userID, AccountType type, Map<CurrencyType, Double> balance) {
        this(accountID, userID, type);
        this.balance = balance;
        this.lastRedeemDate = Utils.getTimestamp();
    }

    public SavingAccount(int accountID, int userID, AccountType type, CurrencyType t, double balance) {
        this(accountID, userID, type);
        this.balance.put(t, balance);
        this.lastRedeemDate = Utils.getTimestamp();
    }

    public long getLastRedeemDate() {
        return lastRedeemDate;
    }

    public void setLastRedeemDate(long lastRedeemDate) {
        this.lastRedeemDate = lastRedeemDate;
    }
}