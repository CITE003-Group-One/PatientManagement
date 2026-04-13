package org.one.patientmanagement.domain.models;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import org.one.patientmanagement.domain.enums.AppointmentBlock;

public record Schedule(
        long id,
        DayOfWeek day,
        LocalTime start,
        LocalTime end,
        long doctorId
        ) {

    public Schedule {
        if (day == null) {
            throw new IllegalArgumentException("day is required");
        }
        if (start == null || end == null) {
            throw new IllegalArgumentException("start/end required");
        }
        if (!end.isAfter(start)) {
            throw new IllegalArgumentException("end must be after start");
        }
        if (doctorId <= 0) {
            throw new IllegalArgumentException("doctorId must be positive");
        }
    }

    public List<AppointmentBlock> blocks() {
        LocalTime noon = LocalTime.NOON;
        if (!start.isBefore(noon)) {    
            return List.of(AppointmentBlock.AFTERNOON);
        }
        if (!end.isAfter(noon)) {
            return List.of(AppointmentBlock.MORNING);
        }
        return List.of(AppointmentBlock.MORNING, AppointmentBlock.AFTERNOON);
    }

    public String timeRange(AppointmentBlock block) {
        LocalTime noon = LocalTime.NOON;
        LocalTime rangeStart = block == AppointmentBlock.AFTERNOON && start.isBefore(noon) ? noon : start;
        LocalTime rangeEnd = block == AppointmentBlock.MORNING && end.isAfter(noon) ? noon : end;
        return rangeStart + " - " + rangeEnd;
    }
}
