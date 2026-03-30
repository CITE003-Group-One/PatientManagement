package org.one.patientmanagement.domain.models;

import java.time.LocalDate;
import java.time.Period;

/**
 * Represents a patient.
 */
public record Patient(
        long id,
        Long accountId,
        String name,
        String sex,
        LocalDate birthday,
        String bloodType,
        String contactNumber,
        String email,
        String address
) {
    public int getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }
}