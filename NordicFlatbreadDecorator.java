package ru.university.decorator;

/** +7 септ. */
public class NordicFlatbreadDecorator extends MealDecorator {

    private static final int EXTRA = 7;

    public NordicFlatbreadDecorator(Meal wrapped) {
        super(wrapped);
    }

    public int getPriceSeptims() {
        return super.getPriceSeptims() + EXTRA;
    }

    public String describeOrderLine() {
        return super.describeOrderLine() + " +нордская лепешка";
    }
}
