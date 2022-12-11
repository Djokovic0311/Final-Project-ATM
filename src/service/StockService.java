package service;

import dao.AccountDao;
import dao.CustomerHoldStocksDao;
import dao.StockDao;
import model.AccountType;
import model.CurrencyType;
import model.Customer;
import model.SecurityAccount;
import utils.ATMConstant;
import utils.Utils;

public class StockService {
    private StockDao stockDao = new StockDao();
    private CustomerHoldStocksDao customerHoldStocksDao = new CustomerHoldStocksDao();
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
            return atmConstant.getSUCCESS();
        }
        else if(heldStocks == quantity){
            customerHoldStocksDao.removeCustomerHeldStock(stockID, customer.getID());
            accountDao.updateAccountBalance(securityAccount.getAccountId(), customer.getID(), AccountType.SECURITY, CurrencyType.USD,
                    securityAccount.getInvestmentAmount()+price * quantity);
            return atmConstant.getSUCCESS();
        }
        else {
            return atmConstant.getERROR();
        }
    }
}
