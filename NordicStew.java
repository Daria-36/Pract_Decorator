package ru.university.decorator;

/**
 * Компонент Decorator без обёрток — просто «нордское рагу» за 50 септимов, как в условии.
 * От него строится цепочка: каждый декоратор кладёт внутрь предыдущий {@link Meal}.
 */
public class NordicStew implements Meal {

    public static final int BASE_PRICE_SEPTIMS = 50;

    @Override
    public int getPriceSeptims() {
        return BASE_PRICE_SEPTIMS;
    }

    @Override
    public String describeOrderLine() {
        return "рагу";
    }
}
