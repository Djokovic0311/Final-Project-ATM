public class SavingAccount extends Account{

    @Override
    public boolean transfer(Account to, Currency currency) {
        return false;
    }
}
