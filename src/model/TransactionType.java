package model;

/**
    This is the enum for transaction type
    @author: Jiahang Li
    @version: 1.0
 */

public enum TransactionType {
    BUYSTOCK,
    SELLSTOCK,
    TRANSFER,
    WITHDRAW,
    MANAGER,
    DEPOSIT;

    public static TransactionType getTypeFromString (String s) {
        switch (s) {
            case "BUYSTOCK":
                return BUYSTOCK;
            case "SELLSTOCK":
                return SELLSTOCK;
            case "TRANSFER":
                return TRANSFER;
            case "WITHDRAM":
                return WITHDRAW;
            case "MANAGER":
                return MANAGER;
            case "DEPOSIT":
                return DEPOSIT;
        }
        return null;
    }
}
