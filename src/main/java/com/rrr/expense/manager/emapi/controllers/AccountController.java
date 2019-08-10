package com.rrr.expense.manager.emapi.controllers;

import com.rrr.expense.manager.emapi.models.Account;
import com.rrr.expense.manager.emapi.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
                .orElseThrow(() -> new RuntimeException("Accounts Not Found"));
    }

    @GetMapping(value = "/accounts/{accountId}")
    private ResponseEntity<Account> accountById(@PathVariable Long accountId) {
        return this
                .service
                .findById(accountId)
                .map(account -> ResponseEntity.ok().body(account))
                .orElseThrow(
                        () -> new RuntimeException("Account Id " + accountId + " Is Not Found")
                );
    }

    @PostMapping(value = "/accounts")
    private ResponseEntity<Account> addAccount(@Valid @RequestBody Account account) {
        return ResponseEntity
                .ok()
                .body(this
                        .service
                        .save(account));
    }

    @PutMapping(value = "/accounts/{accountId}")
    private ResponseEntity<Account> updateAccount(@RequestBody Account account, @PathVariable Long accountId) {
        return ResponseEntity
                .ok()
                .body(this
                        .service
                        .update(account, accountId));
    }

    @DeleteMapping(value = "/accounts/{accountId}")
    private ResponseEntity<Account> deleteAccount(@PathVariable Long accountId) {
        return ResponseEntity
                .ok()
                .body(this
                        .service
                        .delete(accountId));
    }
}