package miniBank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import miniBank.dao.RoleRepository;
import miniBank.dao.UserRepository;
import miniBank.message.JwtResponse;
import miniBank.message.LoginForm;
import miniBank.model.User;
import miniBank.security.JwtProvider;
import miniBank.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	  @Autowired
	    AuthenticationManager authenticationManager;

	    @Autowired
	    UserRepository userRepository;

	    @Autowired
	    RoleRepository roleRepository;

	    @Autowired
	    PasswordEncoder encoder;

	    @Autowired
	    JwtProvider jwtProvider;
	    
	    
	    @PostMapping("/signin")
	    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

	    	System.out.println("password: " + loginRequest.getPassword());
	    	System.out.println("encode: " + encoder.encode(loginRequest.getPassword()));
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        loginRequest.getUsername(),
	                        loginRequest.getPassword()
	                )
	        );

	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        String jwt = jwtProvider.generateJwtToken(authentication);
	        return ResponseEntity.ok(new JwtResponse(jwt));
	    }

}
