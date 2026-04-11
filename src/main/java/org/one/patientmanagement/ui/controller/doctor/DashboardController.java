/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.controller.doctor;

import com.google.inject.Inject;
import org.one.patientmanagement.ui.controller.AbstractController;
import org.one.patientmanagement.ui.view.DoctorDashboard;

/**
 *
 * @author KAROL JOHN
 */
public class DashboardController extends AbstractController<DoctorDashboard, DashboardController> {
    
    @Inject // TODO: maybe opt to provider
    public DashboardController(DoctorDashboard view) {
        super(view);
    }
}