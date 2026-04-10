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
        updateGreeting("Dr. Ariah Mercado"); 
    }

    private void applyFigmaStyles() {
    // 1. Switch to GridBagLayout for precise centering
    this.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    
    // 2. Clear anything NetBeans added to start fresh
    this.removeAll();

    // 3. SETTINGS FOR THE GREETING (The Centerpiece)
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1.0; // This makes it take up all available horizontal space
    gbc.anchor = GridBagConstraints.CENTER; // This forces it to the absolute middle
    greetings.setHorizontalAlignment(SwingConstants.CENTER);
    greetings.setVerticalAlignment(SwingConstants.CENTER);
    this.add(greetings, gbc);

    // 4. SETTINGS FOR THE DATE (The Right side)
    // We use a separate set of constraints so it doesn't fight the center text
    GridBagConstraints dateGbc = new GridBagConstraints();
    dateGbc.gridx = 0;
    dateGbc.gridy = 0;
    dateGbc.weightx = 1.0;
    dateGbc.anchor = GridBagConstraints.EAST; // Pin to the right
    dateGbc.insets = new Insets(0, 0, 0, 30); // 30px padding from the right edge
    this.add(dateLabel, dateGbc);

    // 5. COLORS & FONTS (Figma Specs)
    this.setBackground(new java.awt.Color(250, 234, 238)); 
    this.setPreferredSize(new Dimension(950, 60)); 

    greetings.setForeground(new Color(89, 64, 74)); 
    greetings.setFont(new Font("Poppins", Font.BOLD, 16)); 

    dateLabel.setForeground(new Color(80, 67, 72)); 
    dateLabel.setFont(new Font("Poppins", Font.PLAIN, 14)); 
    
    // 6. Refresh UI
    this.revalidate();
    this.repaint();
}

    private void updateGreeting(String doctorName) {
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

        dateLabel = new javax.swing.JLabel();
        greetings = new javax.swing.JLabel();

        setBackground(new java.awt.Color(250, 234, 238));
        setMaximumSize(new java.awt.Dimension(9999, 60));
        setMinimumSize(new java.awt.Dimension(950, 60));
        setPreferredSize(new java.awt.Dimension(950, 60));

        dateLabel.setText("dateLabel");

        greetings.setText("Good Morning");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(213, Short.MAX_VALUE)
                .addComponent(greetings)
                .addGap(361, 361, 361)
                .addComponent(dateLabel)
                .addGap(247, 247, 247))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateLabel)
                    .addComponent(greetings))
                .addContainerGap(48, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel greetings;
    // End of variables declaration//GEN-END:variables
}
