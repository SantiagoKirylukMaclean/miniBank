package miniBank.Service;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import miniBank.dao.UserRepository;
import miniBank.model.User;
import miniBank.service.UserService;
 


@RunWith(MockitoJUnitRunner.class)
public class TestUserService {
	
	@InjectMocks
	UserService userService;
	
	@Mock
	UserRepository userRepository;
	
	
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
  
    @Before
    public void before() {
    	List<User> users = new ArrayList<User>();
		User user1 = new User("Santiago","santiago","colo@colo.com","$2a$10$QStG9IjkEkmqA7SBR/puN.rn.odQmLcWx94WwtYOcIZHM8xAlVtDG");
		User user2 = new User("Joe","joe","colo@colo.com","$2a$10$MKg2jPCfIDH3CSLXhP37ROsO4qauG6YHeHyEgzZwzwbqwlrLmQioW");
		users.add(user1);
		users.add(user2);
		
		when(userRepository.findAll()).thenReturn(users);
    }
    
	@Test
	public void getAllUserTest() {
		List<User> users = userService.getUsers();
		assertEquals(2, users.size());
	}
	

}
