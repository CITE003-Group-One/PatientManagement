/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.controller.patient;

import com.google.inject.Inject;
import org.one.patientmanagement.ui.components.StepProgress;
import org.one.patientmanagement.ui.controller.AbstractComponentController;
import org.one.patientmanagement.ui.controller.navigation.patient.PatientFlowNavigator;

/**
 *
 * @author KAROL JOHN
 */
public class StepProgressController extends AbstractComponentController<StepProgress, StepProgressController>  {

    private final PatientFlowNavigator navigator;

    @Inject
    public StepProgressController(PatientFlowNavigator navigator) {
        this.navigator = navigator;
    }
    
    public void onBack() {
        navigator.back();
    }
    
    @Override
    public void onAttached() {
        // TODO step progress controller attached
    }
    
}
