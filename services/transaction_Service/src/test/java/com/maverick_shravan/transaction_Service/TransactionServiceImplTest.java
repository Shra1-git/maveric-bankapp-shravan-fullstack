package com.maverick_shravan.transaction_Service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.maverick_shravan.transaction_Service.Service.TransactionServiceImpl;
import com.maverick_shravan.transaction_Service.TransactionRepo.TransactionRepository;
import com.maverick_shravan.transaction_Service.modal.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImplTest.class);

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    public void testCreateTransaction() {
        // Create a sample transaction
        Transaction transaction = new Transaction();
        transaction.setAccountId("sampleAccountId");
        transaction.setAmount(100.0); // Assuming some value for amount

        // Mock behavior of the transactionRepository
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> {
            Transaction argument = invocation.getArgument(0);
            argument.setId(UUID.randomUUID().toString()); // Set a random ID
            return argument;
        });

        // Call the method under test
        Transaction createdTransaction = transactionService.createTransaction(transaction);

        // Verify that the transaction ID is set and saved
        assertEquals(36, createdTransaction.getId().length());
        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    public void testFindByAccountId() {
        // Define sample data
        String accountId = "sampleAccountId";
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction = new Transaction();
        transaction.setId("1");
        transaction.setType("Deposit");
        transaction.setAmount(100.0);
        transaction.setAccountId(accountId);
        transaction.setCreatedAt(new Date());
        transactions.add(transaction);

        // Mock behavior of the transactionRepository
        when(transactionRepository.findByAccountId(accountId)).thenReturn(transactions);

        // Call the method under test
        List<Transaction> foundTransactions = transactionService.findByAccountId(accountId);

        // Verify that the correct transactions are returned
        assertEquals(1, foundTransactions.size());
        assertEquals(accountId, foundTransactions.get(0).getAccountId());
        verify(transactionRepository, times(1)).findByAccountId(accountId);
    }
}
