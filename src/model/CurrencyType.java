package model;

public enum CurrencyType {
    USD(1F),
    EUR(0.82780F),
    CNY(0.14234F);

    private final float value;

    CurrencyType(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public static CurrencyType getTypeFromString(String s) {
        switch (s) {
            case "USD" -> { return USD; }
            case "EUR" -> { return EUR; }
            case "CNY" -> { return CNY; }
        }
        return null;
    }

}
