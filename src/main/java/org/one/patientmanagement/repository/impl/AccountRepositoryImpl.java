/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.repository.impl;

import com.google.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javax.sql.DataSource;
import org.one.patientmanagement.domain.enums.Role;
import org.one.patientmanagement.domain.models.Account;
import org.one.patientmanagement.repository.AccountRepository;

/**
 *
 * @author KAROL JOHN
 */
public class AccountRepositoryImpl implements AccountRepository {

    private final DataSource dataSource;
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Inject
    public AccountRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Account save(Account model) {
        String sql = "INSERT INTO accounts (user, password, role, created_at) VALUES (?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, model.user());
            stmt.setString(2, model.password());
            stmt.setString(3, model.role().name());
            stmt.setString(4, model.createdAt().format(FORMATTER));
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return new Account(
                            rs.getLong(1),
                            model.user(),
                            model.password(),
                            model.role(),
                            model.createdAt()
                    );
                }
                throw new RuntimeException("Failed to retrieve generated id after saving Account");
            }
        } catch (SQLException e) {
            throw new RuntimeException("DB Error: Save failed", e);
        }
    }

    @Override
    public void update(Account model) {
        String sql = "UPDATE accounts SET user = ?, password = ?, role = ?, created_at = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, model.user());
            stmt.setString(2, model.password());
            stmt.setString(3, model.role().name());
            stmt.setString(4, model.createdAt().format(FORMATTER));
            stmt.setLong(5, model.id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("DB Error: Update failed", e);
        }
    }

    @Override
    public void delete(long id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM accounts WHERE id = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("DB Error: Delete failed", e);
        }
    }

    @Override
    public Optional<Account> findById(long id) {
        String sql = "SELECT * FROM accounts WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? Optional.of(mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("DB Error: findById failed", e);
        }
    }

    public Optional<Account> findByCredentials(String user, String password) {
        String sql = "SELECT * FROM accounts WHERE user = ? AND password = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? Optional.of(mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("DB Error: findByCredentials failed", e);
        }
    }

    @Override
    public Optional<Account> findByCredentials(String password, Role role) {
        String sql = "SELECT * FROM accounts WHERE password = ? AND role = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, password);
            stmt.setString(2, role.name());
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? Optional.of(mapRow(rs)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("DB Error: findByCredentials failed", e);
        }
    }

    private Account mapRow(ResultSet rs) throws SQLException {
        return new Account(
                rs.getLong("id"),
                rs.getString("user"),
                rs.getString("password"),
                Role.valueOf(rs.getString("role")),
                LocalDateTime.parse(rs.getString("created_at"), FORMATTER)
        );
    }
}