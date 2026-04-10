package org.one.patientmanagement.ui.components;

import java.awt.*;
import javax.swing.*;

public class PatientsCompleted extends javax.swing.JPanel {

    public PatientsCompleted() {
        initComponents();
        this.setOpaque(false); // Allows us to draw rounded corners
        applyFigmaStyles();
    }

    private void applyFigmaStyles() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;

        // --- 1. "Patients Completed" (20px) ---
        // Based on Figma: Manrope, Medium, 20px, #504348
        CompletedPatients.setFont(new Font("Manrope", Font.PLAIN, 20)); 
        CompletedPatients.setForeground(new Color(80, 67, 72));
        gbc.gridy = 0;
        gbc.insets = new Insets(25, 30, 0, 0); 
        this.add(CompletedPatients, gbc);

        // --- 2. "numPatients" (Bold 36px) ---
        // Based on Figma: Manrope, Bold, Large Title, #21191C
        numPatients.setText("0"); // Initialize to 0 instead of "num of completed"
        numPatients.setFont(new Font("Manrope", Font.BOLD, 36)); 
        numPatients.setForeground(new Color(33, 25, 28));
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 30, 0, 0);
        this.add(numPatients, gbc);

        // --- 3. ProgressBar ---
        // Based on Figma Layout: Width 215, Height 8
        ProgressBar.setPreferredSize(new Dimension(215, 8));
        ProgressBar.setMaximumSize(new Dimension(215, 8));
        ProgressBar.setForeground(new Color(125, 82, 96)); // Matching the 'Proceed' button color
        ProgressBar.setBackground(new Color(220, 200, 205)); // Lighter shade for bar background
        gbc.gridy = 2;
        gbc.insets = new Insets(15, 30, 25, 30);
        this.add(ProgressBar, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Background color #FAEAEE
        g2.setColor(new Color(250, 234, 238));
        
        // Radius: 28px from Figma
        int arc = 28;
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
        
        g2.dispose();
        super.paintComponent(g);
    }

    // ==========================================================
    // DATA HOOKS FOR TEAMMATES (SQL CONNECTION)
    // ==========================================================
    
    /**
     * Updates both the label and the progress bar.
     * @param completed The number of completed patients
     * @param total The total number of patients today (to calculate % of bar)
     */
    public void updateCompletedData(Object completed, int total) {
        if (completed == null || String.valueOf(completed).equalsIgnoreCase("null")) {
            numPatients.setText("0");
            ProgressBar.setValue(0);
        } else {
            int compInt = Integer.parseInt(String.valueOf(completed));
            numPatients.setText(String.valueOf(compInt));
            
            // Calculate progress percentage
            if (total > 0) {
                int percentage = (int) (((double) compInt / total) * 100);
                ProgressBar.setValue(percentage);
            }
        }
        this.revalidate();
        this.repaint();
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CompletedPatients = new javax.swing.JLabel();
        numPatients = new javax.swing.JLabel();
        ProgressBar = new javax.swing.JProgressBar();

        setBackground(new java.awt.Color(250, 234, 238));
        setMaximumSize(new java.awt.Dimension(245, 140));
        setMinimumSize(new java.awt.Dimension(245, 140));
        setPreferredSize(new java.awt.Dimension(245, 140));

        CompletedPatients.setText("Patients Completed");

        numPatients.setText("num of completed");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numPatients)
                            .addComponent(CompletedPatients)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(144, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(CompletedPatients)
                .addGap(52, 52, 52)
                .addComponent(numPatients)
                .addGap(58, 58, 58)
                .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CompletedPatients;
    private javax.swing.JProgressBar ProgressBar;
    private javax.swing.JLabel numPatients;
    // End of variables declaration//GEN-END:variables
}
