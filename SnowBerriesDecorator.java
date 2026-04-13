package ru.university.decorator;

/** Декоратор «снежные ягоды» (+5 септимов) — кусочек текста и сумма добавляются поверх вложенного блюда. */
public class SnowBerriesDecorator extends MealDecorator {

    private static final int EXTRA = 5;

    public SnowBerriesDecorator(Meal wrapped) {
        super(wrapped);
    }

    @Override
    public int getPriceSeptims() {
        return super.getPriceSeptims() + EXTRA;
    }

    @Override
    public String describeOrderLine() {
        return super.describeOrderLine() + " +снежные ягоды";
    }
}
