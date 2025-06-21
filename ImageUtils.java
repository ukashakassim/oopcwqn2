package com.vu.exhibition.view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageUtils {
    public static void displayImage(String path, JLabel label) {
        if (path != null && !path.isEmpty()) {
            try {
                BufferedImage img = ImageIO.read(new File(path));
                Image scaledImage = img.getScaledInstance(
                    label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(scaledImage));
            } catch (IOException ex) {
                System.err.println("Error loading image: " + ex.getMessage());
            }
        } else {
            label.setIcon(null);
        }
    }
}