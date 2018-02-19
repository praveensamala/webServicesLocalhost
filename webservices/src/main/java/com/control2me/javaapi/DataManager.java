package com.control2me.javaapi;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.control2me.javaapi.services.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.WriteResult;

public class DataManager
{
	private static DB db;
	private static DBCollection dbcollection;
	
	private static DataManager INSTANCE;
	public static DataManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DataManager();
		}
		return INSTANCE;
	}
	
	private DataManager() {
		try {
			MongoClient mongoclient = new MongoClient(new ServerAddress("localhost", 27017));
			db = mongoclient.getDB("local");
			dbcollection = db.getCollection("users");
		}
		catch (Exception e) {
			System.out.println("db connection error");
		}
	}
	
	public User mongoAddUser(User user) {
		BasicDBObject dbobj = new BasicDBObject();
		dbobj.put("id", user.getId());
		dbobj.put("name", user.getName());
		dbobj.put("location", user.getLocation());
		dbcollection.insert(dbobj);
		user.setSystemid(dbobj.get("_id").toString());
		return user;
	}
	
	public List<User> mongoGetUser(String userId) {
		List<User> users = new ArrayList<User>();
		
		if (userId == null) {
			return null;
		}
		
		try {			
			BasicDBObject allQuery = new BasicDBObject();
			allQuery.put("id", userId);
			BasicDBObject fields = new BasicDBObject();
			
			DBCursor cursor = dbcollection.find(allQuery, fields);
			while (cursor.hasNext()) {
				BasicDBObject doc = (BasicDBObject) cursor.next();
				User user = new User();

				try { user.setId(doc.get("id").toString());	} catch (NullPointerException e) { };
				try { user.setName(doc.get("name").toString());	} catch (NullPointerException e) { };
				try { user.setLocation(doc.get("location").toString());	} catch (NullPointerException e) { };
				try { user.setSystemid(doc.get("_id").toString());	} catch (NullPointerException e) { };
				
				users.add(user);
			}
		}
		catch (Exception e) {
			System.out.println("received exception in mongoGetUser"+e);
		}
		return users;
	}
	
	public List<User> mongoAllUsers() {
		List<User> users = new ArrayList<User>();
		
		try {
			DBCursor cursor = dbcollection.find();
			if (cursor!=null) {
				while (cursor.hasNext()) {
					BasicDBObject doc = (BasicDBObject) cursor.next();
					User user = new User();

					try { user.setId(doc.get("id").toString());	} catch (NullPointerException e) { };
					try { user.setName(doc.get("name").toString());	} catch (NullPointerException e) { };
					try { user.setLocation(doc.get("location").toString());	} catch (NullPointerException e) { };
					try { user.setSystemid(doc.get("_id").toString());	} catch (NullPointerException e) { };
					
					users.add(user);
				}
			}
			
			return users;
		}
		catch (Exception e) {
			System.out.println("received exception in mongoAllUsers() : "+e);
		}
		
		return null;
	}
	
	
	public String mongoUpdateUser(String userid, String name, String location) {
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("id", userid);
		newDocument.put("name", name);
		newDocument.put("location", location);

		if ((name.isEmpty() || name == null) || (location.isEmpty() || location == null)) {
			return "User Name and Location details are mandatory, Please provide the valid User Name and Location details";
		}
		
		BasicDBObject searchQuery = new BasicDBObject().append("id", userid);
		
		WriteResult wr = dbcollection.update(searchQuery, newDocument);
		if (wr.getN()==1) {
			return "User "+userid+" details updated successfully";
		}
		else {
			return "There are no records with the user id "+userid+", None of the records updated";
		}
	}
	
	public String mongoDeleteUser(String userid) {
		BasicDBObject allquery = new BasicDBObject();
		allquery.append("id", userid);

		WriteResult wr = dbcollection.remove(allquery);
		System.out.println("\n\n*****removed number : "+wr.getN());
		
		if (wr.getN() > 0) {
			return wr.getN()+" Matching User Ids deleted successfully";
		}
		else {
			return "There are no records with the user id "+userid+", None of the records deleted";
		}
	}
}
