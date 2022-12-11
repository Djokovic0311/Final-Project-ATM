package controller;

import model.CurrencyType;
import model.Customer;
import model.SecurityAccount;
import service.AccountService;
import service.LoginService;
import service.StockService;
import utils.ATMConstant;

import java.util.Objects;

public class StockController {
    StockService stockService = new StockService();
    LoginService loginService = new LoginService();
    AccountService accountService = new AccountService();
    ATMConstant atmConstant = new ATMConstant();

    public int trade(String userName, int stockID, int quantity, String tradeType) {
        Customer customer = (Customer) loginService.getCustomerInfo(userName);
        int accountID = customer.getSecurityAccount();
        SecurityAccount securityAccount = (SecurityAccount) accountService.getAccountByID(accountID);
        if(Objects.equals(tradeType, "buy")) {
            return stockService.buyStock(customer,securityAccount,stockID,quantity);
        } else {
            return stockService.sellStock(customer,securityAccount,stockID,quantity);
        }
    }
}
