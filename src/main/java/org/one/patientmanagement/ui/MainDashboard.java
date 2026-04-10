package org.one.patientmanagement.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainDashboard extends javax.swing.JPanel {

    public MainDashboard() {
        initComponents();
        setupManualLayout();
    }

    private void setupManualLayout() {
        // 1. Root Layout: Sidebar (WEST) | Content Area (CENTER)
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(255, 248, 248)); // Figma Background #FFF8F8

        // --- SIDEBAR (Left) ---
        navigationSidebar1.setPreferredSize(new Dimension(250, 800));
        this.add(navigationSidebar1, BorderLayout.WEST);

        // --- RIGHT SIDE WRAPPER ---
        JPanel rightWrapper = new JPanel(new BorderLayout());
        rightWrapper.setOpaque(false);

        // --- TOP BAR (North) ---
        topBar2.setPreferredSize(new Dimension(950, 60));
        rightWrapper.add(topBar2, BorderLayout.NORTH);

        // --- MAIN DASHBOARD CONTENT (Center) ---
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        // Minimal bottom padding to ensure the panel sits high
        contentPanel.setBorder(new EmptyBorder(20, 40, 0, 40)); 
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx = 0;

        // A. Summary Cards Row - Scaled for 1200x800
        totalPatient2.setPreferredSize(new Dimension(210, 145));
        patientsCompleted3.setPreferredSize(new Dimension(245, 145));
        remainingPatient2.setPreferredSize(new Dimension(240, 145));

        JPanel cardRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        cardRow.setOpaque(false);
        cardRow.add(totalPatient2);
        cardRow.add(patientsCompleted3);
        cardRow.add(remainingPatient2);
        
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 15, 0); // Tightened spacing
        contentPanel.add(cardRow, gbc);

        // B. "Today's Schedule" Title
        TodaySched.setFont(new Font("Poppins", Font.BOLD, 22)); 
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 5, 0); 
        contentPanel.add(TodaySched, gbc);

        // C. Date Strip (TodaySchedule)
        todaySchedule1.setPreferredSize(new Dimension(850, 140));
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 5, 0); // Stuck close to the panel below
        contentPanel.add(todaySchedule1, gbc);

        // D. Schedule Overview (The Morning/Afternoon Blocks)
        gbc.gridy = 3;
        gbc.weighty = 0.0; // Stop vertical stretching to prevent bottom overlap
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.anchor = GridBagConstraints.NORTH; // Stick it to the top of its cell
        gbc.insets = new Insets(0, 0, 20, 0); // Bottom margin to prevent hitting the edge
        contentPanel.add(scheduleOverviewPanel1, gbc);

        // Wrap in ScrollPane so it's impossible to "overlap" or cut off
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        rightWrapper.add(scrollPane, BorderLayout.CENTER);
        this.add(rightWrapper, BorderLayout.CENTER);
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        patientsCompleted2 = new org.one.patientmanagement.ui.components.PatientsCompleted();
        todaySchedule1 = new org.one.patientmanagement.ui.components.TodaySchedule();
        patientsCompleted3 = new org.one.patientmanagement.ui.components.PatientsCompleted();
        remainingPatient2 = new org.one.patientmanagement.ui.components.RemainingPatient();
        totalPatient2 = new org.one.patientmanagement.ui.components.TotalPatient();
        TodaySched = new javax.swing.JLabel();
        scheduleOverviewPanel1 = new org.one.patientmanagement.ui.components.ScheduleOverviewPanel();
        navigationSidebar1 = new org.one.patientmanagement.ui.components.NavigationSidebar();
        topBar2 = new org.one.patientmanagement.ui.components.TopBar();

        setBackground(new java.awt.Color(255, 248, 248));
        setMaximumSize(new java.awt.Dimension(1200, 800));
        setMinimumSize(new java.awt.Dimension(1200, 800));
        setPreferredSize(new java.awt.Dimension(1200, 800));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(patientsCompleted2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 946, 240, 800));
        add(todaySchedule1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 250, -1, -1));
        add(patientsCompleted3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, -1, -1));
        add(remainingPatient2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, -1, -1));
        add(totalPatient2, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 70, -1, -1));

        TodaySched.setText("Today’s Schedule");
        add(TodaySched, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, -1, 20));
        add(scheduleOverviewPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 390, -1, -1));
        add(navigationSidebar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        add(topBar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TodaySched;
    private org.one.patientmanagement.ui.components.NavigationSidebar navigationSidebar1;
    private org.one.patientmanagement.ui.components.PatientsCompleted patientsCompleted2;
    private org.one.patientmanagement.ui.components.PatientsCompleted patientsCompleted3;
    private org.one.patientmanagement.ui.components.RemainingPatient remainingPatient2;
    private org.one.patientmanagement.ui.components.ScheduleOverviewPanel scheduleOverviewPanel1;
    private org.one.patientmanagement.ui.components.TodaySchedule todaySchedule1;
    private org.one.patientmanagement.ui.components.TopBar topBar2;
    private org.one.patientmanagement.ui.components.TotalPatient totalPatient2;
    // End of variables declaration//GEN-END:variables
}
