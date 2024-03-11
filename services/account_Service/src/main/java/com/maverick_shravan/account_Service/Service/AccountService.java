package com.maverick_shravan.account_Service.Service;

import com.maverick_shravan.account_Service.DTO.Balance;
import com.maverick_shravan.account_Service.modal.Account;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account createAccount(Account account);
    List<Account> findByCustomerId(String customerId);

    void deleteAccountById(String accountId);

    Optional<Account> findAccountById(String accountId);

    ResponseEntity<Balance> getBalanceByAccountId(String accountId);


}
