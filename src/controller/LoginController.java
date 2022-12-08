package controller;

import model.Customer;
import service.LoginService;
import utils.ATMConstant;
import utils.Utils;

public class LoginController {

    LoginService loginService = new LoginService();
    ATMConstant atmConstant = new ATMConstant();
    public int signIn(String userId, String password) throws Exception {
        return loginService.signIn(userId,password);
    }

    public int signUpCustomer(String name, String pwd) throws Exception {
        int customerId = Utils.getFixedLengthRandom(atmConstant.getIdBits());
        Customer customer = new Customer();
        customer.setID(customerId);
        customer.setName(name);
        return loginService.signUp(customer,pwd);
    }

}