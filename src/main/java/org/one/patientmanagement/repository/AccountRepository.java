package org.one.patientmanagement.repository;

import java.util.Optional;
import org.one.patientmanagement.domain.models.Account;

public interface AccountRepository extends Repository<Account> {
    
    Optional<Account> findById(long id);
}
