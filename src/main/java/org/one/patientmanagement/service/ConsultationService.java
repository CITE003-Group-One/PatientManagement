package org.one.patientmanagement.service;

import java.util.List;
import org.one.patientmanagement.domain.enums.ConsultationType;
import org.one.patientmanagement.domain.models.Consultation;

public interface ConsultationService {
    
    Consultation create(Consultation consultation);
    
    Consultation remove(Consultation consultation);
    
    Consultation update(Consultation consultation);
    
    List<Consultation> getConsultations(long patientId);
    
    List<Consultation> getConsultations(long patientId, long doctorId, ConsultationType... types);
}
