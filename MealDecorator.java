package ru.university.decorator;

import java.util.Objects;

public abstract class MealDecorator implements Meal {

    protected final Meal wrapped;

    protected MealDecorator(Meal wrapped) {
        this.wrapped = Objects.requireNonNull(wrapped, "Внутри декоратора не должно быть пустой ссылки на яство");
    }

    public int getPriceSeptims() {
        return wrapped.getPriceSeptims();
    }

    public String describeOrderLine() {
        return wrapped.describeOrderLine();
    }
}
