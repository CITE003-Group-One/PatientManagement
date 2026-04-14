/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.ui.model;

import com.google.inject.Singleton;
import org.one.patientmanagement.ui.core.dto.AppointmentInfo;
import org.one.patientmanagement.ui.core.dto.PatientInfo;
import org.one.patientmanagement.domain.models.Account;
import org.one.patientmanagement.domain.models.Appointment;
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

    private ServiceSelection serviceSelection = ServiceSelection.GENERAL;
    private Account account;
    private Patient patient;
    private Appointment appointment;

    private AppointmentInfo appointmentInfo;
    private PatientInfo patientInfo;
    private PatientType patientType;

    public void restart() {
        this.serviceSelection = null;
        this.account = null;
        this.appointmentInfo = null;
        this.patientInfo = null;
        this.patientType = null;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
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

    public AppointmentInfo getAppointmentInfo() {
        return appointmentInfo;
    }

    public void setAppointmentInfo(AppointmentInfo appointment) {
        this.appointmentInfo = appointment;
    }

    public PatientInfo getPatientInfo() {
        return patientInfo;
    }

    public void setPatientInfo(PatientInfo patient) {
        this.patientInfo = patient;
    }
}
