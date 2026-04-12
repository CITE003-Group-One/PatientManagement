package org.one.patientmanagement.ui.components;

import java.awt.*;
import javax.swing.*;

public class PatientRow extends JPanel {
    
    public PatientRow() {
        initComponents();
        applyFigmaStyles();
    }

    public void setPatientData(String number, String patientName, String patientId, String patientSex) {
        num.setText(number);
        name.setText(patientName);
        id.setText("ID: " + patientId);
        sex.setText(patientSex);
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
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        sex = new javax.swing.JLabel();
        edit = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        num = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        name = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5));
        id = new javax.swing.JLabel();
        statusBadgePanel1 = new org.one.patientmanagement.ui.components.StatusBadgePanel();

        setMinimumSize(new java.awt.Dimension(400, 50));
        setPreferredSize(new java.awt.Dimension(400, 50));
        setLayout(new java.awt.GridBagLayout());

        sex.setFont(new java.awt.Font("Manrope", 0, 13)); // NOI18N
        sex.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sex.setText("Female");
        sex.setMaximumSize(new java.awt.Dimension(50, 18));
        sex.setMinimumSize(new java.awt.Dimension(50, 18));
        sex.setPreferredSize(new java.awt.Dimension(50, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        add(sex, gridBagConstraints);

        edit.setText("...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        add(edit, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(255, 240, 244));
        jPanel1.setMinimumSize(new java.awt.Dimension(40, 32));
        jPanel1.setPreferredSize(new java.awt.Dimension(40, 100));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        num.setFont(new java.awt.Font("Manrope", 0, 13)); // NOI18N
        num.setText("M01");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 7);
        jPanel1.add(num, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        add(jPanel1, gridBagConstraints);

        jPanel2.setMaximumSize(new java.awt.Dimension(233, 34));
        jPanel2.setMinimumSize(new java.awt.Dimension(233, 34));
        jPanel2.setName(""); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS));

        name.setFont(new java.awt.Font("Manrope Medium", 0, 13)); // NOI18N
        name.setText("Cyrill S. Murillo Cyrill S. Murillo Cyrill S. Murillo");
        jPanel2.add(name);
        jPanel2.add(filler2);

        id.setFont(new java.awt.Font("Manrope", 0, 11)); // NOI18N
        id.setText("ID: 012345");
        jPanel2.add(id);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        add(jPanel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        add(statusBadgePanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton edit;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JLabel id;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel name;
    private javax.swing.JLabel num;
    private javax.swing.JLabel sex;
    private org.one.patientmanagement.ui.components.StatusBadgePanel statusBadgePanel1;
    // End of variables declaration//GEN-END:variables
}

