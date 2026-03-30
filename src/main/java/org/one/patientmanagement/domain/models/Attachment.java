package org.one.patientmanagement.domain.models;

import java.time.LocalDateTime;
import org.one.patientmanagement.domain.enums.AttachmentStatus;

/**
 * Represents an attachment of any file.
 * 
 * @param data any file, a blob in the database. 
 */
public record Attachment(
        long id,
        byte[] data,
        String name,
        Long doctorId,
        Long patientId,
        AttachmentStatus status,
        LocalDateTime createdAt
) {}
