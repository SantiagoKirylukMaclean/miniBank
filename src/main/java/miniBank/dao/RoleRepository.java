package miniBank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import miniBank.model.Role;
import miniBank.model.User;


@Repository
public class  RoleRepository extends ConnectionDAO{
    
	public List<Role> findByName(String roleName){
		List<Role> roles = new ArrayList<Role>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn = getCurrentConnection();
		try {

			ps = conn.prepareStatement("SELECT * FROM roles");
			rs = ps.executeQuery();
			while (rs.next()) {
				Role role = new Role();
				role.setId(rs.getLong("id"));
				role.setName(rs.getString("name"));
				roles.add(role);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close(rs);
			close(ps);
			close(conn);
		}
		return roles;
	}
}