package com.maverick_shravan.account_Service;
import com.maverick_shravan.account_Service.AccountRepo.AccountRepository;
import com.maverick_shravan.account_Service.Service.AccountServiceImpl;
import com.maverick_shravan.account_Service.modal.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImplTest.class);

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    public void testCreateAccount() {
        // Arrange
        Account accountToCreate = new Account();
        accountToCreate.setCustomerId("sampleCustomerId");
        accountToCreate.setType("savings"); // Assuming type

        Account savedAccount = new Account();
        savedAccount.setId(UUID.randomUUID().toString());
        savedAccount.setCustomerId(accountToCreate.getCustomerId());
        savedAccount.setType(accountToCreate.getType());

        when(accountRepository.save(any(Account.class))).thenReturn(savedAccount);

        // Act
        Account createdAccount = accountService.createAccount(accountToCreate);

        // Assert
        assertNotNull(createdAccount);
        assertEquals(accountToCreate.getCustomerId(), createdAccount.getCustomerId());
        assertEquals(accountToCreate.getType(), createdAccount.getType());
        assertNotNull(createdAccount.getId());
    }

    @Test
    public void testFindAccountById() {
        // Arrange
        String accountId = "sampleAccountId";
        Account account = new Account();
        account.setId(accountId);
        account.setCustomerId("sampleCustomerId");
        account.setType("savings"); // Assuming type

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        // Act
        Optional<Account> foundAccountOptional = accountService.findAccountById(accountId);

        // Assert
        assertTrue(foundAccountOptional.isPresent());
        Account foundAccount = foundAccountOptional.get();
        assertEquals(accountId, foundAccount.getId());
        assertEquals(account.getCustomerId(), foundAccount.getCustomerId());
        assertEquals(account.getType(), foundAccount.getType());
    }

    @Test
    public void testFindByCustomerId() {
        // Arrange
        String customerId = "sampleCustomerId";
        Account account1 = new Account();
        account1.setId(UUID.randomUUID().toString());
        account1.setCustomerId(customerId);
        account1.setType("savings"); // Assuming type

        Account account2 = new Account();
        account2.setId(UUID.randomUUID().toString());
        account2.setCustomerId(customerId);
        account2.setType("current"); // Assuming type

        List<Account> accounts = Arrays.asList(account1, account2);

        when(accountRepository.findByCustomerId(customerId)).thenReturn(accounts);

        // Act
        List<Account> foundAccounts = accountService.findByCustomerId(customerId);

        // Assert
        assertEquals(2, foundAccounts.size());
        assertTrue(foundAccounts.stream().allMatch(a -> a.getCustomerId().equals(customerId)));
    }

    @Test
    public void testDeleteAccountById() {
        // Arrange
        String accountId = "sampleAccountId";

        // Act
        assertDoesNotThrow(() -> accountService.deleteAccountById(accountId));

        // Assert
        verify(accountRepository, times(1)).deleteById(accountId);
    }
}
