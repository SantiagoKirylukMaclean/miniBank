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
	
	

	public Balance getBalance(String userName){
		return repository.findAll().stream().filter(b -> b.getUsername().equals(userName)).findFirst().get();
	}

	public List<Balance> getBalances() {
		return repository.findAll();
	}
	
	
}
