package controller;

import service.AccountService;
import service.LoginService;
import utils.ATMConstant;

public class AccountController {
    AccountService accountService = new AccountService();

    LoginService loginService = new LoginService();
    ATMConstant atmConstant = new ATMConstant();


}
