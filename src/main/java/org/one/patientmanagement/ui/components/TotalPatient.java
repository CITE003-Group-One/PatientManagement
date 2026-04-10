package org.one.patientmanagement.ui.components;

import java.awt.*;
import javax.swing.*;

public class TotalPatient extends javax.swing.JPanel {

    public TotalPatient() {
        initComponents();
        this.setOpaque(false); 
        applyFigmaStyles();
    }

    private void applyFigmaStyles() {
    this.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.weightx = 1.0;
    gbc.anchor = GridBagConstraints.WEST;

    // 1. FORCE THE INITIAL VALUE TO 0
    // This removes the "number" text from the NetBeans designer immediately
    PatientsNum.setText("0"); 

    // --- Labels Styling ---
    Patients.setFont(new Font("Manrope", Font.PLAIN, 20)); 
    Patients.setForeground(new Color(80, 67, 72));
    gbc.gridy = 0;
    gbc.insets = new Insets(25, 30, 0, 0);
    this.add(Patients, gbc);

    PatientsNum.setFont(new Font("Manrope", Font.BOLD, 36)); 
    PatientsNum.setForeground(new Color(33, 25, 28));
    gbc.gridy = 1;
    gbc.insets = new Insets(5, 30, 0, 0);
    this.add(PatientsNum, gbc);

    Sched.setFont(new Font("Manrope", Font.PLAIN, 16));
    Sched.setForeground(new Color(80, 67, 72));
    gbc.gridy = 2;
    gbc.insets = new Insets(10, 30, 25, 0);
    this.add(Sched, gbc);
}

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // FILL THE PANEL WITH #FAEAEE
        g2.setColor(new Color(250, 234, 238));
        int arc = 28;
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
        
        g2.dispose();
        super.paintComponent(g);
    }
    
    public void updatePatientCount(Object totalFromSQL) {
        if (totalFromSQL == null) {
            PatientsNum.setText("0");
        } else {
            // Converts whatever the DB sends (Integer, String, etc.) to a String
            String countValue = String.valueOf(totalFromSQL);
            
            // Extra safety: check if the string itself is empty or says "null"
            if (countValue.trim().isEmpty() || countValue.equalsIgnoreCase("null")) {
                PatientsNum.setText("0");
            } else {
                PatientsNum.setText(countValue);
            }
        }
        
        // Refresh the UI to show the change immediately
        this.revalidate();
        this.repaint();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Patients = new javax.swing.JLabel();
        PatientsNum = new javax.swing.JLabel();
        Sched = new javax.swing.JLabel();

        setBackground(new java.awt.Color(250, 234, 238));
        setMaximumSize(new java.awt.Dimension(210, 140));
        setMinimumSize(new java.awt.Dimension(210, 140));
        setPreferredSize(new java.awt.Dimension(210, 140));

        Patients.setText("Total Patients");

        PatientsNum.setText("number");

        Sched.setText("Scheduled Today");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Sched)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(138, 138, 138)
                            .addComponent(Patients))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(148, 148, 148)
                            .addComponent(PatientsNum))))
                .addContainerGap(190, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(Patients)
                .addGap(44, 44, 44)
                .addComponent(PatientsNum)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(Sched)
                .addGap(65, 65, 65))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Patients;
    private javax.swing.JLabel PatientsNum;
    private javax.swing.JLabel Sched;
    // End of variables declaration//GEN-END:variables
}
