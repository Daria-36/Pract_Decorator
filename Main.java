package ru.university.decorator;

import javax.swing.*;

/**
 * Точка входа: весь GUI создаём в потоке событий Swing ({@link SwingUtilities#invokeLater}),
 * иначе интерфейс может глючить. Look-and-feel системы — чтобы окно выглядело привычно под Windows.
 */
public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
                // оставляем то, что выдал JVM
            }
            RunicStewFrame frame = new RunicStewFrame();
            frame.setVisible(true);
        });
    }
}
