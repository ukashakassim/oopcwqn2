package com.vu.exhibition;

import ExhibitionFrame.ExhibitionFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
    public static void main(String[] args) {
        try {
            // Set look and feel to system default
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }
        
        SwingUtilities.invokeLater(() -> {
            ExhibitionFrame frame = new ExhibitionFrame();
            frame.setVisible(true);
        });
    }
}