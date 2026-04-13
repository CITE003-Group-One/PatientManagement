/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package org.one.patientmanagement.ui.core.dto;

import org.one.patientmanagement.domain.enums.AppointmentBlock;

/**
 *
 * @author KAROL JOHN
 */
public record AppointmentInfo(
        AppointmentBlock block,
        long doctorId,
        long patientId
) {

}
