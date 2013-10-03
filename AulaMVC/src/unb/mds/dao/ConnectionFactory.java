package unb.mds.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private final static String DATABASE = "jdbc:mysql://localhost/mds";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "root";

	public Connection getConnection() {

		try {
			return DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
