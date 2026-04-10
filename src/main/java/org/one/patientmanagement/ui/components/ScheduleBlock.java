package org.one.patientmanagement.ui.components;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

public final class ScheduleBlock extends javax.swing.JPanel {

    public ScheduleBlock() {
        initComponents();
        applyStyles();
        loadDummyData(20);
    }

    private void applyStyles() {
        Color bgColor = Color.decode("#FFF8F8");
        setBackground(bgColor);
        jPanel1.setBackground(bgColor);
        jScrollPane1.getViewport().setBackground(bgColor);
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
        DayBlock.setFont(new Font("Manrope", Font.BOLD, 22));
        DayBlock.setForeground(Color.decode("#21191C"));

        // DayTime Pill Styling (#FDD9E7)
        DayTime.setFont(new Font("Manrope", Font.BOLD, 12));
        DayTime.setForeground(Color.decode("#643E24"));
        DayTime.setBackground(Color.decode("#FDD9E7")); // Color changed to #FDD9E7
        DayTime.setPreferredSize(new Dimension(130, 28));

        jScrollPane1.getVerticalScrollBar().setUnitIncrement(12);
    }

    public void setBlockInfo(String blockName, String timeRange) {
        DayBlock.setText(blockName);
        DayTime.setText(timeRange);
    }

    public void addPatientRow(PatientRow row) {
        jPanel1.add(row);
        jPanel1.revalidate();
        jPanel1.repaint();
    }

    public void clearPatients() {
        jPanel1.removeAll();
        jPanel1.revalidate();
        jPanel1.repaint();
    }

    public void loadDummyData(int count) {
        clearPatients();
        Random rand = new Random();
        String[] names = {"Juan Dela Cruz", "Maria Santos", "Pedro Reyes", "Ana Lopez"};
        String[] sexes = {"Male", "Female"};
        String[] statuses = {"waiting", "with doctor", "done"};

        for (int i = 1; i <= count; i++) {
            PatientRow row = new PatientRow();
            row.setPatientData(i, names[rand.nextInt(names.length)], 
                String.valueOf(1000 + rand.nextInt(9000)), sexes[rand.nextInt(sexes.length)]);
            row.setStatus(statuses[rand.nextInt(statuses.length)]);
            addPatientRow(row);
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DayBlock = new javax.swing.JLabel();
        num = new javax.swing.JLabel();
        Name = new javax.swing.JLabel();
        Sex = new javax.swing.JLabel();
        Status = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        DayTime = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 248, 248));
        setMinimumSize(new java.awt.Dimension(430, 400));
        setPreferredSize(new java.awt.Dimension(430, 400));

        DayBlock.setText("Morning Block");

        num.setText("#");

        Name.setText("Name");

        Sex.setText("Sex");

        Status.setText("Status");

        jPanel1.setBackground(new java.awt.Color(255, 248, 248));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(jPanel1);

        DayTime.setText("7:00 - 12:00 PM");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(DayBlock)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(DayTime)
                .addGap(71, 71, 71))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(num)
                .addGap(37, 37, 37)
                .addComponent(Name)
                .addGap(122, 122, 122)
                .addComponent(Sex)
                .addGap(38, 38, 38)
                .addComponent(Status)
                .addGap(97, 97, 97))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DayBlock)
                    .addComponent(DayTime))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(num)
                    .addComponent(Name)
                    .addComponent(Sex)
                    .addComponent(Status))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DayBlock;
    private javax.swing.JButton DayTime;
    private javax.swing.JLabel Name;
    private javax.swing.JLabel Sex;
    private javax.swing.JLabel Status;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel num;
    // End of variables declaration//GEN-END:variables
}


