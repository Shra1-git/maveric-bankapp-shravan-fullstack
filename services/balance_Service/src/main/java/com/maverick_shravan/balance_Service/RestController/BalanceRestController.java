package com.maverick_shravan.balance_Service.RestController;

import com.maverick_shravan.balance_Service.Service.BalanceService;
import com.maverick_shravan.balance_Service.modal.Balance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BalanceRestController {

    private static final Logger logger = LoggerFactory.getLogger(BalanceRestController.class);

    private final BalanceService balanceService;

    @Autowired
    public BalanceRestController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }



    @GetMapping("balance/{accountId}")
    public ResponseEntity<Balance> getBalanceByAccountId(@PathVariable String accountId) {
        logger.info("Fetching balance with accountId: {}", accountId);
        Optional<Balance> balanceOptional = balanceService.getBalanceByAccountId(accountId);
        if (balanceOptional.isPresent()) {
            Balance balance = balanceOptional.get();
            logger.info("Balance found: {}", balance);
            return ResponseEntity.ok(balance);
        } else {
            logger.warn("Balance with accountId {} not found", accountId);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/balance")
    public ResponseEntity<Balance> createBalance(@RequestBody Balance balance) {
        logger.info("Creating balance: {}", balance);
        Balance createdBalance = balanceService.addBalance(balance);
        logger.info("Balance created: {}", createdBalance);
        return ResponseEntity.ok(createdBalance);
    }

    @PutMapping("/balance/update")
    public ResponseEntity<Balance> updateBalanceAmount(@RequestParam String accountId, @RequestParam double amountToAdd) {
        logger.info("Updating balance for account ID: {}, Amount to add: {}", accountId, amountToAdd);
        Optional<Balance> updatedBalanceOptional = balanceService.updateBalanceAmount(accountId, amountToAdd);
        if (updatedBalanceOptional.isPresent()) {
            Balance updatedBalance = updatedBalanceOptional.get();
            logger.info("Balance updated: {}", updatedBalance);
            return ResponseEntity.ok(updatedBalance);
        } else {
            logger.warn("Balance with account ID {} not found", accountId);
            return ResponseEntity.notFound().build();
        }
    }
}
