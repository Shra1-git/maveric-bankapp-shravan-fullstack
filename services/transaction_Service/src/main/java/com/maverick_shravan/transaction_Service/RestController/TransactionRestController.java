package com.maverick_shravan.transaction_Service.RestController;

import com.maverick_shravan.transaction_Service.Service.TransactionService;
import com.maverick_shravan.transaction_Service.modal.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
public class TransactionRestController {
    private static final Logger logger = LoggerFactory.getLogger(TransactionRestController.class);

    private final TransactionService transactionService;

    @Autowired
    public TransactionRestController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transaction")
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        logger.info("Adding transaction: {}", transaction);
        return transactionService.createTransaction(transaction);
    }

    @GetMapping("/transaction/{accountId}")
    public List<Transaction> getTransactionsByAccountId(@PathVariable String accountId) {
        logger.info("Getting transactions for accountId: {}", accountId);
        return transactionService.findByAccountId(accountId);
    }
}
