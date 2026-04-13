package ru.university.decorator;

/**
 * Общий контракт для блюда в нашем маленьком заказе (паттерн Decorator из курса про проектирование).
 * <p>
 * Конкретные классы: база {@link NordicStew} и декораторы-надбавки. Интерфейс с {@code default}-методами
 * — это как раз «дефолтный интерфейс» из Java 8+: общую мелочь можно написать один раз здесь.
 * </p>
 */
public interface Meal {

    /** Полная стоимость позиции в септимах. */
    int getPriceSeptims();

    /** Как писать заказ на свитке, например: «рагу +огненный соус +снежные ягоды». */
    String describeOrderLine();

    /** Короткая подпись для подсказок (база + цепочка добавок). */
    default String receiptHint() {
        return describeOrderLine() + " — " + getPriceSeptims() + " септимов";
    }
}
