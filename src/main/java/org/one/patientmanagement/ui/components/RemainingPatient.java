package org.one.patientmanagement.ui.components;

import java.awt.*;
import javax.swing.*;

public class RemainingPatient extends javax.swing.JPanel {

    public RemainingPatient() {
        initComponents();
        this.setOpaque(false); // Important for rounded corners
        applyFigmaStyles();
    }

    private void applyFigmaStyles() {
        // Use GridBagLayout for vertical stacking and left alignment
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;

        // --- 1. "Remaining Patients" Label ---
        // Figma: Manrope, Medium (500), 20px, Color #504348
        RemainingPatients.setFont(new Font("Manrope", Font.PLAIN, 20)); 
        RemainingPatients.setForeground(new Color(80, 67, 72));
        gbc.gridy = 0;
        gbc.insets = new Insets(32, 39, 0, 0); // Figma Top/Left padding
        this.add(RemainingPatients, gbc);

        // --- 2. "numRemaining" (SQL Target) ---
        // Figma: Manrope, Bold (700), Large Title, Color #21191C
        numRemaining.setText("0"); // Clear "numRemaining" placeholder
        numRemaining.setFont(new Font("Manrope", Font.BOLD, 36)); 
        numRemaining.setForeground(new Color(33, 25, 28));
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 39, 0, 0);
        this.add(numRemaining, gbc);

        // --- 3. "pending" Label ---
        // Figma: Manrope, Regular (400), 16px, Color #504348
        pending.setFont(new Font("Manrope", Font.PLAIN, 16));
        pending.setForeground(new Color(80, 67, 72));
        gbc.gridy = 2;
        gbc.insets = new Insets(15, 39, 32, 0);
        this.add(pending, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Background color #FAEAEE
        g2.setColor(new Color(250, 234, 238));
        
        // 28px Radius to match the other cards
        int arc = 28;
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
        
        g2.dispose();
        super.paintComponent(g);
    }

    // ==========================================================
    // DATA HOOK FOR TEAMMATES (SQL CONNECTION)
    // ==========================================================
    
    /**
     * Updates the remaining patient count. 
     * Defaults to 0 if the input is null.
     */
    public void updateRemainingCount(Object totalFromSQL) {
        if (totalFromSQL == null || String.valueOf(totalFromSQL).equalsIgnoreCase("null")) {
            numRemaining.setText("0");
        } else {
            numRemaining.setText(String.valueOf(totalFromSQL));
        }
        this.revalidate();
        this.repaint();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RemainingPatients = new javax.swing.JLabel();
        numRemaining = new javax.swing.JLabel();
        pending = new javax.swing.JLabel();

        setBackground(new java.awt.Color(250, 234, 238));
        setMaximumSize(new java.awt.Dimension(240, 140));
        setMinimumSize(new java.awt.Dimension(240, 140));
        setPreferredSize(new java.awt.Dimension(240, 140));

        RemainingPatients.setText("Remaining Patients");

        numRemaining.setText("numRemaining");

        pending.setText("Pending, waiting patients");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(RemainingPatients)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(137, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(numRemaining)
                        .addGap(156, 156, 156))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(pending)
                        .addGap(129, 129, 129))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(RemainingPatients)
                .addGap(81, 81, 81)
                .addComponent(numRemaining)
                .addGap(65, 65, 65)
                .addComponent(pending)
                .addContainerGap(81, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel RemainingPatients;
    private javax.swing.JLabel numRemaining;
    private javax.swing.JLabel pending;
    // End of variables declaration//GEN-END:variables
}
