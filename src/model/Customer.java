package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Customer extends User {

    void createConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(ClassNotFoundException ex){
            System.out.println("Driver not found");
        }
    }

    public Customer(String name, int ID, String type, int password) {
        super(name, ID, type, password);
    }

    public boolean createSavorLAccount(AccountType type, double startBalance) {
        // Is there any minimum balance requirement when opening an account?
        // How to get the opening fee?
        // When creating a new account, how does the startBalance come from? (especially for checking account)
        // Where to store the new account?
        // You can only start a new account with USD (Since this is a US bank)
        if (type == AccountType.SAVINGS) {
            double open_fee = Database_Queries.get_open_fee();
            SavingAccount saving_account = NewAccountCreator.getNewSavingAccount(this.ID, startBalance - open_fee);
            pay(0, CurrencyType.USD, open_fee);
            // Write the account into database.
            return true;
        } else if (type == AccountType.LOAN) {
            LoanAccount loan_account = NewAccountCreator.getNewLoanAccount(this.ID, startBalance); // Here startBalance is in fact how much loan requested
            // Write the account into database.
            return true;
        }
        return false;
    }

    public boolean createCorSeAccount(AccountType type, double startBalance, int payeeAccountID) {
        SavingAccount payeeAccount = (SavingAccount) Database_Queries.get_user_account(payeeAccountID, "Saving");
        if (payeeAccount == null || payeeAccount.getUserID() != this.ID) {
            return false;
        }
        if (payeeAccount.getBalance().get(CurrencyType.USD) < startBalance) {
            return false;
        }
        double open_fee = Database_Queries.get_open_fee();
        pay(payeeAccountID, CurrencyType.USD, open_fee);
        if (type == AccountType.CHECKINGS) {
            CheckingAccount c = NewAccountCreator.getNewCheckingAccount(this.ID, 0);
            transfer(payeeAccountID, c.getAccountID(), CurrencyType.USD, startBalance - (double) open_fee, false);
            // Write the account into database.
            return true;
        } else if (type == AccountType.SECURITY) {
            if (startBalance <= 1000) { // must have over 1000 at beginning.
                return false;
            }
            SecurityAccount se = NewAccountCreator.getNewSecurityAccount(this.ID, 0);
            transfer(payeeAccountID, se.getAccountID(), CurrencyType.USD, startBalance - (double) open_fee, false);
            return false;
        }
        return false;
    }

    public Map<CurrencyType, Double> getBalance() {
        double balance_USD = 0.0;
        double balance_EUR = 0.0;
        double balance_CNY = 0.0;
        List<Account> savingAccounts = getCorSaveAccounts("Saving");
        for (Account savingAccount : savingAccounts) {
            SavingAccount sa = (SavingAccount) savingAccount;
            Map<CurrencyType, Double> balance = sa.getBalance();
            balance_USD += balance.get(CurrencyType.USD);
            balance_EUR += balance.get(CurrencyType.EUR);
            balance_CNY += balance.get(CurrencyType.CNY);
        }
        List<Account> checkingAccounts = getCorSaveAccounts("Checking");
        for (Account checkingAccount : checkingAccounts) {
            SavingAccount c = (SavingAccount) checkingAccount;
            Map<CurrencyType, Double> balance = c.getBalance();
            balance_USD += balance.get(CurrencyType.USD);
            balance_EUR += balance.get(CurrencyType.EUR);
            balance_CNY += balance.get(CurrencyType.CNY);
        }
        SecurityAccount se = (SecurityAccount) getLorSeAcount("Security");
        balance_USD += se.getBalance();
        Map<CurrencyType, Double> totalBalance = new Hashtable<>();
        totalBalance.put(CurrencyType.USD, balance_USD);
        totalBalance.put(CurrencyType.EUR, balance_EUR);
        totalBalance.put(CurrencyType.CNY, balance_CNY);
        return totalBalance;
    }

    public double getLoan() {
        SecurityAccount se = (SecurityAccount) getLorSeAcount("Loan");
        return se.getBalance();
    }


    public String toString() {
        return "Customer name: " + name + ", Customer ID: " + ID + ", Current Balance: " + getBalance();
    }


    public boolean closeAccount(int accountID) {
        // When closing account, where does the money goes?
        double close_fee = Database_Queries.get_close_fee();
        pay(0, CurrencyType.USD, close_fee);
        return Database_Queries.closeAccount(this.ID, accountID);
    }

    public boolean deposit(int accountID, CurrencyType t, double amount) {
        Account a = Database_Queries.get_user_account(accountID, "");
        if (a == null) {
            return false;
        }
        a.deposit(t, amount);
        return true;
    }

    public boolean withdraw(int accountID, CurrencyType t, double amount) {
        double fee = Database_Queries.get_traction_fee(amount);
        pay(accountID, t, fee);
        Account a = Database_Queries.get_user_account(accountID, "");
        if (a == null) {
            return false;
        }
        return a.withdraw(t, amount);
    }

    public boolean payLoan(int payAccountID, int amount, int loanID) {
        LoanAccount l = // Here get the account using its ID
        for (int i = 0; i < savingAccounts.length; i ++) {
            if (savingAccounts[i] == accountID) {
                SavingAccount sa = // Here get the account using its ID
                if (sa.withdraw(amount)) {
                    return l.pay(amount, loanID);
                }
            }
        }
        for (int i = 0; i < checkingAccounts.length; i ++) {
            if (checkingAccounts[i] == accountID) {
                LoanAccount.CheckingAccount c = // Here get the account using its ID
                if (c.withdraw(amount)) {
                    return l.pay(amount, loanID);
                }
            }
        }
    }

    public Transaction[] checkTransaction() {
        return // get all transactions from database.
    }


    public boolean transfer(int sourceID, int destiniationID, CurrencyType t, double amount, boolean shouldPay) {
        double fee = 0.0;
        if (shouldPay) {
            fee = Database_Queries.get_traction_fee(amount);
        }
        Account from = Database_Queries.get_user_account(sourceID, "");
        Account to = Database_Queries.get_user_account(destiniationID, "");
        if (from != null && to != null) {
            return (from.transfer(to, t, amount, fee));
        }

        return false;
    }


    public Stock[] checkHoldStocks() {
        SecurityAccount se = // Here get the account using its ID
        return se.checkHoldStocks();
    }


    public Stock[] checkStockMarket() {
        SecurityAccount se = // Here get the account using its ID
        return se.checkStockMarket();
    }


    public String checkSecurityAccountStatus() {
        SecurityAccount se = // Here get the account using its ID
        return se.toString();
    }


    public boolean buyStock(int stockID) {
        SecurityAccount se = // Here get the account using its ID
        return se.buyStock(stockID);
    }


    public boolean sellStock(int stockID) {
        SecurityAccount se = // Here get the account using its ID
        return se.sellStock(stockID);
    }


    public void redeem() {
        double interest = // How to get the interest of saving?
        for (int i = 0; i < savingAccounts.length; i ++) {
            SavingAccount sa = // Here get the account using its ID
            sa.redeem(interest);
        }
    }

    public List<Account> getCorSaveAccounts(String type) {
        List<Account> accounts = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","108875556");
            Statement stmt = con.createStatement();
            ResultSet rs;
            if (type.equals("Checking")) {
                rs = stmt.executeQuery("SELECT * FROM ACCOUNTS WHERE TYPE = 'CHECKING' and ID = " + this.getID());
            } else {
                rs = stmt.executeQuery("SELECT * FROM ACCOUNTS WHERE TYPE = 'Saving' and ID = " + this.getID());
            }
            while (rs.next()) {
                int accountID = rs.getInt(0);
                String accountType = rs.getString(2);
                AccountType accType = AccountType.convertStrToType(accountType);
                double balance_usd = rs.getDouble(3);
                double balance_eur = rs.getInt(4);
                double balance_cny = rs.getInt(5);
                Map<CurrencyType, Double> balance = new Hashtable<>();
                balance.put(CurrencyType.USD, balance_usd);
                balance.put(CurrencyType.EUR, balance_eur);
                balance.put(CurrencyType.CNY, balance_cny);
                Account a;
                if (type.equals("Checking")) {
                    a = new CheckingAccount(accountID, this.ID, accType, balance);
                } else {
                    a = new SavingAccount(accountID, this.ID, accType, balance);
                }
                accounts.add(a);
            }
        } catch (Exception e) {
            return null;
        }
        return accounts;
    }

    public Account getLorSeAcount(String type) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank","root","108875556");
            Statement stmt = con.createStatement();
            ResultSet rs;
            if (type.equals("Loan")) {
                rs = stmt.executeQuery("SELECT * FROM ACCOUNTS WHERE TYPE = 'Loan' and ID = " + this.getID());
            } else {
                rs = stmt.executeQuery("SELECT * FROM ACCOUNTS WHERE TYPE = 'Security' and ID = " + this.getID());
            }
            while (rs.next()) {
                int accountID = rs.getInt(0);
                String accountType = rs.getString(2);
                AccountType accType = AccountType.convertStrToType(accountType);
                double balance_usd = rs.getDouble(3);
                Account a;
                if (type.equals("Loan")) {
                    a = new LoanAccount(accountID, this.ID, accType);
                } else {
                    a = new SecurityAccount(accountID, accType, this.ID, balance_usd);
                }
                return a;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    // Pay to the manager account
    // currentAccount = 0 means pay from outside (e.g., open an saving account, pay with cash)
    public void pay(int currentAccountID, CurrencyType t, double amount) {
        ManagerAccount m = Database_Queries.get_manager_account();
        if (currentAccountID != 0) {
            m.deposit(t, amount);
        } else {
            transfer(currentAccountID, m.getAccountID(), t, amount, false);
        }
    }


}