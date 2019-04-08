package miniBank.Controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import miniBank.controller.AuthController;
import miniBank.dao.RoleRepository;
import miniBank.dao.UserRepository;
import miniBank.message.LoginForm;
import miniBank.message.SignUpForm;
import miniBank.model.Role;
import miniBank.model.RoleName;
import miniBank.security.JwtProvider;

@RunWith(MockitoJUnitRunner.class)
public class TestAuthController {


	
	@Mock
	LoginForm loginForm = new LoginForm();
	
	
	SignUpForm signupFormSantiago = new SignUpForm();
	SignUpForm signupFormUserError = new SignUpForm();
	SignUpForm signupFormMailError=  new SignUpForm();
	
	@Mock
	AuthenticationManager authenticationManager;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	Authentication authentication;
	
	@Mock
	RoleRepository roleRepository;
	
	@Mock
	JwtProvider jwtProvider;
	
	@Mock
	PasswordEncoder encoder;
	
	@InjectMocks
	AuthController authController;

	@Before
	public void setup() {

	when(authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()))).thenReturn(authentication);
	when(jwtProvider.generateJwtToken(authentication)).thenReturn("Tocken");

	signupFormSantiago.setUsername("santiago");
	signupFormSantiago.setName("santiago");
	signupFormSantiago.setEmail("santiago@santiago.com");
	signupFormSantiago.setPassword("123456789");
	Set<String> rolesSantiago = new HashSet<String>();
	rolesSantiago.add("admin");
	rolesSantiago.add("pm");
	rolesSantiago.add("user");
	signupFormSantiago.setRole(rolesSantiago);
	when(userRepository.existsByUsername(signupFormSantiago.getUsername())).thenReturn(false);
	when(userRepository.existsByEmail(signupFormSantiago.getEmail())).thenReturn(false);
	
	signupFormUserError.setUsername("joe");
	when(userRepository.existsByUsername(signupFormUserError.getUsername())).thenReturn(true);
	
	signupFormMailError.setEmail("joe@joe.com");
	when(userRepository.existsByEmail(signupFormMailError.getEmail())).thenReturn(true);
	
	Role roleAdmin = new Role(RoleName.ROLE_ADMIN);
	when(roleRepository.findByName(RoleName.ROLE_ADMIN)).thenReturn(Optional.of(roleAdmin));
	Role rolePm = new Role(RoleName.ROLE_PM);
	when(roleRepository.findByName(RoleName.ROLE_PM)).thenReturn(Optional.of(rolePm));
	Role roleUser = new Role(RoleName.ROLE_USER);
	when(roleRepository.findByName(RoleName.ROLE_USER)).thenReturn(Optional.of(roleUser));

	
	}

	@Test
	public void authenticateUserTest() {
		ResponseEntity<?> responseEntity = authController.authenticateUser(loginForm);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	public void registerUserOkTest() {
		ResponseEntity<?> responseEntity = authController.registerUser(signupFormSantiago);
		assertEquals(ResponseEntity.ok().body("User registered successfully!"), responseEntity);
	}
	
	@Test
	public void registerUserUsernameErrorTest() {
		ResponseEntity<?> responseEntity = authController.registerUser(signupFormUserError);
		assertEquals(ResponseEntity.badRequest().body("Fail -> Username is already taken!"), responseEntity);
	}
	
	@Test
	public void registerUserEmailErrorTest() {
		ResponseEntity<?> responseEntity = authController.registerUser(signupFormMailError);
		assertEquals(ResponseEntity.badRequest().body("Fail -> Email is already in use!"), responseEntity);
	}
	
}