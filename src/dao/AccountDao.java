package dao;

import model.*;
import utils.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class AccountDao {

    // This function returns the account that match the input id and type
    public Account selectAccountByID(int accountID, AccountType type) { // remove customer here, add account type
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","108875556");
            Statement stmt = con.createStatement();
            ResultSet rs;
            switch (type) {
                case SAVINGS :
                    rs = stmt.executeQuery("SELECT * FROM SavingAccount WHERE accountID = " + accountID + ";");
                    break;
                case CHECKINGS :
                    rs = stmt.executeQuery("SELECT * FROM CheckingAccount WHERE accountID = " + accountID + ";");
                    break;
                case SECURITY :
                    rs = stmt.executeQuery("SELECT * FROM SecurityAccount WHERE accountID = " + accountID + ";");
                    break;
                default :
                    return null;
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
                        case SAVINGS :
                            long redeemTime = (long) rs.getDouble(5);
                            // Date
                            return new SavingAccount(accountID, customerID, type, balance, redeemTime);
                        case CHECKINGS :
                            return new CheckingAccount(accountID, customerID, type, balance);
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


    // This function returns the account that match the input id
    public Account selectAccountByID(int accountID) {
        SavingAccount sa = (SavingAccount) selectAccountByID(accountID, AccountType.SAVINGS);
        if (sa != null) { return sa; }
        CheckingAccount c = (CheckingAccount) selectAccountByID(accountID, AccountType.CHECKINGS);
        if (c != null) { return c; }
        SecurityAccount se = (SecurityAccount) selectAccountByID(accountID, AccountType.SECURITY);
        return se;
    }

    // This function returns the whether the corresponding account exist based on accountID or customerID
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
                case SAVINGS :
                    rs = stmt.executeQuery("SELECT * FROM SavingAccount " + queryWhere + ";");
                    break;
                case CHECKINGS :
                    rs = stmt.executeQuery("SELECT * FROM CheckingAccount " + queryWhere + ";");
                    break;
                case SECURITY :
                    rs = stmt.executeQuery("SELECT * FROM SecurityAccount " + queryWhere + ";");
                    break;
                default :
                    return false;
            }
            return rs.next();
        } catch (Exception e) { return false; }
    }

    // This function insert a new saving/checking account into database, return 1 is success, 0 if fail.
    public int insertIntoCheckingOrSaving(int accountID, int customerID, AccountType accountType, double balance, CurrencyType currencyType){
        double balanceUSD = 0;
        double balanceEUR = 0;
        double balanceCNY = 0;
        switch (currencyType) {
            case USD:
                balanceUSD = balance;
                break;
            case EUR:
                balanceEUR = balance;
                break;
            case CNY:
                balanceCNY = balance;
                break;
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "108875556");
            Statement stmt = con.createStatement();
            switch (accountType) {
                case SAVINGS:
                    long timeStamp = Calendar.getInstance().getTimeInMillis();
                    stmt.executeQuery("INSERT INTO SavingAccount ( accountID, customerID, balanceUSD, balanceEUR, balanceCNY, lastTimeRedeem )" +
                            "VALUES ( " + accountID + ", " + customerID + ", " + balanceUSD + ", " + balanceEUR + ", " + balanceCNY + ", " + (double) timeStamp + ");");
                    return 1;
                case CHECKINGS:
                    stmt.executeQuery("INSERT INTO CheckingAccount ( accountID, customerID, balanceUSD, balanceEUR, balanceCNY )" +
                            "VALUES ( " + accountID + ", " + customerID + ", " + balanceUSD + ", " + balanceEUR + ", " + balanceCNY +  ");");
                    return 1;
                default : return 0;
            }
        } catch (Exception e) { return 0; }
    }

    // This function returns the whether the corresponding account exist based on accountID
    public boolean doesAccountExists(int accountID) {
        boolean saving = checkAccountExistByID(accountID, AccountType.SAVINGS, "accountID");
        boolean checking = checkAccountExistByID(accountID, AccountType.CHECKINGS, "accountID");
        boolean security = checkAccountExistByID(accountID, AccountType.SECURITY, "accountID");
        return saving && checking && security;
    }

    // This function returns the whether the corresponding saving account exist based on customerID
    public boolean doesSavingAccountExist(int customerID){
        return checkAccountExistByID(customerID, AccountType.SAVINGS, "customerID");
    }

    // This function returns the whether the corresponding security account exist based on customerID
    public boolean doesSecuritiesAccountExist(int accountID) {
        return checkAccountExistByID(accountID, AccountType.SECURITY, "accountID");
    }

    // This function pays the fee to banker
    public void payBankFees(double amount, int bankId) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "108875556");
            Statement stmt = con.createStatement();
            stmt.executeQuery("UPDATE CheckingAccount SET balanceUSD = " + amount + " WHERE ID = " + bankId + ";");
        } catch (Exception ignored) {}
    }

    // This function add a new security account into database
    public void insertIntoSecurity(int accountID, int customerID, AccountType accountType, double balance){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "108875556");
            Statement stmt = con.createStatement();
            stmt.executeQuery("INSERT INTO SecurityAccount ( accountID, customerID, balanceUSD, realizedProfit, unrealizedProfit )" +
                    "VALUES ( " + accountID + ", " + customerID + ", " + balance + ", " + 0 + ", " + 0 +  ");");
        } catch (Exception ignored) {
        }
    }

    // This function returns a list contains all saving account of this customer
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
                long redeemTime = (long) rs.getDouble(5);
                savingAccountList.add(new SavingAccount(accountID, customerID, AccountType.SAVINGS, balance, redeemTime));
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

    // Delete this customer's account
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

    // Get the balance of the corresponding account in specified currency
    public double getBalanceByCurrencyType(int accountID, int customerID, AccountType accountType, CurrencyType currencyType) {
        Account a = selectAccountByID(accountID, accountType);
        return a.getBalanceByCurrency(currencyType);
    }


    // Update the balance of an account
    public void updateAccountBalance(int accountID, AccountType accountType, CurrencyType currencyType, double amount) {
        String querySet;
        switch (currencyType) {
            case USD:
                querySet = "SET balanceUSD = " + amount;
                break;
            case EUR:
                querySet = "SET balanceEUR = " + amount;
                break;
            case CNY:
                querySet = "SET balanceCNY = " + amount;
                break;
            default: return;
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","108875556");
            Statement stmt = con.createStatement();
            switch (accountType) {
                case SAVINGS :
                    stmt.executeQuery("UPDATE SavingAccount " + querySet + " WHERE accountIDID = " + accountID + ";");
                    break;
                case CHECKINGS :
                    stmt.executeQuery("UPDATE CheckingAccount " + querySet + " WHERE accountIDID = " + accountID + ";");
                    break;
                case SECURITY :
                    stmt.executeQuery("UPDATE SecurityAccount " + querySet + " WHERE accountIDID = " + accountID + ";");
                    break;
            }
        } catch (Exception ignored) {
        }
    }


    // Redeem the interest of saving account
    public void redeemForSavingAccount(int accountID, long timestamp){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","108875556");
            Statement stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery("SELECT * FROM SavingAccount WHERE accountID = " + accountID + ";");
            double balanceUSD = rs.getDouble(2);
            double balanceEUR = rs.getDouble(3);
            double balanceCNY = rs.getDouble(4);
            long lastDateRedeem = (long) rs.getDouble(5);
            int dayPass = Utils.dayPass(lastDateRedeem, timestamp);
            if (balanceUSD >= 500) {
                balanceUSD = Utils.redeem(balanceUSD, dayPass);
            }
            if (balanceEUR >= 500) {
                balanceEUR = Utils.redeem(balanceEUR, dayPass);
            }
            if (balanceCNY >= 500) {
                balanceCNY = Utils.redeem(balanceCNY, dayPass);
            }
            double lastTimeRedeem = (double) Calendar.getInstance().getTimeInMillis();
            stmt.executeQuery("UPDATE SavingAccount SET balanceUSD = " + balanceUSD + ", balanceEUR = " + balanceEUR + ", balanceCNY = " + balanceCNY
                    + ", lastTimeRedeem = " + lastTimeRedeem + " WHERE accountID = " + accountID + ";");
        } catch (Exception ignored) {}
    }
}