package ru.university.decorator;

/** Декоратор «нордская лепешка» (+7 септимов). */
public class NordicFlatbreadDecorator extends MealDecorator {

    private static final int EXTRA = 7;

    public NordicFlatbreadDecorator(Meal wrapped) {
        super(wrapped);
    }

    @Override
    public int getPriceSeptims() {
        return super.getPriceSeptims() + EXTRA;
    }

    @Override
    public String describeOrderLine() {
        return super.describeOrderLine() + " +нордская лепешка";
    }
}
