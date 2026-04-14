package org.one.patientmanagement.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nonnull;
import org.one.patientmanagement.domain.enums.AppointmentBlock;
import org.one.patientmanagement.domain.enums.AppointmentStatus;
import org.one.patientmanagement.domain.models.Appointment;

public interface AppointmentManager {

    Appointment schedule(@Nonnull Appointment appointment);

//    Optional<Appointment> getById(long id);

    List<Appointment> getAppointments();

    /**
     *
     * @param patientId can be null
     * @param doctorId can be null
     * @param status list of appointment status
     * @return list of consultations
     * @throws IllegalArgumentException if both {@code patientId} and {@code doctorId} are {@code null}
     */
    List<Appointment> getAppointments(long patientId, long doctorId, AppointmentStatus... status) throws IllegalArgumentException;
    
    List<Appointment> getAppointmentsToday(long doctorId, AppointmentStatus... status);
    
    List<Appointment> getAppointments(long doctorId, LocalDate date, AppointmentStatus... status);

    Appointment update(@Nonnull Appointment appointment);

    void delete(long appointmentId);
}
