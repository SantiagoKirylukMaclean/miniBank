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
	@PreAuthorize("hasRole('ROLE_USER')")
	public Balance findBalanceByUser(@PathVariable String identityId){
		JWT jwt = new JWT();
		System.out.println("token Decripted: " + jwtProvider.getUserNameFromJwtToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbGJlciIsImlhdCI6MTU1MzgyNDIyNywiZXhwIjoxNTUzOTEwNjI3fQ.AesYIxc99o-xKYC_eTiFD6KJ8EV6mIs_MR1tQOzPcOkJjap2yMYcfq-Kidn41W7ESbSWuCceNzrAzA0uqGtYKQ"));
		jwt.parseJWT(jwt.createJWT("30366514", "USER", 12312312));
		return service.getBalance(identityId);
	}
	
	private boolean isUserAuthenticated(String authString){
        
        String decodedAuth = "";
        // Header is in the format "Basic 5tyc0uiDat4"
        // We need to extract data before decoding it back to original string
        String[] authParts = authString.split("\\s+");
        String authInfo = authParts[1];
        // Decode the data back to original string
        byte[] bytes = null;
        try {
            bytes = new BASE64Decoder().decodeBuffer(authInfo);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        decodedAuth = new String(bytes);
        System.out.println("decodedAuth: " + decodedAuth);
         
        /**
         * here you include your logic to validate user authentication.
         * it can be using ldap, or token exchange mechanism or your 
         * custom authentication mechanism.
         */
        // your validation code goes here....
         
        return true;
    }
}
