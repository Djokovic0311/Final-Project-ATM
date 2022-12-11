package model;

import java.util.Map;

public class NewAccountCreator {
    private static int numberCreated = 0;
    private static final int startID = 1111111; // This is the manager account ID, all user account ID start after it.

    public static SavingAccount getNewSavingAccount(int userID, double balance) {
        numberCreated += 1;
        return new SavingAccount(startID + numberCreated, userID, AccountType.SAVINGS, balance);
    }

    public static CheckingAccount getNewCheckingAccount(int userID, double balance) {
        numberCreated += 1;
        return new CheckingAccount(startID + numberCreated, userID, AccountType.CHECKINGS, balance);
    }

    public static LoanAccount getNewLoanAccount(int userID, double balance) {
        numberCreated += 1;
        return new LoanAccount(startID + numberCreated, userID, AccountType.LOAN, balance);
    }

    public static SecurityAccount getNewSecurityAccount(int userID, double balance) {
        numberCreated += 1;
        return new SecurityAccount(startID + numberCreated, userID, AccountType.SECURITY, balance);
    }
}
