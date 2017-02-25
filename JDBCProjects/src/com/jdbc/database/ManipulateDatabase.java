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
		int reply = 0;
		Scanner input = null;

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

			input = new Scanner(System.in);
			if (input.hasNextInt()) {
				reply = input.nextInt();
			}
			;

			switch (reply) {
			case 1:
				CreateTables();
				break;
			default:
				System.out.println("Goodbye!");
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
			// System.out.println("Goodbye!");

	}

	public void ChangeDatabase() {
		// TODO Auto-generated method stub

	}

	public void DropDatabase() {
		// TODO Auto-generated method stub
		int reply = 0;
		Scanner input = null;

		System.out.println("1. Drop Table. ");
		System.out.println("2. Drop Database. ");
		System.out.println("3. Exit. ");

		input = new Scanner(System.in);
		if (input.hasNextInt()) {
			reply = input.nextInt();
		}
		;

		switch (reply) {
		case 1:
			DropTable();
			break;
		case 2:
			DeleteDatabase();
			break;
		default:
			System.exit(0);
		}
		input.close();
	}

	private void DeleteDatabase() {
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
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(myDbUrl, myUser, myPass);
			System.out.println("Connected database successfully...");

			// STEP 4: Execute a query
			System.out.println("Deleting database...");
			stmt = conn.createStatement();

			String sql = "DROP DATABASE " + databaseName;
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
			//// System.out.println("Goodbye!");
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
			// System.out.println("Goodbye!");
	}

	public void ShowDatabase() {
		// TODO Auto-generated method stub
		int reply = 0;
		Scanner input = null;

		System.out.println("1. Show Database. ");
		System.out.println("2. Table Regsitration. ");
		System.out.println("3. Table Courses. ");
		System.out.println("4. Table Takes. ");
		System.out.println("5. Exit. ");

		input = new Scanner(System.in);
		if (input.hasNextInt()) {
			reply = input.nextInt();
		}
		;

		switch (reply) {
		case 1:
			DisplayDatabases();
			break;
		case 2:
			TableRegsitration();
			break;
		case 3:
			TableCourses();
			break;
		case 4:
			TableTakes();
			break;
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
			// System.out.println("Goodbye!");

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
			sql = "SELECT id, courseid1, courseid2, courseid3, courseid4 FROM Takes";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set

			System.out.println("------------------------------------------------------");
			System.out.println("|  ID  | Course 1 | Course 2  |  Course 3 | Course 4 |");
			System.out.println("------------------------------------------------------");

			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("id");
				String courseid1 = rs.getString("courseid1");
				String courseid2 = rs.getString("courseid2");
				String courseid3 = rs.getString("courseid3");
				String courseid4 = rs.getString("courseid4");

				// Display values
				System.out.print("|  " + id);
				System.out.print("  |  " + courseid1);
				System.out.print(" |  " + courseid2);
				System.out.print(" |  " + courseid3);
				System.out.println("  |  " + courseid4 + " |");
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
			// System.out.println("Goodbye!");
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
			sql = "SELECT courseid1, courseid2, courseid3, courseid4, department FROM Courses";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			System.out.println("------------------------------------------------------------------------------------");
			System.out
					.println("| Course 1    |    Course 2   |   Course 3 |    Course 4  |     		Department 		|");
			System.out.println("------------------------------------------------------------------------------------");
			while (rs.next()) {
				// Retrieve by column name
				String coursesid1 = rs.getString("courseid1");
				String coursesid2 = rs.getString("courseid2");
				String coursesid3 = rs.getString("courseid3");
				String coursesid4 = rs.getString("courseid4");
				String dept = rs.getString("department");

				// Display values
				System.out.print("| " + coursesid1);
				System.out.print("  |  " + coursesid2);
				System.out.print(" | " + coursesid3);
				System.out.print(" | " + coursesid4);
				System.out.println(" | " + dept + " | ");
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
			// System.out.println("Goodbye!");
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
			System.out.println("------------------------------------------------");
			System.out.println("| ID    |    First Name   |   Last Name |    Age |");
			System.out.println("------------------------------------------------");
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("id");
				String first = rs.getString("first");
				String last = rs.getString("last");
				String age = rs.getString("age");

				// Display values
				System.out.print("| " + id);
				System.out.print("   |  " + first);
				System.out.print("      | " + last);
				System.out.println("   | " + age + " | ");
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
			// System.out.println("Goodbye!");
	}

	public void CreateTables() {
		// TODO Auto-generated method stub
		try {
			int reply = 0;
			Scanner input = null;

			System.out.println("Existing Databases...");
			DisplayDatabases();

			// STEP 2: Register JDBC driver
			Class.forName(myJdbcDriver);

			// Enter database name
			System.out.print("Enter Database Name: ");
			databaseName = inputDatabase.next();
			myDbUrl = myDbUrl + databaseName;

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

			input = new Scanner(System.in);
			if (input.hasNextInt()) {
				reply = input.nextInt();
			}
			;

			String sql = null, answer = null;

			switch (reply) {
			case 1:
				sql = "CREATE TABLE REGISTRATION " + "(id INTEGER not NULL, " + " first VARCHAR(255), "
						+ " last VARCHAR(255), " + " age INTEGER, " + " PRIMARY KEY ( id ))";
				stmt.executeUpdate(sql);
				System.out.println("Table created successfully! ");

				System.out.println("Do you want to insert data into table? ");
				answer = input.next();
				if (answer == "Y") {
					insertDataRegistration();
				}
				break;
			case 2:
				sql = "CREATE TABLE COURSES " + "(courseid1 VARCHAR(8), " + " courseid2 VARCHAR(8), "
						+ " courseid3 VARCHAR(8), " + " courseid4 VARCHAR(8), " + " department VARCHAR(35))";
				stmt.executeUpdate(sql);
				System.out.println("Table created successfully! ");

				System.out.println("Do you want to insert data into table? ");
				answer = input.next();
				if (answer == "Y") {
					insertDataCourses();
				}
				break;
			case 3:
				sql = "CREATE TABLE TAKES " + "(id INTEGER not NULL, " + "courseid1 VARCHAR(8), "
						+ " courseid2 VARCHAR(8), " + " courseid3 VARCHAR(8), " + " courseid4 VARCHAR(8), "
						+ " PRIMARY KEY ( id ))";
				stmt.executeUpdate(sql);
				System.out.println("Table created successfully! ");

				System.out.println("Do you want to insert data into table? ");
				answer = input.next();
				if (answer == "Y") {
					insertDataTakes();
				}
				break;
			case 4:
				sql = "ALTER TABLE TAKES ADD FOREIGN KEY (ID) REFERENCES REGISTRATION (ID);";
				stmt.executeUpdate(sql);
				System.out.println("FOREIGN KEY created successfully! ");
				break;
			default:
				System.exit(0);
			}
			input.close();
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
			// System.out.println("Goodbye!");
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
			System.out.println("Existing Databases...");
			DisplayDatabases();

			// STEP 2: Register JDBC driver
			Class.forName(myJdbcDriver);

			// Enter database name
			System.out.print("Enter Database Name: ");
			databaseName = inputDatabase.next();
			myDbUrl = myDbUrl + databaseName;

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

				String sql = "INSERT INTO Takes VALUES (" + idNumber + ", '" + courseid1 + "', '" + courseid2 + "', '"
						+ courseid3 + "', '" + courseid4 + "')";
				stmt.executeUpdate(sql);

				System.out.print(" Enter more data (Y/N)? ");
				answer = Character.toUpperCase(input.next().charAt(0));
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
			// System.out.println("Goodbye!");
	}

	private void insertDataCourses() {
		// TODO Auto-generated method stub

		Scanner inputCourses = new Scanner(System.in);
		String courseid1 = null;
		String courseid2 = null;
		String courseid3 = null;
		String courseid4 = null;
		String dept = null;
		char answer = '\0';

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
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(myDbUrl, myUser, myPass);
			System.out.println("Connected database successfully...");

			// STEP 4: Execute a query
			System.out.println("Inserting records into the table...");
			stmt = conn.createStatement();
			do {
				System.out.print("Enter Student First Course code: ");
				courseid1 = inputCourses.next();
				System.out.print("Enter Student 2nd Course code: ");
				courseid2 = inputCourses.next();
				System.out.print("Enter Student 3rd Course code: ");
				courseid3 = inputCourses.next();
				System.out.print("Enter Student 4th Course code: ");
				courseid4 = inputCourses.next();
				System.out.print("Enter the Department: ");
				dept = inputCourses.next();

				String sql = "INSERT INTO Courses VALUES ('" + courseid1 + "', '" + courseid2 + "', '" + courseid3
						+ "', '" + courseid4 + "', '" + dept + "')";
				stmt.executeUpdate(sql);

				System.out.print(" Enter more data (Y/N)? ");
				answer = Character.toUpperCase(inputCourses.next().charAt(0));
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
			inputCourses.close();
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
			// System.out.println("Goodbye!");
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
			System.out.println("Existing Databases...");
			DisplayDatabases();

			// STEP 2: Register JDBC driver
			Class.forName(myJdbcDriver);

			// Enter database name
			System.out.print("Enter Database Name: ");
			databaseName = inputDatabase.next();
			myDbUrl = myDbUrl + databaseName;

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
				answer = Character.toUpperCase(input.next().charAt(0));
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
			// System.out.println("Goodbye!");
	}

	public void AddRecords() {
		// TODO Auto-generated method stub
		int reply = 0;
		Scanner input = null;

		System.out.println("1. Add records to Registration. ");
		System.out.println("2. Add records to Courses. ");
		System.out.println("3. Add records to Takes. ");
		System.out.println("4. Exit. ");

		input = new Scanner(System.in);
		if (input.hasNextInt()) {
			reply = input.nextInt();
		}
		;

		switch (reply) {
		case 1:
			insertDataRegistration();
			break;
		case 2:
			insertDataCourses();
			break;
		case 3:
			insertDataTakes();
			break;
		default:
			System.exit(0);
		}
		input.close();
	}

	public void ChangeRecords() {
		// TODO Auto-generated method stub
		int reply = 0;
		Scanner input = null;

		System.out.println("1. Change records in Table Registration. ");
		System.out.println("2. Change records in Table Courses. ");
		System.out.println("3. Change records in Table Takes ");
		System.out.println("4. Exit. ");

		input = new Scanner(System.in);
		if (input.hasNextInt()) {
			reply = input.nextInt();
		}
		;

		switch (reply) {
		case 1:
			ChangeRegsitration();
			break;
		case 2:
			ChangeCourses();
			break;
		case 3:
			ChangeTakes();
			break;
		default:
			// System.out.println("Goodbye!");
			System.exit(0);
		}
input.close();
	}

	private void ChangeRegsitration() {
		// TODO Auto-generated method stub
		int reply = 0;
		Scanner input = null;

		try {
			TableRegsitration();

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			System.out.println("What do you want to change? ");
			System.out.println("1. First Name. ");
			System.out.println("2. Last Name. ");
			System.out.println("3. Age. ");

			input = new Scanner(System.in);
			if (input.hasNextInt()) {
				reply = input.nextInt();
			}
			;

			switch (reply) {
			case 1:
				System.out.print("Enter Old First Name ");
				String oldFirstName = input.next();
				System.out.print("Enter New First Name ");
				String newFirstName = input.next();
				String sql = "UPDATE Registration SET first = " + newFirstName + " WHERE id in (" + oldFirstName + ")";
				stmt.executeUpdate(sql);
				break;
			case 2:
				System.out.print("Enter Old Last Name ");
				String oldLastName = input.next();
				System.out.print("Enter New Last Name ");
				String newLastName = input.next();
				sql = "UPDATE Registration SET last = " + newLastName + "WHERE id in (" + oldLastName + ")";
				stmt.executeUpdate(sql);
				break;
			case 3:
				System.out.print("Enter Old Age ");
				String oldAge = input.next();
				System.out.print("Enter New Age");
				String newAge = input.next();
				sql = "UPDATE Registration SET first = " + newAge + " WHERE id in (" + oldAge + ")";
				stmt.executeUpdate(sql);
				break;
			default:
				new JavaJDBCDatabase();

			}

			TableRegsitration();

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

	}

	private void ChangeCourses() {
		// TODO Auto-generated method stub
		int reply = 0;
		Scanner input = null;

		try {
			TableCourses();

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			System.out.println("What do you want to change? ");
			System.out.println("1. Course 1. ");
			System.out.println("2. Course 2. ");
			System.out.println("3. Course 3. ");
			System.out.println("4. Course 4. ");
			System.out.println("5. Department. ");

			input = new Scanner(System.in);
			if (input.hasNextInt()) {
				reply = input.nextInt();
			}
			;

			switch (reply) {
			case 1:
				System.out.print("Enter Old Course 1 ");
				String oldCourse1 = input.next();
				System.out.print("Enter New Course 1 ");
				String newCourse1 = input.next();
				String sql = "UPDATE Registration SET first = " + newCourse1 + " WHERE id in (" + oldCourse1 + ")";
				stmt.executeUpdate(sql);
				break;
			case 2:
				System.out.print("Enter Old Course 2 ");
				String oldCourse2 = input.next();
				System.out.print("Enter New Course 2 ");
				String newCourse2 = input.next();
				sql = "UPDATE Registration SET first = " + newCourse2 + " WHERE id in (" + oldCourse2 + ")";
				stmt.executeUpdate(sql);
				break;
			case 3:
				System.out.print("Enter Old Course 3 ");
				String oldCourse3 = input.next();
				System.out.print("Enter New Course 3 ");
				String newCourse3 = input.next();
				sql = "UPDATE Registration SET first = " + newCourse3 + " WHERE id in (" + oldCourse3 + ")";
				stmt.executeUpdate(sql);
				break;
			case 4:
				System.out.print("Enter Old Course 4 ");
				String oldCourse4 = input.next();
				System.out.print("Enter New Course 4 ");
				String newCourse4 = input.next();
				sql = "UPDATE Registration SET first = " + newCourse4 + " WHERE id in (" + oldCourse4 + ")";
				stmt.executeUpdate(sql);
				break;
			case 5:
				System.out.print("Enter Old Department ");
				String oldDepartment = input.next();
				System.out.print("Enter New Department ");
				String newDepartment = input.next();
				sql = "UPDATE Registration SET first = " + newDepartment + " WHERE id in (" + oldDepartment + ")";
				stmt.executeUpdate(sql);
				break;
			default:
				new JavaJDBCDatabase();
			}

			TableCourses();

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

	}

	private void ChangeTakes() {
		// TODO Auto-generated method stub
		int reply = 0;
		Scanner input = null;

		try {
			TableCourses();

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			System.out.println("What do you want to change? ");
			System.out.println("1. Course 1. ");
			System.out.println("2. Course 2. ");
			System.out.println("3. Course 3. ");
			System.out.println("4. Course 4. ");
			System.out.println("5. Department. ");

			input = new Scanner(System.in);
			if (input.hasNextInt()) {
				reply = input.nextInt();
			}
			;

			switch (reply) {
			case 1:
				System.out.print("Enter Old Course 1 ");
				String oldCourse1 = input.next();
				System.out.print("Enter New Course 1 ");
				String newCourse1 = input.next();
				String sql = "UPDATE Registration SET first = " + newCourse1 + " WHERE id in (" + oldCourse1 + ")";
				stmt.executeUpdate(sql);
				break;
			case 2:
				System.out.print("Enter Old Course 2 ");
				String oldCourse2 = input.next();
				System.out.print("Enter New Course 2 ");
				String newCourse2 = input.next();
				sql = "UPDATE Registration SET first = " + newCourse2 + " WHERE id in (" + oldCourse2 + ")";
				stmt.executeUpdate(sql);
				break;
			case 3:
				System.out.print("Enter Old Course 3 ");
				String oldCourse3 = input.next();
				System.out.print("Enter New Course 3 ");
				String newCourse3 = input.next();
				sql = "UPDATE Registration SET first = " + newCourse3 + " WHERE id in (" + oldCourse3 + ")";
				stmt.executeUpdate(sql);
				break;
			case 4:
				System.out.print("Enter Old Course 4 ");
				String oldCourse4 = input.next();
				System.out.print("Enter New Course 4 ");
				String newCourse4 = input.next();
				sql = "UPDATE Registration SET first = " + newCourse4 + " WHERE id in (" + oldCourse4 + ")";
				stmt.executeUpdate(sql);
				break;
			case 5:
				System.out.print("Enter Old Department ");
				String oldDepartment = input.next();
				System.out.print("Enter New Department ");
				String newDepartment = input.next();
				sql = "UPDATE Registration SET first = " + newDepartment + " WHERE id in (" + oldDepartment + ")";
				stmt.executeUpdate(sql);
				break;
			default:
				new JavaJDBCDatabase();
			}

			TableCourses();

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

	}
}