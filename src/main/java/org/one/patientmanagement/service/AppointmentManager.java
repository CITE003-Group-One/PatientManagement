package org.one.patientmanagement.service;

import java.util.List;
import java.util.Optional;
import org.one.patientmanagement.domain.enums.AppointmentBlock;
import org.one.patientmanagement.domain.enums.AppointmentStatus;
import org.one.patientmanagement.domain.models.Appointment;

public interface AppointmentManager {

    Appointment schedule(Appointment appointment);

    Optional<Appointment> getById(long id);

    List<Appointment> getAppointments();

    List<Appointment> getAppointments(long patientId, long doctorId, AppointmentStatus... status);

    Appointment update(Appointment appointment);

    void delete(long appointmentId);

    boolean isDoctorAvailable(long doctorId, AppointmentBlock block);
}
