package com.rrr.expense.manager.emapi.dao;

import com.rrr.expense.manager.emapi.models.Account;

import java.util.List;
import java.util.Optional;

public interface AccountsDao {
    Optional<List<Account>> findAll();
    Optional<Account> findById(Long id);
    boolean save(Account account);
    void update(Account account, Account withAccount);
    boolean delete(Account account);
}
