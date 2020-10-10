package com.code.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Trial {
	
	static Connection conn;
	static PreparedStatement inserimportedusers;
	//preparing queries...
	static {
		conn=DBUtil.getMyConnection();
		try {
			//write preparedStatements here
			
			//to import users from json to db
			inserimportedusers = conn.prepareStatement("insert into usertable values(DEFAULT,?,?,?)");
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			inserimportedusers.setString(1, "TrialName");
			inserimportedusers.setString(2, "Trialemail");
			inserimportedusers.setString(3, "Trialtype");
			System.out.println(inserimportedusers.execute());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
