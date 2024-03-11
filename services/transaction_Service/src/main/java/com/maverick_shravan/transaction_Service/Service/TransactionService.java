package com.maverick_shravan.transaction_Service.Service;

import com.maverick_shravan.transaction_Service.modal.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);
    List<Transaction> findByAccountId(String accountId);
}
