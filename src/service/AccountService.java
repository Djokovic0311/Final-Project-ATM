package service;

import dao.AccountDao;

import model.*;
import utils.ATMConstant;
import utils.Utils;

import java.sql.SQLException;
import java.util.ArrayList;
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
    public List<Object> getAccountsForCustomer(Customer customer) throws Exception {
        List<Object> accounts = new ArrayList<>();

        for(int i = 0; i < customer.getCheckingAccounts().length; i++) {
            CheckingAccount checkingAccount = (CheckingAccount) accountDao.selectAccountByID(customer.getCheckingAccounts()[i], AccountType.CHECKINGS);
            accounts.add(checkingAccount);
        }
        for(int i = 0; i < customer.getSavingAccounts().length; i++) {
            SavingAccount savingAccount = (SavingAccount) accountDao.selectAccountByID(customer.getSavingAccounts()[i], AccountType.SAVINGS);
            accounts.add(savingAccount);
        }

        if(customer.getSecurityAccount() != -1) {
            SecurityAccount securityAccount = (SecurityAccount) accountDao.selectAccountByID(customer.getSecurityAccount(), AccountType.SECURITY);
            accounts.add(securityAccount);
        }
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

    public void transcurrency(Account account, CurrencyType from,CurrencyType to, double amount){
        accountDao.updateAccountBalance(account.getAccountID(),account.getType(),from,
                account.getBalanceByCurrency(from)-amount);
        accountDao.updateAccountBalance(account.getAccountID(),account.getType(),from,
                account.getBalanceByCurrency(to)+amount*from.getValue()/to.getValue());
    }

    public void redeem(int accountID) throws Exception {
        long timestamp = Utils.getTimestamp();
        accountDao.redeemForSavingAccount(accountID,timestamp);
        SavingAccount savingAccount = (SavingAccount) accountDao.selectAccountByID(accountID);
        for(CurrencyType currencyType : CurrencyType.values()){
            double interest = savingAccount.getLastRedeemDate() * atmConstant.getREDEEM_INTEREST();
            accountDao.updateAccountBalance(savingAccount.getAccountID(),AccountType.SAVINGS,currencyType,
                    savingAccount.getBalanceByCurrency(currencyType)+interest);
        }

    }

}
