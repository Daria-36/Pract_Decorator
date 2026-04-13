package ru.university.decorator;

public class FireSauceDecorator extends MealDecorator {

    private static final int EXTRA = 10;

    public FireSauceDecorator(Meal wrapped) {
        super(wrapped);
    }

    public int getPriceSeptims() {
        return super.getPriceSeptims() + EXTRA;
    }

    public String describeOrderLine() {
        return super.describeOrderLine() + " +огненный соус";
    }
}
