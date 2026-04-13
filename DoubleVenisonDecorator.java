package ru.university.decorator;

/** Декоратор «двойная порция оленины» — надбавка +20 септимов к уже посчитанной цене. */
public class DoubleVenisonDecorator extends MealDecorator {

    private static final int EXTRA = 20;

    public DoubleVenisonDecorator(Meal wrapped) {
        super(wrapped);
    }

    @Override
    public int getPriceSeptims() {
        return super.getPriceSeptims() + EXTRA;
    }

    @Override
    public String describeOrderLine() {
        return super.describeOrderLine() + " +двойная порция оленины";
    }
}
