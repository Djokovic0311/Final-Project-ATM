public class SavingAccount extends Account{


    @Override
    public boolean transfer(int from, int to, Currency currency) {
        return false;
    }
}
