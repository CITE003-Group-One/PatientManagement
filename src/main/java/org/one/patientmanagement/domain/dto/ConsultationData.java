/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package org.one.patientmanagement.domain.dto;

import java.time.LocalDateTime;
import org.one.patientmanagement.domain.enums.ConsultationType;

/**
 *
 * @author KAROL JOHN
 */
public record ConsultationData(
        ConsultationType type,
        String title,
        String description,
        String doctor,
        LocalDateTime createdAt
) {

}
