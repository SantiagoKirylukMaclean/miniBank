package miniBank.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import miniBank.model.Balance;
import miniBank.security.JWT;
import miniBank.security.JwtProvider;
import miniBank.service.BalanceService;
import sun.misc.BASE64Decoder;

@RestController
public class BalanceController {
	
	@Autowired
	private BalanceService service;
	
    @Autowired
    JwtProvider jwtProvider;

	@RequestMapping("/balance/{identityId}")
	@PreAuthorize("hasRole('USER')")
	public Balance findBalanceByUser(@PathVariable String identityId){
		//JWT jwt = new JWT();
		//System.out.println("token Decripted: " + jwtProvider.getUserNameFromJwtToken(authHeader));
		//jwt.parseJWT(jwt.createJWT("30366514", "USER", 12312312));
		return service.getBalance(identityId);
	}

  
}
