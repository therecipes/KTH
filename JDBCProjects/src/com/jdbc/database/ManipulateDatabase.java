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
	Scanner inputDatabase = new Scanner(System.in);
	String databaseName = null;

	public ManipulateDatabase(String jdbcDriver, String dbUrl, String user, String pass) {
		myJdbcDriver = jdbcDriver;
		myDbUrl = dbUrl;
		myUser = user;
		myPass = pass;
	}

	public void AddDatabase() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Existing Databases...");
			DisplayDatabases();

			// STEP 2: Register JDBC driver
			Class.forName(myJdbcDriver);

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(myDbUrl, myUser, myPass);

			// STEP 4: Execute a query
			System.out.println("Creating New database...");
			stmt = conn.createStatement();

			// Enter database name
			System.out.print("Enter New Database Name: ");
			databaseName = inputDatabase.next();

			String sql = "CREATE DATABASE " + databaseName;
			stmt.executeUpdate(sql);
			System.out.println("Database created successfully...");

			// Create Table(s)
			System.out.println("1. Create Table. ");
			System.out.println("2. Exit. ");

			Scanner input = new Scanner(System.in);
			int reply = input.nextInt();

			switch (reply) {
			case 1:
				CreateTables();
				break;
			default:
				System.exit(0);
			}
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
		input.close();
	}

	private void DeleteDatabase() {
		// TODO Auto-generated method stub
		try {
			// STEP 2: Register JDBC driver
			Class.forName(myJdbcDriver);

			// Enter database name
			System.out.println("Existing Databases...");
			DisplayDatabases();

			System.out.print("Enter Database Name: ");
			databaseName = inputDatabase.next();
			myDbUrl = myDbUrl + databaseName;

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

			// Enter database name
			System.out.println("Existing Databases...");
			DisplayDatabases();

			System.out.print("Enter Database Name: ");
			databaseName = inputDatabase.next();
			myDbUrl = myDbUrl + databaseName;

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
		input.close();
	}

	private void DisplayDatabases() {
		// TODO Auto-generated method stub
		try {
			// STEP 2: Register JDBC driver
			Class.forName(myJdbcDriver);

			// Enter database name
			// System.out.print("Enter Database Name: ");
			// databaseName = inputDatabase.next();
			// myDbUrl = myDbUrl + databaseName;

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

			System.out.println("Existing Databases...");
			DisplayDatabases();

			// STEP 2: Register JDBC driver
			Class.forName(myJdbcDriver);

			// Enter database name
			System.out.print("Enter Database Name: ");
			databaseName = inputDatabase.next();
			myDbUrl = myDbUrl + databaseName;

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
			System.out.println("Existing Databases...");
			DisplayDatabases();

			// STEP 2: Register JDBC driver
			Class.forName(myJdbcDriver);

			// Enter database name
			System.out.print("Enter Database Name: ");
			databaseName = inputDatabase.next();
			myDbUrl = myDbUrl + databaseName;

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
			System.out.println("Existing Databases...");
			DisplayDatabases();

			// STEP 2: Register JDBC driver
			Class.forName(myJdbcDriver);

			// Enter database name
			System.out.print("Enter Database Name: ");
			databaseName = inputDatabase.next();
			myDbUrl = myDbUrl + databaseName;

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

	public void CreateTables() {
		// TODO Auto-generated method stub
		try {
			// STEP 2: Register JDBC driver
			Class.forName(myJdbcDriver);

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(myDbUrl, myUser, myPass);
			System.out.println("Connected database successfully...");

			// STEP 4: Execute a query
			System.out.println("Creating table in given database...");
			stmt = conn.createStatement();

			// Select table
			System.out.println("1. Table Registration. ");
			System.out.println("2. Table Courses. ");
			System.out.println("3. Table Takes. ");
			System.out.println("4. Create Foreign Key. ");
			System.out.println("5. Exit. ");

			Scanner input = new Scanner(System.in);
			int reply = input.nextInt();

			String sql = null, answer = null;

			switch (reply) {
			case 1:
				sql = "CREATE TABLE REGISTRATION " + "(id INTEGER not NULL, " + " first VARCHAR(255), "
						+ " last VARCHAR(255), " + " age INTEGER, " + " PRIMARY KEY ( id ))";
				stmt.executeUpdate(sql);

				System.out.println("Do you want to insert data into table? ");
				answer = input.next();
				if (answer == "Y") {
					insertDataRegistration();
				}
				break;
			case 2:
				sql = "CREATE TABLE COURSES " + "(courseid1 VARCHAR(8), " + " courseid2 VARCHAR(8), "
						+ " courseid3 VARCHAR(8), " + " courseid4 VARCHAR(8) " + " department VARCHAR(35))";
				stmt.executeUpdate(sql);

				System.out.println("Do you want to insert data into table? ");
				answer = input.next();
				if (answer == "Y") {
					insertDataCourses();
				}
				break;
			case 3:
				sql = "CREATE TABLE TAKES " + "(id INTEGER not NULL, " + "courseid1 VARCHAR(8), "
						+ " courseid2 VARCHAR(8), " + " courseid3 VARCHAR(8), " + " courseid4 VARCHAR(8) "
						+ " PRIMARY KEY ( id ))";
				stmt.executeUpdate(sql);

				System.out.println("Do you want to insert data into table? ");
				answer = input.next();
				if (answer == "Y") {
					insertDataTakes();
				}
				break;
			case 4:
				sql = "ALTER TABLE TAKES ADD FOREIGN KEY (ID) REFERENCES REGISTRATION (ID);";
				stmt.executeUpdate(sql);
				break;
			default:
				System.exit(0);
			}
			stmt.executeUpdate(sql);

			System.out.println("Created table in given database...");
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

	private void insertDataTakes() {
		// TODO Auto-generated method stub

		Scanner input = new Scanner(System.in);
		int idNumber = 0;
		String courseid1 = null;
		String courseid2 = null;
		String courseid3 = null;
		String courseid4 = null;
		char answer = '\0';

		try {
			// STEP 2: Register JDBC driver
			Class.forName(myJdbcDriver);

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(myDbUrl, myUser, myPass);
			System.out.println("Connected database successfully...");

			// STEP 4: Execute a query
			System.out.println("Inserting records into the table...");
			stmt = conn.createStatement();
			do {
				System.out.print("Enter ID number (001 - 999): ");
				idNumber = input.nextInt();
				System.out.print("Enter Student First Course code: ");
				courseid1 = input.next();
				System.out.print("Enter Student 2nd Course code: ");
				courseid2 = input.next();
				System.out.print("Enter Student 3rd Course code: ");
				courseid3 = input.next();
				System.out.print("Enter Student 4th Course code: ");
				courseid4 = input.next();

				String sql = "INSERT INTO Takes " + "VALUES (" + idNumber + ", '" + courseid1 + "', '" + courseid2
						+ "', '" + courseid3 + "', '" + courseid4 + "')";
				stmt.executeUpdate(sql);

				System.out.print(" Enter more data (Y/N)? ");
				answer = Character.toUpperCase((char) input.nextByte());
			} while (answer == 'Y');

			System.out.println("Inserted records into the table...");

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			input.close();
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

	private void insertDataCourses() {
		// TODO Auto-generated method stub

		Scanner input = new Scanner(System.in);
		String courseid1 = null;
		String courseid2 = null;
		String courseid3 = null;
		String courseid4 = null;
		String dept = null;
		char answer = '\0';

		try {
			// STEP 2: Register JDBC driver
			Class.forName(myJdbcDriver);

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(myDbUrl, myUser, myPass);
			System.out.println("Connected database successfully...");

			// STEP 4: Execute a query
			System.out.println("Inserting records into the table...");
			stmt = conn.createStatement();
			do {
				System.out.print("Enter Student First Course code: ");
				courseid1 = input.next();
				System.out.print("Enter Student 2nd Course code: ");
				courseid2 = input.next();
				System.out.print("Enter Student 3rd Course code: ");
				courseid3 = input.next();
				System.out.print("Enter Student 4th Course code: ");
				courseid4 = input.next();
				System.out.print("Enter the Department: ");
				dept = input.next();

				String sql = "INSERT INTO Takes " + "VALUES ('" + courseid1 + "', '" + courseid2 + "', '" + courseid3
						+ "', '" + courseid4 + "', '" + dept + "')";
				stmt.executeUpdate(sql);

				System.out.print(" Enter more data (Y/N)? ");
				answer = Character.toUpperCase((char) input.nextByte());
			} while (answer == 'Y');

			System.out.println("Inserted records into the table...");

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			input.close();
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

	private void insertDataRegistration() {
		// TODO Auto-generated method stub

		Scanner input = new Scanner(System.in);
		int idNumber = 0;
		String firstName = null;
		String lastName = null;
		int age = 0;
		char answer = '\0';

		try {
			// STEP 2: Register JDBC driver
			Class.forName(myJdbcDriver);

			// STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(myDbUrl, myUser, myPass);
			System.out.println("Connected database successfully...");

			// STEP 4: Execute a query
			System.out.println("Inserting records into the table...");
			stmt = conn.createStatement();
			do {
				System.out.print("Enter ID number (001 - 999): ");
				idNumber = input.nextInt();
				System.out.print("Enter Student FirstName: ");
				firstName = input.next();
				System.out.print("Enter Student LastName: ");
				lastName = input.next();
				System.out.print("Enter Student Age: ");
				age = input.nextInt();

				String sql = "INSERT INTO Registration " + "VALUES (" + idNumber + ", '" + firstName + "', '" + lastName
						+ "', " + age + ")";
				stmt.executeUpdate(sql);

				System.out.print(" Enter more data (Y/N)? ");
				answer = Character.toUpperCase((char) input.nextByte());
			} while (answer == 'Y');

			System.out.println("Inserted records into the table...");

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			input.close();
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

}
