package ru.university.decorator;

public class SnowBerriesDecorator extends MealDecorator {

    private static int EXTRA = 5;

    public SnowBerriesDecorator(Meal wrapped) {
        super(wrapped);
    }
    public int getPriceSeptims() {
        return super.getPriceSeptims() + EXTRA;
    }

    public String describeOrderLine() {
        return super.describeOrderLine() + " +снежные ягоды";
    }
}
