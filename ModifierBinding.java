package ru.university.decorator;

import javax.swing.JCheckBox;

public final class ModifierBinding {
    public static final int FIRE_SAUCE = 1;
    public static final int DOUBLE_VENISON = 2;
    public static final int SNOW_BERRIES = 3;
    public static final int NORDIC_FLATBREAD = 4;

    private final JCheckBox notch;
    private final int modifierType;

    public ModifierBinding(JCheckBox notch, int modifierType) {
        this.notch = notch;
        this.modifierType = modifierType;
    }

    public JCheckBox notch() {
        return notch;
    }

    public Meal fold(Meal base) {
        if (notch.isSelected()) {
            switch (modifierType) {
                case FIRE_SAUCE:
                    return new FireSauceDecorator(base);
                case DOUBLE_VENISON:
                    return new DoubleVenisonDecorator(base);
                case SNOW_BERRIES:
                    return new SnowBerriesDecorator(base);
                case NORDIC_FLATBREAD:
                    return new NordicFlatbreadDecorator(base);
                default:
                    return base;
            }
        }

        return base;
    }
}
