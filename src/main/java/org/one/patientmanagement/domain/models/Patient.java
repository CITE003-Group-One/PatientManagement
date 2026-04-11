package org.one.patientmanagement.domain.models;

import java.time.LocalDate;
import java.time.Period;

/**
 * Represents a patient.
 */
public record Patient(
        long id,
        Long accountId,
        String firstName,
        String lastName,
        String sex,
        LocalDate birthday,
        String bloodType,
        String contactNumber,
        String email,
        String address
) {
    public Patient {
        if (accountId == null || accountId <= 0) {
            throw new IllegalArgumentException("accountId is required");
        }
        if (birthday == null) {
            throw new IllegalArgumentException("birthday is required");
        }

        if ((email == null || email.isBlank()) &&
            (contactNumber == null || contactNumber.isBlank())) {
            throw new IllegalArgumentException(
                "Either email or contact number must be provided"
            );
        }
    }

    public int getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }
}