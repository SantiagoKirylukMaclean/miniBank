package miniBank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import miniBank.model.Balance;
import miniBank.service.BalanceService;

@RestController
public class BalanceController {
	
	@Autowired
	private BalanceService service;

	@RequestMapping("/balance/{identityId}")
	public Balance findBalanceByUser(@PathVariable String identityId){
		return service.getBalance(identityId);
	}
}
