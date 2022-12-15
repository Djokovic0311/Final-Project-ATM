package model;

public class SecurityAccount extends Account{

    private double realizedProfit;
    private double unrealizedProfit;

    public SecurityAccount(int accountID, int userID, AccountType type, double balance, double realizedProfit, double unrealizedProfit) {
        super(accountID, userID, type);
        this.balance.put(CurrencyType.USD, balance);
        this.realizedProfit = realizedProfit;
        this.unrealizedProfit = unrealizedProfit;
    }

    public double getInvestmentAmount() {
        return this.balance.get(CurrencyType.USD);
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
}
