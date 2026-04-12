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

    public Appointment {
        if (block == null) {
            throw new IllegalArgumentException("block is required");
        }

        if (status == null) {
            throw new IllegalArgumentException("status is required");
        }

        if (doctorId <= 0) {
            throw new IllegalArgumentException("doctorId must be valid");
        }

        if (patientId <= 0) {
            throw new IllegalArgumentException("patientId must be valid");
        }

        if (queueNumber == null || queueNumber.isBlank()) {
            throw new IllegalArgumentException("queueNumber is required");
        }

        if (createdAt == null) {
            throw new IllegalArgumentException("createdAt is required");
        }

        // business consistency rule
        if ((referred == null || referred.isBlank()) &&
            (referredDescription != null && !referredDescription.isBlank())) {
            throw new IllegalArgumentException(
                "referredDescription cannot exist without referred"
            );
        }
    }
    
    public String getFormattedQueue() {
        return block.getPrefix() + String.format("%02d", Integer.valueOf(queueNumber));
    }

    public boolean isReferred() {
        return referred != null && !referred.isBlank();
    }
}