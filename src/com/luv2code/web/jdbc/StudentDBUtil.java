package com.luv2code.web.jdbc;

import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

public class StudentDBUtil {
	private DataSource dataSource;
	
	public StudentDBUtil(DataSource theDataSource) {
		this.dataSource = theDataSource;
	}
	
	public List<Student> getStudents() throws Exception{
		List<Student> students = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			// get connection
			myConn = dataSource.getConnection();
			// create sql statement
			String query = "select * from student order by last_name";
			myStmt = myConn.createStatement();
			// execute query
			myRs = myStmt.executeQuery(query);
			// process the result set
			while (myRs.next()) {
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				// create new student object 
				Student newStudent = new Student(id,firstName, lastName, email);
				
				// add it to the list of students
				students.add(newStudent);
			}
			// close JDBC objects
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
		
		return students;
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if(myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myRs.close();
			}
			
			if (myConn != null) {
				myConn.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
