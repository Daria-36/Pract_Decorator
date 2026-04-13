package ru.university.decorator;

public class NordicStew implements Meal {

    public static final int BASE_PRICE_SEPTIMS = 50;

    public int getPriceSeptims() {
        return BASE_PRICE_SEPTIMS;
    }

    public String describeOrderLine() {
        return "рагу";
    }
}
