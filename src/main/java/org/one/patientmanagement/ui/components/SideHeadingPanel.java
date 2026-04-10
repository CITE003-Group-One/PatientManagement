package org.one.patientmanagement.ui.components;

import java.awt.*;
import javax.swing.*;

public class SideHeadingPanel extends JPanel {

    public SideHeadingPanel() {
        initComponents();
        applyFigmaStyles();
    }

    private void applyFigmaStyles() {
        // 1. Panel Setup
        Dimension size = new Dimension(450, 800);
        this.setPreferredSize(size);
        this.setBackground(new Color(255, 241, 235));
        this.setLayout(new GridBagLayout()); // Using GridBag for perfect centering

        // 2. Clear old GridBag constraints from initComponents
        this.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();

        // 3. Setup "Doctor Panel" Chip (jLabel1)
        jLabel1.setOpaque(false);
        jLabel1.setPreferredSize(new Dimension(323, 56));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setForeground(new Color(133, 76, 100)); 
        jLabel1.setFont(new Font("Poppins", Font.PLAIN, 31)); 
        jLabel1.setText("Doctor Panel");

        jLabel1.setUI(new javax.swing.plaf.basic.BasicLabelUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(253, 217, 231)); 
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 56, 56); 
                g2.dispose();
                super.paint(g, c);
            }
        });

        // 4. Setup "Welcome Message" (jLabel2)
        jLabel2.setFont(new Font("Poppins Medium", Font.PLAIN, 49));
        jLabel2.setForeground(new Color(89, 64, 74)); 
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2.setText("<html><center>Welcome Back, <br>Doctor</center></html>");

        // 5. Add to layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0); // Space between chip and text
        this.add(jLabel1, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        this.add(jLabel2, gbc);
    }
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 240, 230));
        setPreferredSize(new java.awt.Dimension(450, 800));
        setLayout(new java.awt.GridBagLayout());

        jLabel1.setBackground(new java.awt.Color(255, 204, 204));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 31)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("<html><div style='padding: 10px;'>Doctor Panel</div></html>");
        jLabel1.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 83;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(297, 123, 0, 0);
        add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 49)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("<html><center>Welcome Back, <br>Doctor</center></html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 124;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 61, 303, 61);
        add(jLabel2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
