package org.one.patientmanagement.service;

import java.util.Optional;
import javax.annotation.Nonnull;
import org.one.patientmanagement.domain.models.Account;

public interface AccountManager {

    Account register(@Nonnull Account account);

    void delete(long id);

    Optional<Account> getById(long id);

    /**
     *
     * @param patientId id of patient
     * @param password hashed password
     * @return the account
     */
    Account authenticate(long patientId, String password);
}
