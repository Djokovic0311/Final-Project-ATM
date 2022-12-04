public enum CurrencyType {
    USD(1F),
    EUR(0.82780F),
    GBP(0.71954F),
    CNY(0.14234F),
    HKD(0.11526F);

    private final float value;

    CurrencyType(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}
