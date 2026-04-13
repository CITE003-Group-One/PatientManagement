/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.core.dto;

import java.time.LocalDate;
import java.time.Period;

public record PatientInfo(
    String firstName,
    String lastName,
    String sex,
    LocalDate birthday,
    String bloodType,
    String contactNumber,
    String alternativeContactNumber,
    String address
) {

    public int getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }
}
