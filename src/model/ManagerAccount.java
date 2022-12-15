package model;

import java.util.Map;
/*
    This is the class for Manager's model.Account
    @author: Jiahang Li
    @version: 1.0
*/
public class ManagerAccount extends Account{
    // constructor with currency
    public ManagerAccount(int accountID, int userID, AccountType type) {
        super(accountID, userID, type);
    }

    public ManagerAccount(int accountID, int userID, String pwd, AccountType type, Map<CurrencyType, Double> balance) {
        this(accountID, userID, type);
        setBalance(balance);
    }

}
