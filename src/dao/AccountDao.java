package dao;

import model.*;

import java.sql.Connection;

public class AccountDao {
    ConnectDao connectDao = new ConnectDao();
    public Account selectAccountByID(int accountID, int customerID) {
        return null;
    }
    public Object selectAccountByID(int accountID) {
        return null;
    }
    public int insertIntoCheckingOrSaving(int customerID, AccountType accountType, double balance, CurrencyType currencyType){
        return 0;
    }
    public boolean doesAccountExists(int accountID) {

        return false;
    }
    public boolean doesSavingAccountExist(Customer customer){

        return true;
    }
    public boolean doesSecuritiesAccountExist(int accountID) throws Exception {
        Connection connection = connectDao.connectToDb();
        return false;
    }
    public void payBankFees(double amount,int bankId) {

    }
    public int insertIntoSecurity(int customerID, AccountType accountType, double balance, CurrencyType currencyType){
        return 0;
    }
    public SavingAccount[] getSavingAccountInfoForCustomer(Customer customer) throws Exception {
        return null;
    }
    // check and delete this customer's account
    public int deleteAccount(int accountID, int customerID) {
        return 0;
    }

    public double getBalanceByCurrencyType(int accountID, int customerID, AccountType accountType, CurrencyType currencyType) {
        return 0.0;
    }

    public void updateAccountBalance(int accountID, int customerID, AccountType accountType, CurrencyType currencyType, double amount) {

    }

    public void redeemForSavingAccount(int accountID, long timestamp){

    }
}
