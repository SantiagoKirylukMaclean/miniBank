package miniBank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import miniBank.dao.BalanceRepository;
import miniBank.dao.TransactionRepository;
import miniBank.model.Balance;
import miniBank.model.Transaction;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository repository;
	
	@Autowired
	private BalanceService bservice;
	
	
	public Balance deposit(Transaction transaction) {
		Balance balance = new Balance();
		balance = bservice.getBalance(transaction.getIdentityId());
		balance.setAmount(balance.getAmount().add(transaction.getAmount()));
		repository.addTransaction(transaction);
		return bservice.putBalance(balance);
		
	}
	
	public Balance withdraw(Transaction transaction) {
		Balance balance = new Balance();
		balance = bservice.getBalance(transaction.getIdentityId());
		balance.setAmount(balance.getAmount().subtract(transaction.getAmount()));
		repository.addTransaction(transaction);
		return bservice.putBalance(balance);
		
	}
	
	

}
