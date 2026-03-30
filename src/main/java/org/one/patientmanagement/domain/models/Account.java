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
        String password,
        Role role,
        LocalDateTime createdAt
) {}
