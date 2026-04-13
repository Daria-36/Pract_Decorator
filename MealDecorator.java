package ru.university.decorator;

import java.util.Objects;

/**
 * Абстрактный декоратор из учебника GoF: внутри храним другое {@link Meal} и делегируем ему вызовы,
 * а в наследниках добавляем свою надбавку к цене и куски текста в название заказа.
 */
public abstract class MealDecorator implements Meal {

    protected final Meal wrapped;

    protected MealDecorator(Meal wrapped) {
        this.wrapped = Objects.requireNonNull(wrapped, "Внутри декоратора не должно быть пустой ссылки на яство");
    }

    @Override
    public int getPriceSeptims() {
        return wrapped.getPriceSeptims();
    }

    @Override
    public String describeOrderLine() {
        return wrapped.describeOrderLine();
    }
}
