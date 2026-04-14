/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.one.patientmanagement.service.impl;

import com.google.inject.Inject;
import java.util.Optional;
import javax.annotation.Nonnull;
import org.one.patientmanagement.domain.enums.Role;
import org.one.patientmanagement.domain.models.Account;
import org.one.patientmanagement.repository.AccountRepository;
import org.one.patientmanagement.repository.impl.AccountRepositoryImpl;
import org.one.patientmanagement.service.AccountManager;

/**
 *
 * @author KAROL JOHN
 */
public class AccountManagerImpl implements AccountManager {

    private final AccountRepository accountRepository;

    @Inject
    public AccountManagerImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account register(@Nonnull Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void delete(long id) {
        accountRepository.delete(id);
    }

    @Override
    public Account update(@Nonnull Account account) {
        accountRepository.update(account);
        return account;
    }

    @Override
    public Optional<Account> getById(long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Optional<Account> authenticate(@Nonnull String user, @Nonnull String password) {
        return accountRepository.findByCredentials(user, password);
    }

    @Override
    public Optional<Account> authenticate(@Nonnull String password) {
        return accountRepository.findByCredentials(password, Role.DOCTOR);
    }
}
