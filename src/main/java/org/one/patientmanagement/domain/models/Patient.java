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
        String contact,
        String address
        ) {

    public int getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getSchemedId() {
        int year = birthday.getYear();
        int month = birthday.getMonthValue();

        return String.format("%d%02d%04d", year, month, id);
    }
}
