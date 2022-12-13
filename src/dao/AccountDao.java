package dao;

import model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class AccountDao {

    public Account selectAccountByID(int accountID, AccountType type) { // remove customer here, add account type
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","108875556");
            Statement stmt = con.createStatement();
            ResultSet rs;
            switch (type) {
                case SAVINGS -> {
                    rs = stmt.executeQuery("SELECT * FROM SavingAccount WHERE accountID = " + accountID + ";");
                }
                case CHECKINGS -> {
                    rs = stmt.executeQuery("SELECT * FROM CheckingAccount WHERE accountID = " + accountID + ";");
                }
                case SECURITY -> {
                    rs = stmt.executeQuery("SELECT * FROM SecurityAccount WHERE accountID = " + accountID + ";");
                }
                default -> {
                    return null;
                }
            }
            if (rs.next()) {
                int customerID = rs.getInt(1);
                double balance_usd = rs.getDouble(2);
                if (type == AccountType.SAVINGS || type == AccountType.CHECKINGS) {
                    double balance_eur = rs.getDouble(3);
                    double balance_cny = rs.getDouble(4);
                    Map<CurrencyType, Double> balance = new Hashtable<>();
                    balance.put(CurrencyType.USD, balance_usd);
                    balance.put(CurrencyType.EUR, balance_eur);
                    balance.put(CurrencyType.CNY, balance_cny);
                    switch (type) {
                        case SAVINGS -> {
                            // Date
                            return new SavingAccount(accountID, customerID, type, balance);
                        }
                        case CHECKINGS -> { return new CheckingAccount(accountID, customerID, type, balance); }
                    }
                } else {
                    double realizedProfit = rs.getDouble(3);
                    double unrealizedProfit = rs.getDouble(4);
                    return new SecurityAccount(accountID, customerID, type, balance_usd, realizedProfit, unrealizedProfit);
                }
            }
        } catch (Exception e) { return null; }
        return null;
    }

    public Account selectAccountByID(int accountID) {
        SavingAccount sa = (SavingAccount) selectAccountByID(accountID, AccountType.SAVINGS);
        if (sa != null) { return sa; }
        CheckingAccount c = (CheckingAccount) selectAccountByID(accountID, AccountType.CHECKINGS);
        if (c != null) { return c; }
        SecurityAccount se = (SecurityAccount) selectAccountByID(accountID, AccountType.SECURITY);
        return se;
    }

    public boolean checkAccountExistByID(int ID, AccountType type, String basedOn) {
        String queryWhere;
        if (basedOn.equals("accountID")) {
            queryWhere = "WHERE accountID = " + ID;
        } else if (basedOn.equals("customerID"))  {
            queryWhere = "WHERE customerID = " + ID;
        } else { return false; }
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","108875556");
            Statement stmt = con.createStatement();
            ResultSet rs;
            switch (type) {
                case SAVINGS -> {
                    rs = stmt.executeQuery("SELECT * FROM SavingAccount " + queryWhere + ";");
                }
                case CHECKINGS -> {
                    rs = stmt.executeQuery("SELECT * FROM CheckingAccount " + queryWhere + ";");
                }
                case SECURITY -> {
                    rs = stmt.executeQuery("SELECT * FROM SecurityAccount " + queryWhere + ";");
                }
                default -> {
                    return false;
                }
            }
            return rs.next();
        } catch (Exception e) { return false; }
    }

    public int insertIntoCheckingOrSaving(int accountID, int customerID, AccountType accountType, double balance, CurrencyType currencyType){
        double balanceUSD = 0;
        double balanceEUR = 0;
        double balanceCNY = 0;
        switch (currencyType) {
            case USD -> { balanceUSD = balance; }
            case EUR -> { balanceEUR = balance; }
            case CNY -> { balanceCNY = balance; }
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "108875556");
            Statement stmt = con.createStatement();
            switch (accountType) {
                case SAVINGS -> {
                    // How to process Date?
//                    stmt.executeQuery("INSERT INTO SavingAccount ( accountID, customerID, balanceUSD, balanceEUR, balanceCNY )" +
//                            "VALUES ( " + accountID + ", " + customerID + ", " + balanceUSD + ", " + balanceEUR + ", " + balanceCNY +  ");");
                    return 1;
                }
                case CHECKINGS -> {
                    stmt.executeQuery("INSERT INTO CheckingAccount ( accountID, customerID, balanceUSD, balanceEUR, balanceCNY )" +
                            "VALUES ( " + accountID + ", " + customerID + ", " + balanceUSD + ", " + balanceEUR + ", " + balanceCNY +  ");");
                    return 1;
                }
                default -> { return 0; }
            }
        } catch (Exception e) { return 0; }
    }
    public boolean doesAccountExists(int accountID) {
        boolean saving = checkAccountExistByID(accountID, AccountType.SAVINGS, "accountID");
        boolean checking = checkAccountExistByID(accountID, AccountType.CHECKINGS, "accountID");
        boolean security = checkAccountExistByID(accountID, AccountType.SECURITY, "accountID");
        return saving && checking && security;
    }
    public boolean doesSavingAccountExist(int customerID){
        return checkAccountExistByID(customerID, AccountType.SAVINGS, "customerID");
    }
    public boolean doesSecuritiesAccountExist(int accountID) {
        return checkAccountExistByID(accountID, AccountType.SECURITY, "accountID");
    }
    public void payBankFees(double amount, int bankId) { // No currency type！
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "108875556");
            Statement stmt = con.createStatement();
            stmt.executeQuery("UPDATE CheckingAccount SET balanceUSD = " + amount + " WHERE ID = " + bankId + ";");
        } catch (Exception ignored) {}
    }
    public void insertIntoSecurity(int accountID, int customerID, AccountType accountType, double balance){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "108875556");
            Statement stmt = con.createStatement();
            stmt.executeQuery("INSERT INTO SecurityAccount ( accountID, customerID, balanceUSD, realizedProfit, unrealizedProfit )" +
                    "VALUES ( " + accountID + ", " + customerID + ", " + balance + ", " + 0 + ", " + 0 +  ");");
        } catch (Exception ignored) {
        }
    }
    public SavingAccount[] getSavingAccountInfoForCustomer(int customerID) throws Exception {
        List<SavingAccount> savingAccountList = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","108875556");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SavingAccount WHERE customerID = " + customerID + ";");
            while (rs.next()) {
                int accountID = rs.getInt(0);
                double balance_usd = rs.getDouble(2);
                double balance_eur = rs.getDouble(3);
                double balance_cny = rs.getDouble(4);
                Map<CurrencyType, Double> balance = new Hashtable<>();
                balance.put(CurrencyType.USD, balance_usd);
                balance.put(CurrencyType.EUR, balance_eur);
                balance.put(CurrencyType.CNY, balance_cny);
                savingAccountList.add(new SavingAccount(accountID, customerID, AccountType.SAVINGS, balance));
            }
        } catch (Exception e) { return null; }
        if (savingAccountList.size() == 0) {
            return null;
        } else {
            SavingAccount[] s = new SavingAccount[savingAccountList.size()];
            for (int i = 0; i < savingAccountList.size(); i ++) {
                s[i] = savingAccountList.get(i);
            }
            return s;
        }
    }
    // check and delete this customer's account
    public int deleteAccount(int accountID, int customerID) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","108875556");
            Statement stmt = con.createStatement();
            stmt.executeQuery("DELETE FROM SavingAccount WHERE accountID = " + accountID + "AND customerID = " + customerID + ";");
            stmt.executeQuery("DELETE FROM CheckingAccount WHERE accountID = " + accountID + "AND customerID = " + customerID + ";");
            stmt.executeQuery("DELETE FROM SecurityAccount WHERE accountID = " + accountID + "AND customerID = " + customerID + ";");
            return 1;
        } catch (Exception e) { return 0; }
    }

    public double getBalanceByCurrencyType(int accountID, int customerID, AccountType accountType, CurrencyType currencyType) {
        Account a = selectAccountByID(accountID, accountType);
        return a.getBalanceByCurrency(currencyType);
    }

    public void updateAccountBalance(int accountID, AccountType accountType, CurrencyType currencyType, double amount) {
        String querySet;
        switch (currencyType) {
            case USD -> { querySet = "SET balanceUSD = " + amount; }
            case EUR -> { querySet = "SET balanceEUR = " + amount; }
            case CNY -> { querySet = "SET balanceCNY = " + amount; }
            default -> { return; }
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","108875556");
            Statement stmt = con.createStatement();
            switch (accountType) {
                case SAVINGS -> {
                    stmt.executeQuery("UPDATE SavingAccount " + querySet + " WHERE accountIDID = " + accountID + ";");
                }
                case CHECKINGS -> {
                    stmt.executeQuery("UPDATE CheckingAccount " + querySet + " WHERE accountIDID = " + accountID + ";");
                }
                case SECURITY -> {
                    stmt.executeQuery("UPDATE SecurityAccount " + querySet + " WHERE accountIDID = " + accountID + ";");
                }
            }
        } catch (Exception ignored) {
        }
    }

    public void redeemForSavingAccount(int accountID, long timestamp){

    }
}
