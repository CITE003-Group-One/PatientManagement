package org.one.patientmanagement.repository;

import java.util.List;
import java.util.Optional;
import org.one.patientmanagement.domain.models.Attachment;

public interface AttachmentRepository extends Repository<Attachment> {
    
    Optional<Attachment> findByPatient(long patientId);
    
    List<Attachment> findAllByPatient(long patientId);
}
