/*
    This is the class for Currency
    @author: Jiahang Li
    @version: 1.0
*/

public class Currency {
    private CurrencyType currencyType;
    protected double exchangeRate;

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public float getExchangeRate() {
        return currencyType.getValue();
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
//
//enum CurrencyType {
//    // currency types supported
//    USD,
//    GBP,
//    EUR,
//    CNY,
//    HKD
//}

