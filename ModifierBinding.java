package ru.university.decorator;

import javax.swing.*;
import java.util.Map;
import java.util.function.UnaryOperator;

public final class ModifierBinding {

    private final JCheckBox notch;
    private final UnaryOperator<Meal> decorateSelected;

    public ModifierBinding(JCheckBox notch, UnaryOperator<Meal> decorateSelected) {
        this.notch = notch;
        this.decorateSelected = decorateSelected;
    }

    public JCheckBox notch() {
        return notch;
    }

    /** Если зарубка отмечена — декоратор, иначе возвращаем то же самое.*/
    public Meal fold(Meal base) {
        UnaryOperator<Meal> identity = UnaryOperator.identity();
        Map<Boolean, UnaryOperator<Meal>> branch = Map.of(
                true, decorateSelected,
                false, identity
        );
        return branch.get(notch.isSelected()).apply(base);
    }
}
