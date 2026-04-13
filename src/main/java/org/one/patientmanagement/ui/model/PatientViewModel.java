/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.model;

import com.google.inject.Singleton;
import org.one.patientmanagement.ui.core.dto.AppointmentInfo;
import org.one.patientmanagement.ui.core.dto.PatientInfo;
import org.one.patientmanagement.domain.models.Account;
import org.one.patientmanagement.domain.models.Patient;

/**
 *
 * @author KAROL JOHN
 */
@Singleton
public class PatientViewModel {

    public PatientViewModel() {
        System.out.println(getClass().toString() + " created");
    }

    public enum ServiceSelection {
        GENERAL("General"),
        PEDIATRICS("Pediatrics"),
        COUNSELING("Counseling"),
        EXAMINATION("Physical/Medical Examination");

        private final String name;

        ServiceSelection(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum PatientType {
        EXISTING,
        NEW
    }

    private ServiceSelection serviceSelection;
    private Account account;
    private AppointmentInfo appointment;
    private PatientInfo patient;
    private PatientType patientType;

    public void restart() {
        this.serviceSelection = null;
        this.account = null;
        this.appointment = null;
        this.patient = null;
        this.patientType = null;
    }

    public PatientType getPatientType() {
        return patientType;
    }

    public void setPatientType(PatientType patientType) {
        this.patientType = patientType;
    }

    public ServiceSelection getServiceSelection() {
        return serviceSelection;
    }

    public void setServiceSelection(ServiceSelection serviceSelection) {
        this.serviceSelection = serviceSelection;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public AppointmentInfo getAppointment() {
        return appointment;
    }

    public void setAppointment(AppointmentInfo appointment) {
        this.appointment = appointment;
    }

    public PatientInfo getPatient() {
        return patient;
    }

    public void setPatient(PatientInfo patient) {
        this.patient = patient;
    }
}
