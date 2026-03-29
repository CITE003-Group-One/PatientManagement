package org.one.patientmanagement.repository;

import java.util.List;
import java.util.Optional;
import org.one.patientmanagement.domain.models.Prescription;

public interface PrescriptionRepository extends Repository<Prescription> {
    
    Optional<Prescription> findByPatient(long patientId);
    
    List<Prescription> findAllByPatient(long patientId);
}
