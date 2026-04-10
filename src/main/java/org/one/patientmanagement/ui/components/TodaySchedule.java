package org.one.patientmanagement.ui.components;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TodaySchedule extends javax.swing.JPanel {

    private DayCardComponent selectedHighlight; 

    public TodaySchedule() {
        initComponents();
        setupLayout();
        generateCalendar();
    }

    private void setupLayout() {
        // Scaled container size for 1200x800 JFrame
        this.setPreferredSize(new Dimension(850, 150)); 
        this.setOpaque(false);
        
        // FlowLayout with 15px gap replicates the Figma spacing
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 22));
    }

    private void generateCalendar() {
        this.removeAll();
        LocalDate today = LocalDate.now();
        
        DateTimeFormatter monthFmt = DateTimeFormatter.ofPattern("MMM");
        DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter dayFmt = DateTimeFormatter.ofPattern("EEE");

        for (int i = 0; i < 7; i++) {
            LocalDate targetDate = today.plusDays(i);
            
            String m = targetDate.format(monthFmt).toUpperCase();
            String d = targetDate.format(dateFmt);
            String dy = targetDate.format(dayFmt).toUpperCase();
            
            boolean isInitial = (i == 0);
            DayCardComponent card = new DayCardComponent(m, d, dy, isInitial);
            
            card.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    handleCardSelection(card, targetDate);
                }
            });

            this.add(card);
            if(isInitial) selectedHighlight = card; 
        }
        revalidate();
        repaint();
    }

    private void handleCardSelection(DayCardComponent clickedCard, LocalDate date) {
        if (selectedHighlight != null) selectedHighlight.setSelected(false);
        clickedCard.setSelected(true);
        selectedHighlight = clickedCard;
        System.out.println("Selected: " + date);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Figma Light Pink Background for the whole section
        g2.setColor(new Color(252, 245, 246)); 
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 28, 28);
        g2.dispose();
    }
    
    
    
    
    






    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
