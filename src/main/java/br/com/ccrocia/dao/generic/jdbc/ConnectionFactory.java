package br.com.ccrocia.dao.generic.jdbc;

import java.sql.*;

public class ConnectionFactory {
	private static Connection connection;
	
	private ConnectionFactory(Connection connection) {
	
	};
	
	public static Connection getConnection() throws SQLException {
		if(connection == null) {
			connection = initConnection();
		} else if(connection.isClosed() && connection != null) {
			connection = initConnection();
		}
		return connection;
	}
	
	private static Connection initConnection() {
		try {
			return DriverManager.getConnection(
					"jdbc:postgresql://localhost:15432"
					+ "/vendas_online",
					"postgres", "@PostgreeS@L"
					);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
