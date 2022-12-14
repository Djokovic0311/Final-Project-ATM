package service;

import controller.AccountController;
import dao.AccountDao;

import model.*;
import utils.ATMConstant;
import utils.Utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountService {

    AccountDao accountDao = new AccountDao();
    ATMConstant atmConstant = new ATMConstant();
    public List<Object> getAccountInfoForCustomer(User user) throws Exception {
        List<Object> info = new ArrayList<>();
        info.add(user.getName());
        info.add(user.getPassword());
        return info;
    }
    public List<Account> getAccountsForCustomer(Customer customer) throws Exception {
        List<Account> accounts = new ArrayList<>();

        accounts.addAll(accountDao.selectAccountByCustomerID(customer.getID(), AccountType.CHECKINGS));
        accounts.addAll(accountDao.selectAccountByCustomerID(customer.getID(), AccountType.SAVINGS));

        accounts.addAll(accountDao.selectAccountByCustomerID(customer.getID(), AccountType.SECURITY));

        System.out.println("accounts ");

        System.out.println(accounts.size());
        return accounts;
    }

    public int createNewAccount(Customer customer,AccountType accountType,double balance,CurrencyType currencyType) throws Exception {
        int accountID;
        int status;

        switch (accountType) {
            case SAVINGS:
                accountID = Utils.getFixedLengthRandom(8);
                while(accountDao.doesAccountExists(accountID)) {
                    accountID = Utils.getFixedLengthRandom(8);
                }
                status = accountDao.insertIntoCheckingOrSaving(accountID, customer.getID(), AccountType.SAVINGS, balance, currencyType);
                if(status != 0) {
                    // successfully create
                    // pay fee to manager account
                    accountDao.payBankFees(balance,atmConstant.getMANAGER_ACCOUNT_ID());
                    return atmConstant.getSUCCESS();
                }
                else return atmConstant.getERROR();

            case CHECKINGS:
                    accountID = Utils.getFixedLengthRandom(8);
                    while(accountDao.doesAccountExists(accountID)) {
                        accountID = Utils.getFixedLengthRandom(8);
                    }
                    status = accountDao.insertIntoCheckingOrSaving(accountID, customer.getID(), AccountType.CHECKINGS, balance, currencyType);
                    if(status != 0) {
                        // successfully create
                        // pay fee to manager account
                        accountDao.payBankFees(balance,atmConstant.getMANAGER_ACCOUNT_ID());
                        return atmConstant.getSUCCESS();
                    }
                    else return atmConstant.getERROR();

            case SECURITY:
                return createNewSecuritiesAccount(customer, balance, currencyType);
        }
        return 0;
    }

    public int createNewSecuritiesAccount (Customer customer, double depositAmount, CurrencyType currencyType) throws Exception, SQLException {
        int responseStatus = 0;

        boolean accountExists = accountDao.doesSecuritiesAccountExist(customer.getID());

        if(!accountExists && accountDao.doesSavingAccountExist(customer.getID()) ){
            SavingAccount[] savingAccounts = accountDao.getSavingAccountInfoForCustomer(customer.getID());
            for(SavingAccount savingAccount : savingAccounts) {
                if(savingAccount.getBalanceByCurrency(currencyType)>=5000 && savingAccount.getBalanceByCurrency(currencyType)-depositAmount >=2500) {
                    savingAccount.setBalanceByCurrency(currencyType,savingAccount.getBalanceByCurrency(currencyType)-depositAmount);

                    int accountID = Utils.getFixedLengthRandom(8);
                    while(accountDao.doesAccountExists(accountID)) {
                        accountID = Utils.getFixedLengthRandom(8);
                    }
                    accountDao.insertIntoSecurity(accountID, customer.getID(),AccountType.SECURITY,depositAmount);
                    responseStatus = atmConstant.getSUCCESS();
                    break;
                }
                else {
                    responseStatus = atmConstant.getERROR();
                }
            }
        }else {
            responseStatus = atmConstant.getERROR();
        }
        return responseStatus;
    }

    public int closeAccount(Customer customer, int accountID) {

        int customerID = Utils.createHashCodeForPersonId(customer.getName());
        boolean accountExists = accountDao.doesAccountExists(accountID);
        System.out.println(accountExists);
        int status = 0;
        if(accountExists) {
            status = accountDao.deleteAccount(accountID, customerID);
        }
        else {
            return atmConstant.getERROR();
        }
        return status;
    }

    public Account getAccountByID(int accountID) {
        for(AccountType accountType : AccountType.values()){
            if(accountDao.selectAccountByID(accountID, accountType) != null)
                return accountDao.selectAccountByID(accountID, accountType);
        }
        return null;
    }

    public void transcurrency(Account account, CurrencyType from,CurrencyType to, double amount){
        accountDao.updateAccountBalance(account.getAccountID(),account.getType(),from,
                account.getBalanceByCurrency(from)-amount);
        accountDao.updateAccountBalance(account.getAccountID(),account.getType(),to,
                account.getBalanceByCurrency(to)+amount*from.getValue()/to.getValue());
    }

    public void redeem(int accountID) throws Exception {
        long timestamp = Utils.getTimestamp();

        SavingAccount savingAccount = (SavingAccount) accountDao.selectAccountByID(accountID,AccountType.SAVINGS);
        for(CurrencyType currencyType : CurrencyType.values()){
            accountDao.redeemForSavingAccount(accountID,timestamp);
        }

    }

}
