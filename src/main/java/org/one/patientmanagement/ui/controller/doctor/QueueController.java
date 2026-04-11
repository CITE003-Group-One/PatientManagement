/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.controller.doctor;

import com.google.inject.Inject;
import org.one.patientmanagement.ui.controller.AbstractController;
import org.one.patientmanagement.ui.view.PatientQueue;

/**
 *
 * @author KAROL JOHN
 */
public class QueueController extends AbstractController<PatientQueue, QueueController> {
    
    @Inject // TODO: maybe opt to provider
    public QueueController(PatientQueue view) {
        super(view);
    }
}