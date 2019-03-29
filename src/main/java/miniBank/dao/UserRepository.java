package miniBank.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import miniBank.model.Role;
import miniBank.model.User;

@Repository
public class UserRepository extends ConnectionDAO{
	
	@Autowired
	RoleRepository roleRepository; 
	
	public List<User> findAll(){
		List<User> users = new ArrayList<User>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn = getCurrentConnection();
		try {

			ps = conn.prepareStatement("SELECT * FROM users");
			rs = ps.executeQuery();
			while (rs.next()) {
				Set<Role> roles = new HashSet<>();
				User user = new User();
				user.setId(rs.getLong("id"));
				//roles.add(roleRepository.findAll().stream().filter(r -> r.getId().equals(user.getId())).findAny().get());
				user.setName(rs.getString("name"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				roles.add(new Role());
				
				user.setRoles(roles);
				users.add(user);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close(rs);
			close(ps);
			close(conn);
		}
		return users;
	}

}
