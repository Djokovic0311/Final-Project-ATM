import java.util.Date;

/**
    This is the class for deposit transaction
    @author: Jiahang Li
    @version: 1.0
 */
public class DepositTransaction extends Transaction{
    public DepositTransaction(String ID, Date timestamp, double amount, String userID) {
        super(ID, timestamp, amount, userID, TransactionType.DEPOSIT);
    }
}
