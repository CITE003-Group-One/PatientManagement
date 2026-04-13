/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.one.patientmanagement.ui.view;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.one.patientmanagement.ui.core.dto.PatientInfo;
import org.one.patientmanagement.ui.core.dto.ScheduleOfDoctor;
import org.one.patientmanagement.domain.models.Patient;
import org.one.patientmanagement.domain.models.Schedule;
import org.one.patientmanagement.ui.components.DayOfWeekCard;
import org.one.patientmanagement.ui.components.DoctorSelectionRow;
import org.one.patientmanagement.ui.controller.ControllerBound;
import org.one.patientmanagement.ui.controller.patient.PatientSetupController;

/**
 *
 * @author Lei
 */
public class PatientSetup extends javax.swing.JPanel implements ControllerBound<PatientSetupController> {

    private PatientSetupController controller;

    /**
     * Creates new form New_Acct_Scheduling
     */
    public PatientSetup() {
        initComponents();

        todaySchedule.setDaySelectListener(e -> {
            controller.onDaySelect(e);
        });

        morningCard.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                setSelectedBlockCard(morningCard);
            }
        });

        afternoonCard.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                setSelectedBlockCard(afternoonCard);
            }
        });
    }

    public void loadSchedule(Map<DayOfWeek, List<ScheduleOfDoctor>> scheduleMap) {
        todaySchedule.generateCalendar(scheduleMap);
    }

    public void setDate(String date) {
        dateLabel.setText(date);
    }

    private final Map<ScheduleOfDoctor, DoctorSelectionRow> rowMap = new HashMap<>();
    private DoctorSelectionRow selectedRow;

    private DayOfWeekCard selectedBlockCard; // TODO apply one a time selecetion to block cards

    public void loadDoctorSelection(List<ScheduleOfDoctor> weekScheduleOfDoctors) {
        doctorsSelectionList.removeAll();
        rowMap.clear();
        selectedRow = null;

        for (var sod : weekScheduleOfDoctors) {
            var row = new DoctorSelectionRow();

            row.setData(sod.doctor().name(), sod.doctor().profession());

            row.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    setSelectedRow(sod);
                    controller.onDoctorSelect(sod);
                }
            });

            rowMap.put(sod, row);
            addDoctorSelectionRow(row);
        }

        doctorsSelectionList.revalidate();
        doctorsSelectionList.repaint();
    }

    public void setBlock(Schedule schedule) {
        morningCard.setEnabled(true);
        afternoonCard.setEnabled(true);

        var blocks = schedule.blocks();
        // to populate the block cards
    }

    public void addDoctorSelectionRow(DoctorSelectionRow row) {
        doctorsSelectionList.add(row);
    }

    public void setSelectedRow(ScheduleOfDoctor sod) {
        DoctorSelectionRow newRow = rowMap.get(sod);
        if (newRow == null) {
            return;
        }

        if (selectedRow != null) {
            selectedRow.setSelected(false);
        }

        selectedRow = newRow;
        selectedRow.setSelected(true);

        doctorsSelectionList.repaint();
    }

    private void setSelectedBlockCard(DayOfWeekCard card) {
        if (selectedBlockCard == card) {
            return;
        }

        if (selectedBlockCard != null) {
            selectedBlockCard.setSelected(false);
        }

        selectedBlockCard = card;
        selectedBlockCard.setSelected(true);

        morningCard.repaint();
        afternoonCard.repaint();
    }

    public boolean haveSelected() {
        return selectedRow != null && selectedBlockCard != null;
    }

    public PatientInfo getPatientInfo() {

        String firstName = firstNameField.getFieldText();
        String lastName = lastNameField.getFieldText();
        String sex = sexField.getFieldText();
        String birthdayText = birthdayField.getFieldText();
        String bloodType = bloodTypeField.getFieldText();
        String mainContact = mainContactField.getFieldText();
        String alternativeContact = alternativeContactField.getFieldText();
        String address = addressField.getFieldText();

        if (firstName == null
                || lastName == null
                || sex == null
                || birthdayText == null
                || bloodType == null
                || mainContact == null
                || alternativeContact == null
                || address == null) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        return new PatientInfo(
                firstName,
                lastName,
                sex,
                LocalDate.parse(birthdayText, formatter),
                bloodType,
                mainContact,
                alternativeContact,
                address
        );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel15 = new javax.swing.JPanel();
        stepProgress1 = new org.one.patientmanagement.ui.components.StepProgress();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        doctorsSelectionList = new org.one.patientmanagement.ui.components.FillWidthPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        dateLabel = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        todaySchedule = new org.one.patientmanagement.ui.components.TodaySchedule();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        blockPanel = new javax.swing.JPanel();
        morningCard = new org.one.patientmanagement.ui.components.DayOfWeekCard();
        afternoonCard = new org.one.patientmanagement.ui.components.DayOfWeekCard();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20));
        takePhoto2 = new org.one.patientmanagement.ui.components.TakePhoto();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20));
        birthdayField = new org.one.patientmanagement.ui.components.TextField();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20));
        jPanel10 = new javax.swing.JPanel();
        sexField = new org.one.patientmanagement.ui.components.TextField();
        bloodTypeField = new org.one.patientmanagement.ui.components.TextField();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        fillWidthPanel2 = new org.one.patientmanagement.ui.components.FillWidthPanel();
        firstNameField = new org.one.patientmanagement.ui.components.TextField();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20));
        lastNameField = new org.one.patientmanagement.ui.components.TextField();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20));
        addressField = new org.one.patientmanagement.ui.components.TextField();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20));
        mainContactField = new org.one.patientmanagement.ui.components.TextField();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20));
        alternativeContactField = new org.one.patientmanagement.ui.components.TextField();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20));
        jPanel1 = new javax.swing.JPanel();
        continueBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 248, 248));
        setMaximumSize(new java.awt.Dimension(1200, 800));
        setMinimumSize(new java.awt.Dimension(1200, 800));
        setLayout(new java.awt.BorderLayout());

        jPanel15.setOpaque(false);
        jPanel15.setLayout(new java.awt.GridBagLayout());

        stepProgress1.setMaximumSize(new java.awt.Dimension(162, 32803));
        stepProgress1.setName(""); // NOI18N
        stepProgress1.setPreferredSize(new java.awt.Dimension(162, 108));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 600;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        jPanel15.add(stepProgress1, gridBagConstraints);

        add(jPanel15, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 30, 0, 30));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBackground(new java.awt.Color(255, 240, 244));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 30, 30, 30));
        jPanel3.setMaximumSize(new java.awt.Dimension(2147483647, 115));
        jPanel3.setMinimumSize(new java.awt.Dimension(460, 300));
        jPanel3.setPreferredSize(new java.awt.Dimension(1250, 300));
        jPanel3.setLayout(new java.awt.BorderLayout(40, 0));

        jPanel6.setOpaque(false);
        jPanel6.setLayout(new java.awt.GridBagLayout());

        doctorsSelectionList.setLayout(new javax.swing.BoxLayout(doctorsSelectionList, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(doctorsSelectionList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(jScrollPane1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Poppins Medium", 0, 20)); // NOI18N
        jLabel2.setText("Select a Doctor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 25);
        jPanel6.add(jLabel2, gridBagConstraints);

        jPanel8.setBackground(new java.awt.Color(255, 219, 199));
        jPanel8.setLayout(new java.awt.GridLayout(1, 0));

        dateLabel.setFont(new java.awt.Font("Manrope Medium", 0, 13)); // NOI18N
        dateLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dateLabel.setText("jLabel3");
        jPanel8.add(dateLabel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        jPanel6.add(jPanel8, gridBagConstraints);

        jPanel3.add(jPanel6, java.awt.BorderLayout.EAST);

        jPanel7.setLayout(new java.awt.BorderLayout(0, 10));
        jPanel7.add(todaySchedule, java.awt.BorderLayout.CENTER);

        jLabel1.setFont(new java.awt.Font("Poppins Medium", 0, 25)); // NOI18N
        jLabel1.setText("Select a day");
        jPanel7.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jPanel3.add(jPanel7, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 12, 0);
        jPanel2.add(jPanel3, gridBagConstraints);

        jPanel4.setBackground(new java.awt.Color(255, 240, 244));
        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 30, 30, 30));
        jPanel4.setLayout(new java.awt.BorderLayout(0, 20));

        jLabel5.setFont(new java.awt.Font("Manrope", 0, 10)); // NOI18N
        jLabel5.setText("<html>Avail on the time that you are able to walk into the clinic. Failure to attend the appointment before the desired time ends will have the entire appointment be discarded.</html>\n");
        jPanel4.add(jLabel5, java.awt.BorderLayout.PAGE_END);

        blockPanel.setOpaque(false);
        blockPanel.setLayout(new java.awt.GridLayout(1, 0, 16, 0));

        morningCard.setEnabled(false);
        blockPanel.add(morningCard);

        afternoonCard.setEnabled(false);
        blockPanel.add(afternoonCard);

        jPanel4.add(blockPanel, java.awt.BorderLayout.CENTER);

        jLabel6.setFont(new java.awt.Font("Poppins Medium", 0, 25)); // NOI18N
        jLabel6.setText("Select a time of day");
        jPanel4.add(jLabel6, java.awt.BorderLayout.PAGE_START);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 12);
        jPanel2.add(jPanel4, gridBagConstraints);

        jPanel5.setBackground(new java.awt.Color(255, 240, 244));
        jPanel5.setLayout(new java.awt.BorderLayout(0, 20));

        jPanel11.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 30, 30, 30));
        jPanel11.setOpaque(false);
        jPanel11.setLayout(new java.awt.GridLayout(1, 0, 25, 20));

        jPanel12.setOpaque(false);
        jPanel12.setLayout(new javax.swing.BoxLayout(jPanel12, javax.swing.BoxLayout.Y_AXIS));

        jPanel14.setMaximumSize(new java.awt.Dimension(32767, 39));
        jPanel14.setOpaque(false);
        jPanel14.setLayout(new java.awt.GridLayout(1, 0));

        jLabel4.setFont(new java.awt.Font("Poppins Medium", 0, 25)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Select a time of day");
        jPanel14.add(jLabel4);

        jPanel12.add(jPanel14);
        jPanel12.add(filler8);

        takePhoto2.setMinimumSize(new java.awt.Dimension(149, 100));
        takePhoto2.setPreferredSize(new java.awt.Dimension(269, 100));
        jPanel12.add(takePhoto2);
        jPanel12.add(filler1);

        birthdayField.setMaximumSize(new java.awt.Dimension(2147483647, 75));
        birthdayField.setMinimumSize(new java.awt.Dimension(64, 75));
        birthdayField.setName(""); // NOI18N
        birthdayField.setPreferredSize(new java.awt.Dimension(92, 75));
        jPanel12.add(birthdayField);
        jPanel12.add(filler2);

        jPanel10.setMaximumSize(new java.awt.Dimension(32767, 75));
        jPanel10.setMinimumSize(new java.awt.Dimension(64, 75));
        jPanel10.setName(""); // NOI18N
        jPanel10.setPreferredSize(new java.awt.Dimension(64, 75));
        jPanel10.setLayout(new java.awt.GridLayout(1, 0, 15, 0));
        jPanel10.add(sexField);
        jPanel10.add(bloodTypeField);

        jPanel12.add(jPanel10);

        jPanel11.add(jPanel12);

        jPanel13.setOpaque(false);
        jPanel13.setLayout(new java.awt.BorderLayout());

        fillWidthPanel2.setLayout(new javax.swing.BoxLayout(fillWidthPanel2, javax.swing.BoxLayout.Y_AXIS));

        firstNameField.setMaximumSize(new java.awt.Dimension(2147483647, 75));
        firstNameField.setMinimumSize(new java.awt.Dimension(64, 75));
        firstNameField.setName(""); // NOI18N
        firstNameField.setPreferredSize(new java.awt.Dimension(92, 75));
        fillWidthPanel2.add(firstNameField);
        fillWidthPanel2.add(filler3);

        lastNameField.setMaximumSize(new java.awt.Dimension(2147483647, 75));
        lastNameField.setMinimumSize(new java.awt.Dimension(64, 75));
        lastNameField.setName(""); // NOI18N
        lastNameField.setPreferredSize(new java.awt.Dimension(92, 75));
        fillWidthPanel2.add(lastNameField);
        fillWidthPanel2.add(filler4);

        addressField.setMaximumSize(new java.awt.Dimension(2147483647, 75));
        addressField.setMinimumSize(new java.awt.Dimension(64, 75));
        addressField.setName(""); // NOI18N
        addressField.setPreferredSize(new java.awt.Dimension(92, 75));
        fillWidthPanel2.add(addressField);
        fillWidthPanel2.add(filler5);

        mainContactField.setMaximumSize(new java.awt.Dimension(2147483647, 75));
        mainContactField.setMinimumSize(new java.awt.Dimension(64, 75));
        mainContactField.setName(""); // NOI18N
        mainContactField.setPreferredSize(new java.awt.Dimension(92, 75));
        fillWidthPanel2.add(mainContactField);
        fillWidthPanel2.add(filler6);

        alternativeContactField.setMaximumSize(new java.awt.Dimension(2147483647, 75));
        alternativeContactField.setMinimumSize(new java.awt.Dimension(64, 75));
        alternativeContactField.setName(""); // NOI18N
        alternativeContactField.setPreferredSize(new java.awt.Dimension(92, 75));
        fillWidthPanel2.add(alternativeContactField);
        fillWidthPanel2.add(filler7);

        jScrollPane3.setViewportView(fillWidthPanel2);

        jPanel13.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel11.add(jPanel13);

        jPanel5.add(jPanel11, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jPanel5, gridBagConstraints);

        add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel1.setOpaque(false);

        continueBtn.setFont(new java.awt.Font("Manrope SemiBold", 0, 16)); // NOI18N
        continueBtn.setText("Continue");
        continueBtn.addActionListener(this::continueBtnActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(976, Short.MAX_VALUE)
                .addComponent(continueBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(continueBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void continueBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continueBtnActionPerformed
        controller.onComplete();
    }//GEN-LAST:event_continueBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.one.patientmanagement.ui.components.TextField addressField;
    private org.one.patientmanagement.ui.components.DayOfWeekCard afternoonCard;
    private org.one.patientmanagement.ui.components.TextField alternativeContactField;
    private org.one.patientmanagement.ui.components.TextField birthdayField;
    private javax.swing.JPanel blockPanel;
    private org.one.patientmanagement.ui.components.TextField bloodTypeField;
    private javax.swing.JButton continueBtn;
    private javax.swing.JLabel dateLabel;
    private org.one.patientmanagement.ui.components.FillWidthPanel doctorsSelectionList;
    private org.one.patientmanagement.ui.components.FillWidthPanel fillWidthPanel2;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private org.one.patientmanagement.ui.components.TextField firstNameField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private org.one.patientmanagement.ui.components.TextField lastNameField;
    private org.one.patientmanagement.ui.components.TextField mainContactField;
    private org.one.patientmanagement.ui.components.DayOfWeekCard morningCard;
    private org.one.patientmanagement.ui.components.TextField sexField;
    private org.one.patientmanagement.ui.components.StepProgress stepProgress1;
    private org.one.patientmanagement.ui.components.TakePhoto takePhoto2;
    private org.one.patientmanagement.ui.components.TodaySchedule todaySchedule;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setController(PatientSetupController controller) {
        this.controller = controller;
        
        controller.attachToStepProgressController(stepProgress1);
    }
}
