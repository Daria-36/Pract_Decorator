package ru.university.decorator;

import javax.swing.*;
import java.util.Map;
import java.util.function.UnaryOperator;

/**
 * Одна строка настройки: чекбокс в интерфейсе + «рецепт», как обернуть {@link Meal} декоратором.
 * <p>
 * Через {@link Map} выбираем либо {@link UnaryOperator#identity()} (зарубка не стоит),
 * либо конкретный декоратор — без {@code if} и {@code switch}, как просили в задании.
 * </p>
 */
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

    /**
     * Если зарубка отмечена — накладываем декоратор, иначе возвращаем то же яство.
     */
    public Meal fold(Meal base) {
        UnaryOperator<Meal> identity = UnaryOperator.identity();
        Map<Boolean, UnaryOperator<Meal>> branch = Map.of(
                true, decorateSelected,
                false, identity
        );
        return branch.get(notch.isSelected()).apply(base);
    }
}
