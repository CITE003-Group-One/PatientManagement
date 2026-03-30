package org.one.patientmanagement.domain.models;

import java.time.LocalDateTime;

/**
 * Represents the prescription of a patient.
 */
public record Prescription(
        long id,
        String medicationName,
        String dosage,
        String frequency,
        String duration,
        String instructions,
        long doctorId,
        long patientId,
        LocalDateTime createdAt
) {
    public Prescription {
        if (medicationName == null || medicationName.isBlank()) {
            throw new IllegalArgumentException("medicationName is required");
        }
        if (dosage == null || dosage.isBlank()) {
            throw new IllegalArgumentException("dosage is required");
        }
        if (frequency == null || frequency.isBlank()) {
            throw new IllegalArgumentException("frequency is required");
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