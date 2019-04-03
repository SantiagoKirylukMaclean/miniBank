package miniBank.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import miniBank.model.Balance;
import miniBank.security.JwtAuthTokenFilter;
import miniBank.security.JwtProvider;
import miniBank.service.BalanceService;

@RestController
public class BalanceController {
	
	@Autowired
	private BalanceService service;
	
    @Autowired
    JwtProvider jwtProvider;
    
    @Autowired
    JwtAuthTokenFilter jwtAuthTokenFilter;

	@RequestMapping("/balance/")
	@PreAuthorize("hasRole('USER')")
	public Balance findBalanceByUser(HttpServletRequest req){
		return service.getBalance(jwtProvider.getUserNameFromJwtToken(jwtAuthTokenFilter.getJwt(req)));
	}
	
	@RequestMapping("/balance/all")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Balance> findBalances(){
		return service.getBalances();
	}

  
}
