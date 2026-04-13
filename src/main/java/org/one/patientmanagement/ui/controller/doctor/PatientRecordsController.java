/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.controller.doctor;

import com.google.inject.Inject;
import javax.swing.SwingUtilities;
import org.one.patientmanagement.domain.models.Patient;
import org.one.patientmanagement.service.PatientManager;
import org.one.patientmanagement.ui.controller.AbstractController;
import org.one.patientmanagement.ui.core.factory.DoctorPatientDashboardDialogControllerFactory;
import org.one.patientmanagement.ui.view.DoctorPatientRecords;

/**
 *
 * @author KAROL JOHN
 */
public class PatientRecordsController extends AbstractController<DoctorPatientRecords, PatientRecordsController> {

    private final PatientManager patientManager;
    private final DoctorPatientDashboardDialogControllerFactory dialogFactory;

    @Inject
    public PatientRecordsController(DoctorPatientRecords view, PatientManager patientManager, DoctorPatientDashboardDialogControllerFactory dialogFactory) {
        this.patientManager = patientManager;
        this.dialogFactory = dialogFactory;
        
        super(view);
        
        loadPatients();
        
        view.setRowClickListener(l -> onRowClick(l));
    }

    private void loadPatients() {
        view.loadPatients(patientManager.getPatients());
    }

    private void onRowClick(Patient p) {
        dialogFactory.create(SwingUtilities.getWindowAncestor(view)).openDashboard().setPatient(p);
    }
    
    
}
