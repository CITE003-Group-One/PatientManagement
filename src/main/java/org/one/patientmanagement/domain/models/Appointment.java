package org.one.patientmanagement.domain.models;

import java.time.LocalDateTime;
import org.one.patientmanagement.domain.enums.AppointmentBlock;
import org.one.patientmanagement.domain.enums.AppointmentStatus;

/**
 * Represents an appointment.
 */
public record Appointment(
        long id,
        AppointmentBlock block,
        AppointmentStatus status,
        String referred,
        String referredDescription,
        long doctorId,
        long patientId,
        String queueNumber,
        LocalDateTime createdAt
) {
    public boolean isReferred() {
        return referred == null || referred.isBlank();
    }
}