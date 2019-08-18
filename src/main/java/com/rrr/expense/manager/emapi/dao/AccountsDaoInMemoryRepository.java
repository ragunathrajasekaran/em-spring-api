package com.rrr.expense.manager.emapi.dao;

import com.rrr.expense.manager.emapi.models.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountsDaoInMemoryRepository implements AccountsDao {

    private List<Account> accounts = new ArrayList<>();

    public Optional<List<Account>> findAll() {
        return Optional.ofNullable(this.accounts);
    }

    public Optional<Account> findById(Long id) {
        return this
                .accounts
                .stream()
                .filter(account -> account.getId().equals(id))
                .findFirst();
    }

    public boolean save(Account account) {
        return this.accounts.add(account);
    }

    public void update(Account account, Account withAccount) {
        account.setTitle(withAccount.getTitle());
        account.setDescription(withAccount.getDescription());
    }

    public boolean delete(Account account) {
        return this.accounts.remove(account);
    }
}
