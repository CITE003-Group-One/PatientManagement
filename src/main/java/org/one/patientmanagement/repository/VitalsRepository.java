package org.one.patientmanagement.repository;

import org.one.patientmanagement.domain.models.Vitals;

public interface VitalsRepository extends Repository<Vitals> {
    
    Vitals findByPatient(long patientId);
}
