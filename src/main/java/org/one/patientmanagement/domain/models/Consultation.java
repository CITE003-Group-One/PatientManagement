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
) {}
