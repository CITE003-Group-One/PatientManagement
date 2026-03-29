package org.one.patientmanagement.repository;

import java.util.List;
import org.one.patientmanagement.domain.enums.AppointmentBlock;
import org.one.patientmanagement.domain.models.Appointment;

public interface AppointmentRepository extends Repository<Appointment> {
    
    List<Appointment> findAll();
    
    List<Appointment> findByDoctor(long doctorId);

    List<Appointment> findByPatient(long patientId);

    List<Appointment> findByBlock(AppointmentBlock block);
}
