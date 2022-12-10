package service;

import dao.AccountDao;
import dao.TransactionDao;
import model.AccountType;
import model.CurrencyType;
import model.TransactionType;
import utils.ATMConstant;

public class TransactionService {
    AccountDao accountDao = new AccountDao();
    TransactionDao transactionDao = new TransactionDao();
    ATMConstant atmConstant = new ATMConstant();
    public int withdraw(int customerId, int accountId, AccountType accountType, double amount, CurrencyType currencyType) {
        double balance = accountDao.getBalanceByCurrencyType(accountId, customerId, accountType, currencyType);
        if(balance < amount) {
            System.out.println("Insufficient funds");
            return atmConstant.getNO_ENOUGH_BALANCE();
        }
        else {
            double remaining = balance - amount;
            accountDao.updateAccountBalance(accountId,customerId,accountType,currencyType,remaining);
            accountDao.payBankFees(amount, atmConstant.getMANAGER_ACCOUNT_ID());
            System.out.println("withdrawn");
            return atmConstant.getSUCCESS();
        }
    }
    public void insertTransaction(int customerID, int senderAccountId, int receiverAccountId, double amount, CurrencyType currencyType, TransactionType transactionType){
        transactionDao.insertTransactionIntoDB(customerID,senderAccountId,receiverAccountId,amount,currencyType,transactionType);
    }
}
