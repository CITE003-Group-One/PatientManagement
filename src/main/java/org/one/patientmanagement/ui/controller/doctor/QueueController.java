/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.controller.doctor;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.one.patientmanagement.ui.core.dto.QueueData;
import org.one.patientmanagement.domain.enums.AppointmentBlock;
import org.one.patientmanagement.domain.enums.AppointmentStatus;
import org.one.patientmanagement.domain.models.Appointment;
import org.one.patientmanagement.domain.models.Doctor;
import org.one.patientmanagement.domain.models.Patient;
import org.one.patientmanagement.domain.models.Schedule;
import org.one.patientmanagement.service.AppointmentManager;
import org.one.patientmanagement.service.DoctorManager;
import org.one.patientmanagement.service.PatientManager;
import org.one.patientmanagement.ui.components.ClickablePanel;
import org.one.patientmanagement.ui.components.ClickablePanel.ClickListenerObj;
import org.one.patientmanagement.ui.components.QueueListContainer;
import org.one.patientmanagement.ui.controller.AbstractComponentController;
import org.one.patientmanagement.ui.controller.AbstractController;
import org.one.patientmanagement.ui.core.factory.DoctorPatientDashboardDialogControllerFactory;
import org.one.patientmanagement.ui.model.DoctorViewModel;
import org.one.patientmanagement.ui.view.DoctorPatientQueue;

/**
 *
 * @author KAROL JOHN
 */
public class QueueController extends AbstractController<DoctorPatientQueue, QueueController> {

    private final Provider<QueueListController> queueListControllerProvider;
    private DayOfWeek dayOfWeek; // TODO selection of day of week from the calendar
    private final DoctorManager doctorManager;
    private final DoctorViewModel model;
    private final AppointmentManager appointmentManager;
    private final PatientManager patientManager;
    private final DoctorPatientDashboardDialogControllerFactory dialogFactory;

    // TODO called using on selection from the calendar
    private void loadQueue(Schedule schedule, List<QueueData> queues) {
        Map<AppointmentBlock, QueueData> withDoctor = new EnumMap<>(AppointmentBlock.class);
        Map<AppointmentBlock, List<QueueData>> groupedQueues = new EnumMap<>(AppointmentBlock.class);

        for (QueueData q : queues) {
            if (q.status() == AppointmentStatus.WITH_DOCTOR) {
                withDoctor.put(q.block(), q);
            } else {
                groupedQueues.computeIfAbsent(q.block(), k -> new ArrayList<>()).add(q);
            }
        }

        view.loadQueue(AppointmentBlock.MORNING, schedule, withDoctor.get(AppointmentBlock.MORNING), groupedQueues.get(AppointmentBlock.MORNING));
        view.loadQueue(AppointmentBlock.AFTERNOON, schedule, withDoctor.get(AppointmentBlock.AFTERNOON), groupedQueues.get(AppointmentBlock.AFTERNOON));
    }

    @Inject // TODO: maybe opt to provider
    public QueueController(DoctorPatientQueue view, Provider<QueueListController> queueListControllerProvider, DoctorViewModel model,
            PatientManager patientManager, DoctorManager doctorManager, AppointmentManager appointmentManager, DoctorPatientDashboardDialogControllerFactory dialogFactory) {
        this.queueListControllerProvider = queueListControllerProvider;
        this.doctorManager = doctorManager;
        this.appointmentManager = appointmentManager;
        this.patientManager = patientManager;
        this.dialogFactory = dialogFactory;
        this.model = model;

        super(view);

        view.loadSchedules(doctorManager.getSchedules(model.getDoctor().id()));
        view.setDaySelectListener(l -> onDaySelect(l));
        view.setRowClickListener(l -> onRowClick(l));
    }

    public QueueListController attachQueueListController(QueueListContainer view) {
        final QueueListController controller = queueListControllerProvider.get();
        controller.attachTo(view);
        controller.setDayOfWeek(dayOfWeek);
        controller.setRowClickListener(l -> {
            onRowClick(l);
        });

        return controller;
    }

    private void onDaySelect(LocalDate l) {
        doctorManager.getSchedules(model.getDoctor().id()).stream()
                .filter(s -> s.day() == l.getDayOfWeek()).findFirst().ifPresent(schedule -> {
            var appointments = appointmentManager.getAppointments(model.getDoctor().id(), l.getDayOfWeek(), AppointmentStatus.values());

            var patientIds = appointments.stream().map(Appointment::patientId).toList();
            var patientMap = patientManager.getAllByIds(patientIds).stream()
                    .collect(Collectors.toMap(Patient::id, p -> p));

            var queueData = appointments.stream().map(q -> {
                var patient = patientMap.get(q.patientId());
                return new QueueData(
                        patient.id(),
                        patient.firstName(), // TODO use full name instead
                        q.getFormattedQueue(),
                        patient.sex(),
                        patient.getSchemedId(),
                        q.status(),
                        q.block()
                );
            }).toList();

            loadQueue(schedule, queueData);
        });
    }

    private void onRowClick(QueueData l) {
        patientManager.getById(l.patientId()).ifPresent(patient -> {

            dialogFactory.create(SwingUtilities.getWindowAncestor(view)).openDashboard().setPatient(patient);
        });
    }

    public static class QueueListController extends AbstractComponentController<QueueListContainer, QueueListController> {

        private ClickListenerObj<QueueData> clickListener;
        private final AppointmentManager appointmentManager;
        private final Doctor doctor;
        private DayOfWeek dayOfWeek;
        private final PatientManager patientManager;
        private List<QueueData> queues;
        private AppointmentStatus status;

        @Inject // TODO: maybe opt to provider
        public QueueListController(DoctorViewModel model, AppointmentManager appointmentManager, PatientManager patientManager) {
            this.appointmentManager = appointmentManager;
            this.patientManager = patientManager;
            this.doctor = model.getDoctor();

            view.setRowClickListener(l -> {
                clickListener.onClick(l);
            });
        }

        public void setRowClickListener(ClickListenerObj<QueueData> clickListener) {
            this.clickListener = clickListener;
        }

        public void setDayOfWeek(DayOfWeek dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
        }

        // ought to be loaded first 
        public void loadQueueList(AppointmentStatus status, List<QueueData> queues) {
            this.status = status;

            this.queues = queues;
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
