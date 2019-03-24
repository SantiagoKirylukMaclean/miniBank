package miniBank.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.h2.tools.RunScript;

public class StartConfiguration extends ConnectionDAO {
	
	public void startAppConfigurations() {
		Connection conn = getCurrentConnection();
		try {
			RunScript.execute(conn, new BufferedReader (new InputStreamReader(getClass().getResourceAsStream("/SQLInMemory.sql"))));

		} catch (SQLException e) {
			
		}

	}

}
