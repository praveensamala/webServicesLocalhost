package com.control2me.javaapi;
import java.util.ArrayList;
import java.util.List;

import com.control2me.javaapi.services.User;
public class BusinessManager
{
	public static BusinessManager INSTANCE = new BusinessManager();
	public static BusinessManager getInstance() {
		return INSTANCE;
	}
	
	private BusinessManager() {
		
	}
	
	public List<User> findUser(String userId) {
		
		List<User> users = DataManager.getInstance().mongoGetUser(userId);
		return users;
	}
	
	public List<User> allUsers() {
		List<User> allUsersList = new ArrayList<User>();
		
		allUsersList = DataManager.getInstance().mongoAllUsers();
		return allUsersList;
	}
	
	public User addUser(User user) {
		User updateduser = DataManager.getInstance().mongoAddUser(user);
		return updateduser;
	}
	
	public String updateUser(String userid, String name, String location) {
		String responsemsg = DataManager.getInstance().mongoUpdateUser(userid, name, location);
		return responsemsg;
	}
	
	public String deleteUser(String userid) {
		String responsemsg = DataManager.getInstance().mongoDeleteUser(userid);
		return responsemsg;
	}
}
