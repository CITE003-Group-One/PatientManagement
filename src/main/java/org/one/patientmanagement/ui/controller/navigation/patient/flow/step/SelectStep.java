/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.controller.navigation.patient.flow.step;

import org.one.patientmanagement.ui.controller.navigation.patient.flow.FlowState;
import org.one.patientmanagement.ui.controller.navigation.patient.flow.FlowStep;
import org.one.patientmanagement.ui.controller.navigation.patient.flow.PatientRoute;
import org.one.patientmanagement.ui.model.PatientViewModel;

/**
 *
 * @author KAROL JOHN
 */
public class SelectStep implements FlowStep<PatientRoute, PatientViewModel> {

    @Override
    public PatientRoute getRoute() {
        return PatientRoute.SELECTION;
    }

    @Override
    public FlowStep<PatientRoute, PatientViewModel> next(FlowState<PatientViewModel> state) {
        return new SetupStep();
    }
    
}
