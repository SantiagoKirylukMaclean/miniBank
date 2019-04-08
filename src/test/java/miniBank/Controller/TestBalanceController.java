package miniBank.Controller;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import miniBank.controller.BalanceController;
import miniBank.model.Balance;
import miniBank.security.JwtAuthTokenFilter;
import miniBank.security.JwtProvider;
import miniBank.service.BalanceService;

 

@RunWith(MockitoJUnitRunner.class)
public class TestBalanceController {

	@InjectMocks
	BalanceController balanceController;
	
	@Mock
	BalanceService balanceService;
	
	@Mock
	JwtProvider jwtProvider;
	
	@Mock
	JwtAuthTokenFilter jwtAuthTokenFilter;
	
	@Mock
	HttpServletRequest request;
	
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
  
    @Before
    public void before() {
    	List<Balance> balances = new ArrayList<Balance>();
		Balance balance1 = new Balance(1,"santiago",new BigDecimal(5000.43),"ARS");
		Balance balance2 = new Balance(2,"joe",new BigDecimal(3000.43),"ARS");
		balances.add(balance1);
		balances.add(balance2);
		
		when(balanceService.getBalances()).thenReturn(balances);
		when(balanceService.getBalance("santiago")).thenReturn(balance1);
		
		when(jwtProvider.getUserNameFromJwtToken(jwtAuthTokenFilter.getJwt(request))).thenReturn("santiago");
    }
    
	@Test
	public void getBalancesTest() {
		List<Balance> balances = balanceController.findBalances();
		assertEquals(2, balances.size());
	}
	
	@Test
	public void getBalanceTest() {
		Balance balance = balanceController.findBalanceByUser(request);
		assertEquals("santiago", balance.getUsername());
	}
}
