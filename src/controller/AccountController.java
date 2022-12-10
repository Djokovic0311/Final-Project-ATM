package controller;

import model.AccountType;
import model.CurrencyType;
import model.Customer;
import model.User;
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

    public int closeAccount(String userName, int accountID) {
        Customer customer = (Customer) loginService.getCustomerInfo(userName);
        if(customer==null)
            return atmConstant.getNO_USER_FOUND();
        return accountService.closeAccount(customer, accountID);
    }
}
