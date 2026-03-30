package org.one.patientmanagement.domain.models;

import java.time.LocalDateTime;
import org.one.patientmanagement.domain.enums.ConsultationType;

/**
 * Represents a consultation of a patient.
 */
public record Consultation(
        long id,
        ConsultationType type,
        String title,
        String description,
        long doctorId,
        long patientId,
        LocalDateTime createdAt
) {
    public Consultation {
        if (type == null) {
            throw new IllegalArgumentException("type is required");
        }
        if (doctorId <= 0) {
            throw new IllegalArgumentException("doctorId must be valid");
        }
        if (patientId <= 0) {
            throw new IllegalArgumentException("patientId must be valid");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("createdAt is required");
        }
    }
}
