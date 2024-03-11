package com.maverick_shravan.balance_Service.Service;

import com.maverick_shravan.balance_Service.BalanceRepo.BalanceRepository;
import com.maverick_shravan.balance_Service.modal.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class BalanceServiceImpl implements BalanceService {

    private static final Logger logger = LoggerFactory.getLogger(BalanceServiceImpl.class);

    private final BalanceRepository balanceRepository;

    @Autowired
    public BalanceServiceImpl(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @Override
    public Optional<Balance> getBalanceByAccountId(String accountId) {
        return balanceRepository.findByAccountId(accountId);
    }


    @Override
    @Transactional
    public Optional<Balance> updateBalanceAmount(String accountId, double amountToAdd) {
        try {
            Optional<Balance> optionalBalance = balanceRepository.findByAccountId(accountId);
            logger.info("balance account not found");
            logger.info("see" + optionalBalance.isPresent());
            Balance balanceToUpdate;

            if (optionalBalance.isPresent()) {
                balanceToUpdate = optionalBalance.get();
                double currentAmount = balanceToUpdate.getAmount();
                double newAmount = currentAmount + amountToAdd;
                balanceToUpdate.setAmount(newAmount);
                balanceToUpdate.setUpdatedAt(new Date());
                balanceRepository.save(balanceToUpdate);
                logger.info("Updated balance for account ID {}: {}", accountId, balanceToUpdate);
            } else {
                // Create a new balance using addBalance method
                Balance newBalance = new Balance();
                newBalance.setAccountId(accountId);
                newBalance.setAmount(amountToAdd);
                newBalance.setCurrency("USD"); // Set currency to USD
                newBalance.setCreatedAt(new Date());
                newBalance.setUpdatedAt(new Date());
                // Save the new balance using the addBalance method
                balanceToUpdate = addBalance(newBalance);
                logger.info("Created new balance for account ID {}: {}", accountId, balanceToUpdate);
            }

            return Optional.of(balanceToUpdate);
        } catch (Exception e) {
            logger.error("Failed to update balance: {}", e.getMessage());
            throw e;
        }
    }

    // Method to add balance
    @Override
    @Transactional
    public Balance addBalance(Balance balance) {
        try {
            logger.info("Adding balance: {}", balance);

            long uuid = generateRandomLong();
            balance.setId(String.valueOf(uuid));
            balance.setCurrency("USD"); // Set currency to USD
            balance.setCreatedAt(new Date());
            balance.setUpdatedAt(new Date());
            return balanceRepository.save(balance);
        } catch (Exception e) {
            logger.error("Failed to add balance: {}", e.getMessage());
            throw e;
        }
    }


//    @Override
//    @Transactional
//    public Balance addBalance(Balance balance) {
//        try {
//            logger.info("Adding balance: {}", balance);
//
//            long uuid = generateRandomLong();
//            balance.setId(String.valueOf(uuid));
//            return balanceRepository.save(balance);
//        } catch (Exception e) {
//            logger.error("Failed to add balance: {}", e.getMessage());
//            throw e;
//        }
//    }
//
//    @Override
//    @Transactional
//    public Optional<Balance> updateBalanceAmount(String accountId, double amountToAdd) {
//        try {
//            Optional<Balance> optionalBalance = balanceRepository.findByAccountId(accountId);
//            optionalBalance.ifPresent(balance -> {
//                double currentAmount = balance.getAmount();
//                double newAmount = currentAmount + amountToAdd;
//                balance.setAmount(newAmount);
//                balance.setUpdatedAt(new Date());
//                balanceRepository.save(balance);
//                logger.info("Updated balance for account ID {}: {}", accountId, balance);
//            });
//            return optionalBalance;
//        } catch (Exception e) {
//            logger.error("Failed to update balance: {}", e.getMessage());
//            throw e;
//        }
//    }

    private long generateRandomLong() {
        Random random = new Random();
        long uuid;
        do {
            uuid = random.nextLong(); // Generates a random long value
        } while (uuid <= 0); // Ensure the generated value is positive

        return uuid;
    }
}
