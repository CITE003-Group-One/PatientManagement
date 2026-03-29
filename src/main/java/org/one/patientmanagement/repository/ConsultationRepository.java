package org.one.patientmanagement.repository;

import java.util.List;
import org.one.patientmanagement.domain.enums.ConsultationType;
import org.one.patientmanagement.domain.models.Consultation;

public interface ConsultationRepository extends Repository<Consultation> {

    List<Consultation> findAll(long patientId, long doctorId, ConsultationType... type);
}
