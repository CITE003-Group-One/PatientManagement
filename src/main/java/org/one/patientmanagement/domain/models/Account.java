package org.one.patientmanagement.domain.models;

import java.time.LocalDateTime;
import org.one.patientmanagement.domain.enums.Role;

/**
 * Represents an account for doctor or patient.
 * 
 * @param password hashed password, not a plain one
 */
public record Account(
        long id,
        String user,
        String password,
        Role role,
        LocalDateTime createdAt
) {
    public Account {
        if (user == null || user.isBlank()) {
            throw new IllegalArgumentException("user is required");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("password is required");
        }
        if (role == null) {
            throw new IllegalArgumentException("role is required");
        }
        if (createdAt == null) {
            throw new IllegalArgumentException("createdAt is required");
        }
    }
}
