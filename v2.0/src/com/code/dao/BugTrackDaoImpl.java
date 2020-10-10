// Author : Akanksha Shrivastava, Adrija Ghansiyal, Abhijeet Nitin Raut, dharampreet
// Purpose : Establish connection with derby db & run queries to fetch/add/modify data

package com.code.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.code.bean.Bug;
import com.code.bean.Project;
import com.code.bean.User;
import com.code.dao.DBUtil;


public class BugTrackDaoImpl implements BugTrackDao {
	static Connection conn;
	static PreparedStatement pgetAllProjects,pgetAllBugs,inserimportedusers,pgetAllPMProjects,closebug,assigndev,pgetmembers,pinsBug;
	//preparing queries...
	static {
		conn=DBUtil.getMyConnection();
		try {
			//write preparedStatements here
			
			//to import users from json to db
			inserimportedusers = conn.prepareStatement("insert into usertable values(DEFAULT,?,?,?)");
			//to get the project details based on the user of the team member involved
			pgetAllProjects=conn.prepareStatement("select * from projecttable p join teamtable t on p.projectid=t.projectid where t.userid=?");
			pgetAllPMProjects=conn.prepareStatement("select * from projecttable where managerid=?");
			
			//get team members under a manager
			pgetmembers=conn.prepareStatement("select u.userid,name from usertable u join teamtable t on u.userid=t.userid where u.type='developer' and t.managerid=?");
			
			//to display bug details as per its project id
			pgetAllBugs = conn.prepareStatement("select * from bugtable where projectid = ? and status='open'");
			//to close the bug
			closebug= conn.prepareStatement("update bugtable set status='close' where uniqueId=?");
			//assign dev
			assigndev=conn.prepareStatement("");
			//report new bug
			//pinsBug = conn.prepareStatement("insert into bugtable(uniqueid,title,description,projectid,createdby,opendate,status,severitylevel) values(default,?,?,?,?,?,?,?)");
			pinsBug = conn.prepareStatement("insert into bugtable(uniqueid,title,description,projectid,createdby,status,severitylevel) values(default,?,?,?,?,?,?)");
			
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

	//Function to fetch projects based on userid	
	@Override
	public List<Project> getAllProjects(int userid) {
		List<Project> projectList=new ArrayList<>();
		try {
			pgetAllProjects.setInt(1, userid);
			ResultSet rs=pgetAllProjects.executeQuery();
			while(rs.next()) {
				Project p=new Project(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),userid);
				projectList.add(p);
			}
			return projectList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<Project> getAllPMProjects(int mgrid) {
		List<Project> projectList=new ArrayList<>();
		try {
			pgetAllPMProjects.setInt(1, mgrid);
			//System.out.print("in dao");
			ResultSet rs=pgetAllPMProjects.executeQuery();
			while(rs.next()) {
				Project p=new Project(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),mgrid);
				projectList.add(p);
			}
			return projectList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//Function to list bugs based on userid and projectid
	@Override
	public List<Bug> getAllBugs(int projectid) {
		List<Bug> bugList = new ArrayList<Bug>();
		try {
			pgetAllBugs.setInt(1, projectid);
			ResultSet rs=pgetAllBugs.executeQuery();
			while(rs.next()) {
				Bug bug=new Bug(rs.getInt(1),rs.getString(2),rs.getString(3),projectid,rs.getInt(5),rs.getDate(6),rs.getInt(7),rs.getString(8),rs.getInt(9),rs.getDate(10),rs.getString(11),rs.getString(12));
				bugList.add(bug);
			}
			return bugList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//function to return list of users under a manager
	@Override
	public List<User> getUsersByManager(Integer managerId) {
		List<User> ulist=new ArrayList<>();
		
		try {
			pgetmembers.setInt(1, managerId);
			ResultSet rs=pgetmembers.executeQuery();
			while(rs.next()) {
				User u=new User(rs.getInt(1),rs.getString(2),null,null);
				ulist.add(u);
				
			}
			return ulist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean closeBug(int bugId) {
		try {
			closebug.setInt(1, bugId);
			int n=closebug.executeUpdate();
			if(n!=0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	@Override
	public boolean assignDev(int bugId, int userId) {
		//try {
//			assigndev.setInt(1, bugId);
//			assigndev.setInt(2, userId);
//			return assigndev.execute();
			//System.out.println(bugId+" "+userId);
			return true;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
		//}
		
		
	}

	//Function to add the reported bug to database
	@Override
	public int addNewBug(Bug bug) {
		try {
			System.out.println(bug);
			pinsBug.setString(1, bug.getBugTitle());
			pinsBug.setString(2, bug.getBugDescription());
			pinsBug.setInt(3, bug.getProjectId());
			pinsBug.setInt(4, bug.getCreatedBy());
//			Date d=bug.getOpenDate();
//			DateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
//			//java.sql.Date date = new java.sql.Date(bug.getOpenDate().getTime());
//			pinsBug.setDate(5, formatter.format(d));
			pinsBug.setString(6, bug.getStatus());
			pinsBug.setString(7, bug.getSeverityLevel());
			return pinsBug.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	
}
