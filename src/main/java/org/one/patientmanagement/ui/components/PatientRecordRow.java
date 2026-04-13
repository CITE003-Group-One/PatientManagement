package org.one.patientmanagement.ui.components;

import java.awt.*;
import javax.swing.*;
import org.one.patientmanagement.domain.enums.Sex;

public class PatientRecordRow extends ClickablePanel {

    public PatientRecordRow() {
        initComponents();
        applyFigmaStyles();
    }

    public void setPatientData(String patientName, String patientId, String patientSex, String patientBirthday) {
        name.setText(patientName);
        id.setText("ID: " + patientId);
        sexText.setText(patientSex);
        birthday.setText(patientBirthday);
        
        // FIXME can fail parsing sex
        switch(Sex.valueOf(patientSex)) {
            case MALE -> setMale();
            case FEMALE -> setFemale();
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

        birthday.setFont(new Font("Manrope", Font.PLAIN, 13));
        birthday.setForeground(Color.decode("#21191C"));

    }

    private void setMale() {
        sexBadge.setBackground(Color.decode("#FFDBC7"));
        sexText.setForeground(Color.decode("#643E24"));
        repaint();
    }

    private void setFemale() {
        sexBadge.setBackground(Color.decode("#B7F1BA"));
        sexText.setForeground(Color.decode("#1B4D1E"));
        repaint();
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        birthday = new javax.swing.JLabel();
        edit = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        num = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        name = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5));
        id = new javax.swing.JLabel();
        sexBadge = new javax.swing.JPanel();
        sexText = new javax.swing.JLabel();
        sex1 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(400, 50));
        setPreferredSize(new java.awt.Dimension(400, 50));
        setLayout(new java.awt.GridBagLayout());

        birthday.setFont(new java.awt.Font("Manrope", 0, 13)); // NOI18N
        birthday.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        birthday.setText("01/12/2008");
        birthday.setMaximumSize(new java.awt.Dimension(100, 18));
        birthday.setMinimumSize(new java.awt.Dimension(100, 18));
        birthday.setPreferredSize(new java.awt.Dimension(100, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        add(birthday, gridBagConstraints);

        edit.setText("...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
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

        sexBadge.setMaximumSize(new java.awt.Dimension(60, 20));
        sexBadge.setMinimumSize(new java.awt.Dimension(60, 20));
        sexBadge.setPreferredSize(new java.awt.Dimension(60, 20));
        sexBadge.setLayout(new java.awt.GridBagLayout());

        sexText.setFont(new java.awt.Font("Manrope SemiBold", 0, 11)); // NOI18N
        sexText.setText("Female");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 0, 9);
        sexBadge.add(sexText, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        add(sexBadge, gridBagConstraints);

        sex1.setFont(new java.awt.Font("Manrope", 0, 13)); // NOI18N
        sex1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sex1.setText("18 y/o");
        sex1.setMaximumSize(new java.awt.Dimension(100, 18));
        sex1.setMinimumSize(new java.awt.Dimension(100, 18));
        sex1.setPreferredSize(new java.awt.Dimension(100, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        add(sex1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel birthday;
    private javax.swing.JButton edit;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JLabel id;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel name;
    private javax.swing.JLabel num;
    private javax.swing.JLabel sex1;
    private javax.swing.JPanel sexBadge;
    private javax.swing.JLabel sexText;
    // End of variables declaration//GEN-END:variables
}

