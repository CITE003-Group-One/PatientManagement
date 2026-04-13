/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package org.one.patientmanagement.ui.core.dto;

import org.one.patientmanagement.domain.enums.AppointmentBlock;
import org.one.patientmanagement.domain.enums.AppointmentStatus;

/**
 *
 * @author KAROL JOHN
 */
public record QueueData(
        long patientId,
        String fullName,
        String queueNumber,
        String sex,
        String schemedId,
        AppointmentStatus status,
        AppointmentBlock block
) {

}
