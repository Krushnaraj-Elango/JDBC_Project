package dao;

import java.sql.*;

import connectionManager.ConnectionManager;
import model.Login;

public class LoginDAO {
	
	public boolean validate(Login login) throws ClassNotFoundException, SQLException
	{
		//1. user input 
		String username = login.getUsername();
		String password = login.getPassword();
		
		//connect java and jdbc
		ConnectionManager conm = new ConnectionManager();
		Connection con = conm.establishConnection();
		
		//1. statement class declare
		Statement st = con.createStatement();
		
		//2. write query
		ResultSet rs = st.executeQuery("select * from login");
		
		//check username and password
		while(rs.next())
		{
			if(username.equals(rs.getString("username")) && password.equals(rs.getString("password")))
			{
				conm.closeConnection();
				return true;
			}
		}
		conm.closeConnection();
		return false;
	}
}
