package org.one.patientmanagement.domain.models;

import java.time.LocalDateTime;

/**
 * Represents a vitals of a patient.
 */
public record Vitals(
        long id,
        Integer systolicBp,
        Integer diastolicBp,
        Integer heartRate,
        Double temperature,
        Double weight,
        Double height,
        long patientId,
        LocalDateTime recordedAt
) {}
