package ru.son1q.main;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class Connection {

	private static final String URL = "jdbc:mysql://192.168.56.4:3306/mydb?useUnicode=true&characterEncoding=cp1251";
	private static final String USER = "root";
	private static final String PASSWORD = "123123";
	private static java.sql.Connection myConnect;
	private static Statement stmt;
	public static ResultSet result;
	
	public Connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			myConnect = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// CRUD методы для БД.
	public static void setQuery(String query) {
		try {
			result = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void setInsert(String query) {
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) { 
			e.printStackTrace();
		}
	}
}
