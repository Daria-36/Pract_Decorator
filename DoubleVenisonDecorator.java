package ru.university.decorator;

public class DoubleVenisonDecorator extends MealDecorator {

    private static final int EXTRA = 20;

    public DoubleVenisonDecorator(Meal wrapped) {
        super(wrapped);
    }

    public int getPriceSeptims() {
        return super.getPriceSeptims() + EXTRA;
    }

    public String describeOrderLine() {
        return super.describeOrderLine() + " +двойная порция оленины";
    }
}
