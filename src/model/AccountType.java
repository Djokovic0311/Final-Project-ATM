package model;/*
    This is the enum for model.Account, including checking, saving, loan and security
    @author: Jiahang Li
    @version: 1.0
*/

public enum AccountType {
    SAVINGS,
    CHECKINGS,
    LOAN,
    SECURITY,
    MANAGER

    public static AccountType convertStrToType(String s) {
        switch (s) {
            case "Saving" -> {
                return SAVINGS;
            }
            case "Checking" -> {
                return CHECKINGS;
            }
            case "Loan" -> {
                return LOAN;
            }
            case "Security" -> {
                return SECURITY;
            }
        }
    }
}