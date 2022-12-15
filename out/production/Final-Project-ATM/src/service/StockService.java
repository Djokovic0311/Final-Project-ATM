package service;

import dao.AccountDao;
import dao.CustomerHoldStocksDao;
import dao.StockDao;
import model.*;
import utils.ATMConstant;
import utils.Utils;

import java.util.ArrayList;

public class StockService {
    private StockDao stockDao = new StockDao();
    private CustomerHoldStocksDao customerHoldStocksDao = new CustomerHoldStocksDao();
    private AccountService accountService = new AccountService();
    private AccountDao accountDao = new AccountDao();
    ATMConstant atmConstant = new ATMConstant();
    public int buyStock(Customer customer, int stockID, int quantity) {
        double price = stockDao.getPriceByID(stockID);

        long timestamp = Utils.getTimestamp();

        int[] accountID = accountDao.getAccountIDFroCustomer(customer.getID(),AccountType.SECURITY);
        // do not have security account, cannot trade

        if(accountID.length == 0) return atmConstant.getERROR();

        SecurityAccount securityAccount = (SecurityAccount) accountService.getAccountByID(accountID[0]);
        boolean sufficientMoney = securityAccount.getBalanceByCurrency(CurrencyType.USD) >= price * quantity;
        if(stockDao.checkStockByID(stockID)) {
            if(sufficientMoney) {
//            System.out.println("sufficent!!!");
                if(customerHoldStocksDao.checkCustomerHolds(stockID)) {
                    int past = customerHoldStocksDao.getCustomerHeldStocksByID(stockID,customer.getID());
                    customerHoldStocksDao.updateCustomerHeldStocks(stockID,customer.getID(),price,past+quantity,timestamp);
                }
                else {
                    customerHoldStocksDao.insertNewHeldStock(stockID,customer.getID(),price,quantity,timestamp);
                }
                accountDao.updateAccountBalance(securityAccount.getAccountID(), AccountType.SECURITY, CurrencyType.USD,
                        securityAccount.getBalanceByCurrency(CurrencyType.USD)-price * quantity);
                return atmConstant.getSUCCESS();
            }
            else {
//            System.out.println("insufficent!!!");
//            System.out.println(securityAccount.getBalanceByCurrency(CurrencyType.USD));
//            System.out.println(price);
//            System.out.println(quantity);
                return atmConstant.getERROR();
            }
        }
        else {
            return atmConstant.getERROR();
        }

    }

    public int sellStock(Customer customer, int stockID, int quantity) {
        double price = stockDao.getPriceByID(stockID);
        int heldStocks = customerHoldStocksDao.getCustomerHeldStocksByID(stockID,customer.getID());
        long timestamp = Utils.getTimestamp();

        int[] accountID = accountDao.getAccountIDFroCustomer(customer.getID(),AccountType.SECURITY);
        // do not have security account, cannot trade
        if(accountID.length == 0) return atmConstant.getERROR();
        SecurityAccount securityAccount = (SecurityAccount) accountService.getAccountByID(accountID[0]);
        if(heldStocks > quantity) {
            customerHoldStocksDao.updateCustomerHeldStocks(stockID,customer.getID(),price,heldStocks-quantity,timestamp);
            accountDao.updateAccountBalance(securityAccount.getAccountID(), AccountType.SECURITY, CurrencyType.USD,
                    securityAccount.getBalanceByCurrency(CurrencyType.USD)+price * quantity);
            return atmConstant.getSUCCESS();
        }
        else if(heldStocks == quantity){
            customerHoldStocksDao.removeCustomerHeldStock(stockID, customer.getID());
            accountDao.updateAccountBalance(securityAccount.getAccountID(), AccountType.SECURITY, CurrencyType.USD,
                    securityAccount.getBalanceByCurrency(CurrencyType.USD)+price * quantity);
            return atmConstant.getSUCCESS();
        }
        else {
            return atmConstant.getERROR();
        }
    }

    public ArrayList<CustomerHeldStock> getCustomerHeldStocks(Customer customer) {
        ArrayList<CustomerHeldStock> customerOwnedStock = new ArrayList<CustomerHeldStock>();
        customerOwnedStock = customerHoldStocksDao.getStocks(customer.getID());
        return customerOwnedStock;
    }

    public ArrayList<marketStock> getMarketStocks(){
        return stockDao.getStocks();
    }

    public int updateStockPrice(int stockID, double newPrice){
//        System.out.println("enter service");
        if(stockDao.updatePriceByID(stockID,newPrice)){
            return atmConstant.getSUCCESS();
        }
        else return atmConstant.getERROR();
    }

    public int addStock(int stockID, double newPrice){
        if(stockDao.insertIntoStock(stockID,newPrice)){
            return atmConstant.getSUCCESS();
        }
        else return atmConstant.getERROR();
    }
}
