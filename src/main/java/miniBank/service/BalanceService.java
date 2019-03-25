package miniBank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import miniBank.dao.BalanceRepository;
import miniBank.model.Balance;

@Service
public class BalanceService {
	
	@Autowired
	private BalanceRepository repository;
	
	public List<Balance> getAllBalance(){
		List<Balance> balances = repository.findAllBalance();
		return balances;
	}
	
	public Balance getBalance(String identityId){
		return repository.findAllBalance().stream().filter(b -> b.getIdentityId().equals(identityId)).findFirst().get();
	}
	
	public Balance putBalance(Balance balance) {
		return repository.modifyBalance(balance);
	}

}
