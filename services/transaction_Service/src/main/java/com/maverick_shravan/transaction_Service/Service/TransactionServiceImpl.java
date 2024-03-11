package com.maverick_shravan.transaction_Service.Service;

import com.maverick_shravan.transaction_Service.TransactionRepo.TransactionRepository;
import com.maverick_shravan.transaction_Service.modal.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService{

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        logger.info("Creating transaction: {}", transaction);

        long uuid = generateRandomLong();
        transaction.setId(String.valueOf(uuid));
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> findByAccountId(String accountId) {
        logger.info("Finding transactions for accountId: {}", accountId);
        return transactionRepository.findByAccountId(accountId);
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
