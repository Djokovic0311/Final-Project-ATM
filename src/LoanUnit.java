import java.math.BigDecimal;
import java.util.List;

public class LoanUnit {

    private int month; // 
    private BigDecimal repayment; // 
    private BigDecimal payPrincipal; // 
    private BigDecimal interest; // 
    private BigDecimal remainTotal; // 
    private BigDecimal remainPrincipal; // 

    private List<LoanUnit> allLoans; // list
    private int year; 
    private int monthInYear; 

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public BigDecimal getRepayment() {
        return repayment;
    }

    public void setRepayment(BigDecimal repayment) {
        this.repayment = repayment;
    }

    public BigDecimal getPayPrincipal() {
        return payPrincipal;
    }

    public void setPayPrincipal(BigDecimal payPrincipal) {
        this.payPrincipal = payPrincipal;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getRemainTotal() {
        return remainTotal;
    }

    public void setRemainTotal(BigDecimal remainTotal) {
        this.remainTotal = remainTotal;
    }

    public BigDecimal getRemainPrincipal() {
        return remainPrincipal;
    }

    public List<LoanUnit> getAllLoans() {
        return allLoans;
    }

    public void setAllLoans(List<LoanUnit> allLoans) {
        this.allLoans = allLoans;
    }

    public void setRemainPrincipal(BigDecimal remainPrincipal) {
        this.remainPrincipal = remainPrincipal;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonthInYear() {
        return monthInYear;
    }

    public void setMonthInYear(int monthInYear) {
        this.monthInYear = monthInYear;
    }
    

    public String toString() {
        String info = "";
        if (info != null) {
            for (LoanUnit loanunit : allLoans) {
                info = "Month: " + loanunit.getMonth() + "\t year" + loanunit.getYear() +
                        loanunit.getMonthInYear() + "month\t" + "repay every month: " + loanunit.getRepayment() +
                        "\tprinciple: " + loanunit.getPayPrincipal() + "\tinterest: " + loanunit.getInterest() +
                        "\tremaining: " + loanunit.getRemainTotal();
            }
        }
        return "每月还款: " + getAvgRepayment() + "\t总利息: " + getTotalInterest() +
                "\t还款总额：" + getTotalRepayment()  + "\n" + info;
    }
}