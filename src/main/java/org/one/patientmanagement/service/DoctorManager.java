package org.one.patientmanagement.service;

import java.util.Optional;
import org.one.patientmanagement.domain.models.Doctor;

public interface DoctorManager {

    Doctor create(Doctor doctor);
    
    void remove(long doctorId);

    Optional<Doctor> getDoctorById(long doctorId);

    Optional<Doctor> getDoctorByAccountId(long accountId);
    
    // [list of schedules] getSchedules(long doctorId);
}
