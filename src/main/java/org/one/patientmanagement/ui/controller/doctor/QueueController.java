/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.controller.doctor;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.one.patientmanagement.domain.dto.QueueData;
import org.one.patientmanagement.domain.enums.AppointmentBlock;
import org.one.patientmanagement.domain.enums.AppointmentStatus;
import org.one.patientmanagement.domain.models.Appointment;
import org.one.patientmanagement.domain.models.Doctor;
import org.one.patientmanagement.domain.models.Patient;
import org.one.patientmanagement.domain.models.Schedule;
import org.one.patientmanagement.service.AppointmentManager;
import org.one.patientmanagement.service.PatientManager;
import org.one.patientmanagement.ui.components.QueueListContainer;
import org.one.patientmanagement.ui.controller.AbstractComponentController;
import org.one.patientmanagement.ui.controller.AbstractController;
import org.one.patientmanagement.ui.view.DoctorPatientQueue;

/**
 *
 * @author KAROL JOHN
 */
// TODO selection of day of week from the calendar
public class QueueController extends AbstractController<DoctorPatientQueue, QueueController> {
    
    private final Provider<QueueListController> queueListControllerProvider;
    private DayOfWeek dayOfWeek;

    
    // TODO called using on selection from the calendar
    private void loadQueue(Schedule schedule, List<QueueData> queues) {
        var withDoctor = queues.stream()
                .filter(q -> q.status() == AppointmentStatus.WITH_DOCTOR)
                .collect(Collectors.toMap(QueueData::block, q -> q));

        view.loadQueue(AppointmentBlock.MORNING, schedule, withDoctor.get(AppointmentBlock.MORNING));
        view.loadQueue(AppointmentBlock.AFTERNOON, schedule, withDoctor.get(AppointmentBlock.AFTERNOON));
    }

    @Inject // TODO: maybe opt to provider
    public QueueController(DoctorPatientQueue view, Schedule schedule, Provider<QueueListController> queueListControllerProvider) {
        super(view);

        this.queueListControllerProvider = queueListControllerProvider;
    }

    public QueueListController attachQueueListController(QueueListContainer view) {
        final QueueListController controller = queueListControllerProvider.get();
        controller.attachTo(view);
        controller.setDayOfWeek(dayOfWeek);

        return controller;
    }

    public static class QueueListController extends AbstractComponentController<QueueListContainer, QueueListController> {

        private final AppointmentManager appointmentManager;
        private final Doctor doctor;
        private DayOfWeek dayOfWeek;
        private final PatientManager patientManager;
        private List<QueueData> queues;
        private AppointmentStatus status;

        @Inject // TODO: maybe opt to provider
        public QueueListController(Doctor doctor, AppointmentManager appointmentManager, PatientManager patientManager) {
            this.appointmentManager = appointmentManager;
            this.patientManager = patientManager;
            this.doctor = doctor;
        }

        public void setDayOfWeek(DayOfWeek dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
        }

        public void loadQueueList(AppointmentStatus status) {
            this.status = status;
            var appointments = appointmentManager.getAppointments(doctor.id(), dayOfWeek, status);

            var ids = appointments.stream().map(Appointment::patientId).toList();
            var patientMap = patientManager.getAllByIds(ids).stream()
                    .collect(Collectors.toMap(Patient::id, p -> p));

            queues = appointments.stream()
                    .map(a -> {
                        var patient = patientMap.get(a.patientId());
                        return new QueueData(
                                patient.getFullName(),
                                a.queueNumber(),
                                patient.sex(),
                                patient.getSchemedId(),
                                a.status(),
                                a.block()
                        );
                    })
                    .toList();

            view.loadQueue(status, queues);
        }

        public void loadQueueInfo() {
            view.setInfo(status, queues.size());
        }

        @Override
        public void onAttached() {
        }

        public void onSearch(String query) {
            String q = query.toLowerCase().trim();

            var filtered = queues.stream()
                    .filter(p -> p.fullName() != null
                        && p.fullName().toLowerCase().contains(q))
                    .toList();

            view.loadQueue(this.status, filtered);
        }
    }
}
