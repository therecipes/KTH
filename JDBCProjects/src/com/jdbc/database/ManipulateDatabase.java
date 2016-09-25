package com.jdbc.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ManipulateDatabase {

	Connection conn = null;
	Statement stmt = null;
	private String myJdbcDriver;
	private String myDbUrl;
	private String myUser;
	private String myPass;

	public ManipulateDatabase(String jdbcDriver, String dbUrl, String user, String pass) {
		myJdbcDriver = jdbcDriver;
		myDbUrl = dbUrl;
		myUser = user;
		myPass = pass;
	}

	public void AddDatabase() {
		// TODO Auto-generated method stub
		try {
			// STEP 2: Register JDBC driver
			Class.forName(myJdbcDriver);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(myDbUrl, myUser, myPass);

			// STEP 4: Execute a query
			System.out.println("Creating database...");
			stmt = conn.createStatement();

			String sql = "CREATE DATABASE STUDENTS";
			stmt.executeUpdate(sql);
			System.out.println("Database created successfully...");
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

	public void ChangeDatabase() {
		// TODO Auto-generated method stub

	}

	public void DropDatabase() {
		// TODO Auto-generated method stub
		System.out.println("1. Drop Table. ");
		System.out.println("2. Drop Database. ");
		System.out.println("3. Exit. ");

		Scanner input = new Scanner(System.in);
		int reply = input.nextInt();

		switch (reply) {
		case 1:
			DropTable();
			break;
		case 2:
			DeleteDatabase();
		default:
			System.exit(0);
		}

	}

	private void DeleteDatabase() {
		// TODO Auto-generated method stub
		try {
			// STEP 2: Register JDBC driver
			Class.forName(myJdbcDriver);
			myDbUrl = myDbUrl + "STUDENTS";
			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(myDbUrl, myUser, myPass);
			System.out.println("Connected database successfully...");

			// STEP 4: Execute a query
			System.out.println("Deleting database...");
			stmt = conn.createStatement();

			String sql = "DROP DATABASE STUDENTS";
			stmt.executeUpdate(sql);
			System.out.println("Database deleted successfully...");
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
					conn.close();
			} catch (SQLException se) {
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Goodbye!");
	}

	private void DropTable() {
		// TODO Auto-generated method stub
		try {
			// STEP 2: Register JDBC driver
			Class.forName(myJdbcDriver);
			myDbUrl = myDbUrl + "STUDENTS";

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(myDbUrl, myUser, myPass);
			System.out.println("Connected database successfully...");

			// STEP 4: Execute a query
			System.out.println("Deleting table in given database...");
			stmt = conn.createStatement();

			String sql = "DROP TABLE REGISTRATION ";

			stmt.executeUpdate(sql);
			System.out.println("Table  deleted in given database...");
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
					conn.close();
			} catch (SQLException se) {
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Goodbye!");
	}

	public void ShowDatabase() {
		// TODO Auto-generated method stub
		System.out.println("1. Show Database. ");
		System.out.println("2. Table Regsitration. ");
		System.out.println("3. Table Courses. ");
		System.out.println("4. Table Takes. ");
		System.out.println("5. Exit. ");

		Scanner input = new Scanner(System.in);
		int reply = input.nextInt();

		switch (reply) {
		case 1:
			DisplayDatabases();
			break;
		case 2:
			TableRegsitration();
			break;
		case 3:
			TableCourses();
		case 4:
			TableTakes();
		default:
			System.exit(0);
		}

	}

	private void DisplayDatabases() {
		// TODO Auto-generated method stub
		try {
			// STEP 2: Register JDBC driver
			Class.forName(myJdbcDriver);
			myDbUrl = myDbUrl + "STUDENTS";

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(myDbUrl, myUser, myPass);
			System.out.println("Connected database successfully...");

			// STEP 4: Execute a query
			System.out.println("Display databases...");
			stmt = conn.createStatement();

			DatabaseMetaData meta = conn.getMetaData();
			ResultSet res = meta.getCatalogs();
			System.out.println("List of databases: ");
			while (res.next()) {
				System.out.println("   " + res.getString("TABLE_CAT"));
			}
			res.close();

			System.out.println("Database displayed...");
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
					conn.close();
			} catch (SQLException se) {
			} // do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Goodbye!");

	}

	private void TableTakes() {
		// TODO Auto-generated method stub
		try {
			// STEP 2: Register JDBC driver
			Class.forName(myJdbcDriver);
			myDbUrl = myDbUrl + "STUDENTS";
			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(myDbUrl, myUser, myPass);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT id, coursesid1, coursesid2, coursesid3, coursesid4 FROM Takes";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("id");
				String coursesid1 = rs.getString("coursesid1");
				String coursesid2 = rs.getString("coursesid2");
				String coursesid3 = rs.getString("coursesid3");
				String coursesid4 = rs.getString("coursesid4");

				// Display values
				System.out.print("ID: " + id);
				System.out.print(", Courses ID1 : " + coursesid1);
				System.out.print(", Courses ID2: " + coursesid2);
				System.out.println(", Courses ID3: " + coursesid3);
				System.out.println(", Courses ID4: " + coursesid4);
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
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

	private void TableCourses() {
		// TODO Auto-generated method stub
		try {
			// STEP 2: Register JDBC driver
			Class.forName(myJdbcDriver);
			myDbUrl = myDbUrl + "STUDENTS";
			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(myDbUrl, myUser, myPass);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT coursesid1, coursesid2, coursesid3, coursesid4, department FROM Courses";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				String coursesid1 = rs.getString("coursesid1");
				String coursesid2 = rs.getString("coursesid2");
				String coursesid3 = rs.getString("coursesid3");
				String coursesid4 = rs.getString("coursesid4");
				String dept = rs.getString("department");

				// Display values
				System.out.print(", Courses ID1: " + coursesid1);
				System.out.print(", Courses ID2: " + coursesid2);
				System.out.print(", Courses ID3: " + coursesid3);
				System.out.print(", Courses ID4: " + coursesid4);
				System.out.println(", Department: " + dept);
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
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

	private void TableRegsitration() {
		// TODO Auto-generated method stub
		try {
			// STEP 2: Register JDBC driver
			Class.forName(myJdbcDriver);
			myDbUrl = myDbUrl + "STUDENTS";

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(myDbUrl, myUser, myPass);

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT id, first, last, age FROM Registration";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("id");
				String first = rs.getString("first");
				String last = rs.getString("last");
				String age = rs.getString("age");

				// Display values
				System.out.print("ID: " + id);
				System.out.print(", First : " + first);
				System.out.print(", Last " + last);
				System.out.println(", Age " + age);
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
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
