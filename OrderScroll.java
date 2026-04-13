package ru.university.decorator;

import javax.swing.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Модель «свитка» из задания: список прошлых заказов + суммарный «счёт золота» по всем записям.
 */
public final class OrderScroll {

    /** Время в свитке: часы, минуты, секунды (формат чч:мм:сс). */
    private static final DateTimeFormatter TIME_RUNE = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final List<OrderEntry> entries = new ArrayList<>();
    private final DefaultListModel<String> listModel = new DefaultListModel<>();

    public DefaultListModel<String> listModel() {
        return listModel;
    }

    /** Записываем заказ с текущей меткой времени в формате чч:мм:сс. */
    public void record(Meal meal) {
        String time = LocalTime.now().format(TIME_RUNE);
        OrderEntry entry = new OrderEntry(time, meal.describeOrderLine(), meal.getPriceSeptims());
        entries.add(entry);
        listModel.addElement(entry.toScrollLine());
    }

    /** Сколько всего септимов ушло по всем записанным заказам (счёт золота). */
    public int totalSeptimsSpent() {
        return entries.stream().mapToInt(OrderEntry::priceSeptims).sum();
    }
}
