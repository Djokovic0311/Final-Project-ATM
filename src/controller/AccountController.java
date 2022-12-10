package controller;

import model.Customer;
import model.User;
import service.AccountService;
import service.LoginService;
import utils.ATMConstant;

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
}
