package service;

import dao.AccountDao;
import dao.TransactionDao;
import model.*;
import utils.ATMConstant;
import utils.Utils;

import java.util.Date;

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
            long timestamp = Utils.getTimestamp();
            int transactionID = Utils.getFixedLengthRandom(10);
            while(transactionDao.transactionExist(transactionID)) {
                transactionID = Utils.getFixedLengthRandom(10);
            }
            WithdrawTransaction withdrawTransaction = new WithdrawTransaction(transactionID,timestamp,amount,customerId,accountId,-1,currencyType);
            insertTransaction(withdrawTransaction);
            accountDao.updateAccountBalance(accountId,customerId,accountType,currencyType,remaining);
            accountDao.payBankFees(amount, atmConstant.getMANAGER_ACCOUNT_ID());
            System.out.println("withdrawn");
            return atmConstant.getSUCCESS();
        }
    }

    public int deposit(int customerId, int accountId, AccountType accountType, double amount, CurrencyType currencyType) {
        double balance = accountDao.getBalanceByCurrencyType(accountId, customerId, accountType, currencyType);

        double remaining = balance + amount;
        long timestamp = Utils.getTimestamp();
        int transactionID = Utils.getFixedLengthRandom(10);
        while(transactionDao.transactionExist(transactionID)) {
            transactionID = Utils.getFixedLengthRandom(10);
        }
        DepositTransaction depositTransaction = new DepositTransaction(transactionID,timestamp,amount,customerId,accountId,-1,currencyType);
        insertTransaction(depositTransaction);

        accountDao.updateAccountBalance(accountId,customerId,accountType,currencyType,remaining);
        accountDao.payBankFees(amount, atmConstant.getMANAGER_ACCOUNT_ID());
        System.out.println("deposit");
        return atmConstant.getSUCCESS();

    }

    public int transfer(int customerId, int fromAccountId, int toAccountId, double amount, CurrencyType currencyType,AccountType accountType) {
        double fromBalance = accountDao.getBalanceByCurrencyType(fromAccountId, customerId, accountType, currencyType);
        double toBalance = accountDao.getBalanceByCurrencyType(toAccountId, customerId, accountType, currencyType);

        if(fromBalance < amount) {
            System.out.println("Insufficient funds");
            return atmConstant.getNO_ENOUGH_BALANCE();
        }
        else {
            double fromBalanceAfter = fromBalance - amount;
            double toBalanceAfter = toBalance + amount;

            long timestamp = Utils.getTimestamp();
            int transactionID = Utils.getFixedLengthRandom(10);
            while(transactionDao.transactionExist(transactionID)) {
                transactionID = Utils.getFixedLengthRandom(10);
            }
            TransferTransaction transferTransaction = new TransferTransaction(transactionID,timestamp,amount,customerId,fromAccountId,toAccountId,currencyType);
            insertTransaction(transferTransaction);

            accountDao.updateAccountBalance(fromAccountId,customerId,accountType,currencyType,fromBalanceAfter);
            accountDao.updateAccountBalance(toAccountId,customerId,accountType,currencyType,toBalanceAfter);

            accountDao.payBankFees(amount, atmConstant.getMANAGER_ACCOUNT_ID());
            System.out.println("transfer");

            return atmConstant.getSUCCESS();
        }
    }


    public void insertTransaction(Transaction transaction){
        int customerID = transaction.getuserID();
        int senderAccountId = transaction.getFromAccountID();
        int receiverAccountId = transaction.getToAccountID();
        double amount = transaction.getAmount();
        CurrencyType currencyType = transaction.getCurrencyType();
        TransactionType transactionType = transaction.getType();
        long timestamp = transaction.getTimestamp();
        transactionDao.insertTransactionIntoDB(customerID,senderAccountId,receiverAccountId,amount,currencyType,transactionType,timestamp);
    }
}
