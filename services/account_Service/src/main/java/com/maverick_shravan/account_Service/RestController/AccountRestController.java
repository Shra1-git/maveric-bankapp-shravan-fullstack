package com.maverick_shravan.account_Service.RestController;


import com.maverick_shravan.account_Service.DTO.Balance;
import com.maverick_shravan.account_Service.Service.AccountService;
import com.maverick_shravan.account_Service.modal.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
public class AccountRestController {

    private static final Logger logger = LoggerFactory.getLogger(AccountRestController.class);

    private final AccountService accountService;

    @Autowired
    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/account")
    public Account createAccount(@RequestBody Account account) {
        logger.info("Creating account: {}", account);
        Account createdAccount = accountService.createAccount(account);
        logger.info("Account created: {}", createdAccount);
        return createdAccount;
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable String accountId) {
        logger.info("Fetching account by ID: {}", accountId);
        Optional<Account> account = accountService.findAccountById(accountId);
        if (account.isPresent()) {
            logger.info("Account found: {}", account.get());
            return ResponseEntity.ok(account.get());
        } else {
            logger.warn("Account with ID {} not found", accountId);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/account/getaccounts/{customerId}")
    public List<Account> getAccountsByCustomerId(@PathVariable String customerId) {
        logger.info("Fetching accounts by customer ID: {}", customerId);
        List<Account> accounts = accountService.findByCustomerId(customerId);
        logger.info("Found {} accounts for customer ID {}", accounts.size(), customerId);
        return accounts;
    }

    @DeleteMapping("/account/{accountId}")
    public void deleteAccountById(@PathVariable String accountId) {
        logger.info("Deleting account with ID: {}", accountId);
        accountService.deleteAccountById(accountId);
        logger.info("Account with ID {} deleted successfully", accountId);
    }

    @GetMapping("/account/{accountId}/balance")
    public ResponseEntity<Balance> getBalanceByAccountId(@PathVariable String accountId) {
        return accountService.getBalanceByAccountId(accountId);
    }

}