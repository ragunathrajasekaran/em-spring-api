package com.rrr.expense.manager.emapi.services;

import com.rrr.expense.manager.emapi.dao.AccountsDao;
import com.rrr.expense.manager.emapi.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        if (dao.findById(account.getId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST
                    , "Account Id " + account.getId() + " Is Already Present");
        } else {
            if (dao.save(account)) {
                return account;
            } else {
                throw new RuntimeException("Error While Saving An Account");
            }
        }
    }

    public Account update(Account account, Long id) {
        return dao
                .findById(id)
                .map(account1 -> {
                    this.dao.update(account1, account);
                    return account1;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND
                        , "Account Id " + id + " Is Not Found for update"));
    }

    public Account delete(Long id) {
        return dao
                .findById(id)
                .map(account1 -> {
                    if (dao.delete(account1)) {
                        return account1;
                    } else {
                        throw new ResponseStatusException(HttpStatus.NOT_FOUND
                                , "Error While Deleting Account");
                    }
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND
                        , "Account Id " + id + " Is Not Found for update"));
    }
}
