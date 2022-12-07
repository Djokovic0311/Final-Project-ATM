
import java.math.BigDecimal;
import java.util.List;

public class LoanTotal {

    private BigDecimal totalLoanMoney; //
    private int totalMonth; //
    private double loanRate; //

    private BigDecimal totalInterest; // 
    private BigDecimal totalRepayment; // 
    private BigDecimal avgRepayment; // 


    public BigDecimal getTotalLoanMoney() {
        return totalLoanMoney;
    }

    public void setTotalLoanMoney(BigDecimal totalLoanMoney) {
        this.totalLoanMoney = totalLoanMoney;
    }

    public int getTotalMonth() {
        return totalMonth;
    }

    public void setTotalMonth(int totalMonth) {
        this.totalMonth = totalMonth;
    }

    public double getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(double loanRate) {
        this.loanRate = loanRate;
    }

    public BigDecimal getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(BigDecimal totalInterest) {
        this.totalInterest = totalInterest;
    }

    public BigDecimal getTotalRepayment() {
        return totalRepayment;
    }

    public void setTotalRepayment(BigDecimal totalRepayment) {
        this.totalRepayment = totalRepayment;
    }

    public BigDecimal getAvgRepayment() {
        return avgRepayment;
    }

    public void setAvgRepayment(BigDecimal avgRepayment) {
        this.avgRepayment = avgRepayment;
    }
    
    public String toString() {
        return "pay interest per month: " + getAvgRepayment() + "\t total interest: " + getTotalInterest() +
                "\t total repayment：" + getTotalRepayment()  + "\n";
    }
 
}