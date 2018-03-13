package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	public String driver = "com.mysql.jdbc.Driver";
	public String url = "jdbc:mysql://localhost/library?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public String username = "myuser";
	public String password = "mypassword";

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, username, password);
		conn.setAutoCommit(Boolean.FALSE);
		return conn;
	}

}
