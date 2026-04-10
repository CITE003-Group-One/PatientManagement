package org.one.patientmanagement.ui.components;

import java.awt.*;
import javax.swing.*;

public class PatientRow extends JPanel {

    // Custom Pill Button Logic
    public static class PillButton extends JButton {
        private final int arcRadius;

        public PillButton(int arcRadius) {
            this.arcRadius = arcRadius;
            setContentAreaFilled(false);
            setOpaque(false);
            setFocusPainted(false);
            setBorderPainted(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcRadius * 2, arcRadius * 2);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private int statusIndex = 0;

    public PatientRow() {
        initComponents();
        applyFigmaStyles();
        initCustomLogic();
    }

    private void initCustomLogic() {
        statusBtn.addActionListener(evt -> handleStatusChange());
    }

    private void handleStatusChange() {
        statusIndex = (statusIndex + 1) % 3;
        switch (statusIndex) {
            case 0 -> setStatusWaiting();
            case 1 -> setStatusWithDoctor();
            case 2 -> setStatusDone();
        }
    }

    public void setPatientData(int number, String patientName, String patientId, String patientSex) {
        num.setText(String.valueOf(number)); // Iteration logic
        name.setText(patientName);
        id.setText("ID: " + patientId);
        sex.setText(patientSex);
    }

    public void setStatus(String status) {
        switch (status.toLowerCase()) {
            case "waiting"      -> { statusIndex = 0; setStatusWaiting(); }
            case "with doctor" -> { statusIndex = 1; setStatusWithDoctor(); }
            case "done"        -> { statusIndex = 2; setStatusDone(); }
        }
    }

    private void applyFigmaStyles() {
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        // Ensure the row can stretch to full width
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));

        name.setFont(new Font("Manrope", Font.BOLD, 13));
        name.setForeground(Color.decode("#21191C"));

        id.setFont(new Font("Manrope", Font.PLAIN, 11));
        id.setForeground(Color.decode("#504348"));

        sex.setFont(new Font("Manrope", Font.PLAIN, 13));
        sex.setForeground(Color.decode("#21191C"));

        configureStatusButton();
        configureEditButton();
        configureNumButton();

        setStatusWaiting();
    }

    private void configureStatusButton() {
        statusBtn.setFont(new Font("Manrope", Font.BOLD, 11));
        statusBtn.setPreferredSize(new Dimension(100, 26));
    }

    private void configureEditButton() {
        edit.setFont(new Font("Manrope", Font.BOLD, 11));
        edit.setPreferredSize(new Dimension(45, 26));
        edit.setText(". . .");
        edit.setBackground(Color.decode("#FFF0F4")); // Color changed to #FFF0F4
        edit.setForeground(Color.decode("#21191C"));
    }
    
    private void configureNumButton() {
        num.setFont(new Font("Manrope", Font.BOLD, 11));
        num.setPreferredSize(new Dimension(35, 26));
        num.setBackground(Color.decode("#FFF0F4")); // Color changed to #FFF0F4
        num.setForeground(Color.decode("#21191C"));
    }

    private void setStatusWaiting() {
        statusBtn.setText("Waiting");
        statusBtn.setBackground(Color.decode("#FFDBC7"));
        statusBtn.setForeground(Color.decode("#643E24"));
    }

    private void setStatusWithDoctor() {
        statusBtn.setText("With Doctor");
        statusBtn.setBackground(Color.decode("#C7E7FF"));
        statusBtn.setForeground(Color.decode("#002D4F"));
    }

    private void setStatusDone() {
        statusBtn.setText("Done");
        statusBtn.setBackground(Color.decode("#B7F1BA"));
        statusBtn.setForeground(Color.decode("#1B4D1E"));
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        name = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        sex = new javax.swing.JLabel();
        statusBtn = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        num = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(400, 50));
        setPreferredSize(new java.awt.Dimension(400, 50));

        name.setText("Cyrill S. Murillo");

        id.setText("ID: 012345");

        sex.setText("Male");

        statusBtn.setText("Waiting");

        edit.setText("...");

        num.setText("1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(num)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(id)
                    .addComponent(name))
                .addGap(107, 107, 107)
                .addComponent(sex)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(statusBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(edit)
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(name)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(id))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sex)
                            .addComponent(statusBtn)
                            .addComponent(edit)))
                    .addComponent(num))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton edit;
    private javax.swing.JLabel id;
    private javax.swing.JLabel name;
    private javax.swing.JButton num;
    private javax.swing.JLabel sex;
    private javax.swing.JButton statusBtn;
    // End of variables declaration//GEN-END:variables
}

