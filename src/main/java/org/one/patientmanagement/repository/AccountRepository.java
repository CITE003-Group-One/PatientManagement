package org.one.patientmanagement.repository;

import java.util.Optional;
import org.one.patientmanagement.domain.enums.Role;
import org.one.patientmanagement.domain.models.Account;

public interface AccountRepository extends Repository<Account> {

    Optional<Account> findById(long id);

    Optional<Account> findByCredentials(String user, String password);
    
    Optional<Account> findByCredentials(String password, Role role);
}
