package miniBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import miniBank.model.User;
import miniBank.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@RequestMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> findAllUsers() {
		return service.getUsers();

	}

}
