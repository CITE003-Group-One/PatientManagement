package org.one.patientmanagement.ui;

import java.awt.*;
import javax.swing.*;
import org.one.patientmanagement.ui.components.Login;
import org.one.patientmanagement.ui.components.SideHeadingPanel;

public class DoctorPanel extends javax.swing.JPanel {

    // Declared once to avoid duplicates
    private SideHeadingPanel sideHeadingPanel;
    private Login loginPanel;
    private JPanel containerPanel;

    public DoctorPanel() {
        setupComponents();
        setupLayout();
    }

    private void setupComponents() {
        sideHeadingPanel = new SideHeadingPanel();
        loginPanel = new Login();
        containerPanel = new JPanel();
        
        containerPanel.setBackground(new java.awt.Color(255, 248, 248));
        this.setLayout(new BorderLayout());
    }

    private void setupLayout() {
        containerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // 1. Sidebar (Sticky Left)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        containerPanel.add(sideHeadingPanel, gbc);

        // 2. Spacer (Expands to push Login to the right/center)
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        containerPanel.add(Box.createHorizontalGlue(), gbc);

        // 3. Login Panel (Centered vertically, nudged from right)
        gbc.gridx = 2;
        gbc.weightx = 0.5; // Shared weight to help centering
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 100); 
        containerPanel.add(loginPanel, gbc);

        this.add(containerPanel, BorderLayout.CENTER);
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        sideHeadingPanel1 = new org.one.patientmanagement.ui.components.SideHeadingPanel();
        login2 = new org.one.patientmanagement.ui.components.Login();

        setMaximumSize(new java.awt.Dimension(1200, 800));
        setMinimumSize(new java.awt.Dimension(1200, 800));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(255, 248, 248));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(sideHeadingPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 217, Short.MAX_VALUE)
                .addComponent(login2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(sideHeadingPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addComponent(login2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private org.one.patientmanagement.ui.components.Login login2;
    private org.one.patientmanagement.ui.components.SideHeadingPanel sideHeadingPanel1;
    // End of variables declaration//GEN-END:variables
}
