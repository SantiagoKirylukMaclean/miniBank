package miniBank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import miniBank.model.Balance;

@Component
public class BalanceRepository extends ConnectionDAO {

	public List<Balance> findAllBalance() {
		List<Balance> balances = new ArrayList<Balance>();

		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn = getCurrentConnection();
		try {
			ps = conn.prepareStatement("SELECT * FROM balance");

			rs = ps.executeQuery();
			while (rs.next()) {
				Balance balance = new Balance();
				balance.setId(rs.getLong("id"));
				balance.setIdentityId(rs.getString("identityId"));
				balance.setAmount(rs.getBigDecimal("amount"));
				balance.setCurrency(rs.getString("currency"));
				balances.add(balance);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close(rs);
			close(ps);
			close(conn);
		}

		return balances;
	}

	public Balance modifyBalance(Balance balance) {

		PreparedStatement ps = null;
		Connection conn = getCurrentConnection();
		try {
			ps = conn.prepareStatement("update balance set amount = ? where identityId = ?");
			ps.setBigDecimal(1, balance.getAmount());
			ps.setString(2, balance.getIdentityId());
			ps.executeUpdate();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception rollbackEx) {

			}

		} finally {

			close(ps);
			close(conn);
		}

		return balance;
	}

}
