package org.one.patientmanagement.repository.impl;

import com.google.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.sql.DataSource;

import org.one.patientmanagement.domain.models.Patient;
import org.one.patientmanagement.repository.PatientRepository;

public class PatientRepositoryImpl implements PatientRepository {

    private final DataSource dataSource;

    @Inject
    public PatientRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Patient save(Patient patient) {
        String sql = "INSERT INTO patients (account_id, first_name, last_name, sex, birthday, blood_type, contact, address) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, patient.accountId());
            stmt.setString(2, patient.firstName());
            stmt.setString(3, patient.lastName());
            stmt.setString(4, patient.sex());
            stmt.setString(5, patient.birthday().toString());
            stmt.setString(6, patient.bloodType());
            stmt.setString(7, patient.contact());
            stmt.setString(8, patient.address());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return new Patient(rs.getLong(1), patient.accountId(), patient.firstName(), patient.lastName(),
                        patient.sex(), patient.birthday(), patient.bloodType(), patient.contact(), patient.address());
            }

            throw new RuntimeException("Failed to insert patient");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM patients WHERE id = ?";

        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Patient patient) {
        String sql = "UPDATE patients SET account_id=?, first_name=?, last_name=?, sex=?, birthday=?, blood_type=?, contact=?, address=? WHERE id=?";

        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, patient.accountId());
            stmt.setString(2, patient.firstName());
            stmt.setString(3, patient.lastName());
            stmt.setString(4, patient.sex());
            stmt.setString(5, patient.birthday().toString());
            stmt.setString(6, patient.bloodType());
            stmt.setString(7, patient.contact());
            stmt.setString(8, patient.address());
            stmt.setLong(9, patient.id());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating patient", e);
        }
    }

    @Override
    public Optional<Patient> findById(long id) {
        String sql = "SELECT * FROM patients WHERE id = ?";

        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Patient> findAll() {
        String sql = "SELECT * FROM patients";
        List<Patient> list = new ArrayList<>();

        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }

            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Patient mapRow(ResultSet rs) throws SQLException {
        return new Patient(
                rs.getLong("id"),
                rs.getLong("account_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("sex"),
                rs.getObject("birthday", java.time.LocalDate.class),
                rs.getString("blood_type"),
                rs.getString("contact"),
                rs.getString("address")
        );
    }

    @Override
    public Optional<Patient> findByAccountId(long accountId) {
        String sql = "SELECT * FROM patients WHERE account_id = ?";

        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, accountId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Patient> findAllByIds(List<Long> ids) {
        if (ids.isEmpty()) {
            return List.of();
        }

        String placeholders = ids.stream().map(i -> "?").collect(Collectors.joining(", "));
        String sql = "SELECT * FROM patients WHERE id IN (" + placeholders + ")";

        List<Patient> list = new ArrayList<>();
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < ids.size(); i++) {
                stmt.setLong(i + 1, ids.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs)); // your existing patient mapping
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("DB Error: findAllByIds failed", e);
        }
        return list;
    }
}
