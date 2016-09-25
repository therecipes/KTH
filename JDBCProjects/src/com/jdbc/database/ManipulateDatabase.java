package com.jdbc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
		
	}

	public void ShowDatabase() {
		// TODO Auto-generated method stub
		
	}

}
