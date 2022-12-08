package model;

import model.CheckingAccount;

public class Customer extends User {
    private int[] savingAccounts;
    private int[] checkingAccounts;
    private int loanAccount;
    private int securityAccount;

    public Customer() {
    }

    public Customer(String name, int ID, String type, int password) {
        super(name, ID, type, password);
        savingAccounts = null;
        checkingAccounts = null;
        loanAccount = -1;
        securityAccount = -1;
    }

    public boolean createAccount(AccountType type, int startBalance) {
        // Is there any minimum balance requirement when opening an account?
        // How to get the opening fee?
        // When creating a new account, how does the startBalance come from? (espiacially for checking account)
        // Where to store the new account?
        if (type == AccountType.SAVINGS) {
            int open_fee = 0;
            SavingAccount saving_account = new SavingAccount(startBalance - open_fee);
            if (savingAccounts == null) {
                savingAccounts = new int[]{saving_account.getAccountID()};
            } else {
                int[] temp = new int[savingAccounts.length + 1];
                for (int i = 0; i < savingAccounts.length; i ++) {
                    temp[i] = savingAccounts[i];
                }
                temp[temp.length-1] = saving_account.getAccountID();
                savingAccounts = temp;
            }
            return true;
        } else if (type == AccountType.CHECKINGS) {
            int open_fee = 0;
            CheckingAccount checking_account = new CheckingAccount(startBalance - open_fee);
            if (checkingAccounts == null) {
                checkingAccounts = new int[]{checking_account.getAccountID()}
            } else {
                int[] temp = new int[checkingAccounts.length + 1];
                for (int i = 0; i < checkingAccounts.length; i ++) {
                    temp[i] = checkingAccounts[i];
                }
                temp[temp.length-1] = checking_account.getID();
                checkingAccounts = temp;
            }
            return true;
        }  else if (type == AccountType.LOAN) {
            LoanAccount loan_account = new LoanAccount(startBalance); // Here startBalance is in fact how much loan requested
            loanAccount = loaning_account.getID();
            return true;
        }  else if (type == AccountType.SECURITY) {
            if (startBalance <= 1000) { // must have over 1000 at begining.
                return false
            }
            for (int i = 0; i < savingAccounts.length; i ++) {
                if (savingAccounts[i].getBalance > 5000) { // Can open the account
                    SecurityAccount security_account = new SecurityAccount(startBalance);
                    checkngAccounts.append(checking_account.getID());
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int getBalance() {
        int balance = 0;
        for (int i = 0; i < savingAccounts.length; i ++) {
            SavingAccount sa = // Here get the account using its ID
            balance += sa.getBalance();
        }
        for (int j = 0; j < checkingAccounts.length; i ++) {
            CheckingAccount c = // Here get the account using its ID
            balance += c.getBalance();
        }
        SecurityAccount se = // Here get the account using its ID
        balance += se.getBalance();
        return balance;
    }

    public int getLoan() {
        if (loanAccount == null) {
            return 0;
        }
        SecurityAccount se = // Here get the account using its ID
        return se.getBalance();
    }


    @Override
    public String toString() {
        return "Customer name: " + getName() + ", Customer ID: " + getID() + ", Current Balance: " + getBalance();
    }


    public boolean closeAccount(AccountType type, int accountID) {
        // When closing account, where does the money goes?
        if (type == AccountType.SAVINGS) {
            for (int i = 0; i < savingAccounts.length; i ++) {
                if (savingAccounts[i] == accountID) {
                    SavingAccount sa = // Here get the account using its ID
                    return sa.close();
                }
            }
        } else if (type == AccountType.CHECKINGS) {
            for (int i = 0; i < checkingAccounts.length; i ++) {
                if (checkingAccounts[i] == accountID) {
                    CheckingAccount c = // Here get the account using its ID
                    return c.close();
                }
            }
        } else if (type == AccountType.SECURITY) {
            if (securityAccount == accountID) {
                SecurityAccount se = // Here get the account using its ID
                return se.close();
            }
        }
        return false;
    }

    public boolean deposit(int accountID, int amount) {
        for (int i = 0; i < savingAccounts.length; i ++) {
            if (savingAccounts[i] == accountID) {
                SavingAccount sa = // Here get the account using its ID
                return sa.deposit(amount);
            }
        }
        for (int i = 0; i < checkingAccounts.length; i ++) {
            if (checkingAccounts[i] == accountID) {
                CheckingAccount c = // Here get the account using its ID
                return c.deposit(amount);
            }
        }
        if (securityAccount == accountID) {
            SecurityAccount se = // Here get the account using its ID
            return se.deposit(amount);
        }
        return false;
    }

    public boolean withdraw(int accountID, int amount) {
        for (int i = 0; i < savingAccounts.length; i ++) {
            if (savingAccounts[i] == accountID) {
                SavingAccount sa = // Here get the account using its ID
                return sa.withdraw(amount);
            }
        }
        for (int i = 0; i < checkingAccounts.length; i ++) {
            if (checkingAccounts[i] == accountID) {
                CheckingAccount c = // Here get the account using its ID
                return c.withdraw(amount);
            }
        }
        if (securityAccount == accountID) {
            SecurityAccount se = // Here get the account using its ID
            return se.withdraw(amount);
        }
        return false;
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
                CheckingAccount c = // Here get the account using its ID
                if (c.withdraw(amount)) {
                    return l.pay(amount, loanID);
                }
            }
        }
    }

    public Transaction[] checkTransaction() {
        return // get all transactions from database.
    }


    public boolean transfer(int sourceID, int destiniationID, int amount) {
        for (int i = 0; i < savingAccounts.length; i ++) {
            if (savingAccounts[i] == accountID) {
                SavingAccount sa = // Here get the account using its ID
                return sa.transfer(destiniationID, amount);
            }
        }
        for (int i = 0; i < checkingAccounts.length; i ++) {
            if (checkingAccounts[i] == accountID) {
                CheckingAccount c = // Here get the account using its ID
                return c.transfer(destiniationID, amount);
            }
        }
        if (sourceID == securityAccount) {
            SecurityAccount se = // Here get the account using its ID
            return se.tranfer(destiniationID, amount);
        }
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


}