package controller;

import model.*;
import service.AccountService;
import service.LoanService;
import service.LoginService;
import service.TransactionService;
import utils.ATMConstant;

import java.util.List;

public class TransactionController {
    TransactionService transactionService = new TransactionService();

    ATMConstant atmConstant = new ATMConstant();
    LoanService loanService = new LoanService();
    LoginService loginService = new LoginService();
    AccountService accountService = new AccountService();

    public int withdraw(int customerId, int accountId, double amount, CurrencyType currencyType) {
        Account account = (Account) accountService.getAccountByID(accountId);
        AccountType accountType = account.getType();
        int status = transactionService.withdraw(customerId,accountId,accountType,amount,currencyType);
        return status;
    }

    public int deposit(int customerId, int accountId, double amount, CurrencyType currencyType) {
        Account account = (Account) accountService.getAccountByID(accountId);
        AccountType accountType = account.getType();
        int status = transactionService.deposit(customerId,accountId,accountType,amount,currencyType);
        return status;
    }

    public int transfer(int customerId, int fromAccountId, int toAccountId, double amount, CurrencyType currencyType) {
        Account account = (Account) accountService.getAccountByID(fromAccountId);
        AccountType accountType = account.getType();
        int status = transactionService.transfer(customerId,fromAccountId, toAccountId,amount,currencyType,accountType);
        return status;
    }

    public List<Transaction> getTransactionsForCustomer(String userName) throws Exception {
        Customer customer = (Customer) loginService.getCustomerInfo(userName);

        return transactionService.getTransactions(customer);
    }

    public List<Transaction> getDailyReport(long timestamp){
        return
    }
}
