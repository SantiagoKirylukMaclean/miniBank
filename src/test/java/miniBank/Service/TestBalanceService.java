package miniBank.Service;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import miniBank.dao.BalanceRepository;
import miniBank.model.Balance;
import miniBank.service.BalanceService;
 


@RunWith(MockitoJUnitRunner.class)
public class TestBalanceService {
	
	@InjectMocks
	BalanceService balanceService;
	
	@Mock
	BalanceRepository balanceRepository;
	
	
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
		
		when(balanceRepository.findAll()).thenReturn(balances);
    }
    
	@Test
	public void getAllBalanceTest() {
		List<Balance> balances2 = balanceService.getBalances();
		assertEquals(2, balances2.size());
	}
	
	@Test
	public void getBalanceByUsernameTest() {
		Balance balance = balanceService.getBalance("santiago");
		assertEquals("santiago", balance.getUsername());
	}

}
