/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.controller.patient;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.one.patientmanagement.ui.components.StepProgress;
import org.one.patientmanagement.ui.controller.AbstractController;
import org.one.patientmanagement.ui.view.PatientSignUp;

/**
 *
 * @author KAROL JOHN
 */
public class PatientSignupController extends AbstractController<PatientSignUp, PatientSignupController> {

    private final Provider<StepProgressController> stepProgressControllerProvider;
    
    @Inject
    public PatientSignupController(PatientSignUp view, Provider<StepProgressController> stepProgressControllerProvider) {
        this.stepProgressControllerProvider = stepProgressControllerProvider;
        
        super(view);
    }
    
    public void attachToStepProgressController(StepProgress c) {
        stepProgressControllerProvider.get().attachTo(c);
    }
}
