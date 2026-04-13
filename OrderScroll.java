package ru.university.decorator;

import javax.swing.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public final class OrderScroll {

    private static final DateTimeFormatter TIME_RUNE = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final List<OrderEntry> entries = new ArrayList<>();
    private final DefaultListModel<String> listModel = new DefaultListModel<>();

    public DefaultListModel<String> listModel() {
        return listModel;
    }

    /** Записываем заказ с временем*/
    public void record(Meal meal) {
        String time = LocalTime.now().format(TIME_RUNE);
        OrderEntry entry = new OrderEntry(time, meal.describeOrderLine(), meal.getPriceSeptims());
        entries.add(entry);
        listModel.addElement(entry.toScrollLine());
    }

    public int totalSeptimsSpent() {
        return entries.stream().mapToInt(OrderEntry::priceSeptims).sum();
    }
}
