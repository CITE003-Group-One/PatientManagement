/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ProfilePicturePanel extends JPanel {

    private Image image;

    public ProfilePicturePanel() {
        setPreferredSize(new Dimension(120, 120)); // default size
        
        this.image = new ImageIcon(getClass().getResource("/images/afternoon icon.png")).getImage();
    }

    public void setImage(Image image) {
        this.image = image;
        repaint();
    }

    public void setImage(String path) {
        this.image = new ImageIcon(path).getImage();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image == null) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();

        // Enable smooth rendering
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create circular clip
        Shape circle = new java.awt.geom.Ellipse2D.Double(0, 0, width, height);
        g2.setClip(circle);

        int imgW = image.getWidth(this);
        int imgH = image.getHeight(this);

        double scale = Math.min(
                (double) width / imgW,
                (double) height / imgH
        );

        int newW = (int) (imgW * scale);
        int newH = (int) (imgH * scale);

        int x = (width - newW) / 2;
        int y = (height - newH) / 2;

        // Draw image (cover behavior)
        g2.drawImage(image, x, y, newW, newH, this);

        g2.dispose();
    }
}
