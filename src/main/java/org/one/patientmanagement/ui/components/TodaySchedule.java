package org.one.patientmanagement.ui.components;

import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.one.patientmanagement.domain.dto.ScheduleOfDoctor;
import org.one.patientmanagement.domain.enums.AppointmentBlock;
import org.one.patientmanagement.domain.models.Schedule;

public class TodaySchedule extends javax.swing.JPanel {

    private DayCardComponent selectedHighlight;

    public TodaySchedule() {
        initComponents();
        setupLayout();

        this.putClientProperty("FlatLaf.style", "arc: 999;");
    }

    private void setupLayout() {
        // Scaled container size for 1200x800 JFrame
        this.setPreferredSize(new Dimension(850, 150));

        GridBagLayout gbl = new GridBagLayout();
        this.setLayout(gbl);
    }

    public void generateCalendar(java.util.List<Schedule> schedules) {
        this.removeAll();
        LocalDate today = LocalDate.now();
        DateTimeFormatter monthFmt = DateTimeFormatter.ofPattern("MMM");
        DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter dayFmt = DateTimeFormatter.ofPattern("EEE");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 7, 0, 7);

        Set<DayOfWeek> availableDays = schedules.stream()
                .map(Schedule::day)
                .collect(java.util.stream.Collectors.toSet());

        for (int i = 0; i < 7; i++) {

            LocalDate targetDate = today.plusDays(i);

            DayOfWeek dow = targetDate.getDayOfWeek();

            boolean isAvailable = availableDays.contains(dow);

            String m = targetDate.format(monthFmt).toUpperCase();
            String d = targetDate.format(dateFmt);
            String dy = targetDate.format(dayFmt).toUpperCase();
            boolean isInitial = (i == 0);

            DayCardComponent card = new DayCardComponent(m, d, dy, isInitial);
            card.setEnabled(isAvailable);
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
                selectedHighlight = card;// TODO potentially ui logical error
            }
        }

        revalidate();
        repaint();
    }

    public interface DaySelectListener {

        void onDaySelected(LocalDate date);
    }

    private DaySelectListener daySelectListener;

    public void setDaySelectListener(DaySelectListener listener) {
        this.daySelectListener = listener;
    }

    // Color provider API - call this to conditionally color blocks
    public interface BlockColorProvider {

        Color getColor(ScheduleOfDoctor scheduleOfDoctor, AppointmentBlock block);
    }

    private BlockColorProvider blockColorProvider = (s, block) -> Color.GRAY; // default

    public void setBlockColorProvider(BlockColorProvider provider) {
        this.blockColorProvider = provider;
    }

    public void generateCalendar(Map<DayOfWeek, java.util.List<ScheduleOfDoctor>> scheduleMap) {
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
            DayOfWeek dow = targetDate.getDayOfWeek();
            java.util.List<ScheduleOfDoctor> sods = scheduleMap.getOrDefault(dow, java.util.List.of());
            boolean isAvailable = !sods.isEmpty();

            String m = targetDate.format(monthFmt).toUpperCase();
            String d = targetDate.format(dateFmt);
            String dy = targetDate.format(dayFmt).toUpperCase();
            boolean isInitial = (i == 0);

            DayCardComponent card = new DayCardComponent(m, d, dy, isInitial);
            card.setEnabled(isAvailable);

            if (isAvailable) {
                card.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        handleCardSelection(card, targetDate);

                        if (daySelectListener != null) {
                            daySelectListener.onDaySelected(targetDate);
                        }
                    }
                });
            }

            gbc.gridx = i;
            gbc.gridy = 0;
            this.add(card, gbc);

            if (isAvailable) {
                JPanel doctorIndicators = new JPanel(new FlowLayout(FlowLayout.CENTER, 3, 0));
                doctorIndicators.setPreferredSize(new Dimension(card.getPreferredSize().width, 50));
                doctorIndicators.setOpaque(false);

                for (ScheduleOfDoctor sod : sods) {
                    for (AppointmentBlock block : sod.schedule().blocks()) {
                        JPanel dot = new JPanel();
                        dot.setPreferredSize(new Dimension(10, 10));
                        dot.setBackground(blockColorProvider.getColor(sod, block));
                        dot.putClientProperty("FlatLaf.style", "arc: 999;");
                        dot.setToolTipText(sod.doctor().name() + " - " + block.name());
                        doctorIndicators.add(dot);
                    }
                }

                gbc.gridy = 1;
                this.add(doctorIndicators, gbc);
            }

            if (isInitial) {
                selectedHighlight = card;// TODO potentially ui logical error
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
//        System.out.println("Selected: " + date);
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
