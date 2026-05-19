# Pract Decorator — нордское рагу

Исправленный учебный проект по паттерну **Decorator**.

## Что делает программа

Swing-приложение позволяет оформить заказ базового блюда **«нордское рагу»** стоимостью 50 септимов и добавить к нему до трёх модификаторов:

- огненный соус: +10 септимов;
- двойная порция оленины: +20 септимов;
- снежные ягоды: +5 септимов;
- нордская лепёшка: +7 септимов.

Если пользователь выбрал три модификатора, остальные чекбоксы блокируются. Уже выбранные чекбоксы остаются активными, чтобы пользователь мог снять один модификатор и выбрать другой.

История заказов отображает:

- время заказа;
- полное название заказа;
- итоговую цену.

Также приложение показывает общий счёт золота по всем оформленным заказам.

## Как реализован Decorator

- `Meal` — компонент, общий интерфейс для базового блюда и декораторов.
- `NordicStew` — конкретный компонент, базовое блюдо.
- `MealDecorator` — базовый декоратор, хранит ссылку на `Meal` и делегирует операции.
- `FireSauceDecorator`, `DoubleVenisonDecorator`, `SnowBerriesDecorator`, `NordicFlatbreadDecorator` — конкретные декораторы, которые добавляют цену и текст к описанию заказа.
- `RunicStewFrame` — клиентский код, который собирает цепочку декораторов на основе выбранных чекбоксов.

Пример цепочки:

```java
Meal meal = new NordicStew();
meal = new FireSauceDecorator(meal);
meal = new SnowBerriesDecorator(meal);
```

Результат:

```text
нордское рагу + огненный соус + снежные ягоды — 65 септимов
```

## Что исправлено

1. Исправлен `Main`: запуск Swing-интерфейса перенесён в `SwingUtilities.invokeLater(...)`.
2. Исправлен незакрытый Javadoc-комментарий в `RunicStewFrame`.
3. Исправлены raw types:
   - `List<ModifierBinding>` вместо сырого `List`;
   - `UnaryOperator<Meal>` вместо сырого `UnaryOperator`;
   - `DefaultListModel<String>` / `JList<String>` вместо raw-моделей.
4. Добавлены корректные модификаторы доступа:
   - публичными оставлены только классы и методы, которые должны быть доступны извне;
   - поля сделаны `private final`;
   - константы сделаны `private static final` или `public static final`, если являются частью публичного API;
   - классы без дальнейшего наследования сделаны `final`;
   - конструктор базового декоратора сделан `protected`.
5. Улучшена проверка ограничения «не более трёх модификаторов».
6. Программа разложена в стандартную структуру проекта `src/main/java/...`.

## Как запустить без Maven

Из корня проекта:

```bash
javac -encoding UTF-8 -d out src/main/java/ru/university/decorator/*.java
java -cp out ru.university.decorator.Main
```

В Windows PowerShell:

```powershell
javac -encoding UTF-8 -d out src/main/java/ru/university/decorator/*.java
java -cp out ru.university.decorator.Main
```

## Как запустить через Maven

```bash
mvn clean compile exec:java
```

