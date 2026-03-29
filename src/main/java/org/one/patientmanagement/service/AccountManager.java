package org.one.patientmanagement.service;

import java.util.Optional;
import org.one.patientmanagement.domain.models.Account;

public interface AccountManager {

    Account register(Account account);

    void delete(long id);

    Optional<Account> getById(long id);

    Account authenticate(String username, String password);
}
