package model;

import java.util.Date;

public class Loan {
    private int loanID;
    private String  customerID;
    private double principalAmount;
    private CurrencyType currency;
    private int tenure;
    private double rateOfInterest;
    private long loanCommenceDate;

    public int getLoanID() {
        return loanID;
    }

    public void setLoanID(int loanID) {
        this.loanID = loanID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public double getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(double principalAmount) {
        this.principalAmount = principalAmount;
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
