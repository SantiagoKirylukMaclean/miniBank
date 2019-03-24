package miniBank.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import miniBank.dao.ConnectionDAO;

@RestController
public class TestBootController extends ConnectionDAO {

	@RequestMapping("/test")
	public String Test() {
		return "SpringBoot is started";
	}

	@RequestMapping("/testDB")
	public String TestDB() {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn = getCurrentConnection();
		String trxs = "";
		try {

			ps = conn.prepareStatement("SELECT * FROM contracts");
			rs = ps.executeQuery();
			while (rs.next()) {

				trxs = rs.getString("contractID");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return trxs;
	}
}
