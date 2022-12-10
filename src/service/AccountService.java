package service;

import dao.AccountDao;
import dao.ConnectDao;
import model.*;
import utils.ATMConstant;

import java.sql.Connection;
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
}
