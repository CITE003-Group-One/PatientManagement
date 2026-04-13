/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.controller.patient;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import org.one.patientmanagement.domain.dto.ScheduleOfDoctor;
import org.one.patientmanagement.domain.enums.AppointmentBlock;
import org.one.patientmanagement.service.DoctorManager;
import org.one.patientmanagement.ui.components.StepProgress;
import org.one.patientmanagement.ui.controller.AbstractController;
import org.one.patientmanagement.ui.model.PatientViewModel;
import org.one.patientmanagement.ui.view.PatientSetup;

/**
 *
 * @author KAROL JOHN
 */
public class PatientSetupController extends AbstractController<PatientSetup, PatientSetupController> {

    private final DoctorManager doctorManager;
    private final Map<DayOfWeek, List<ScheduleOfDoctor>> weekScheduleOfDoctors;
    private final PatientViewModel model;
    private final Provider<StepProgressController> stepProgressControllerProvider;
    
    @Inject
    public PatientSetupController(PatientSetup view, DoctorManager doctorManager, PatientViewModel model, Provider<StepProgressController> stepProgressControllerProvider) {
        this.stepProgressControllerProvider = stepProgressControllerProvider;
        this.doctorManager = doctorManager;
        this.model = model;
        
        super(view);
        
        weekScheduleOfDoctors = doctorManager.getWeekScheduleOfDoctors();
        
        view.loadSchedule(weekScheduleOfDoctors);
        
        // TODO load if data in model is available
    }
    
    public void attachToStepProgressController(StepProgress c) {
        stepProgressControllerProvider.get().attachTo(c);
    }

    public void onDaySelect(LocalDate d) {
        view.setDate(d.format(DateTimeFormatter.ofPattern("MMM dd")));
        view.loadDoctorSelection(weekScheduleOfDoctors.get(d.getDayOfWeek()));
    }
    
    // used to populate model
    public void onTimeSelect(AppointmentBlock block) {
        //  assume that in model is already present
    }

    public void onComplete() {
        // TODO evaluate view#haveSelected in boolean and view#getPatientInfo if null
        // TODO get the mode#patientinfo
        
        // TODO access the service to write in db
    }

    // used to populate model
    public void onDoctorSelect(ScheduleOfDoctor sod) {
        view.setBlock(sod.schedule());
        
    }
}
