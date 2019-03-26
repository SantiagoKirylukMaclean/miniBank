package miniBank.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import miniBank.model.Balance;
import miniBank.security.JWT;
import miniBank.service.BalanceService;
import sun.misc.BASE64Decoder;

@RestController
public class BalanceController {
	
	@Autowired
	private BalanceService service;

	@RequestMapping("/balance/{identityId}")
	public Balance findBalanceByUser(@PathVariable String identityId, @RequestHeader("authorization") String authString){
		JWT jwt = new JWT();
		System.out.println("authString: " + authString);
		System.out.println("token: " + jwt.createJWT("30366514", "USER", 12312312));
		jwt.parseJWT(jwt.createJWT("30366514", "USER", 12312312));
		isUserAuthenticated(authString);
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
