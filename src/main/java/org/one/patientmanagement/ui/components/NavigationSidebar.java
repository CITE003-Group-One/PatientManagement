package org.one.patientmanagement.ui.components;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

public class NavigationSidebar extends javax.swing.JPanel {

    private JButton selectedButton;
    private final Color activeColor = new Color(255, 220, 199); // #FFDBC7
    private final Color textColor = new Color(80, 67, 72);      // #504348
    private final Color sidebarBg = new Color(244, 228, 232);   // #F4E4E8

    public NavigationSidebar() {
        initComponents();
        applyFigmaStyles();
    }

    private void applyFigmaStyles() {
        // Updated Sidebar Dimensions for the 1200x800 Frame
        Dimension size = new Dimension(250, 800); 
        this.setPreferredSize(size);
        this.setMinimumSize(size);
        this.setMaximumSize(size);
        setBackground(sidebarBg);
        setLayout(null);

        // Updated Button Measurements for the 270px width
        // Scaled width to 210px to ensure a 30px margin on each side
        Dimension btnSize = new Dimension(210, 53);
        int x = 30;   // Left margin (X) from your updated specs
        int y = 138;  // Starting vertical position
        int gap = 15; // Vertical spacing between buttons

        // Apply formatting to each button
        formatButton(jButton1, "Dashboard", x, y, btnSize);
        formatButton(jButton2, "Patient Queue", x, y + (53 + gap), btnSize);
        formatButton(jButton3, "Patient Records", x, y + 2 * (53 + gap), btnSize);

        // Set default selection
        setSelected(jButton1);
    }

    private void formatButton(JButton btn, String text, int x, int y, Dimension size) {
        btn.setText(text);
        btn.setBounds(x, y, size.width, size.height);
        btn.setFont(new Font("Poppins", Font.BOLD, 20)); 
        btn.setForeground(textColor);
        
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setMargin(new Insets(0, 20, 0, 0)); 
        
        btn.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (c == selectedButton) {
                    g2.setColor(activeColor);
                    g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 10, 10);
                }
                g2.dispose();
                super.paint(g, c);
            }
        });

        btn.addActionListener(e -> setSelected(btn));
    }

    private void setSelected(JButton btn) {
        selectedButton = btn;
        this.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setBackground(new java.awt.Color(244, 228, 232));
        setMinimumSize(new java.awt.Dimension(250, 800));
        setPreferredSize(new java.awt.Dimension(250, 800));
        setLayout(null);

        jButton1.setText("jButton1");
        jButton1.addActionListener(this::jButton1ActionPerformed);
        add(jButton1);
        jButton1.setBounds(148, 164, 75, 23);

        jButton2.setText("jButton2");
        add(jButton2);
        jButton2.setBounds(148, 268, 75, 23);

        jButton3.setText("jButton3");
        add(jButton3);
        jButton3.setBounds(148, 364, 75, 23);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    // End of variables declaration//GEN-END:variables
}
