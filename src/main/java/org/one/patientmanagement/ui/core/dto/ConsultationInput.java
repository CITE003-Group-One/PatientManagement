/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package org.one.patientmanagement.ui.core.dto;

import org.one.patientmanagement.domain.enums.ConsultationType;

public record ConsultationInput(
        ConsultationType type,
        String title,
        String description
        ) {

}
