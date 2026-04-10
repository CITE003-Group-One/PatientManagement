package org.one.patientmanagement.ui.components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Login extends javax.swing.JPanel {

    public Login() {
        initComponents();
        applyCustomStyles();
    }

    private void applyCustomStyles() {
        jLabel1.setFont(new Font("Poppins", Font.BOLD, 18));
        jLabel1.setForeground(new Color(33, 25, 28));
        jLabel1.setText("Login with PIN");

        Color backgroundBlend = new Color(255, 248, 248); 
        
        jPasswordField1.setPreferredSize(new Dimension(250, 45));
        jPasswordField1.setFont(new Font("Poppins", Font.PLAIN, 18)); 
        jPasswordField1.setText(""); 
        jPasswordField1.setBackground(backgroundBlend); 
        jPasswordField1.setOpaque(true); 
        jPasswordField1.setBorder(new LineBorder(backgroundBlend, 1)); 

        jButton1.setPreferredSize(new Dimension(325, 50));
        jButton1.setFont(new Font("Poppins", Font.BOLD, 16));
        jButton1.setForeground(Color.WHITE);
        jButton1.setContentAreaFilled(false);
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        jButton1.setOpaque(false);

        final Color designColor = new Color(137, 74, 105);
        final Color hoverColor = new Color(117, 54, 85);

        jButton1.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                JButton b = (JButton) c;
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(b.getModel().isRollover() ? hoverColor : designColor);
                g2.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), 50, 50);
                g2.dispose();
                super.paint(g, c);
            }
        });
    }

    private void handleLoginLogic() {
        String pin = new String(jPasswordField1.getPassword());

        if (pin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your PIN.", "Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // --- SQLITE HOOK FOR TEAMMATES ---
        // Teammates: Use this section to query your database.
        // Example: "SELECT * FROM doctors WHERE pin = '" + pin + "'"
        boolean isAuthenticated = authenticateWithDatabase(pin);

        if (isAuthenticated) {
            System.out.println("Access Granted!");
            // Logic to switch to MainDashboard goes here
        } else {
            JOptionPane.showMessageDialog(this, "Invalid PIN. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            jPasswordField1.setText("");
        }
    }

    private boolean authenticateWithDatabase(String pin) {
        // TEMPORARY: Dummy logic until SQLite is connected.
        // Accepts '1234' as the valid PIN.
        return pin.equals("1234");
    }
    
    
    
    
    
    
    
    
    
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(250, 234, 238));
        setMinimumSize(new java.awt.Dimension(435, 355));
        setPreferredSize(new java.awt.Dimension(435, 355));
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 5, 0};
        layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        setLayout(layout);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel1.setText("Login with PIN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(jLabel1, gridBagConstraints);

        jPasswordField1.setBackground(new java.awt.Color(250, 234, 238));
        jPasswordField1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jPasswordField1.setText("jPasswordField1");
        jPasswordField1.setPreferredSize(new java.awt.Dimension(180, 40));
        jPasswordField1.addActionListener(this::jPasswordField1ActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        add(jPasswordField1, gridBagConstraints);

        jButton1.setBackground(new java.awt.Color(137, 74, 105));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton1.setText("Proceed");
        jButton1.setBorderPainted(false);
        jButton1.setPreferredSize(new java.awt.Dimension(220, 45));
        jButton1.addActionListener(this::jButton1ActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        add(jButton1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
              
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField jPasswordField1;
    // End of variables declaration//GEN-END:variables
}
