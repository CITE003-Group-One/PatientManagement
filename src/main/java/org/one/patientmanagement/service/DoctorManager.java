package org.one.patientmanagement.service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nonnull;
import org.one.patientmanagement.ui.core.dto.ScheduleOfDoctor;
import org.one.patientmanagement.domain.models.Doctor;
import org.one.patientmanagement.domain.models.Schedule;

public interface DoctorManager {

    Optional<Doctor> getDoctorById(long doctorId);

    Optional<Doctor> getDoctorByAccountId(long accountId);
    
    List<Schedule> getSchedules(long doctorId);
    
    Map<DayOfWeek, List<ScheduleOfDoctor>> getWeekScheduleOfDoctors();
    
    Schedule addSchedule(@Nonnull Schedule schedule);
    
    Schedule removeSchedule(@Nonnull Schedule schedule);
    
    Schedule update(@Nonnull Schedule schedule);
    
    List<Doctor> getAllByIds(List<Long> ids);
}
