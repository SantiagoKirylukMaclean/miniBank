package miniBank.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import miniBank.model.Balance;
import miniBank.model.Transaction;
import miniBank.security.JwtAuthTokenFilter;
import miniBank.security.JwtProvider;
import miniBank.service.TransactionService;

@RestController
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
    @Autowired
    JwtProvider jwtProvider;
    
    @Autowired
    JwtAuthTokenFilter jwtAuthTokenFilter;
	
	
	@RequestMapping(method=RequestMethod.POST, value="/transaction/deposit")
	@PreAuthorize("hasRole('USER')")
	public Balance transactionDeposit(@RequestBody Transaction transaction, HttpServletRequest req) {
		return transactionService.deposit(transaction, jwtProvider.getUserNameFromJwtToken(jwtAuthTokenFilter.getJwt(req)));
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/transaction/withdraw")
	@PreAuthorize("hasRole('USER')")
	public Balance transactionWithdraw(@RequestBody Transaction transaction, HttpServletRequest req) throws IOException{
		return transactionService.withdraw(transaction, jwtProvider.getUserNameFromJwtToken(jwtAuthTokenFilter.getJwt(req)));
	}
	
}
