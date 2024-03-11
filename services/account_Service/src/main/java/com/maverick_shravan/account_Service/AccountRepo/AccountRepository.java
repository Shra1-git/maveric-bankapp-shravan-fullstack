package com.maverick_shravan.account_Service.AccountRepo;

import com.maverick_shravan.account_Service.modal.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AccountRepository extends JpaRepository<Account, String> {
    List<Account> findByCustomerId(String customerId);
}
