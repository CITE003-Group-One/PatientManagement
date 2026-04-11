package org.one.patientmanagement.ui.components;

import java.awt.*;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class TopBar extends javax.swing.JPanel {

    public TopBar() {
        initComponents(); 
        // We call our custom fix AFTER initComponents so it overwrites the NetBeans layout
        applyFigmaStyles(); 
        updateDate();       
        setGreeting("Dr. Ariah Mercado"); 
    }

    private void applyFigmaStyles() {

    greetings.setForeground(new Color(89, 64, 74)); 
    greetings.setFont(new Font("Poppins", Font.BOLD, 16)); 

    dateLabel.setForeground(new Color(80, 67, 72)); 
    dateLabel.setFont(new Font("Poppins", Font.PLAIN, 14)); 
    
    // 6. Refresh UI
    this.revalidate();
    this.repaint();
}

    private void setGreeting(String doctorName) {
        int hour = LocalTime.now().getHour();
        String timeGreeting = (hour >= 5 && hour < 12) ? "Good Morning, " : 
                            (hour >= 12 && hour < 17) ? "Good Afternoon, " : "Good Evening, ";
        greetings.setText(timeGreeting + doctorName);
    }

    private void updateDate() {
        ZonedDateTime now = ZonedDateTime.now();
        dateLabel.setText(now.format(DateTimeFormatter.ofPattern("EEE, MMM dd yyyy")));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        dateLabel = new javax.swing.JLabel();
        greetings = new javax.swing.JLabel();

        setBackground(new java.awt.Color(250, 234, 238));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 15));
        setMaximumSize(new java.awt.Dimension(9999, 60));
        setMinimumSize(new java.awt.Dimension(950, 60));
        setPreferredSize(new java.awt.Dimension(950, 60));
        setLayout(new java.awt.GridBagLayout());

        dateLabel.setFont(new java.awt.Font("Manrope Medium", 0, 16)); // NOI18N
        dateLabel.setText("dateLabel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        add(dateLabel, gridBagConstraints);

        greetings.setFont(new java.awt.Font("Manrope SemiBold", 0, 16)); // NOI18N
        greetings.setText("Good Morning");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        add(greetings, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel greetings;
    // End of variables declaration//GEN-END:variables
}
