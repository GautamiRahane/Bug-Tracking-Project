// Author : Akanksha Shrivastava, Adrija Ghansiyal
// Purpose : Establish connection with derby db & run queries to fetch/add/modify data

package com.code.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.code.dao.DBUtil;

public class BugTrackDaoImpl implements BugTrackDao {
	static Connection conn;
	static PreparedStatement ptrial;
	static {
		conn=DBUtil.getMyConnection();
		try {
			//write preparedStatements here
			ptrial=conn.prepareStatement("");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	

}
