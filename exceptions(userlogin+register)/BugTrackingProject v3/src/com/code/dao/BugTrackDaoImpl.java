// Author : Akanksha Shrivastava, Adrija Ghansiyal, Abhijeet Nitin Raut, dharampreet,Hrishita,,Gautami Ravindra Rahane
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
	static PreparedStatement pgetAllProjects,pgetAllBugs,inserimportedusers,pgetAllPMProjects,closebug,assigndev,pgetmembers,pinsBug,
	pgetAllDevelopers,pgetAllTesters,pins,pgetUser,pinsNewProject,pupdateProject,pgetProjName,addremarks,pmarkForClosing,pgetAllDevBugs;
	//preparing queries...
	static {
		conn=DBUtil.getMyConnection();
		try {
			//write preparedStatements here
			
			//to import users from json to db
			inserimportedusers = conn.prepareStatement("insert into usertable values(DEFAULT,?,?,?,0)");
			//to get the project details based on the user of the team member involved
			pgetAllProjects=conn.prepareStatement("select * from projecttable p join teamtable t on p.projectid=t.projectid where t.userid=?");
			pgetAllPMProjects=conn.prepareStatement("select * from projecttable where managerid=?");
			
			//get team members under a manager
			pgetmembers=conn.prepareStatement("select u.userid,name from usertable u join teamtable t on u.userid=t.userid where u.type='developer' and t.managerid=?");
			
			//to display bug details as per its project id
			pgetAllBugs = conn.prepareStatement("select * from bugtable where projectid = ? and status='open'");
			pgetAllDevBugs = conn.prepareStatement("select * from bugtable where projectid = ? and markedforclosing in ('false','null') and assignedTo=?");
			//to close the bug
			closebug= conn.prepareStatement("update bugtable set status='close' where uniqueId=?");
			//assign dev
			assigndev=conn.prepareStatement("update bugtable set assignedto=? where uniqueid=?");
			//report new bug
			pinsBug = conn.prepareStatement("insert into bugtable(title,description,projectid,createdby,opendate,markedforclosing,status,severitylevel) values(?,?,?,?,?,'false',?,?)");
			
			//__________________________________________________________________
			pgetAllDevelopers=conn.prepareStatement("select * from usertable where type='developer' and noOfProjects<1");
			pgetAllTesters=conn.prepareStatement("select * from usertable where type='tester' and noOfProjects<2");
			pins=conn.prepareStatement("insert into projecttable values(default,?,?,?,?,?)");
			//to get a proj id from proj name
			pgetProjName=conn.prepareStatement("select projectid from projecttable where projectname=?");
			//get all the users coming under a new project
			pgetUser=conn.prepareStatement("select * from usertable  where userid=?");
			//update the no. of projects under a given user
			pupdateProject=conn.prepareStatement("update usertable set noOfProjects=? where userid=?");
			//To insert new project to the teamtable
			pinsNewProject=conn.prepareStatement("insert into teamtable values(?,?,?)");
			//To update the team
			
			//to add remarks in bug table after marking bug for closing
			addremarks = conn.prepareStatement("update bugtable set remarks=? where uniqueid=?");
			pmarkForClosing=conn.prepareStatement("update bugtable set markedforclosing='true' where uniqueid=?");
			
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
				Project p=new Project(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),rs.getInt(6));
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
				Bug bug=new Bug(rs.getInt(1),rs.getString(2),rs.getString(3),projectid,rs.getInt(5),rs.getDate(6),rs.getInt(7),
						rs.getString(8),rs.getInt(9),rs.getDate(10),rs.getString(11),rs.getString(12),rs.getString(13));
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
	//assigning dev to bug by manager
	@Override
	public boolean assignDev(int bugId, int userId) {
		try {
		assigndev.setInt(1, userId);
		assigndev.setInt(2, bugId);
		int n= assigndev.executeUpdate();
		if(n!=0)
			return true;
		else 
			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}


	}

	//Function to add the reported bug to database
	@Override
	public int addNewBug(Bug bug) {
		try {
			System.out.println(bug);
			java.sql.Date date = new java.sql.Date((bug.getOpenDate()).getTime());
			System.out.println(date);
			System.out.println(bug.getBugTitle());
			pinsBug.setString(1, bug.getBugTitle());
			pinsBug.setString(2, bug.getBugDescription());
			pinsBug.setInt(3, bug.getProjectId());
			pinsBug.setInt(4, bug.getCreatedBy());
			pinsBug.setDate(5, date);
			pinsBug.setString(6, bug.getStatus());
			pinsBug.setString(7, bug.getSeverityLevel());
			
			int n=pinsBug.executeUpdate();
			return n;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	//__________________________________________________
	//To get all free employees
	public List<User> getAllEmployees(){
		List<User> userList = new ArrayList<>();
		try {
			
			ResultSet rs1=pgetAllDevelopers.executeQuery();
			while(rs1.next()) {
				User user=new User(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getString(4));
				userList.add(user);
			}
			ResultSet rs2=pgetAllTesters.executeQuery();
			while(rs2.next()) {
				User user=new User(rs2.getInt(1),rs2.getString(2),rs2.getString(3),rs2.getString(4));
				userList.add(user);
			}
			
			return userList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
		
		//To add a new project
		public boolean addProject(Project p) {
			try {
				java.sql.Date date = new java.sql.Date((p.getStartDate()).getTime());
//				pins.setInt(1, p.getProjectId());
				pins.setString(1, p.getProjectName());
				pins.setString(2,p.getDescription());
				pins.setDate(3,date);
				pins.setString(4,p.getStatus());
				pins.setInt(5,p.getManagerId());
				int i=pins.executeUpdate();
				if(i>0) {
					return true;
				}
				else {
					return false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
	
	@Override
	public void updateNoOfProjects(int[] userIdChecked) {
		/*
		 * for(int i=0;i<checked.length;i++) { pNoOfProjects.setInt(1,checked[i]);
		 * pupdate.setInt(2,p.getQty());
		 */
		for(int id:userIdChecked) {
			try {
				pgetUser.setInt(1, id);
				ResultSet rs=pgetUser.executeQuery();
				int numproject=rs.getInt(5);
				numproject+=1;
				pupdateProject.setInt(1, numproject);
				pupdateProject.setInt(2, id);
				pupdateProject.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//To add users to the team
	@Override
	public void addUsersToProject(String projectName,int[] userIdChecked,int managerid) {
		for(int id:userIdChecked) {
			try {
				pgetProjName.setString(1, projectName);
				int projectId=0;
				ResultSet rs=pgetProjName.executeQuery();
				while(rs.next()) {
					projectId=rs.getInt(1);
				}
				pinsNewProject.setInt(1, projectId);
				pinsNewProject.setInt(2, id);
				pinsNewProject.setInt(3, managerid);
				pinsNewProject.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	//Function to add remarks to the bug and mark it for closing
		@Override
		public boolean addRemarks(Bug bug) {
			try {
				//System.out.println(bug.getRemarks());
				addremarks.setString(1,bug.getRemarks());
				addremarks.setInt(2, bug.getBugId());
				int n = addremarks.executeUpdate();
				
				if(n>=0) {
					pmarkForClosing.setInt(1, bug.getBugId());
					pmarkForClosing.executeUpdate();
					return true;
				}
					
				else
					return false;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}

		@Override
		public List<Bug> getAllDevBugs(int projectId,int userId) {
			List<Bug> bugList = new ArrayList<Bug>();
			try {
				pgetAllDevBugs.setInt(1, projectId);
				pgetAllDevBugs.setInt(2, userId);
				ResultSet rs=pgetAllDevBugs.executeQuery();
				while(rs.next()) {
					Bug bug=new Bug(rs.getInt(1),rs.getString(2),rs.getString(3),projectId,rs.getInt(5),rs.getDate(6),rs.getInt(7),
							rs.getString(8),rs.getInt(9),rs.getDate(10),rs.getString(11),rs.getString(12),rs.getString(13));
					bugList.add(bug);
				}
				return bugList;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	
	
	
}
