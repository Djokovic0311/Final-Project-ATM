package model;

import java.util.Date;

public class Loan {
    private int loanID;
    private int customerID;
    private double amount;
    private CurrencyType currency;
    private int tenure;
    private long loanCommenceDate;
    private double rateOfInterest;

    public Loan(int loanID, int customerID, double amount, CurrencyType currency, long date) {
        this.loanID = loanID;
        this.customerID = customerID;
        this.amount = amount;
        this.currency = currency;
        this.tenure = 1;
        this.rateOfInterest = 0.01;
        this.loanCommenceDate = date;
    }

    public int getLoanID() {
        return loanID;
    }

    public int getCustomerID() {
        return customerID;
    }


    public double getPrincipalAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    public int getTenure() {
        return tenure;
    }

    public void setTenure(int tenure) {
        this.tenure = tenure;
    }

    public double getRateOfInterest() {
        return rateOfInterest;
    }

    public void setRateOfInterest(double rateOfInterest) {
        this.rateOfInterest = rateOfInterest;
    }

    public long getLoanCommenceDate() {
        return loanCommenceDate;
    }

    public void setLoanCommenceDate(long loanCommenceDate) {
        this.loanCommenceDate = loanCommenceDate;
    }
}
