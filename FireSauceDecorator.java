package ru.university.decorator;

/**
 * Конкретный декоратор «огненный соус»: +10 септимов и хвост {@code +огненный соус} в строке заказа
 * (пример из задания: «рагу +огненный соус +…»).
 */
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
