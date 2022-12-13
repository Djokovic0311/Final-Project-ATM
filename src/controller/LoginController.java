package controller;

import model.Customer;
import service.LoginService;
import utils.ATMConstant;
import utils.Utils;

public class LoginController {

    LoginService loginService = new LoginService();
    ATMConstant atmConstant = new ATMConstant();
    public int signIn(String userName, String password, String userType) throws Exception {
        return loginService.signIn(userName,password,userType);
    }

    public int signUpCustomer(String name, String pwd) throws Exception {

        Customer customer = new Customer();
        customer.setName(name);
        int customerId = Utils.createHashCodeForPersonId(name);

        customer.setID(customerId);
        customer.setPassword(pwd);
        return loginService.signUp(customer,pwd);
    }

}