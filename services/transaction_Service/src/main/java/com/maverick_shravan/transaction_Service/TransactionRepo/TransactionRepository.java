package com.maverick_shravan.transaction_Service.TransactionRepo;

import com.maverick_shravan.transaction_Service.modal.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByAccountId(String accountId);
}
