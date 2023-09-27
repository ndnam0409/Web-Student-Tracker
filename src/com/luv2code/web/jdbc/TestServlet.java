package com.luv2code.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.*;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Define datasource/connection pool for Resource Injection
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set up the printWriter
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		// Get a connection to the database
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRes = null;
		try {
			myConn = dataSource.getConnection();
			// Create a SQL statements
			String query = "select * from student";
			myStmt = myConn.createStatement();
			
			// Execute SQL query
			myRes = myStmt.executeQuery(query);
			// Process the result set
			while(myRes.next()) {
				String email = myRes.getString("email");
				out.println(email);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
