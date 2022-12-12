package service;

import dao.AccountDao;
import dao.CustomerHoldStocksDao;
import dao.StockDao;
import dao.StockTransactionDao;
import model.*;
import utils.ATMConstant;
import utils.Utils;

import java.util.ArrayList;

public class StockService {
    private StockTransactionDao stockTransactionDao = new StockTransactionDao();

    private CustomerHoldStocksDao customerHoldStocksDao = new CustomerHoldStocksDao();
    private StockDao stockDao = new StockDao();
    private AccountDao accountDao = new AccountDao();
    ATMConstant atmConstant = new ATMConstant();
    public int buyStock(Customer customer, SecurityAccount securityAccount, int stockID, int quantity) {
        double price = stockDao.getPriceByID(stockID);
        boolean sufficientMoney = securityAccount.getInvestmentAmount() >= price * quantity;
        long timestamp = Utils.getTimestamp();
        if(sufficientMoney) {
            if(customerHoldStocksDao.checkCustomerHolds(stockID)) {
                customerHoldStocksDao.updateCustomerHeldStocks(stockID,customer.getID(),price,quantity,timestamp);
            }
            else {
                customerHoldStocksDao.insertNewHeldStock(stockID,customer.getID(),price,quantity,timestamp);
            }
            stockTransactionDao.insertTransaction(stockID,quantity,timestamp,customer.getID(),quantity * price, TransactionType.BUYSTOCK);
            accountDao.updateAccountBalance(securityAccount.getAccountId(), customer.getID(), AccountType.SECURITY, CurrencyType.USD,
                    securityAccount.getInvestmentAmount()-price * quantity);
            return atmConstant.getSUCCESS();
        }
        else {
            return atmConstant.getERROR();
        }
    }

    public int sellStock(Customer customer, SecurityAccount securityAccount, int stockID, int quantity) {
        double price = stockDao.getPriceByID(stockID);
        int heldStocks = customerHoldStocksDao.getCustomerHeldStocksByID(stockID,customer.getID());
        long timestamp = Utils.getTimestamp();
        if(heldStocks > quantity) {
            customerHoldStocksDao.updateCustomerHeldStocks(stockID,customer.getID(),price,heldStocks-quantity,timestamp);
            accountDao.updateAccountBalance(securityAccount.getAccountId(), customer.getID(), AccountType.SECURITY, CurrencyType.USD,
                    securityAccount.getInvestmentAmount()+price * quantity);
            stockTransactionDao.insertTransaction(stockID,quantity,timestamp,customer.getID(),quantity * price,TransactionType.SELLSTOCK);
            return atmConstant.getSUCCESS();
        }
        else if(heldStocks == quantity){
            customerHoldStocksDao.removeCustomerHeldStock(stockID, customer.getID());
            accountDao.updateAccountBalance(securityAccount.getAccountId(), customer.getID(), AccountType.SECURITY, CurrencyType.USD,
                    securityAccount.getInvestmentAmount()+price * quantity);
            stockTransactionDao.insertTransaction(stockID,quantity,timestamp,customer.getID(),quantity * price,TransactionType.SELLSTOCK);
            return atmConstant.getSUCCESS();
        }
        else {
            return atmConstant.getERROR();
        }
    }

    public ArrayList<customerHeldStock> getCustomerHeldStocks(Customer customer) {
        ArrayList<customerHeldStock> customerOwnedStock = new ArrayList<customerHeldStock>();
        customerOwnedStock = customerHoldStocksDao.getStocks(customer.getID());
        return customerOwnedStock;
    }

    public ArrayList<marketStock> getMarketStocks(){
        ArrayList<marketStock> marketStocks = stockDao.getMarketStocks();
        return marketStocks;
    }
}
