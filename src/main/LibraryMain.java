package main;

import ui.MainFrame;
import javax.swing.SwingUtilities;

public class LibraryMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}
