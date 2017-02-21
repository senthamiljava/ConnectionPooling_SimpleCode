package com.senthamil;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.naming.*;
import javax.sql.*;
import java.sql.*;



@WebServlet("/select")
public class select extends HttpServlet { 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			Context context=new InitialContext();
			Context envi=(Context) context.lookup("java:comp/env");
			DataSource dataSource=(DataSource) envi.lookup("jdbc/testdb");
			System.out.println("Select datasource:"+ dataSource);
		    Connection connection=dataSource.getConnection();
		    Statement s=connection.createStatement();
		    ResultSet res=s.executeQuery("SELECT id FROM user_details");
		    while(res.next())
		    {
		    	PrintWriter printWriter=response.getWriter();
		    	printWriter.println(res.getInt(1));
		    }
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	

}
