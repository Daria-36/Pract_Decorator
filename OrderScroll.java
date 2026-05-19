package ru.university.decorator;

import javax.swing.DefaultListModel;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public final class OrderScroll {
    private static final DateTimeFormatter TIME_RUNE = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final List<OrderEntry> entries = new ArrayList<OrderEntry>();
    private final DefaultListModel<String> listModel = new DefaultListModel<String>();

    public DefaultListModel<String> listModel() {
        return listModel;
    }

    public void record(Meal meal) {
        String time = LocalTime.now().format(TIME_RUNE);
        OrderEntry entry = new OrderEntry(time, meal.describeOrderLine(), meal.getPriceSeptims());

        entries.add(entry);
        listModel.addElement(entry.toScrollLine());
    }

    public int totalSeptimsSpent() {
        int total = 0;

        for (OrderEntry entry : entries) {
            total = total + entry.priceSeptims();
        }
        return total;
    }
}
