package com.maverick_shravan.account_Service.Service;

import com.maverick_shravan.account_Service.AccountRepo.AccountRepository;
import com.maverick_shravan.account_Service.DTO.Balance;
import com.maverick_shravan.account_Service.modal.Account;
import com.maverick_shravan.account_Service.modal.BalanceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class AccountServiceImpl implements AccountService{

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountRepository accountRepository;
    private final BalanceFeignClient balanceFeignClient;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository,BalanceFeignClient balanceFeignClient) {

        this.accountRepository = accountRepository;
        this.balanceFeignClient = balanceFeignClient;
    }

    @Override
    public Account createAccount(Account account) {
        logger.info("Creating account: {}", account);
        long uuid = generateRandomLong();
        account.setId(String.valueOf(uuid));
        Account createdAccount = accountRepository.save(account);
        logger.info("Account created: {}", createdAccount);
        return createdAccount;
    }

    @Override
    public Optional<Account> findAccountById(String accountId) {
        logger.info("Finding account by ID: {}", accountId);
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            logger.info("Account found: {}", account.get());
        } else {
            logger.warn("Account with ID {} not found", accountId);
        }
        return account;
    }

    @Override
    public List<Account> findByCustomerId(String customerId) {
        logger.info("Finding accounts by customer ID: {}", customerId);
        List<Account> accounts = accountRepository.findByCustomerId(customerId);
        if (!accounts.isEmpty()) {
            logger.info("Found {} accounts for customer ID {}", accounts.size(), customerId);
        } else {
            logger.warn("No accounts found for customer ID {}", customerId);
        }
        return accounts;
    }

    @Override
    public void deleteAccountById(String accountId) {
        logger.info("Deleting account with ID: {}", accountId);
        accountRepository.deleteById(accountId);
        logger.info("Account with ID {} deleted successfully", accountId);
    }

    @Override
    public ResponseEntity<Balance> getBalanceByAccountId(String accountId) {
        return balanceFeignClient.getBalanceByAccountId(accountId);
    }

    private long generateRandomLong() {
        Random random = new Random();
        long uuid;
        do {
            uuid = random.nextLong(); // Generates a random long value
        } while (uuid <= 0); // Ensure the generated value is positive

        return uuid;
    }
    }

