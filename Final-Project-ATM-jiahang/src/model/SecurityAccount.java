package model;

public class SecurityAccount extends Account{

    private int accountId;
    private int customerId;
    private double investmentAmount = getBalanceByCurrency(CurrencyType.USD);
    private double realizedProfit=0;
    private double unrealizedProfit=0;
    private CurrencyType currencyType = CurrencyType.USD;


    public void buyStock(double price, int amount) {
        this.investmentAmount-=price*amount;
    }


    public void sellStock(double price, double profit, int amount) {
        //change inverstment amount
        this.investmentAmount+=price*amount;
        //change realizedProfit
        this.realizedProfit+=profit*amount;
    }



    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(double investmentAmount) {
        this.investmentAmount = investmentAmount;
    }

    public double getRealizedProfit() {
        return realizedProfit;
    }

    public void setRealizedProfit(double realizedProfit) {
        this.realizedProfit = realizedProfit;
    }

    public double getUnrealizedProfit() {
        return unrealizedProfit;
    }

    public void setUnrealizedProfit(double unrealizedProfit) {
        this.unrealizedProfit = unrealizedProfit;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    @Override
    public boolean transfer(Account to, CurrencyType currencyType, double amount) {
        return false;
    }
}
