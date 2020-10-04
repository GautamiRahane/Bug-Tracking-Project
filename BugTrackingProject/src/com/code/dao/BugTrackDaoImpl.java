// Author : Akanksha Shrivastava, Adrija Ghansiyal
// Purpose : Establish connection with derby db & run queries to fetch/add/modify data

package com.code.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.code.bean.User;
import com.code.dao.DBUtil;

public class BugTrackDaoImpl implements BugTrackDao {
	static Connection conn;
	static PreparedStatement ptrial;
	static PreparedStatement inserimportedusers;
	
	static {
		conn=DBUtil.getMyConnection();
		try {
			//write preparedStatements here
//			ptrial=conn.prepareStatement("");
			inserimportedusers = conn.prepareStatement("insert into usertable values(DEFAULT,?,?,?)");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	// Function to save imported users into database
	@Override
	public int importUsers(List<User> userList) {
		int i = 1;
		for(User user : userList) {
			try {
				inserimportedusers.setString(1, user.getName());
				inserimportedusers.setString(2, user.getEmail());
				inserimportedusers.setString(3, user.getType());
				
				inserimportedusers.addBatch();
				
				if(i % 1000 == 0 || i == userList.size()) {
					int[] result = inserimportedusers.executeBatch();
					return result.length;
				}
				i++;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	

}
