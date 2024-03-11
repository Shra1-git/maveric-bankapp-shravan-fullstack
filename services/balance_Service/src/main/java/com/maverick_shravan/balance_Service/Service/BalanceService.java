package com.maverick_shravan.balance_Service.Service;

import com.maverick_shravan.balance_Service.modal.Balance;

import java.util.Optional;

public interface BalanceService {

    Optional<Balance> getBalanceByAccountId(String accountId);

    Balance addBalance(Balance balance);

    Optional<Balance> updateBalanceAmount(String accountId, double amountToAdd);
}
