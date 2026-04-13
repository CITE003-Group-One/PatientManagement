package org.one.patientmanagement.ui.components;

import java.awt.*;
import javax.swing.*;
import org.one.patientmanagement.ui.core.dto.QueueData;
import org.one.patientmanagement.domain.enums.AppointmentBlock;
import org.one.patientmanagement.domain.models.Schedule;

public final class ScheduleBlock extends javax.swing.JPanel {
    
    private ClickablePanel.ClickListenerObj<QueueData> clickListener;

    public ScheduleBlock() {
        initComponents();
        applyStyles();
    }

    private void applyStyles() {
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.getViewport().setBackground(null);
        jScrollPane1.setOpaque(false);
        jScrollPane1.setBorder(null);

        // Header Styling
        Font headerFont = new Font("Manrope", Font.PLAIN, 13);
        Color headerColor = Color.decode("#504348");

        num.setFont(headerFont);
        num.setBackground(Color.decode("#FFF0F4")); // Color changed to #FFF0F4
        num.setForeground(headerColor);

        Name.setFont(headerFont);
        Sex.setFont(headerFont);
        Status.setFont(headerFont);
        Name.setForeground(headerColor);
        Sex.setForeground(headerColor);
        Status.setForeground(headerColor);

        // DayBlock Styling
        DayBlock.setForeground(Color.decode("#21191C"));

        dayTimeLabel.setForeground(Color.decode("#643E24"));
        dayTimeBadge.setBackground(Color.decode("#FDD9E7"));

        jScrollPane1.getVerticalScrollBar().setUnitIncrement(12);
    }

    public void setBlockInfo(String blockName, String timeRange) {
        DayBlock.setText(blockName);
        dayTimeLabel.setText(timeRange);
    }

    private void addQueueRow(PatientRow row) {
        jPanel1.add(row);
        jPanel1.add(Box.createVerticalStrut(5));
        jPanel1.revalidate();
        jPanel1.repaint();
    }

    public void clearQueue() {
        jPanel1.removeAll();
        jPanel1.revalidate();
        jPanel1.repaint();
    }

    public void loadQueue(java.util.List<QueueData> queues, AppointmentBlock block, Schedule schedule) {
        var available = schedule.blocks().contains(block);
        setEnabled(available); // TODO set proper enabled
        if (!available) {
            return;
        }

        setBlockInfo(block == AppointmentBlock.MORNING ? "Morning" : "Afternoon", schedule.timeRange(block));
        clearQueue();
        
        queues.forEach(q -> {
            var row = new PatientRow();
            row.setPatientData(q.queueNumber(), q.fullName(), q.schemedId(), q.sex());
            row.setClickListener(l -> { clickListener.onClick(q); });
            addQueueRow(row);
        });
    }
    
    public void setRowClickListener(ClickablePanel.ClickListenerObj<QueueData> clickListener) {
        this.clickListener = clickListener;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        DayBlock = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        dayTimeBadge = new javax.swing.JPanel();
        dayTimeLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Name = new javax.swing.JLabel();
        Status = new javax.swing.JLabel();
        Sex = new javax.swing.JLabel();
        num = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 248, 248));
        setMinimumSize(new java.awt.Dimension(430, 400));
        setPreferredSize(new java.awt.Dimension(430, 400));
        setLayout(new java.awt.GridBagLayout());

        DayBlock.setFont(new java.awt.Font("Poppins Medium", 0, 20)); // NOI18N
        DayBlock.setText("Morning Block");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 18, 0);
        add(DayBlock, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(255, 248, 248));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(jPanel1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 402;
        gridBagConstraints.ipady = 238;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        add(jScrollPane1, gridBagConstraints);

        dayTimeBadge.setLayout(new java.awt.GridBagLayout());

        dayTimeLabel.setFont(new java.awt.Font("Manrope Medium", 0, 13)); // NOI18N
        dayTimeLabel.setText("7:00 - 12:00 PM");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 26, 3, 26);
        dayTimeBadge.add(dayTimeLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 18, 0);
        add(dayTimeBadge, gridBagConstraints);

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridBagLayout());

        Name.setFont(new java.awt.Font("Manrope SemiBold", 0, 13)); // NOI18N
        Name.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel3.add(Name, gridBagConstraints);

        Status.setFont(new java.awt.Font("Manrope SemiBold", 0, 13)); // NOI18N
        Status.setText("Status");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel3.add(Status, gridBagConstraints);

        Sex.setFont(new java.awt.Font("Manrope SemiBold", 0, 13)); // NOI18N
        Sex.setText("Sex");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel3.add(Sex, gridBagConstraints);

        num.setFont(new java.awt.Font("Manrope SemiBold", 0, 13)); // NOI18N
        num.setText("#");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        jPanel3.add(num, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 226;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jPanel3, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DayBlock;
    private javax.swing.JLabel Name;
    private javax.swing.JLabel Sex;
    private javax.swing.JLabel Status;
    private javax.swing.JPanel dayTimeBadge;
    private javax.swing.JLabel dayTimeLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel num;
    // End of variables declaration//GEN-END:variables
}
