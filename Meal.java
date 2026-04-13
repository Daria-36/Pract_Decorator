package ru.university.decorator;

public interface Meal {

    int getPriceSeptims();
    String describeOrderLine();

    default String receiptHint() {
        return describeOrderLine() + " — " + getPriceSeptims() + " септимов";
    }
}
