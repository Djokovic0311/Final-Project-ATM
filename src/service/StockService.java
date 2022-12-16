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
                if(customerHoldStocksDao.checkCustomerHolds(stockID, customer.getID())) {
                    int past = (int) customerHoldStocksDao.getCustomerHeldStocksInfoByID(stockID,customer.getID())[0];
                    customerHoldStocksDao.updateCustomerHeldStocks(stockID,customer.getID(),price,past+quantity,timestamp);
                }
                else {
                    customerHoldStocksDao.insertNewHeldStock(stockID,customer.getID(),price,quantity,timestamp);
                }
                accountDao.updateAccountBalance(securityAccount.getAccountID(), AccountType.SECURITY, CurrencyType.USD,
                        securityAccount.getBalanceByCurrency(CurrencyType.USD)- price * quantity);
                return atmConstant.getSUCCESS();
            }
            else {
                return atmConstant.getERROR();
            }
        }
        else {
            return atmConstant.getERROR();
        }

    }

    public int sellStock(Customer customer, int stockID, int quantity) {
        double price = stockDao.getPriceByID(stockID);
        double[] heldStocksInfo = customerHoldStocksDao.getCustomerHeldStocksInfoByID(stockID,customer.getID());
        int numHoldStocks = (int) heldStocksInfo[0];
        double buy_price = heldStocksInfo[1];
        long timestamp = Utils.getTimestamp();

        int[] accountID = accountDao.getAccountIDFroCustomer(customer.getID(),AccountType.SECURITY);
        // do not have security account, cannot trade
        if(accountID.length == 0) return atmConstant.getERROR();
        SecurityAccount securityAccount = (SecurityAccount) accountService.getAccountByID(accountID[0]);
        if(numHoldStocks > quantity) {
            customerHoldStocksDao.updateCustomerHeldStocks(stockID,customer.getID(),buy_price,numHoldStocks-quantity,timestamp);
            accountDao.updateAccountBalance(securityAccount.getAccountID(), AccountType.SECURITY, CurrencyType.USD,
                    securityAccount.getBalanceByCurrency(CurrencyType.USD)+price * quantity);
            accountDao.updateRealizedProfit(securityAccount.getAccountID(), (price - buy_price) * quantity);
            return atmConstant.getSUCCESS();
        }
        else if(numHoldStocks == quantity){
            customerHoldStocksDao.removeCustomerHeldStock(stockID, customer.getID());
            accountDao.updateAccountBalance(securityAccount.getAccountID(), AccountType.SECURITY, CurrencyType.USD,
                    securityAccount.getBalanceByCurrency(CurrencyType.USD)+price * quantity);
            accountDao.updateRealizedProfit(securityAccount.getAccountID(), (price - buy_price) * quantity);
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
