package ru.university.decorator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.Map;

/** «руническая скрежаль»
public class RunicStewFrame extends JFrame {

    private final OrderScroll scroll = new OrderScroll();
    private final List<ModifierBinding> bindings;
    private final JLabel currentSeptims = new JLabel(" ");
    private final JLabel totalGold = new JLabel(" ");

    public RunicStewFrame() {
        super("Руническая скрежаль — заказ нордского рагу");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        JCheckBox fire = new JCheckBox("Огненный соус (+10 септимов)");
        JCheckBox venison = new JCheckBox("Двойная порция оленины (+20 септимов)");
        JCheckBox berries = new JCheckBox("Снежные ягоды (+5 септимов)");
        JCheckBox bread = new JCheckBox("Нордская лепешка (+7 септимов)");

        bindings = List.of(
                new ModifierBinding(fire, FireSauceDecorator::new),
                new ModifierBinding(venison, DoubleVenisonDecorator::new),
                new ModifierBinding(berries, SnowBerriesDecorator::new),
                new ModifierBinding(bread, NordicFlatbreadDecorator::new)
        );

        bindings.forEach(b -> b.notch().addItemListener(ev -> refreshAll()));

        JButton placeOrder = new JButton("Оформить заказ базового блюда (нордское рагу)");
        placeOrder.addActionListener(ev -> submitOrder());

        JLabel header = new JLabel("<html><b>Нордское рагу</b> — 50 септимов. "
                + "Отметь до трёх зарубок; остальные обратятся в камень.</html>");

        JPanel notches = new JPanel(new GridLayout(0, 1, 4, 4));
        notches.setBorder(BorderFactory.createTitledBorder("Зарубки — модификаторы"));
        bindings.stream().map(ModifierBinding::notch).forEach(notches::add);

        JList<String> list = new JList<>(scroll.listModel());
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
        return bindings.stream()
                .sequential()
                .reduce(
                        (Meal) new NordicStew(),
                        (Meal dish, ModifierBinding binding) -> binding.fold(dish),
                        (left, right) -> right
                );
    }

    private void applyStoneCurseToNotches() {
        long selected = bindings.stream()
                .mapToLong(b -> Map.of(true, 1L, false, 0L).get(b.notch().isSelected()))
                .sum();
        bindings.forEach(b -> {
            JCheckBox n = b.notch();
            boolean alive = (selected < 3) | n.isSelected();
            n.setEnabled(alive);
            Map<Boolean, String> tips = Map.of(
                    true, "Живая зарубка — можно менять выбор",
                    false, "Камень: уже три добавки, сперва сними одну зарубку"
            );
            n.setToolTipText(tips.get(alive));
        });
    }

    private void refreshAll() {
        applyStoneCurseToNotches();
        Meal dish = composeMeal();
        currentSeptims.setText("Текущий заказ: " + dish.getPriceSeptims() + " септимов (50 база + модификаторы)");
        totalGold.setText("Счёт золота по свитку: " + scroll.totalSeptimsSpent() + " септимов");
    }

    private void submitOrder() {
        Meal dish = composeMeal();
        scroll.record(dish);
        bindings.forEach(b -> b.notch().setSelected(false));
        refreshAll();
    }
}
