package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSetMetaData;

// TEST database code from Tutorials Point, working

public class Database {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/";

	// Database credentials
	static final String DB_NAME = "SECANALYSIS";
	static final String USER = "user";
	static final String PASS = "password";

	private Connection conn;
	private Statement stmt;
	private ResultSet rs;

	public void startDB() {
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL + DB_NAME + "?autoReconnect=true&useSSL=false", USER, PASS);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();

			// STEP 5: Create database
			System.out.println("Initialising database...");
			stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS USERS (firstname VARCHAR(40), lastname VARCHAR(100))");

		} catch (SQLException se) {
			// Handle database already exists
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	}

	public void closeDB() {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException se2) {

		}
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public void query(String q) {
		if (stmt != null) {
			try {
				System.out.println("Trying..." + q);
				stmt.executeUpdate(q);
				System.out.println("Success: " + q);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<String[]> selectValues(String q, int n) {
		assert(n > 0);
		System.out.println("Trying..." + q);
		List<String[]> result = new ArrayList<String[]>();
		if (stmt != null) {
			try {
				rs = stmt.executeQuery(q);
				System.out.println("Success: " + q);
				while (rs.next()) {
					String[] values = new String[n];
					for (int i = 0; i < n; i++) {
						values[i] = rs.getString(i + 1);
						
					}
					result.add(values);
				}
			}  catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
