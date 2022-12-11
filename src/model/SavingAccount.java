package model;

import model.Account;
import model.AccountType;
import model.Currency;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/*
    This is the class for Saving model.Account
    @author: Jiahang Li
    @version: 1.0
*/
public class SavingAccount extends Account{
    // interest rate, by default 0.01
    private final double interestRate = 0.01;
    // interest in this saving account, which should be redeemed manually by customer
    private double interest;
    // lastest redeem record
    private Date lastRedeemDate;
    public SavingAccount(int accountID, int userID, AccountType type) {
        super(accountID, userID, type);
    }

    public SavingAccount(int accountID, int userID, AccountType type, Map<CurrencyType, Double> balance) {
        this(accountID, userID, type);
        setBalance(balance);
    }

    public SavingAccount(int accountID, int userID, AccountType type, double balance) {
        this(accountID, userID, type);
        this.balance.put(CurrencyType.USD, balance);
        this.lastRedeemDate = new Date();
    }

    public Map<CurrencyType, Double> getBalance() {
        return balance;
    }


    @Override
    public boolean transfer(Account to, CurrencyType currencyType, double amount) {
        boolean isValid = this.withdraw(currencyType, amount);
        if (isValid) {
            to.deposit(currencyType, amount);
            return true;
        }
        else return false;
    }

    // gain interest by month
    public void gainInterest() {
        Date now = new Date();
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(lastRedeemDate);
        c2.setTime(now);
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        int yearInterval = year1 - year2;
        if (month1 < month2 || month1 == month2 && day1 < day2) {
            yearInterval--;
        }
        int monthInterval = (month1 + 12) - month2;
        if (day1 < day2) {
            monthInterval--;
        }
        monthInterval %= 12;
        int monthsDiff = Math.abs(yearInterval * 12 + monthInterval);
        this.interest += monthsDiff * interestRate;
    }

    public void gainInterest(Date date) {

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
