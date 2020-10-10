// Author : Hrushikesh, Adrija Ghansiyal, 
// Purpose : Establish connection with derby db & run queries to fetch/add/modify data
package com.code.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.code.bean.User;

public class UserDaoImpl implements UserDao {
	static Connection conn;
	static PreparedStatement pgetLoginDetails,pgetUserDetails;
	static {
		conn = DBUtil.getMyConnection();
		try {
			pgetLoginDetails=conn.prepareStatement("select * from logintable where email = ? and password = ?");
			pgetUserDetails=conn.prepareStatement("select * from usertable where userid = ?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

<<<<<<< HEAD
	static Connection con;
	
	//authenticate user using userName and password
=======
	

>>>>>>> 72cd59025506597701208e8020577fb1cbcd9331
	@Override
	public User validateUser(String userName, String password)  {
			
		try {
			pgetLoginDetails.setString(1, userName);
			pgetLoginDetails.setString(2, password);
			ResultSet rs = pgetLoginDetails.executeQuery();
			if(rs.next()) {
				pgetUserDetails.setInt(1, rs.getInt(3));
				ResultSet rs1 = pgetUserDetails.executeQuery();
				if(rs1.next()) {
					//System.out.println("in rs1");
					return new User(rs1.getInt(1), rs1.getString(2), rs1.getString(3), rs1.getString(4));
				}else {				//add exception
					return null;
				}
			}
<<<<<<< HEAD
		}else {
			return null;			
		}
	}
	
	//add user into database
	@Override
	public int addUser(String name, String type, String email) throws SQLException {
		String sql = "insert into user (name, email, type) values (?, ?, ?)";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, name);
		st.setString(2, email);
		st.setString(3, type);
		
		return st.executeUpdate();
=======
			else {					//add exception
				return null;			
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
			
>>>>>>> 72cd59025506597701208e8020577fb1cbcd9331
	}
}
