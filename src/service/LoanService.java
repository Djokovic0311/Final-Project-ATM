package service;

import dao.AccountDao;
import dao.LoanDao;
import model.Account;
import model.CurrencyType;
import model.Customer;
import model.Loan;
import utils.ATMConstant;
import utils.Utils;

import java.util.List;

public class LoanService {
    private LoanDao loanDao = new LoanDao();
    private AccountDao accountDao = new AccountDao();
    ATMConstant atmConstant = new ATMConstant();
    public List<Loan> getLoansForCustomer(int customerID){
        return loanDao.getLoansByCustomerID(customerID);
    }
    public void requireLoan(Customer customer,double amount, int tenure, double rateOfInterest, long timestamp, CurrencyType currencyType){
        int loanID = Utils.getFixedLengthRandom(10);
        loanDao.insertLoan(customer.getID(),amount,rateOfInterest,timestamp,currencyType,loanID);
    }
    public Loan getLoanByID(int loanID){
        return loanDao.getLoanByID(loanID);
    }

    public int payForLoan(Customer customer, double amount, Account account, int loanID) {
        Loan loan = getLoanByID(loanID);
        double left = loanDao.getLoanRemaining(loanID);
        CurrencyType currencyType = loan.getCurrency();
        if(loan == null){
            return atmConstant.getERROR();
        }
        else {
            if(account.getBalance().get(currencyType) < amount) {
                System.out.print("No enough money to pay for loan!");
                return atmConstant.getERROR();
            }
            else {
                if(amount > left) {
                    System.out.print("Too much money to pay for loan!");
                    return atmConstant.getERROR();
                }
                else if(amount < left){
                    loanDao.updateLoan(loanID,left-amount);
                    accountDao.updateAccountBalance(account.getAccountID(),account.getType(),currencyType,
                            account.getBalance().get(currencyType)-amount);
                    return atmConstant.getSUCCESS();
                }
                else {
                    loanDao.deleteLoan(loanID);
                    accountDao.updateAccountBalance(account.getAccountID(),account.getType(),currencyType,
                            account.getBalance().get(currencyType)-amount);
                    return atmConstant.getSUCCESS();
                }
            }

        }
    }
}
