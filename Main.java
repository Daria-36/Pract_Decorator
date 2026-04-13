package ru.university.decorator;

import javax.swing.*;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {

            }
            RunicStewFrame frame = new RunicStewFrame();
            frame.setVisible(true);
        });
    }
}
