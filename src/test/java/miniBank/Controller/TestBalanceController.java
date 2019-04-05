package miniBank.Controller;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import miniBank.MiniBankApiApp;
import miniBank.dao.BalanceRepository;
import miniBank.model.Balance;

 

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MiniBankApiApp.class)
@RunWith(SpringRunner.class)
public class TestBalanceController {
	
	@Autowired
    private MockMvc mockMvc;
	
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
		Balance balance2 = new Balance(1,"joe",new BigDecimal(3000.43),"ARS");
		balances.add(balance1);
		balances.add(balance2);
		
		when(balanceRepository.findAll()).thenReturn(balances);
		
		
    }
    
	@Test
	//@DisplayName("the return should be food, vip and location grouped by 500 Meters Slots and the glover must have a MOTORCYCLE and GLOVE BOX")
	public void getAllBalanceTest() throws Exception {
		this.mockMvc.perform(get("/balance"))
		.andExpect(status().isOk());

	}
	
}
