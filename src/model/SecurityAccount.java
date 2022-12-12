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

}
