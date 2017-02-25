package com.jdbc.database;

import java.util.Scanner;

public class JavaJDBCDatabase {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "?XLq4K*Z(oyH";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ManipulateDatabase manipulateDatabase = new ManipulateDatabase(JDBC_DRIVER, DB_URL, USER, PASS);
		int reply = 0;
		Scanner input = null;
		while (true) {
			System.out.println("1. Add Database. ");
			System.out.println("2. Change Database. ");
			System.out.println("3. Remove Database. ");
			System.out.println("4. Show Database. ");
			System.out.println("5. Add Tables. ");
			System.out.println("6. Add records to database. ");
			System.out.println("7. Change records in database. ");
			System.out.println("8. Exit. ");

			input = new Scanner(System.in);
			if (input.hasNextInt()) {reply = input.nextInt();}

			switch (reply) {
			case 1:
				manipulateDatabase.AddDatabase();
				break;
			case 2:
				manipulateDatabase.ChangeDatabase();
				break;
			case 3:
				manipulateDatabase.DropDatabase();
				break;
			case 4:
				manipulateDatabase.ShowDatabase();
				break;
			case 5:
				manipulateDatabase.CreateTables();
				break;
			case 6:
				manipulateDatabase.AddRecords();
				break;
			case 7:
				manipulateDatabase.ChangeRecords();
				break;
			default:
				System.out.println("Goodbye!");
				System.exit(0);
			}
			
			input.close();
		}

	}

}
