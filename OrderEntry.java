package ru.university.decorator;

/**
 * Запись для «свитка заказов»: время, полное имя (как вернул {@link Meal#describeOrderLine()}) и цена в септимах.
 */
public final class OrderEntry {

    private final String timeRune;
    private final String fullOrderName;
    private final int priceSeptims;

    public OrderEntry(String timeRune, String fullOrderName, int priceSeptims) {
        this.timeRune = timeRune;
        this.fullOrderName = fullOrderName;
        this.priceSeptims = priceSeptims;
    }

    public String timeRune() {
        return timeRune;
    }

    public String fullOrderName() {
        return fullOrderName;
    }

    public int priceSeptims() {
        return priceSeptims;
    }

    public String toScrollLine() {
        return timeRune + "  |  " + fullOrderName + "  |  " + priceSeptims + " септимов";
    }
}
