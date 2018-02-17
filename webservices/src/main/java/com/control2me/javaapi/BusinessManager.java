package com.control2me.javaapi;
import java.util.ArrayList;
import java.util.List;

import com.control2me.javaapi.services.Users;
public class BusinessManager
{
	public static BusinessManager INSTANCE = new BusinessManager();
	public static BusinessManager getInstance() {
		return INSTANCE;
	}
	
	private BusinessManager() {
		
	}
	
	public Users findUser(String userId) {
		Users user = new Users();
		user.setId(userId);
		user.setName("Tom");
		
		return user;
	}
	
	public List<Users> allUsers() {
		List<Users> allUsersList = new ArrayList<Users>();
		Users user1 = new Users();
		Users user2 = new Users();
		
		user1.setId("123");
		user1.setName("Micheal");
		user2.setId("456");
		user2.setName("Jackson");
		allUsersList.add(user1);
		allUsersList.add(user2);
		
		return allUsersList;
	}
	
	public Users addUser(Users user) {		
		return user;
	}
	
	public Users updateUser(String userId, String name) {
		Users user = new Users();
		user.setId(userId);
		user.setName(name);
		
		return user;
	}
}
