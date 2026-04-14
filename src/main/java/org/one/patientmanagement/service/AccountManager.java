package org.one.patientmanagement.service;

import java.util.Optional;
import javax.annotation.Nonnull;
import org.one.patientmanagement.domain.models.Account;

public interface AccountManager {

    Account register(@Nonnull Account account);

    void delete(long id);
    
    Account update(@Nonnull Account account);

    Optional<Account> getById(long id);

    Optional<Account> authenticate(@Nonnull String user, @Nonnull String password);
    
    Optional<Account> authenticate(@Nonnull String password);
}
