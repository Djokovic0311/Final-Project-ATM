package service;/*
    This is the class for login service
    @author: Jiahang Li
    @version: 1.0
*/

import model.Customer;
import utils.ATMConstant;

public class LoginService {
    DbService dbService = new DbService();
    AccountService accountService = new AccountService();
    ATMConstant atmConstant = new ATMConstant();

    // TODO: sign in the customer and return status code for front end display
    public int signIn(String userId,String password) {
        return 0;
    }
    // TODO: sign up for new customers and return status code
    public int signUp(Customer customer, String pwd) {
        return 0;
    }
}
