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
) {
    public Vitals {
        if (patientId <= 0) {
            throw new IllegalArgumentException("patientId is required");
        }
        if (recordedAt == null) {
            throw new IllegalArgumentException("recordedAt is required");
        }

        boolean hasAnyVitals =
                systolicBp != null ||
                diastolicBp != null ||
                heartRate != null ||
                temperature != null ||
                weight != null ||
                height != null;

        if (!hasAnyVitals) {
            throw new IllegalArgumentException("At least one vital sign must be provided");
        }
    }
}
