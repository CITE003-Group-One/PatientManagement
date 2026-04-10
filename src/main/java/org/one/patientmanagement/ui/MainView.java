package org.one.patientmanagement.ui;

public class MainView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainView.class.getName());

    public MainView() {
        initComponents();
        // We set the title and center the window on screen
        setTitle("Patient Management System");
        setLocationRelativeTo(null);
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        doctorPanel2 = new org.one.patientmanagement.ui.DoctorPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1200, 800));
        getContentPane().add(doctorPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.one.patientmanagement.ui.DoctorPanel doctorPanel2;
    // End of variables declaration//GEN-END:variables
}
