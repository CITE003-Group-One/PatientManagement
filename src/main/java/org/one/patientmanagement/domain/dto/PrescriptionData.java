/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package org.one.patientmanagement.domain.dto;

import java.time.LocalDateTime;
import java.time.Period;

/**
 *
 * @author KAROL JOHN
 */
public record PrescriptionData(
        String name,
        String doctor, 
        String frequency,
        Period duration,
        boolean isActive,
        LocalDateTime createdAt
) {

}
