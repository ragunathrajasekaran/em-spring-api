package com.rrr.expense.manager.emapi.services;

import com.rrr.expense.manager.emapi.models.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Optional<List<Account>> findAll();
    Optional<Account> findById(Long id);
    Account save(Account account);
    Account update(Account account, Long id);
    Account delete(Long id);
}
