package com.maverick_shravan.balance_Service;
import com.maverick_shravan.balance_Service.BalanceRepo.BalanceRepository;
import com.maverick_shravan.balance_Service.Service.BalanceServiceImpl;
import com.maverick_shravan.balance_Service.modal.Balance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BalanceServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(BalanceServiceImplTest.class);

    @Mock
    private BalanceRepository balanceRepository;

    @InjectMocks
    private BalanceServiceImpl balanceService;

    @Test
    public void testGetBalanceByAccountId() {
        // Arrange
        String accountId = "sampleAccountId";
        Balance balance = new Balance();
        balance.setAccountId(accountId);
        balance.setAmount(100.0); // Assuming some initial balance

        when(balanceRepository.findByAccountId(accountId)).thenReturn(Optional.of(balance));

        // Act
        Optional<Balance> retrievedBalance = balanceService.getBalanceByAccountId(accountId);

        // Assert
        assertTrue(retrievedBalance.isPresent());
        assertEquals(accountId, retrievedBalance.get().getAccountId());
        assertEquals(100.0, retrievedBalance.get().getAmount());
    }

    @Test
    public void testAddBalance() {
        // Arrange
        Balance balanceToAdd = new Balance();
        balanceToAdd.setAccountId("sampleAccountId");
        balanceToAdd.setAmount(100.0); // Assuming some initial amount

        when(balanceRepository.save(any(Balance.class))).thenAnswer(invocation -> {
            Balance savedBalance = invocation.getArgument(0);
            savedBalance.setId(UUID.randomUUID().toString()); // Simulate saving with generated ID
            return savedBalance;
        });

        // Act
        Balance savedBalance = balanceService.addBalance(balanceToAdd);

        // Assert
        assertNotNull(savedBalance);
        assertEquals("sampleAccountId", savedBalance.getAccountId());
        assertEquals(100.0, savedBalance.getAmount());
        assertNotNull(savedBalance.getId());
    }

    @Test
    public void testUpdateBalanceAmount() {
        // Arrange
        String accountId = "sampleAccountId";
        double amountToAdd = 50.0;

        Balance existingBalance = new Balance();
        existingBalance.setId(UUID.randomUUID().toString());
        existingBalance.setAccountId(accountId);
        existingBalance.setAmount(100.0); // Assuming some initial amount

        when(balanceRepository.findByAccountId(accountId)).thenReturn(Optional.of(existingBalance));
        when(balanceRepository.save(any(Balance.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Optional<Balance> updatedBalanceOptional = balanceService.updateBalanceAmount(accountId, amountToAdd);

        // Assert
        assertTrue(updatedBalanceOptional.isPresent());
        Balance updatedBalance = updatedBalanceOptional.get();
        assertEquals(accountId, updatedBalance.getAccountId());
        assertEquals(150.0, updatedBalance.getAmount());
        assertNotNull(updatedBalance.getUpdatedAt());
    }
}
