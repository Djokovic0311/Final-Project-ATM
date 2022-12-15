package dao;

import model.*;
import utils.ATMConstant;
import utils.Utils;

import java.sql.*;
import java.util.*;

public class AccountDao {
    ATMConstant atmConstant = new ATMConstant();
    public Account selectAccountByID(int accountID, AccountType type) { // remove customer here, add account type
        try {
            String query;
            switch (type) {
                case SAVINGS :
                    query = "SELECT * FROM SavingAccount WHERE ID = ?;";
                    break;
                case CHECKINGS :
                    query = "SELECT * FROM CheckingAccount WHERE accountID = ?;";
                    break;
                case SECURITY :
                    query = "SELECT * FROM SecurityAccount WHERE accountID = ?;";
                    break;
                default :
                    return null;
            }
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, accountID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int customerID = rs.getInt(2);
                double balance_usd = rs.getDouble(3);
                if (type == AccountType.SAVINGS || type == AccountType.CHECKINGS) {
                    double balance_eur = rs.getDouble(4);
                    double balance_cny = rs.getDouble(5);
                    Map<CurrencyType, Double> balance = new Hashtable<>();
                    balance.put(CurrencyType.USD, balance_usd);
                    balance.put(CurrencyType.EUR, balance_eur);
                    balance.put(CurrencyType.CNY, balance_cny);
                    switch (type) {
                        case SAVINGS :
                            long redeemTime = (long) rs.getDouble(6);
                            return new SavingAccount(accountID, customerID, type, balance, redeemTime);
                        case CHECKINGS :
                            return new CheckingAccount(accountID, customerID, type, balance);
                    }
                } else {
                    double realizedProfit = rs.getDouble(4);
                    double unrealizedProfit = rs.getDouble(5);
                    return new SecurityAccount(accountID, customerID, type, balance_usd, realizedProfit, unrealizedProfit);
                }
            }
            return null;
        } catch (Exception e) { return null; }
    }
    public List<Account> selectAccountByCustomerID(int customerID, AccountType type) {
        List<Account> result = new ArrayList<>();
        try {
            String query;
            switch (type) {
                case SAVINGS :
                    query = "SELECT * FROM SavingAccount WHERE customerID = ?;";
                    break;
                case CHECKINGS :
                    query = "SELECT * FROM CheckingAccount WHERE customerID = ?;";
                    break;
                case SECURITY :
                    query = "SELECT * FROM SecurityAccount WHERE customerID = ?;";
                    break;
                default :
                    return null;
            }
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int accountID = rs.getInt(1);
                double balance_usd = rs.getDouble(3);
                if (type == AccountType.SAVINGS || type == AccountType.CHECKINGS) {
                    double balance_eur = rs.getDouble(4);
                    double balance_cny = rs.getDouble(5);
                    Map<CurrencyType, Double> balance = new Hashtable<>();
                    balance.put(CurrencyType.USD, balance_usd);
                    balance.put(CurrencyType.EUR, balance_eur);
                    balance.put(CurrencyType.CNY, balance_cny);
                    switch (type) {
                        case SAVINGS :
                            System.out.println(accountID);
                            long redeemTime = (long) rs.getDouble(6);
                            result.add(new SavingAccount(accountID, customerID, type, balance, redeemTime));
                            break;
                        case CHECKINGS :
                            result.add(new CheckingAccount(accountID, customerID, type, balance));
                            break;
                    }
                } else {
                    double realizedProfit = rs.getDouble(4);
                    double unrealizedProfit = rs.getDouble(5);
                    result.add(new SecurityAccount(accountID, customerID, type, balance_usd, realizedProfit, unrealizedProfit));
                }
            }
            System.out.println(result.size());
            return result;
        } catch (Exception e) { return null; }
    }


    public boolean checkAccountExistByID(int ID, AccountType type, String basedOn) {
        try {
            String query;
            switch (type) {
                case SAVINGS :
                    if (basedOn.equals("accountID")) {
                        query = "SELECT * FROM SavingAccount WHERE ID = ?;";
                    } else if (basedOn.equals("customerID")) {
                        query = "SELECT * FROM SavingAccount WHERE customerID = ?;";
                    } else { return false; }
                    break;
                case CHECKINGS :
                    if (basedOn.equals("accountID")) {
                        query = "SELECT * FROM CheckingAccount WHERE accountID = ?;";
                    } else if (basedOn.equals("customerID")) {
                        query = "SELECT * FROM CheckingAccount WHERE customerID = ?;";
                    } else { return false; }
                    break;
                case SECURITY :
                    if (basedOn.equals("accountID")) {
                        query = "SELECT * FROM SecurityAccount WHERE accountID = ?;";
                    } else if (basedOn.equals("customerID")) {
                        query = "SELECT * FROM SecurityAccount WHERE customerID = ?;";
                    } else { return false; }
                    break;
                default :
                    return false;
            }
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, ID);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) { return false; }
    }

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
            Connection conn = ConnectDao.connectToDb();
            String query;
            PreparedStatement stmt;
            System.out.println(accountType);
            switch (accountType) {
                case SAVINGS:
                    long timeStamp = Calendar.getInstance().getTimeInMillis();
                    query = "INSERT INTO SavingAccount (ID, customerID, balanceUSD, balanceEUR, balanceCNY, lastTimeRedeem) VALUES (?,?,?,?,?,?);";
                    stmt = conn.prepareStatement(query);
                    stmt.setInt(1, accountID);
                    stmt.setInt(2, customerID);
                    stmt.setDouble(3, balanceUSD);
                    stmt.setDouble(4, balanceEUR);
                    stmt.setDouble(5, balanceCNY);
                    stmt.setDouble(6, (double) timeStamp);
                    stmt.executeUpdate();
                    return atmConstant.getSUCCESS();
                case CHECKINGS:
                    query = "INSERT INTO CheckingAccount (accountID, customerID, balanceUSD, balanceEUR, balanceCNY) VALUES (?,?,?,?,?);";
                    stmt = conn.prepareStatement(query);
                    stmt.setInt(1, accountID);
                    stmt.setInt(2, customerID);
                    stmt.setDouble(3, balanceUSD);
                    stmt.setDouble(4, balanceEUR);
                    stmt.setDouble(5, balanceCNY);
                    stmt.executeUpdate();
                    return atmConstant.getSUCCESS();
                default : return atmConstant.getERROR();
            }
        } catch (Exception e) { return atmConstant.getERROR(); }
    }
    public boolean doesAccountExists(int accountID) {
        boolean saving = checkAccountExistByID(accountID, AccountType.SAVINGS, "accountID");
        boolean checking = checkAccountExistByID(accountID, AccountType.CHECKINGS, "accountID");
        boolean security = checkAccountExistByID(accountID, AccountType.SECURITY, "accountID");
        return saving || checking || security;
    }
    public boolean doesSavingAccountExist(int customerID){
        return checkAccountExistByID(customerID, AccountType.SAVINGS, "customerID");
    }
    public boolean doesSecuritiesAccountExist(int accountID) {
        return checkAccountExistByID(accountID, AccountType.SECURITY, "accountID");
    }
    public void payBankFees(double amount, int bankId) { // No currency type！
        try {
            String query = "UPDATE CheckingAccount SET balanceUSD = ? WHERE accountID = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDouble(1, amount);
            stmt.setInt(2, bankId);
            stmt.executeUpdate();
        } catch (Exception ignored) {}
    }
    public void insertIntoSecurity(int accountID, int customerID, AccountType accountType, double balance){
        try {
            String query = "INSERT INTO SecurityAccount (accountID, customerID, currentBalance, realizedProfit, unrealizedProfit) " +
                    "VALUES (?,?,?,?,?);";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, accountID);
            stmt.setInt(2, customerID);
            stmt.setDouble(3, balance);
            stmt.setDouble(4, 0.0);
            stmt.setDouble(5, 0.0);
            stmt.executeUpdate();
        } catch (Exception ignored) {}
    }
    public SavingAccount[] getSavingAccountInfoForCustomer(int customerID) {
        List<SavingAccount> savingAccountList = new ArrayList<>();
        try {
            String query = "SELECT * FROM SavingAccount WHERE customerID = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int accountID = rs.getInt(1);
                double balance_usd = rs.getDouble(3);
                double balance_eur = rs.getDouble(4);
                double balance_cny = rs.getDouble(5);
                Map<CurrencyType, Double> balance = new Hashtable<>();
                balance.put(CurrencyType.USD, balance_usd);
                balance.put(CurrencyType.EUR, balance_eur);
                balance.put(CurrencyType.CNY, balance_cny);
                long redeemTime = (long) rs.getDouble(6);
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
    // check and delete this customer's account
    public int deleteAccount(int accountID, int customerID) {
        try {
            Connection conn = ConnectDao.connectToDb();
            String query1 = "DELETE FROM SavingAccount WHERE ID = ? AND customerID = ?;";
            PreparedStatement stmt1 = conn.prepareStatement(query1);
            stmt1.setInt(1, accountID);
            stmt1.setInt(2, customerID);
            stmt1.executeUpdate();
            String query2 = "DELETE FROM CheckingAccount WHERE accountID = ? AND customerID = ?;";
            PreparedStatement stmt2 = conn.prepareStatement(query2);
            stmt2.setInt(1, accountID);
            stmt2.setInt(2, customerID);
            stmt2.executeUpdate();
            String query3 = "DELETE FROM SecurityAccount WHERE accountID = ? AND customerID = ?;";
            PreparedStatement stmt3 = conn.prepareStatement(query3);
            stmt3.setInt(1, accountID);
            stmt3.setInt(2, customerID);
            stmt3.executeUpdate();
            return atmConstant.getSUCCESS();
        } catch (Exception e) { return atmConstant.getERROR(); }
    }

    public double getBalanceByCurrencyType(int accountID, int customerID, AccountType accountType, CurrencyType currencyType) {
        Account a = selectAccountByID(accountID, accountType);
        return a.getBalanceByCurrency(currencyType);
    }

    public void updateAccountBalance(int accountID, AccountType accountType, CurrencyType currencyType, double amount) {
        String querySet;
        switch (currencyType) {
            case USD:
                querySet = "SET balanceUSD = ?";
                break;
            case EUR:
                querySet = "SET balanceEUR = ?";
                break;
            case CNY:
                querySet = "SET balanceCNY = ?";
                break;
            default: return;
        }
        try {
            String query;
            switch (accountType) {
                case SAVINGS :
                    query = "UPDATE SavingAccount " + querySet + " WHERE ID = ?;";
                    break;
                case CHECKINGS :
                    query = "UPDATE CheckingAccount " + querySet + " WHERE accountID = ?;";
                    break;
                case SECURITY :
                    query = "UPDATE SecurityAccount " + querySet + " WHERE accountID = ?;";
                    break;
                default:
                    return;
            }
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDouble(1, amount);
            stmt.setInt(2, accountID);
            stmt.executeUpdate();
        } catch (Exception ignored) {}
    }

    public double[] redeemForSavingAccount(int accountID, long timestamp) throws Exception {
        try {
            String query1 = "SELECT * FROM SavingAccount WHERE ID = ?;";
            Connection conn = ConnectDao.connectToDb();
            PreparedStatement stmt1 = conn.prepareStatement(query1);
            stmt1.setInt(1, accountID);
            ResultSet rs =  stmt1.executeQuery();
            double balanceUSD = rs.getDouble(3);
            double balanceEUR = rs.getDouble(4);
            double balanceCNY = rs.getDouble(5);
            long lastDateRedeem = (long) rs.getDouble(6);
            int dayPass = Utils.dayPass(lastDateRedeem, timestamp);
            double[] list_interest = new double[]{0, 0, 0};
            double newBalanceUSD = balanceUSD;
            double newBalanceEUR = balanceEUR;
            double newBalanceCNY = balanceCNY;
            if (balanceUSD >= 500) {
                newBalanceUSD = Utils.redeem(balanceUSD, dayPass);
                list_interest[0] = newBalanceUSD - balanceUSD;
            }
            if (balanceEUR >= 500) {
                newBalanceEUR = Utils.redeem(balanceEUR, dayPass);
                list_interest[1] = newBalanceEUR - balanceEUR;
            }
            if (balanceCNY >= 500) {
                newBalanceCNY = Utils.redeem(balanceCNY, dayPass);
                list_interest[2] = newBalanceCNY - balanceCNY;
            }
            double lastTimeRedeem = (double) Calendar.getInstance().getTimeInMillis();
            String query2 = "UPDATE SavingAccount SET balanceUSD = ?, balanceEUR = ?, balanceCNY = ?, " +
                    "lastTimeRedeem = ? WHERE ID = ?;";
            PreparedStatement stmt2 = conn.prepareStatement(query2);
            stmt2.setDouble(1, newBalanceUSD);
            stmt2.setDouble(2, newBalanceEUR);
            stmt2.setDouble(3, newBalanceCNY);
            stmt2.setDouble(4, lastTimeRedeem);
            stmt2.setInt(5, accountID);
            stmt2.executeUpdate();
            return list_interest;
        } catch (Exception ignored) {
            return new double[]{0, 0, 0};
        }
    }
}
