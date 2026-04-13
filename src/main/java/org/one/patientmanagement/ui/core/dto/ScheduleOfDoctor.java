/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package org.one.patientmanagement.ui.core.dto;

import org.one.patientmanagement.domain.models.Doctor;
import org.one.patientmanagement.domain.models.Schedule;

/**
 *
 * @author KAROL JOHN
 */
public record ScheduleOfDoctor(Doctor doctor, Schedule schedule) {

}
