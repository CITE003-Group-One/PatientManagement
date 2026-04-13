package org.one.patientmanagement.ui.view;

import java.util.List;
import org.one.patientmanagement.domain.models.Schedule;
import org.one.patientmanagement.ui.components.ScheduleOverviewPanel;
import org.one.patientmanagement.ui.controller.ControllerBound;
import org.one.patientmanagement.ui.controller.doctor.DashboardController;

public class DoctorDashboard extends javax.swing.JPanel implements ControllerBound<DashboardController> {

    private DashboardController controller;

    public DoctorDashboard() {
        initComponents();
        
        todaySchedule1.setStatic(true);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        fillWidthPanel1 = new org.one.patientmanagement.ui.components.FillWidthPanel();
        jPanel3 = new javax.swing.JPanel();
        statsCard1 = new org.one.patientmanagement.ui.components.DoctorStatsCard();
        statsCard2 = new org.one.patientmanagement.ui.components.DoctorStatsCard();
        statsCard3 = new org.one.patientmanagement.ui.components.DoctorStatsCard();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 40), new java.awt.Dimension(0, 40), new java.awt.Dimension(0, 40));
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        todaySchedule1 = new org.one.patientmanagement.ui.components.TodaySchedule();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 40), new java.awt.Dimension(0, 40), new java.awt.Dimension(0, 40));
        scheduleOverviewPanel1 = new org.one.patientmanagement.ui.components.ScheduleOverviewPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 248, 248));
        setMaximumSize(new java.awt.Dimension(1200, 800));
        setMinimumSize(new java.awt.Dimension(1200, 800));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1200, 800));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.BorderLayout());

        fillWidthPanel1.setLayout(new javax.swing.BoxLayout(fillWidthPanel1, javax.swing.BoxLayout.Y_AXIS));

        jPanel3.setMinimumSize(new java.awt.Dimension(0, 150));
        jPanel3.setName(""); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(100, 150));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0, 25, 0));
        jPanel3.add(statsCard1);
        jPanel3.add(statsCard2);
        jPanel3.add(statsCard3);

        fillWidthPanel1.add(jPanel3);
        fillWidthPanel1.add(filler2);

        jPanel4.setMaximumSize(new java.awt.Dimension(2147483647, 230));
        jPanel4.setMinimumSize(new java.awt.Dimension(299, 230));
        jPanel4.setName(""); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(100, 230));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Poppins Medium", 0, 25)); // NOI18N
        jLabel1.setText("Today's Schedule");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 25, 0);
        jPanel4.add(jLabel1, gridBagConstraints);

        todaySchedule1.setMaximumSize(new java.awt.Dimension(2147483647, 500));
        todaySchedule1.setMinimumSize(new java.awt.Dimension(299, 500));
        todaySchedule1.setName(""); // NOI18N
        todaySchedule1.setPreferredSize(new java.awt.Dimension(850, 500));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(todaySchedule1, gridBagConstraints);

        fillWidthPanel1.add(jPanel4);
        fillWidthPanel1.add(filler1);
        fillWidthPanel1.add(scheduleOverviewPanel1);

        jPanel1.add(fillWidthPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(40, 40, 40, 40));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(jPanel2);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.one.patientmanagement.ui.components.FillWidthPanel fillWidthPanel1;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private org.one.patientmanagement.ui.components.ScheduleOverviewPanel scheduleOverviewPanel1;
    private org.one.patientmanagement.ui.components.DoctorStatsCard statsCard1;
    private org.one.patientmanagement.ui.components.DoctorStatsCard statsCard2;
    private org.one.patientmanagement.ui.components.DoctorStatsCard statsCard3;
    private org.one.patientmanagement.ui.components.TodaySchedule todaySchedule1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setController(DashboardController controller) {
        this.controller = controller;
    }
    
    public void setCards(int total, int completed, int remaining) {
        statsCard1.setContent("Total patients", "Scheduled today", String.valueOf(total));
        statsCard2.setContent("Patients completed", null, String.valueOf(completed));
        int percent = (int) ((completed * 100.0) / total);
        statsCard2.setProgressBar(percent);
        statsCard3.setContent("Remaining patients", "Pending, waiting patients", String.valueOf(completed));
    }
    
    public void setSchedule(List<Schedule> schedules) {
        todaySchedule1.generateCalendar(schedules);
    }
    
    public ScheduleOverviewPanel getOverviewPanel() {
        return scheduleOverviewPanel1;
    }
    
    
}
