package com.rrr.expense.manager.emapi.controllers;

import com.rrr.expense.manager.emapi.models.Account;
import com.rrr.expense.manager.emapi.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AccountController {

    private List<Account> accounts = new ArrayList<>();

    private final AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping(value = "/accounts")
    private ResponseEntity<List<Account>> accounts() {
        return this
                .service
                .findAll()
                .map(accountList -> ResponseEntity.ok().body(accountList))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/accounts/{accountId}")
    private ResponseEntity<Account> accountById(@PathVariable Long accountId) {
        return this
                .service
                .findById(accountId)
                .map(account -> ResponseEntity.ok().body(account))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/accounts")
    private ResponseEntity<Account> addAccount(@Valid @RequestBody Account account) {
        return ResponseEntity
                .created(URI.create("accounts/" + account.getId()))
                .body(this
                        .service
                        .save(account));
    }

    @PutMapping(value = "/accounts/{accountId}")
    private ResponseEntity<Account> updateAccount(@RequestBody Account account, @PathVariable Long accountId) {
        return ResponseEntity
                .accepted()
                .body(this
                        .service
                        .update(account, accountId));
    }

    @DeleteMapping(value = "/accounts/{accountId}")
    private ResponseEntity<Account> deleteAccount(@PathVariable Long accountId) {
        return ResponseEntity
                .accepted()
                .body(this
                        .service
                        .delete(accountId));
    }
}