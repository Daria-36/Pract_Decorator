package ru.university.decorator;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class RunicStewFrame extends JFrame implements ActionListener, ItemListener {
    private final OrderScroll scroll = new OrderScroll();
    private final List<ModifierBinding> bindings = new ArrayList<ModifierBinding>();

    private final JLabel currentSeptims = new JLabel(" ");
    private final JLabel totalGold = new JLabel(" ");

    public RunicStewFrame() {
        super("Руническая скрижаль — заказ нордского рагу");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        JCheckBox fire = new JCheckBox("Огненный соус (+10 септимов)");
        JCheckBox venison = new JCheckBox("Двойная порция оленины (+20 септимов)");
        JCheckBox berries = new JCheckBox("Снежные ягоды (+5 септимов)");
        JCheckBox bread = new JCheckBox("Нордская лепешка (+7 септимов)");

        bindings.add(new ModifierBinding(fire, ModifierBinding.FIRE_SAUCE));
        bindings.add(new ModifierBinding(venison, ModifierBinding.DOUBLE_VENISON));
        bindings.add(new ModifierBinding(berries, ModifierBinding.SNOW_BERRIES));
        bindings.add(new ModifierBinding(bread, ModifierBinding.NORDIC_FLATBREAD));

        for (ModifierBinding binding : bindings) {
            binding.notch().addItemListener(this);
        }

        JButton placeOrder = new JButton("Оформить заказ базового блюда (нордское рагу)");
        placeOrder.addActionListener(this);

        JLabel header = new JLabel(
                "Нордское рагу — 50 септимов. "
                        + "Отметь до трёх зарубок; остальные обратятся в камень."
        );

        JPanel notches = new JPanel(new GridLayout(0, 1, 4, 4));
        notches.setBorder(BorderFactory.createTitledBorder("Зарубки — модификаторы"));

        for (ModifierBinding binding : bindings) {
            notches.add(binding.notch());
        }

        JList<String> list = new JList<String>(scroll.listModel());
        list.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Свиток заказов (история)"));

        JPanel north = new JPanel(new GridLayout(0, 1, 6, 6));
        north.add(header);
        north.add(currentSeptims);
        north.add(totalGold);

        JPanel east = new JPanel(new BorderLayout(8, 8));
        east.add(notches, BorderLayout.NORTH);
        east.add(placeOrder, BorderLayout.SOUTH);

        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(new EmptyBorder(12, 12, 12, 12));
        root.add(north, BorderLayout.NORTH);
        root.add(scrollPane, BorderLayout.CENTER);
        root.add(east, BorderLayout.EAST);

        setContentPane(root);
        setPreferredSize(new Dimension(880, 420));
        pack();

        refreshAll();
    }

    private Meal composeMeal() {
        Meal dish = new NordicStew();

        for (ModifierBinding binding : bindings) {
            dish = binding.fold(dish);
        }

        return dish;
    }

    private void applyStoneCurseToNotches() {
        int selected = 0;

        for (ModifierBinding binding : bindings) {
            if (binding.notch().isSelected()) {
                selected = selected + 1;
            }
        }

        for (ModifierBinding binding : bindings) {
            JCheckBox notch = binding.notch();

            boolean alive = selected < 3 || notch.isSelected();
            notch.setEnabled(alive);

            if (alive) {
                notch.setToolTipText("Живая зарубка — можно менять выбор");
            } else {
                notch.setToolTipText("Камень: уже три добавки, сперва сними одну зарубку");
            }
        }
    }

    private void refreshAll() {
        applyStoneCurseToNotches();

        Meal dish = composeMeal();

        currentSeptims.setText(
                "Текущий заказ: "
                        + dish.getPriceSeptims()
                        + " септимов (50 база + модификаторы)"
        );

        totalGold.setText(
                "Счёт золота по свитку: "
                        + scroll.totalSeptimsSpent()
                        + " септимов"
        );
    }

    private void submitOrder() {
        Meal dish = composeMeal();
        scroll.record(dish);

        for (ModifierBinding binding : bindings) {
            binding.notch().setSelected(false);
        }

        refreshAll();
    }
}
