/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.controller.patient;

import com.google.inject.Inject;
import org.one.patientmanagement.ui.controller.AbstractController;
import org.one.patientmanagement.ui.controller.navigation.patient.PatientFlowNavigator;
import org.one.patientmanagement.ui.model.PatientViewModel;
import org.one.patientmanagement.ui.view.PatientStartupView;

/**
 *
 * @author KAROL JOHN
 */
public class PatientStartupController extends AbstractController<PatientStartupView, PatientStartupController> {

    private final PatientFlowNavigator navigator;
    private final PatientViewModel model;
    
    @Inject
    public PatientStartupController(PatientStartupView view, PatientFlowNavigator navigator, PatientViewModel model) {
        super(view);
        
        this.navigator = navigator;
        this.model = model;
    }

    public void onExistingPatient() {
        model.setPatientType(PatientViewModel.PatientType.EXISTING);
        navigator.next();
    }

    public void onNewPatient() {
        model.setPatientType(PatientViewModel.PatientType.NEW);
        navigator.next();
    }
}
