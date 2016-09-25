package com.jdbc.database;

import java.sql.Connection;
import java.sql.DriverManager;
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
		try{
		      //STEP 2: Register JDBC driver
		      Class.forName(myJdbcDriver);

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(myDbUrl, myUser, myPass);

		      //STEP 4: Execute a query
		      System.out.println("Creating database...");
		      stmt = conn.createStatement();
		      
		      String sql = "CREATE DATABASE STUDENTS";
		      stmt.executeUpdate(sql);
		      System.out.println("Database created successfully...");
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
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
		try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(myDbUrl, myUser, myPass);
		      System.out.println("Connected database successfully...");
		      
		      //STEP 4: Execute a query
		      System.out.println("Deleting database...");
		      stmt = conn.createStatement();
		      
		      String sql = "DROP DATABASE STUDENTS";
		      stmt.executeUpdate(sql);
		      System.out.println("Database deleted successfully...");
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
	}

	private void DropTable() {
		// TODO Auto-generated method stub
		try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(myDbUrl, myUser, myPass);
		      System.out.println("Connected database successfully...");
		      
		      //STEP 4: Execute a query
		      System.out.println("Deleting table in given database...");
		      stmt = conn.createStatement();
		      
		      String sql = "DROP TABLE REGISTRATION ";
		 
		      stmt.executeUpdate(sql);
		      System.out.println("Table  deleted in given database...");
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
	}

	public void ShowDatabase() {
		// TODO Auto-generated method stub
		
	}

}
