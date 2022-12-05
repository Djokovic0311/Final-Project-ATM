/**
    This is the factory to create accounts
    @author: Jiahang Li
    @version: 1.0
*/

public class AccountFactory {
    public Account createAccount(String[] args) {
        int id = Integer.parseInt(args[0]);
        String name = args[1], pwd = args[2];
        AccountType type = AccountType.valueOf(args[3]);
        String[] balance = args[4].split(" ");
        switch (type) {
            case SAVINGS -> {
                SavingAccount account = new SavingAccount(id, name, pwd, type);
                return account;
            }
            case CHECKINGS -> {
                CheckingAccount account = new CheckingAccount(id, name, pwd, type);
                return account;
            }
            // to do
            case LOAN ->  {
                return null;
            }
            case SECURITY -> {
                return null;
            }
            default -> {
                throw new AccountNotExistException();
            }
        }
    }
}
