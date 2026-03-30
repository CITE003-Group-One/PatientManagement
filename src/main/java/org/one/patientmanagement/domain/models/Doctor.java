package org.one.patientmanagement.domain.models;

/**
 * Represents a doctor.
 */
public record Doctor(
        long id,
        Long accountId,
        String name
) {}