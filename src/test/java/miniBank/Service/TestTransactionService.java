package miniBank.Service;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import miniBank.dao.BalanceRepository;
import miniBank.model.Balance;
import miniBank.model.Transaction;
import miniBank.service.BalanceService;
import miniBank.service.TransactionService;
 


@RunWith(MockitoJUnitRunner.class)
public class TestTransactionService {
	
	@Mock
	private BalanceRepository balanceRepository;
	
	@Mock
	private BalanceService balanceService;
	
	@InjectMocks
	private TransactionService transactionService;
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();

	
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
  
    @Before
    public void before() {
		Balance balanceOriginal = new Balance(1,"santiago",new BigDecimal(5000.43),"ARS");
		Balance balanceModificado = new Balance(1,"santiago",new BigDecimal(5100.43),"ARS");
		

		when(balanceService.getBalance("santiago")).thenReturn(balanceOriginal);
		when(balanceRepository.save(balanceOriginal)).thenReturn(balanceModificado);
    }
    
	@Test
	public void depositTest() {
		Transaction transaction = new Transaction(1, new BigDecimal(100));
		Balance balance = transactionService.deposit(transaction, "santiago");
		assertEquals(new BigDecimal(5100.43), balance.getAmount());
	}
	
	@Test
	public void withdrawOk() throws IOException {
		Transaction transaction = new Transaction(1, new BigDecimal(100));
		Balance balance = transactionService.withdraw(transaction, "santiago");
		assertEquals(new BigDecimal(4900.43), balance.getAmount());
	}
	
	@Test
	public void withdrawError() throws IOException {
		thrown.expect(ResponseStatusException.class);
		Transaction transaction = new Transaction(1, new BigDecimal(5100));
		Balance balance = transactionService.withdraw(transaction, "santiago");
	}


}
