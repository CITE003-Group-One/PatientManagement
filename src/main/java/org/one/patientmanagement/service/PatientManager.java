package org.one.patientmanagement.service;

import java.util.List;
import java.util.Optional;
import org.one.patientmanagement.domain.models.Attachment;
import org.one.patientmanagement.domain.models.Doctor;
import org.one.patientmanagement.domain.models.Patient;
import org.one.patientmanagement.domain.models.Prescription;
import org.one.patientmanagement.domain.models.Vitals;

public interface PatientManager {
    
    Patient create(Patient patient);
    
    void remove(Patient patient);
    
    List<Attachment> getAttachments(Patient patient);
    
    Optional<Vitals> getVitals(Patient patient);
    
    List<Prescription> getPrescriptions(Patient patient);
    
    Optional<Patient> getById(long patientId);
    
    Optional<Patient> getByAccountId(long accountId);

    List<Patient> getPatients();
    
    List<Doctor> getDoctors(long patientId);
    
    Prescription recordPrescription(Prescription prescription);
    
    void removePrescription(Prescription prescription);
    
    Vitals setVitals(Vitals vitals);
    
    Attachment addAttachment(Attachment attachment);
    
    void deleteAttachment(Attachment attachment);
}
