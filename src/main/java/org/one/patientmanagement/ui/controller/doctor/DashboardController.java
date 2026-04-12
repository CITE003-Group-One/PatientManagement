/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.controller.doctor;

import com.google.inject.Inject;
import org.one.patientmanagement.domain.models.Schedule;
import org.one.patientmanagement.ui.controller.AbstractController;
import org.one.patientmanagement.ui.model.AppointmentListModel;
import org.one.patientmanagement.ui.view.DoctorDashboard;

/**
 *
 * @author KAROL JOHN
 */
public class DashboardController extends AbstractController<DoctorDashboard, DashboardController> {

    private final AppointmentListModel model;
    private final Schedule schedule;
    
    @Inject // TODO: maybe opt to provider
    public DashboardController(AppointmentListModel model, DoctorDashboard view, Schedule schedule) {
        super(view);
        this.model = model;
        this.schedule = schedule;
        
        init();
    }

    private void init() {
        view.getOverviewPanel().setListModel(model.getMorningItems(), model.getAfternoonItems(), schedule);
    }
}