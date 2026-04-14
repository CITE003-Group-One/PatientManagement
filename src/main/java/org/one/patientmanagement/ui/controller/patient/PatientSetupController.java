/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.controller.patient;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import org.one.patientmanagement.ui.core.dto.ScheduleOfDoctor;
import org.one.patientmanagement.domain.enums.AppointmentBlock;
import org.one.patientmanagement.domain.enums.AppointmentStatus;
import org.one.patientmanagement.domain.enums.ConsultationType;
import org.one.patientmanagement.domain.models.Appointment;
import org.one.patientmanagement.domain.models.Consultation;
import org.one.patientmanagement.domain.models.Patient;
import org.one.patientmanagement.service.AccountManager;
import org.one.patientmanagement.service.AppointmentManager;
import org.one.patientmanagement.service.ConsultationService;
import org.one.patientmanagement.service.DoctorManager;
import org.one.patientmanagement.service.PatientManager;
import org.one.patientmanagement.ui.components.StepProgress;
import org.one.patientmanagement.ui.controller.AbstractController;
import org.one.patientmanagement.ui.controller.navigation.patient.PatientFlowNavigator;
import org.one.patientmanagement.ui.core.dto.AppointmentInfo;
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
    private final PatientFlowNavigator navigator;
    private ScheduleOfDoctor selectedDoctor;
    private final AppointmentManager appointmentManager;
    private final PatientManager patientManager;
    private final AccountManager accountManager;
    private final ConsultationService consultationService;

    @Inject
    public PatientSetupController(PatientSetup view, DoctorManager doctorManager, PatientViewModel model, Provider<StepProgressController> stepProgressControllerProvider,
            PatientFlowNavigator navigator, PatientManager patientManager, AppointmentManager appointmentManager, AccountManager accountManager, ConsultationService consultationService) {
        this.stepProgressControllerProvider = stepProgressControllerProvider;
        super(view);
        this.appointmentManager = appointmentManager;
        this.patientManager = patientManager;
        this.doctorManager = doctorManager;
        this.navigator = navigator;
        this.accountManager = accountManager;
        this.consultationService = consultationService;
        this.model = model;

        weekScheduleOfDoctors = doctorManager.getWeekScheduleOfDoctors();
        view.loadSchedule(weekScheduleOfDoctors);

        view.setMainContact(model.getAccount().user());
    }

    public void attachToStepProgressController(StepProgress c) {
        stepProgressControllerProvider.get().attachTo(c);
    }

    public void onDaySelect(LocalDate d) {
        view.setDate(d.format(DateTimeFormatter.ofPattern("MMM dd")));
        view.loadDoctorSelection(weekScheduleOfDoctors.get(d.getDayOfWeek()));
        selectedDoctor = null;
        model.setAppointmentInfo(null);
    }

    // used to populate model
    public void onTimeSelect(AppointmentBlock block) {
        if (selectedDoctor == null) {
            return;
        }
        model.setAppointmentInfo(new AppointmentInfo(block, selectedDoctor.doctor().id(), 0L));
    }

    public void onComplete() {
        if (!view.haveSelected()) {
            JOptionPane.showMessageDialog(
                    view,
                    "Please select a schedule first.",
                    "Missing Selection",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        var appointment = model.getAppointmentInfo();
        var patientInfo = view.getPatientInfo();
        if (appointment == null || patientInfo == null) {
            JOptionPane.showMessageDialog(
                    view,
                    "Incomplete appointment or patient information.",
                    "Missing Data",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int result = JOptionPane.showConfirmDialog(view, "Are you sure you want to confirm this appointment?", "Confirm Appointment", JOptionPane.YES_NO_OPTION);
        if (result != JOptionPane.YES_OPTION) {
            return;
        }

        model.setPatientInfo(patientInfo);

        model.setAccount(accountManager.register(model.getAccount()));

        var patient = patientManager.create(new Patient(
                0L,
                model.getAccount().id(),
                patientInfo.firstName(),
                patientInfo.lastName(),
                patientInfo.sex(),
                patientInfo.birthday(),
                patientInfo.bloodType(),
                patientInfo.alternativeContact(),
                patientInfo.address()
        ));
        
        model.setPatient(patient);

        model.setAppointment(appointmentManager.schedule(new Appointment(
                0L,
                appointment.block(),
                AppointmentStatus.WAITING,
                null,
                null,
                selectedDoctor.doctor().id(),
                patient.id(),
                "01", // TODO change to real queue #
                LocalDateTime.now()
        )));
        
        consultationService.create(new Consultation(
                0L,
                ConsultationType.GENERAL,
                model.getServiceSelection() + " appointment",
                "",
                selectedDoctor.doctor().id(),
                patient.id(),
                LocalDateTime.now()
        ));

        navigator.next();

    }

    // used to populate model
    public void onDoctorSelect(ScheduleOfDoctor sod) {
        selectedDoctor = sod;
        view.setBlock(sod.schedule());
        model.setAppointmentInfo(null);
    }
}
