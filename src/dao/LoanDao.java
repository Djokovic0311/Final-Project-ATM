package dao;

import model.CurrencyType;
import model.Loan;

import java.util.Currency;
import java.util.List;

public class LoanDao {
    public List<Loan> getLoansByCustomerID(int customerID) {
        return null;
    }

    public void insertLoan(int customerId, double amount, double rateOfInterest, long loanDate, CurrencyType currencyType, int loanID){

    }

    public Loan getLoanByID(int loanID){
        return null;
    }

    public void updateLoan(int loanID, double amount) {

    }
    public double getLoanRemaining(int loanID){
        return 0.0;
    }
    public void deleteLoan(int loanID){

    }
}
