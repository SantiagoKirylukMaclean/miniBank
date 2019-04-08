package miniBank.Controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import miniBank.controller.TransactionController;
import miniBank.model.Balance;
import miniBank.model.Transaction;
import miniBank.security.JwtAuthTokenFilter;
import miniBank.security.JwtProvider;
import miniBank.service.TransactionService;

@RunWith(MockitoJUnitRunner.class)
public class TestTransactionController {
	
	@InjectMocks
	TransactionController transactionController;
	
	@Mock
	TransactionService transactionService;
	
	@Mock
	JwtProvider jwtProvider;
	
	@Mock
	JwtAuthTokenFilter jwtAuthTokenFilter;
	
	@Mock
	HttpServletRequest request;

	@Mock
	Transaction transaction = new Transaction(1, new BigDecimal(100));
	
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
  
    @Before
    public void before() throws IOException {

		Balance balanceDeposit = new Balance(1,"santiago",new BigDecimal(5100.43),"ARS");
		Balance balanceWhitdraw = new Balance(1,"santiago",new BigDecimal(4900.43),"ARS");

		when(transactionService.deposit(transaction, "santiago")).thenReturn(balanceDeposit);
		when(transactionService.withdraw(transaction, "santiago")).thenReturn(balanceWhitdraw);

		
		when(jwtProvider.getUserNameFromJwtToken(jwtAuthTokenFilter.getJwt(request))).thenReturn("santiago");
    }
    
	@Test
	public void transactionDepositTest() {

		Balance balance = transactionController.transactionDeposit(transaction, request);
		assertEquals(new BigDecimal(5100.43), balance.getAmount());
	}
	
	@Test
	public void withdrawOk() throws IOException {
		
		Balance balance = transactionController.transactionWithdraw(transaction, request);
		assertEquals(new BigDecimal(4900.43), balance.getAmount());
	}
}
