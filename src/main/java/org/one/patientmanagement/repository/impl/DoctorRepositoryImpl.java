package org.one.patientmanagement.repository.impl;

import com.google.inject.Inject;
import org.one.patientmanagement.repository.DoctorRepository;

import org.one.patientmanagement.domain.models.Doctor;
import org.one.patientmanagement.domain.models.Schedule;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import org.one.patientmanagement.domain.dto.ScheduleOfDoctor;

public class DoctorRepositoryImpl implements DoctorRepository {

    private final DataSource dataSource;

    @Inject
    public DoctorRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Doctor save(Doctor doctor) {
        String sql = "INSERT INTO doctors (account_id, name, profession) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, doctor.accountId());
            stmt.setString(2, doctor.name());
            stmt.setString(3, doctor.profession());
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    return new Doctor(keys.getLong(1), doctor.accountId(), doctor.profession(), doctor.name());
                }
                throw new RuntimeException("Failed to retrieve generated id after saving Doctor");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save Doctor", e);
        }
    }

    @Override
    public void update(Doctor doctor) {
        String sql = "UPDATE doctors SET name = ?, profession = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, doctor.name());
            stmt.setString(2, doctor.profession());
            stmt.setLong(3, doctor.id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update Doctor with id=" + doctor.id(), e);
        }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM doctors WHERE id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete Doctor with id=" + id, e);
        }
    }

    @Override
    public Optional<Doctor> findById(long id) {
        String sql = "SELECT id, account_id, name, profession FROM doctors WHERE id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? Optional.of(mapDoctor(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find Doctor with id=" + id, e);
        }
    }

    @Override
    public Optional<Doctor> findByAccountId(long accountId) {
        String sql = "SELECT id, account_id, name, profession FROM doctors WHERE account_id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? Optional.of(mapDoctor(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find Doctor with accountId=" + accountId, e);
        }
    }

    @Override
    public List<Schedule> findSchedules(long doctorId) {
        String sql = "SELECT id, day, start, end, doctor_id FROM schedules WHERE doctor_id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, doctorId);
            try (ResultSet rs = stmt.executeQuery()) {
                List<Schedule> schedules = new ArrayList<>();
                while (rs.next()) {
                    schedules.add(mapSchedule(rs));
                }
                return schedules;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find schedules for doctorId=" + doctorId, e);
        }
    }

    private Doctor mapDoctor(ResultSet rs) throws SQLException {
        return new Doctor(
                rs.getLong("id"),
                rs.getLong("account_id"),
                rs.getString("profession"),
                rs.getString("name")
        );
    }

    private Schedule mapSchedule(ResultSet rs) throws SQLException {
        return new Schedule(
                rs.getLong("id"),
                DayOfWeek.valueOf(rs.getString("day")),
                LocalTime.parse(rs.getString("start")),
                LocalTime.parse(rs.getString("end")),
                rs.getLong("doctor_id")
        );
    }

    @Override
    public List<Doctor> findAllByIds(List<Long> ids) {
        if (ids.isEmpty()) {
            return List.of();
        }

        String placeholders = ids.stream().map(i -> "?").collect(Collectors.joining(", "));
        String sql = "SELECT id, account_id, name, profession FROM doctors WHERE id IN (" + placeholders + ")";

        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < ids.size(); i++) {
                stmt.setLong(i + 1, ids.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                List<Doctor> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(mapDoctor(rs));
                }
                return list;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find doctors by ids", e);
        }
    }

    @Override
    public Map<DayOfWeek, List<ScheduleOfDoctor>> findWeekScheduleOfDoctors() {
        String sql = """
        SELECT s.id, s.day, s.start, s.end, s.doctor_id,
               d.id as d_id, d.account_id, d.name, d.profession
        FROM schedules s
        JOIN doctors d ON s.doctor_id = d.id
    """;
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            Map<DayOfWeek, List<ScheduleOfDoctor>> map = new LinkedHashMap<>();
            while (rs.next()) {
                DayOfWeek day = DayOfWeek.valueOf(rs.getString("day"));
                Doctor doctor = new Doctor(rs.getLong("d_id"), rs.getLong("account_id"), rs.getString("profession"), rs.getString("name"));
                Schedule schedule = new Schedule(rs.getLong("id"), day, LocalTime.parse(rs.getString("start")), LocalTime.parse(rs.getString("end")), rs.getLong("doctor_id"));
                map.computeIfAbsent(day, k -> new ArrayList<>()).add(new ScheduleOfDoctor(doctor, schedule));
            }
            return map;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch week schedule of doctors", e);
        }
    }
}
