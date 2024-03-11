package com.maverick_shravan.account_Service.modal;

import com.maverick_shravan.account_Service.DTO.Balance;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "balance-service") // Name of the balance service registered with Eureka
public interface BalanceFeignClient {
    @GetMapping("/balance/{accountId}")
    ResponseEntity<Balance> getBalanceByAccountId(@PathVariable String accountId);
}
