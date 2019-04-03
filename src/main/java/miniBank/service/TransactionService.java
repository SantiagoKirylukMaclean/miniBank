package miniBank.service;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import miniBank.dao.BalanceRepository;
import miniBank.model.Balance;
import miniBank.model.Transaction;

@Service
public class TransactionService {
	
	@Autowired
	private BalanceService balanceService;
	
	@Autowired
	private BalanceRepository balanceRepository;
	

	public Balance deposit(Transaction transaction, String userName) {
		Balance balance = new Balance();
		balance = balanceService.getBalance(userName);
		balance.setAmount(balanceService.getBalance(userName).getAmount().add(transaction.getAmount()));
		return balanceRepository.save(balance);
		
	}
	
	public Balance withdraw(Transaction transaction, String userName) throws IOException{
		Balance balance = new Balance();
		balance = balanceService.getBalance(userName);
		if (balanceService.getBalance(userName).getAmount().subtract(transaction.getAmount()).compareTo(BigDecimal.ZERO) > 0) {
			balance.setAmount(balanceService.getBalance(userName).getAmount().subtract(transaction.getAmount()));
			balanceRepository.save(balance);
		}else {
			throw new ResponseStatusException(
			           HttpStatus.BAD_REQUEST, "There is not enough money in the account");
		}
		return balance;
		
	}
	
	

}
