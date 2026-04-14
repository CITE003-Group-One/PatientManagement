package org.one.patientmanagement.service.impl;

import com.google.inject.Inject;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

import org.one.patientmanagement.domain.enums.AppointmentBlock;
import org.one.patientmanagement.domain.enums.AppointmentStatus;
import org.one.patientmanagement.domain.models.Appointment;
import org.one.patientmanagement.repository.AppointmentRepository;
import org.one.patientmanagement.service.AppointmentManager;

public class AppointmentManagerImpl implements AppointmentManager {

    private final AppointmentRepository repo;

    @Inject
    public AppointmentManagerImpl(AppointmentRepository repo) {
        this.repo = repo;
    }

    @Override
    public Appointment schedule(Appointment appointment) {
        return repo.save(appointment);
    }

    @Override
    public List<Appointment> getAppointments() {
        return repo.findAll();
    }

    @Override
    public List<Appointment> getAppointments(long patientId, long doctorId, AppointmentStatus... status)
            throws IllegalArgumentException {
        if (patientId <= 0 && doctorId <= 0) {
            throw new IllegalArgumentException("Must provide at least a Patient or Doctor ID.");
        }
        return repo.findAll(patientId, doctorId, status);
    }

    @Override
    public Appointment update(Appointment appointment) {
        // Updates status when Doctor clicks 'With Doctor' or 'Done' in Doctor's View
        return repo.save(appointment);
    }

    @Override
    public void delete(long appointmentId) {
        repo.delete(appointmentId);
    }

    @Override
    public List<Appointment> getAppointmentsToday(long doctorId, AppointmentStatus... status) {
        return repo.findAllDay(doctorId, LocalDate.now(), status);
    }

    @Override
    public List<Appointment> getAppointments(long doctorId, LocalDate date, AppointmentStatus... status) {
        return repo.findAllDay(doctorId, date, status);
    }
}
