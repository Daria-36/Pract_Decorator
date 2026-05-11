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
    
    public Meal fold(Meal base) {
        if (notch.isSelected()) {

            return decorateSelected.apply(base);
        } else {
            return base;
        }
    }
}
