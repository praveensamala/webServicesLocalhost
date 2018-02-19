package com.control2me.javaapi.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.control2me.javaapi.BusinessManager;
import com.sun.istack.internal.logging.Logger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/users")
@Api(value="/users", description="manage users")
public class UsersResource
{	
	private static final Logger log = Logger.getLogger(UsersResource.class.getName(), UsersResource.class);
	
	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value="find user", notes="This API retrieves the public information")
	public Response getUserById(@PathParam("userId") String userId)
	{
		log.info("in getUserById method for testing");
		
		if (userId == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\" : \"Empty userId\", \"status\" : \"FAIL\"}").build();
		}
		
		try {
			List<User> usersList = BusinessManager.getInstance().findUser(userId);
			UsersHolder usersHolder = new UsersHolder();
			usersHolder.setUserslist(usersList);
			
			return Response.status(Response.Status.OK).entity(usersHolder).build();
		}
		catch (Exception e) {
			
		}
		
		return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\" : \"Invalid userId\", \"status\" : \"FAIL\"}").build();
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value="list all users", notes="This API retrieves all users information")
	public Response getAllUsers()
	{
		log.info("in getAllUsers method for testing");
		
		try {
			List<User> usersList = BusinessManager.getInstance().allUsers();
			UsersHolder usersHolder = new UsersHolder();
			usersHolder.setUserslist(usersList);
			
			return Response.status(Response.Status.OK).entity(usersHolder).build();
		}
		catch (Exception e) {
			
		}
		
		return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\" : \"Invalid Request\", \"status\" : \"FAIL\"}").build();
	}
	
	@POST
	@Path("/adduser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value="adduser", notes="This API adds new user information")
	public Response addUser(User user)
	{
		log.info("in addUser method for testing");
		
		if ((user.getId()==null || user.getId().isEmpty()) ||
			(user.getName()==null || user.getName().isEmpty()) || 
			(user.getLocation()==null || user.getLocation().isEmpty()))
		{
			return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\" : \"User Id, Name and Location details are mandatory. Plese provide valid Enter valid User Id, Name and Location details\", \"status\" : \"FAIL\"}").build();
		}
		
		try {
			User newuser = BusinessManager.getInstance().addUser(user);
			
			return Response.status(Response.Status.CREATED).entity(newuser).build();
		}
		catch (Exception e) {
			
		}
		
		return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\" : \"Invalid request\", \"status\" : \"FAIL\"}").build();
	}
	
	@PUT
	@Path("/updateuser/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value="updateuser", notes="This API updates existing user information")
	public String updateUser(@PathParam("userId") @DefaultValue("InvalidRequest_NoUserId_Provided") String userId, @DefaultValue("{\"null\":\"null\"}") String jsonString)
	{
		log.info("in updateuser method for testing");
		System.out.println("\n\n*****in updateuser method for testing");
		
		if ((userId==null || userId.isEmpty()) ||
			(jsonString==null || jsonString.isEmpty()))
		{
			return "User Id is mandatory. Please provide the valid User Id to process";
		}
		
		String name;
		String location;
		
		try {
			Object obj = JSONValue.parse(jsonString);
			JSONObject jsonobj = (JSONObject) obj;
			name = (String) jsonobj.get("name");
			location = (String) jsonobj.get("location");
		}
		catch (Exception e){
			return "Received error during processing - update request";
		}
		
		try {
			String responsemsg = BusinessManager.getInstance().updateUser(userId, name, location);
			return responsemsg;
		}
		catch (Exception e) {
			
		}
		
		return "Received error during processing - update request";
	}
	
	
	@DELETE
	@Path("/deleteuser/{userId}")
	//@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value="updateuser", notes="This API updates existing user information")
	public String deleteUser(@PathParam("userId") String userId)
	{
		log.info("in deleteuser method for testing");
		
		if (userId==null || userId.isEmpty())
		{
			return "User Id is mandatory. Please provide the valid User Id to process";
		}
		
		try {
			String responsemsg = BusinessManager.getInstance().deleteUser(userId);
			
			return responsemsg;
		}
		catch (Exception e) {
			
		}
		
		return "Received error during processing - update request";
	}
}
