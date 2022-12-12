package service;

import dao.AccountDao;
import dao.ConnectDao;
import model.*;
import utils.ATMConstant;
import utils.Utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountService {
    ConnectDao connectDao = new ConnectDao();
    AccountDao accountDao = new AccountDao();
    ATMConstant atmConstant = new ATMConstant();
    public List<Object> getAccountInfoForCustomer(User user) throws Exception {
        Connection conn = connectDao.connectToDb();
        List<Object> info = new ArrayList<>();
        info.add(user.getName());
        info.add(user.getPassword());
        return info;
    }
    public List<Object> getAccountsForCustomer(Customer customer) throws Exception {
        Connection conn = connectDao.connectToDb();
        List<Object> accounts = new ArrayList<>();

        for(int i = 0; i < customer.getCheckingAccounts().length; i++) {
            CheckingAccount checkingAccount = (CheckingAccount) accountDao.selectAccountByID(customer.getCheckingAccounts()[i], customer.getID());
            accounts.add(checkingAccount);
        }
        for(int i = 0; i < customer.getSavingAccounts().length; i++) {
            SavingAccount savingAccount = (SavingAccount) accountDao.selectAccountByID(customer.getSavingAccounts()[i], customer.getID());
            accounts.add(savingAccount);
        }
        if(customer.getLoanAccount() != -1) {
            LoanAccount loanAccount = (LoanAccount) accountDao.selectAccountByID(customer.getLoanAccount(), customer.getID());
            accounts.add(loanAccount);
        }
        if(customer.getSecurityAccount() != -1) {
            SecurityAccount securityAccount = (SecurityAccount) accountDao.selectAccountByID(customer.getSecurityAccount(), customer.getID());
            accounts.add(securityAccount);
        }
        return accounts;
    }

    public int createNewAccount(Customer customer,AccountType accountType,double balance,CurrencyType currencyType) throws Exception {
        switch (accountType) {
            case SAVINGS -> {
                int accountID = Utils.getFixedLengthRandom(8);
                while(accountDao.doesAccountExists(accountID)) {
                    accountID = Utils.getFixedLengthRandom(8);
                }
                int status = accountDao.insertIntoCheckingOrSaving(accountID, AccountType.CHECKINGS, balance, currencyType);
                if(status != 0) {
                    // successfully create
                    // pay fee to manager account
                    accountDao.payBankFees(balance,atmConstant.getMANAGER_ACCOUNT_ID());
                    return atmConstant.getSUCCESS();
                }
                else return atmConstant.getERROR();
            }
            case CHECKINGS -> {
                int accountID = Utils.getFixedLengthRandom(8);
                while(accountDao.doesAccountExists(accountID)) {
                    accountID = Utils.getFixedLengthRandom(8);
                }
                int status = accountDao.insertIntoCheckingOrSaving(accountID, AccountType.SAVINGS, balance, currencyType);
                if(status != 0) {
                    // successfully create
                    // pay fee to manager account
                    accountDao.payBankFees(balance,atmConstant.getMANAGER_ACCOUNT_ID());
                    return atmConstant.getSUCCESS();
                }
                else return atmConstant.getERROR();
            }
            case SECURITY -> {
                return createNewSecuritiesAccount(customer, balance, currencyType);
            }
        }
        return 0;
    }

    public int createNewSecuritiesAccount (Customer customer, double depositAmount, CurrencyType currencyType) throws Exception, SQLException {
        int responseStatus = 0;

        boolean accountExists = accountDao.doesSecuritiesAccountExist(customer.getID());

        if(!accountExists && accountDao.doesSavingAccountExist(customer) ){
            SavingAccount[] savingAccounts = accountDao.getSavingAccountInfoForCustomer(customer);
            for(SavingAccount savingAccount : savingAccounts) {
                if(savingAccount.getBalanceByCurrency(currencyType)>=5000 && savingAccount.getBalanceByCurrency(currencyType)-depositAmount >=2500) {
                    savingAccount.setBalanceByCurrency(currencyType,savingAccount.getBalanceByCurrency(currencyType)-depositAmount);

                    int accountID = Utils.getFixedLengthRandom(8);
                    while(accountDao.doesAccountExists(accountID)) {
                        accountID = Utils.getFixedLengthRandom(8);
                    }
                    accountDao.insertIntoSecurity(customer.getID(),AccountType.SECURITY,depositAmount,currencyType);
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
        int status = 0;
        if(accountExists) {
            status = accountDao.deleteAccount(accountID, customerID);
        }
        else {
            return atmConstant.getERROR();
        }
        return status;
    }

    public Object getAccountByID(int accountID) {
        return accountDao.selectAccountByID(accountID);
    }
}
