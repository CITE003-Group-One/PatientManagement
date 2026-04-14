/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.controller.patient;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import org.one.patientmanagement.domain.enums.Role;
import org.one.patientmanagement.domain.models.Account;
import org.one.patientmanagement.service.AccountManager;
import org.one.patientmanagement.ui.components.StepProgress;
import org.one.patientmanagement.ui.controller.AbstractController;
import org.one.patientmanagement.ui.controller.navigation.patient.PatientFlowNavigator;
import org.one.patientmanagement.ui.model.PatientViewModel;
import org.one.patientmanagement.ui.view.PatientSignUp;

/**
 *
 * @author KAROL JOHN
 */
public class PatientSignupController extends AbstractController<PatientSignUp, PatientSignupController> {

    private final Provider<StepProgressController> stepProgressControllerProvider;
    private final PatientViewModel model;
    private final PatientFlowNavigator navigator;

    @Inject
    public PatientSignupController(PatientSignUp view, PatientViewModel model, PatientFlowNavigator navigator, Provider<StepProgressController> stepProgressControllerProvider) {
        this.stepProgressControllerProvider = stepProgressControllerProvider;

        super(view);
        
        this.navigator = navigator;
        this.model = model;
    }

    public void attachToStepProgressController(StepProgress c) {
        stepProgressControllerProvider.get().attachTo(c);
    }

    public void onSignUp(String username, String password) {
        // the actual sign up is in the setup continue button
        model.setAccount(new Account(
                0L,
                username,
                password,
                Role.PATIENT,
                LocalDateTime.now()
        ));
        
        navigator.next();
    }
}
