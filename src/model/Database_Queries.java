package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Map;

public class Database_Queries {
    public static Account get_user_account(int accountID, String type) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","108875556");
            Statement stmt = con.createStatement();
            ResultSet rs;
            switch (type) {
                case "Saving" -> {
                    rs = stmt.executeQuery("SELECT * FROM ACCOUNTS WHERE account_type = 'Saving' and ID = " + accountID);
                }
                case "Checking" -> {
                    rs = stmt.executeQuery("SELECT * FROM ACCOUNTS WHERE account_type = 'Checking' and ID = " + accountID);
                }
                case "Loan" -> {
                    rs = stmt.executeQuery("SELECT * FROM ACCOUNTS WHERE account_type = 'Loan' and ID = " + accountID);
                }
                case "Security" -> {
                    rs = stmt.executeQuery("SELECT * FROM ACCOUNTS WHERE account_type = 'Security' and ID = " + accountID);
                }
                default -> {
                    rs = stmt.executeQuery("SELECT * FROM ACCOUNTS WHERE and ID = " + accountID);
                }
            }

            if (rs.next()) {
                int userID = rs.getInt(1);
                String accountType = rs.getString(2);
                AccountType accType = AccountType.convertStrToType(accountType);
                double balance_usd = rs.getDouble(3);
                double balance_eur = rs.getDouble(4);
                double balance_cny = rs.getDouble(5);
                Map<CurrencyType, Double> balance = new Hashtable<>();
                balance.put(CurrencyType.USD, balance_usd);
                balance.put(CurrencyType.EUR, balance_eur);
                balance.put(CurrencyType.CNY, balance_cny);
                if (accType == AccountType.SAVINGS) {
                    return new SavingAccount(accountID, userID, accType, balance);
                } else if (accType == AccountType.CHECKINGS) {
                    return new CheckingAccount(accountID, userID, accType, balance);
                } else if (accType == AccountType.LOAN) {
                    return new LoanAccount(accountID, userID, accType, balance_usd);
                } else if (accType == AccountType.SECURITY) {
                    return new SecurityAccount(accountID, userID, accType, balance_usd);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public static ManagerAccount get_manager_account() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","108875556");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ACCOUNTS WHERE account_type = 'Manager'");
            if (rs.next()) {
                int accountID = rs.getInt(0);
                int userID = rs.getInt(1);
                double balance_usd = rs.getDouble(3);
                double balance_eur = rs.getDouble(4);
                double balance_cny = rs.getDouble(5);
                Map<CurrencyType, Double> balance = new Hashtable<>();
                balance.put(CurrencyType.USD, balance_usd);
                balance.put(CurrencyType.EUR, balance_eur);
                balance.put(CurrencyType.CNY, balance_cny);
                return new ManagerAccount(accountID, userID, AccountType.MANAGER, balance);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public static double get_traction_fee(double amount) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","108875556");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Settings");
            if (rs.next()) {
                return rs.getDouble(4) * amount;
            }
        } catch (Exception e) {
            return 0.0;
        }
        return 0.0;
    }

    public static double get_open_fee() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","108875556");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Settings");
            if (rs.next()) {
                return rs.getDouble(0);
            }
        } catch (Exception e) {
            return 0.0;
        }
        return 0.0;
    }

    public static double get_close_fee() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","108875556");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Settings");
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
            return 0.0;
        }
        return 0.0;
    }

    public static boolean closeAccount(int userID, int accountID) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","108875556");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ACCOUNTS WHERE userID = " + userID + " and ID = " + accountID);
            if (rs.next()) {
                // remove this account
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
