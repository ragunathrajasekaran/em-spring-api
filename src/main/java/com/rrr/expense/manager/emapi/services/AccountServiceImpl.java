package com.rrr.expense.manager.emapi.services;

import com.rrr.expense.manager.emapi.dao.AccountsDao;
import com.rrr.expense.manager.emapi.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountsDao dao;

    @Autowired
    public AccountServiceImpl(AccountsDao dao) {
        this.dao = dao;
    }

    public Optional<List<Account>> findAll() {
        return dao.findAll();
    }

    public Optional<Account> findById(Long id) {
        return dao.findById(id);
    }

    public Account save(Account account) {
        if(dao.findById(account.getId()).isPresent()) {
            throw new RuntimeException("Account " + account.getId() + " Info is Present");
        } else {
            if(dao.save(account)) {
                return account;
            } else {
                throw new RuntimeException("Error While Persisting Account");
            }
        }
    }

    public Account update(Account account, Long id) {
        if(dao.update(account, id)) {
            return account;
        }
        else {
            throw new RuntimeException("Account Id " + id + " Is Not Found for update");
        }
    }

    public Account delete(Long id) {
        return dao
                .findById(id)
                .map(account1 -> {
                    if(dao.delete(account1)) { return account1; }
                    else { throw new RuntimeException("Error While Deleting Account"); }
                })
                .orElseThrow(() -> new RuntimeException("Account Id " + id + " Is Not Found"));
    }
}
