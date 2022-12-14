package controller;

import model.*;
import service.AccountService;
import service.LoginService;
import service.StockService;
import utils.ATMConstant;

import java.util.ArrayList;
import java.util.Objects;

public class StockController {
    StockService stockService = new StockService();
    LoginService loginService = new LoginService();
    AccountService accountService = new AccountService();
    ATMConstant atmConstant = new ATMConstant();

    public int trade(String userName, int stockID, int quantity, String tradeType) throws Exception {
        Customer customer = (Customer) loginService.getCustomerInfo(userName);

        if(Objects.equals(tradeType, "buy")) {
            return stockService.buyStock(customer,stockID,quantity);
        } else {
            return stockService.sellStock(customer,stockID,quantity);
        }
    }

    public ArrayList<CustomerHeldStock> showHeldStocks(String userName) throws Exception {
        Customer customer = (Customer) loginService.getCustomerInfo(userName);
        int accountID = customer.getSecurityAccount();
        SecurityAccount securityAccount = (SecurityAccount) accountService.getAccountByID(accountID);
        return stockService.getCustomerHeldStocks(customer);
    }

    public ArrayList<marketStock> showMarketStocks(){
        return stockService.getMarketStocks();
    }

    public int updateStock(int stockID, double newPrice){
        return stockService.updateStockPrice(stockID,newPrice);
    }

    public int addStock(int stockID, double price){
        return stockService.addStock(stockID,price);
    }
}
