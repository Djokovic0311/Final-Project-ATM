package model;

public enum CurrencyType {
    USD(1F),
    EUR(0.82780F),
    CNY(0.14234F),

    private final float value;

    CurrencyType(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}
