/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.controller.patient;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.Arrays;
import javax.swing.JOptionPane;
import org.one.patientmanagement.service.AccountManager;
import org.one.patientmanagement.service.PatientManager;
import org.one.patientmanagement.ui.components.StepProgress;
import org.one.patientmanagement.ui.controller.AbstractController;
import org.one.patientmanagement.ui.controller.navigation.patient.PatientFlowNavigator;
import org.one.patientmanagement.ui.core.dto.AppointmentInfo;
import org.one.patientmanagement.ui.core.dto.PatientInfo;
import org.one.patientmanagement.ui.model.PatientViewModel;
import org.one.patientmanagement.ui.view.PatientLogin;

/**
 *
 * @author KAROL JOHN
 */
public class PatientLoginController extends AbstractController<PatientLogin, PatientLoginController> {

    private final Provider<StepProgressController> stepProgressControllerProvider;
    private final AccountManager accountManager;
    private final PatientViewModel model;
    private final PatientFlowNavigator navigator;
    private final PatientManager patientManager;

    @Inject
    public PatientLoginController(PatientLogin view, PatientViewModel model, Provider<StepProgressController> stepProgressControllerProvider, PatientManager patientMaanger, PatientFlowNavigator navigator, AccountManager accountManager) {
        this.stepProgressControllerProvider = stepProgressControllerProvider;

        super(view);
        this.model = model;
        this.accountManager = accountManager;
        this.navigator = navigator;
        this.patientManager = patientMaanger;
    }

    public void attachToStepProgressController(StepProgress c) {
        stepProgressControllerProvider.get().attachTo(c);
    }

    public void onLogin(String username, String password) {
        
        accountManager.authenticate(username, password).ifPresentOrElse(account ->
                model.setAccount(account),
                () -> JOptionPane.showMessageDialog(view, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE));
        
        patientManager.getByAccountId(model.getAccount().id()).ifPresent(patient -> {

            model.setPatient(patient);
            navigator.next();
            
        });
        
    }
}
