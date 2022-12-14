package controller;

import model.*;
import service.AccountService;
import service.LoginService;
import utils.ATMConstant;
import utils.Utils;

import java.util.List;

public class AccountController {
    AccountService accountService = new AccountService();


    LoginService loginService = new LoginService();
    ATMConstant atmConstant = new ATMConstant();

    public List<Object> getAccountInfoForCustomer(String userName) throws Exception {
        User user = loginService.getCustomerInfo(userName);
        return accountService.getAccountInfoForCustomer(user);
    }
    public List<Object> getAccountsForCustomer(String userName) throws Exception {
        Customer customer = (Customer) loginService.getCustomerInfo(userName);
        return accountService.getAccountsForCustomer(customer);
    }

    public int createNewCheckingOrSavingAccount(int customerID, AccountType accountType, double balance, CurrencyType currencyType) throws Exception {
        Customer customer = new Customer();
        customer.setID(customerID);

        return accountService.createNewAccount(customer,accountType,balance,currencyType);
    }

    public int createNewSecurityAccount(String userName, AccountType accountType, double balance, CurrencyType currencyType) throws Exception {
        Customer customer = (Customer) loginService.getCustomerInfo(userName);
        if(customer==null)
            return atmConstant.getNO_USER_FOUND();
        return accountService.createNewAccount(customer,accountType,balance,currencyType);
    }

    public int closeAccount(String userName, int accountID) throws Exception {
        Customer customer = (Customer) loginService.getCustomerInfo(userName);
        if(customer==null)
            return atmConstant.getNO_USER_FOUND();
        return accountService.closeAccount(customer, accountID);
    }
    public int transcurrency(String userName, int accountID, CurrencyType from,CurrencyType to, double amount) throws Exception {
        Customer customer = (Customer) loginService.getCustomerInfo(userName);
        Account account = getAccountsForCustomerByID(userName,accountID);
        if(account == null) {
            return atmConstant.getERROR();
        }
        else {
            if(account.getBalance().get(from) < amount) {
                System.out.println("No enough money to transcurrency");
                return atmConstant.getERROR();
            }
            else {
                accountService.transcurrency(account,from,to,amount);
                return atmConstant.getSUCCESS();
            }
        }
    }
    public Account getAccountsForCustomerByID(String userName, int accountID) throws Exception {
        Customer customer = (Customer) loginService.getCustomerInfo(userName);
        return (Account) accountService.getAccountByID(accountID);
    }

    public void redeem(String userName) throws Exception {
        List accounts = getAccountsForCustomer(userName);
        for(Object account : accounts){
            if(account instanceof SavingAccount){
                accountService.redeem(((SavingAccount) account).getAccountID());
            }
        }
    }
}