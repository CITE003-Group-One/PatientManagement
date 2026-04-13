package org.one.patientmanagement.repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.one.patientmanagement.ui.core.dto.ScheduleOfDoctor;
import org.one.patientmanagement.domain.models.Doctor;
import org.one.patientmanagement.domain.models.Schedule;

public interface DoctorRepository extends Repository<Doctor> {
    
    List<Schedule> findSchedules(long doctorId);
    
    public Map<DayOfWeek, List<ScheduleOfDoctor>> findWeekScheduleOfDoctors();
    
    Optional<Doctor> findByAccountId(long accountId);
    
    Optional<Doctor> findById(long id);
    
    public List<Doctor> findAllByIds(List<Long> ids);
}
