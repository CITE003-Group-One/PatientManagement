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

        this.putClientProperty("FlatLaf.style", "arc: 999;");
    }

    private void setupLayout() {
        // Scaled container size for 1200x800 JFrame
        this.setPreferredSize(new Dimension(850, 150));

        GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);
    }

    private void generateCalendar() {
        this.removeAll();
        LocalDate today = LocalDate.now();
        DateTimeFormatter monthFmt = DateTimeFormatter.ofPattern("MMM");
        DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter dayFmt = DateTimeFormatter.ofPattern("EEE");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 7, 0, 7);

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

            // Row 0: day cards
            gbc.gridx = i;
            gbc.gridy = 0;
            this.add(card, gbc);

            JPanel doctorIndicators = new JPanel();
            doctorIndicators.setPreferredSize(new Dimension(card.getPreferredSize().width, 50)); // adjust height as needed
            doctorIndicators.setBackground(Color.LIGHT_GRAY); // replace with your desired style

            gbc.gridy = 1;
            this.add(doctorIndicators, gbc);

            if (isInitial) {
                selectedHighlight = card;
            }
        }

        revalidate();
        repaint();
    }

    private void handleCardSelection(DayCardComponent clickedCard, LocalDate date) {
        if (selectedHighlight != null) {
            selectedHighlight.setSelected(false);
        }
        clickedCard.setSelected(true);
        selectedHighlight = clickedCard;
        System.out.println("Selected: " + date);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 240, 244));

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
