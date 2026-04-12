/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.components;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    private Image image;

    public ImagePanel() {
        this.image = new ImageIcon(getClass().getResource("/images/morning.png")).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image == null) return;

        int panelW = getWidth();
        int panelH = getHeight();

        int imgW = image.getWidth(this);
        int imgH = image.getHeight(this);

        double scale = Math.min(
                (double) panelW / imgW,
                (double) panelH / imgH
        );

        int newW = (int) (imgW * scale);
        int newH = (int) (imgH * scale);

        int x = (panelW - newW) / 2;
        int y = (panelH - newH) / 2;

        g.drawImage(image, x, y, newW, newH, this);
    }
}