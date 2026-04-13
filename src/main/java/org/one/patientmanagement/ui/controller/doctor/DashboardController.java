/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.controller.doctor;

import com.google.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.SwingUtilities;
import org.one.patientmanagement.domain.enums.AppointmentBlock;
import org.one.patientmanagement.domain.enums.AppointmentStatus;
import org.one.patientmanagement.domain.models.Appointment;
import org.one.patientmanagement.domain.models.Patient;
import org.one.patientmanagement.domain.models.Schedule;
import org.one.patientmanagement.service.AppointmentManager;
import org.one.patientmanagement.service.DoctorManager;
import org.one.patientmanagement.service.PatientManager;
import org.one.patientmanagement.ui.controller.AbstractController;
import org.one.patientmanagement.ui.core.dto.QueueData;
import org.one.patientmanagement.ui.core.factory.DoctorPatientDashboardDialogControllerFactory;
import org.one.patientmanagement.ui.model.AppointmentListModel;
import org.one.patientmanagement.ui.model.DoctorViewModel;
import org.one.patientmanagement.ui.view.DoctorDashboard;

/**
 *
 * @author KAROL JOHN
 */
public class DashboardController extends AbstractController<DoctorDashboard, DashboardController> {

    private final DoctorViewModel model;
    private final DoctorManager doctorManager;
    private final AppointmentManager appointmentManager;
    private final PatientManager patientManager;
    private Map<AppointmentBlock, List<QueueData>> scheduleMap;
    private final DoctorPatientDashboardDialogControllerFactory dialogFactory;

    @Inject // TODO: maybe opt to provider
    public DashboardController(DoctorViewModel model, DoctorDashboard view, DoctorManager doctorManager, AppointmentManager appointmentManager, PatientManager patientManager,
            DoctorPatientDashboardDialogControllerFactory dialogFactory) {
        this.model = model;
        this.doctorManager = doctorManager;
        this.appointmentManager = appointmentManager;
        this.patientManager = patientManager;
        this.dialogFactory = dialogFactory;

        super(view);

        loadListModel();
        loadStats();
        loadSchedules();

        view.getOverviewPanel().setRowClickListener(e -> onRowClick(e));
    }

    // Load the schedule map from here
    private void loadListModel() {
        var schedule = doctorManager.getSchedules(model.getDoctor().id()).stream()
                .filter(s -> s.day() == LocalDateTime.now().getDayOfWeek())
                .findFirst()
                .orElse(null);
        if (schedule == null) {
            return;
        }

        var appointments = appointmentManager.getAppointmentsToday(model.getDoctor().id(), AppointmentStatus.values());
        var patientIds = appointments.stream().map(Appointment::patientId).toList();
        var patientMap = patientManager.getAllByIds(patientIds).stream()
                .collect(Collectors.toMap(Patient::id, p -> p));
        scheduleMap = appointments.stream()
                .collect(Collectors.groupingBy(
                        Appointment::block,
                        Collectors.mapping(a -> {
                            var patient = patientMap.get(a.patientId());
                            return new QueueData(
                                    patient.id(),
                                    patient.firstName(), // TODO provide the full name instead
                                    a.getFormattedQueue(),
                                    patient.sex(),
                                    patient.getSchemedId(),
                                    a.status(),
                                    a.block()
                            );
                        }, Collectors.toList())
                ));

        view.getOverviewPanel().setListModel(scheduleMap.get(AppointmentBlock.MORNING), scheduleMap.get(AppointmentBlock.AFTERNOON), schedule);
    }

    private void loadStats() {
        var all = scheduleMap.values().stream()
                .flatMap(List::stream)
                .toList();

        var completedCount = (int) all.stream()
                .filter(q -> q.status() == AppointmentStatus.DONE)
                .count();

        var activeCount = (int) all.stream()
                .filter(q -> q.status() == AppointmentStatus.WAITING
                || q.status() == AppointmentStatus.WITH_DOCTOR)
                .count();

        var totalCount = (int) all.size();

        view.setCards(totalCount, completedCount, activeCount);
    }

    private void loadSchedules() {
        view.setSchedule(doctorManager.getSchedules(model.getDoctor().id()));
    }

    private void onRowClick(QueueData e) {
        patientManager.getById(e.patientId()).ifPresent(patient -> {

            dialogFactory.create(SwingUtilities.getWindowAncestor(view)).openDashboard().setPatient(patient);
        });
    }
}
