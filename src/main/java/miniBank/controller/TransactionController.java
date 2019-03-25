package miniBank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import miniBank.model.Balance;
import miniBank.model.Transaction;
import miniBank.service.TransactionService;

@RestController
public class TransactionController {
	
	@Autowired
	private TransactionService service;
	
	
	@RequestMapping(method=RequestMethod.POST, value="/transaction/deposit")
	public Balance transactionDeposit(@RequestBody Transaction transaction){
		return service.deposit(transaction);
	}

	@RequestMapping(method=RequestMethod.POST, value="/transaction/withdraw")
	public Balance transactionWithdraw(@RequestBody Transaction transaction){
		return service.withdraw(transaction);
	}
}
