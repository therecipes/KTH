package com.jdbc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JavaJDBCDatabase {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/STUDENTS";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "?XLq4K*Z(oyH";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection conn = null;
		Statement stmt = null;

		System.out.println("1. Add Database. ");
		System.out.println("2. Change Database. ");
		System.out.println("3. Remove Database. ");
		System.out.println("4. Show Database. ");
		System.out.println("5. Exit. ");

		Scanner input = new Scanner(System.in);
		int reply = input.nextInt();

		switch (reply) {
		case 1:
			AddDatabase addDatabase = new AddDatabase(JDBC_DRIVER, DB_URL, USER, PASS);
			break;
		case 2:
			ChangeDatabase chnageDatabase = new ChangeDatabase(JDBC_DRIVER, DB_URL, USER, PASS);
			break;
		case 3:
			System.out.println("Well done");
			break;
		case 4:
			System.out.println("You passed");
		default:
			System.exit(0);
		}

		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");

			// STEP 4: Execute a query
			System.out.println("Inserting recordes...");
			stmt = conn.createStatement();

			// execute statement

			String sql = "INSERT INTO Takes " + "VALUES (101, 'COMP111', 'ACCT221', 'SWE011', 'ENGR231')";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Takes " + "VALUES (108, 'CHEM123', 'ENGR131', 'COMP211', 'ITA210')";
			stmt.executeUpdate(sql);
			sql = "INSERT INTO Takes " + "VALUES (103, 'PHYS109', 'CHEM201', 'BIO100', 'PHYS219')";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO Takes " + "VALUES (105, 'ARCH109', 'ARCH198', 'ARCH219', 'PHYS219')";
			stmt.executeUpdate(sql);

			System.out.println("Inserted records into the table...");

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Goodbye!");
	}

}
