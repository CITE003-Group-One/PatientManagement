/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.controller.doctor;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import java.awt.Window;
import org.one.patientmanagement.ui.controller.AbstractController;
import org.one.patientmanagement.ui.core.factory.DoctorPatientDashboardDialogFactory;
import org.one.patientmanagement.ui.view.dialog.DoctorPatientDashboardDialog;

/**
 *
 * @author KAROL JOHN
 */
public class DoctorPatientDashboardDialogController extends AbstractController<DoctorPatientDashboardDialog, DoctorPatientDashboardDialogController> {
    
    @AssistedInject
    public DoctorPatientDashboardDialogController(
            DoctorPatientDashboardDialogFactory dialogFactory, 
            @Assisted Window parent) {
        super(dialogFactory.create(parent));
    }
    
    public DoctorPatientDashboardController openDashboard() {
        view.setVisible(true);
        return view.getViewController();
    }
}
