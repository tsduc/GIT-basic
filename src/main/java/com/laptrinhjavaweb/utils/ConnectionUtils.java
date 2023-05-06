package com.laptrinhjavaweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
	private	static String DB_URL = "jdbc:mysql://localhost:3306/springbootweb22023";
	private static String USER = "root";
	private static String PASS = "123456";
	
	public static final Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			return conn;
		} catch (SQLException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}
}
