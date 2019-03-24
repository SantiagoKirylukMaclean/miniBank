package miniBank.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import miniBank.model.User;

@Component
public class UserRepository extends ConnectionDAO{
	
	
	public List<User> findAll(){
		List<User> users = new ArrayList<User>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn = getCurrentConnection();
		try {
			//users = new ArrayList<users>();
			ps = conn.prepareStatement("SELECT * FROM users");
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getLong("id"));
				user.setUserId(rs.getString("userId"));
				user.setPassword(rs.getString("password"));
				user.setIdentityId(rs.getString("identityId"));
				user.setName(rs.getString("name"));
				user.setLastName(rs.getString("lastName"));
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
