package controller;

import model.Account;
import model.AccountType;
import model.CurrencyType;
import model.TransactionType;
import service.AccountService;
import service.LoanService;
import service.TransactionService;
import utils.ATMConstant;

public class TransactionController {
    TransactionService transactionService = new TransactionService();

    ATMConstant atmConstant = new ATMConstant();
    LoanService loanService = new LoanService();
    AccountService accountService = new AccountService();

    public int withdraw(int customerId, int accountId, double amount, CurrencyType currencyType) {
        Account account = (Account) accountService.getAccountByID(accountId);
        AccountType accountType = account.getType();
        int status = transactionService.withdraw(customerId,accountId,accountType,amount,currencyType);
        if(status == atmConstant.getSUCCESS()) {
            transactionService.insertTransaction(customerId,accountId,-1, amount, currencyType, TransactionType.WITHDRAW);

        }
        return status;
    }

    public int deposit(int customerId, int accountId, double amount, CurrencyType currencyType) {
        Account account = (Account) accountService.getAccountByID(accountId);
        AccountType accountType = account.getType();
        int status = transactionService.deposit(customerId,accountId,accountType,amount,currencyType);
        if(status == atmConstant.getSUCCESS()) {
            transactionService.insertTransaction(customerId,accountId,-1, amount, currencyType, TransactionType.DEPOSIT);
        }
        return status;
    }
}
