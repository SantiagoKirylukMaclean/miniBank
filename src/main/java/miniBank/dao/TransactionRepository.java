package miniBank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

import org.springframework.stereotype.Repository;

import miniBank.model.Transaction;

@Repository
public class TransactionRepository extends ConnectionDAO{
	
	public Transaction addTransaction(Transaction transaction) {
		
		Connection conn = getCurrentConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
		conn.setAutoCommit(false);
		ps = conn.prepareStatement("INSERT into transactions (identityId, amount, created) values (?,?,?)");
		ps.setString(1, transaction.getIdentityId());
		ps.setBigDecimal(2, transaction.getAmount());
		ps.setObject(3,new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		ps.executeUpdate();
		rs = ps.getGeneratedKeys();

		conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception rollbackEx) {

			}

		} finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
		return transaction;

	}

}
