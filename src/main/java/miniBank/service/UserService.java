package miniBank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import miniBank.dao.UserRepository;
import miniBank.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> getUsers(){
		return repository.findAll();

	}

}
