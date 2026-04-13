/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.controller.patient;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.one.patientmanagement.ui.components.StepProgress;
import org.one.patientmanagement.ui.controller.AbstractController;
import org.one.patientmanagement.ui.model.PatientViewModel;
import org.one.patientmanagement.ui.view.ServiceSelectionView;

/**
 *
 * @author KAROL JOHN
 */
public class PatientServiceSelectionController extends AbstractController<ServiceSelectionView, PatientServiceSelectionController> {

    private final PatientViewModel model;
    private final Provider<StepProgressController> stepProgressControllerProvider;
    
    @Inject
    public PatientServiceSelectionController(ServiceSelectionView view, PatientViewModel model, Provider<StepProgressController> stepProgressControllerProvider) {
        this.model = model;
        this.stepProgressControllerProvider = stepProgressControllerProvider;
       
        super(view);
        
        view.setSelection(model.getServiceSelection());
    }

    public void setServiceSelection(PatientViewModel.ServiceSelection service) {
        model.setServiceSelection(service);
    }
    
    public void attachToStepProgressController(StepProgress c) {
        stepProgressControllerProvider.get().attachTo(c);
    }
}
