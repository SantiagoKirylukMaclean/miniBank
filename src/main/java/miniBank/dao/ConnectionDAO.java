package miniBank.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class ConnectionDAO {
	
	private static final String DB_DRIVER = "org.h2.Driver";
	private static final String DB_URL = "jdbc:h2:mem:challenge";
	private static final String DB_USER = "sa";
	private static final String DB_PASSWORD = "sa";
	private static final ComboPooledDataSource poolConnection = new ComboPooledDataSource();
	private static final Logger log = LogManager.getLogger(ConnectionDAO.class);

	static {
		try {
			poolConnection.setDriverClass(DB_DRIVER);
			poolConnection.setJdbcUrl(DB_URL);
			poolConnection.setUser(DB_USER);
			poolConnection.setPassword(DB_PASSWORD);
		} catch (PropertyVetoException e) {
			log.error("Error crate Connection DB",e);
		}

	}

	protected static Connection getCurrentConnection() {
		try {
			return poolConnection.getConnection();
		} catch (Exception e) {
			log.error("Error getCurrentConnection DB",e);
			return null;
		}
	}

	protected static void close(Object obj) {
		if (obj != null) {
			try {
				if (obj instanceof PreparedStatement) {
					((PreparedStatement) obj).close();
				} else if (obj instanceof ResultSet) {
					((ResultSet) obj).close();
				} else if (obj instanceof Connection) {
					((Connection) obj).close();
				}
			} catch (Exception e) {

			}

		}
	}


}
