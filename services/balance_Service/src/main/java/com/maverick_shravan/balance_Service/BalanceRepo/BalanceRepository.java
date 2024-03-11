package com.maverick_shravan.balance_Service.BalanceRepo;

import com.maverick_shravan.balance_Service.modal.Balance;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BalanceRepository extends MongoRepository<Balance, String> {
    Optional<Balance> findByAccountId(String accountId);
}
