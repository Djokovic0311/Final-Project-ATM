package controller;

import model.Account;
import model.CurrencyType;
import model.Customer;
import model.Loan;
import org.omg.CORBA.FREE_MEM;
import service.AccountService;
import service.LoanService;
import service.LoginService;
import service.StockService;
import utils.ATMConstant;

import java.util.List;

public class LoanController {
    LoanService loanService = new LoanService();
    LoginService loginService = new LoginService();
    AccountService accountService = new AccountService();
    ATMConstant atmConstant = new ATMConstant();
    public List getLoansForCustomer(String userName) throws Exception {
        Customer customer = (Customer) loginService.getCustomerInfo(userName);
        int accountID = customer.getSecurityAccount();
        return loanService.getLoansForCustomer(customer.getID());
    }

    public int requireLoan(String userName, double amount, int tenure, long timestamp, CurrencyType currencyType) throws Exception {
        Customer customer = (Customer) loginService.getCustomerInfo(userName);
        if(checkCanLoan(customer,amount,currencyType)){
            loanService.requireLoan(customer,amount,tenure,timestamp,currencyType);
            return atmConstant.getSUCCESS();
        }
        else {
            return atmConstant.getERROR();
        }
    }

    public int payForLoan(String userName, double amount, int accountID, int loanID) throws Exception {
        Customer customer = (Customer) loginService.getCustomerInfo(userName);
        Account account = (Account) accountService.getAccountByID(accountID);
        return loanService.payForLoan(customer, amount,account,loanID);


    }
    public boolean checkCanLoan(Customer customer, double amount, CurrencyType currencyType){
        return true;
    }
}
