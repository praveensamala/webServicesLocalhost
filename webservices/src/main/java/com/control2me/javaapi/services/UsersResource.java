package com.control2me.javaapi.services;

import java.util.List;

import javax.ws.rs.Consumes;
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
	public Response getUserById(@DefaultValue("xyz") @PathParam("userId") String userId)
	{
		log.info("in getUserById method for testing");
		
		if (userId == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\" : \"Empty userId\", \"status\" : \"FAIL\"}").build();
		}
		
		try {
			Users user = BusinessManager.getInstance().findUser(userId);
			return Response.status(Response.Status.OK).entity(user).build();
		}
		catch (Exception e) {
			
		}
		
		return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\" : \"Empty userId\", \"status\" : \"FAIL\"}").build();
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value="list all users", notes="This API retrieves all users information")
	public Response getAllUsers()
	{
		log.info("in getAllUsers method for testing");
		
		try {
			List<Users> usersList = BusinessManager.getInstance().allUsers();
			UsersHolder usersHolder = new UsersHolder();
			usersHolder.setUserslist(usersList);
			
			return Response.status(Response.Status.OK).entity(usersHolder).build();
		}
		catch (Exception e) {
			
		}
		
		return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\" : \"Empty users\", \"status\" : \"FAIL\"}").build();
	}
	
	@POST
	@Path("/adduser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value="adduser", notes="This API adds new user information")
	public Response addUser(Users user)
	{
		log.info("in addUser method for testing");
		
		try {
			Users newuser = BusinessManager.getInstance().addUser(user);
			
			return Response.status(Response.Status.CREATED).entity(newuser).build();
		}
		catch (Exception e) {
			
		}
		
		return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\" : \"Empty users\", \"status\" : \"FAIL\"}").build();
	}
	/*public Users addUser(Users user)
	{
		log.info("in adduser method for testing");
		Users newuser = BusinessManager.getInstance().addUser(user);
		return newuser;
	}*/
	
	@PUT
	@Path("/updateuser/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value="updateuser", notes="This API updates existing user information")
	public Response updateUser(@PathParam("userId") String userId, String jsonString)
	{
		log.info("in updateuser method for testing");
		
		String name;
		
		try {
			Object obj = JSONValue.parse(jsonString);
			JSONObject jsonobj = (JSONObject) obj;
			name = (String) jsonobj.get("name");
		}
		catch (Exception e){
			return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Invalid or missing fields error\", \"status\":\"FAIL\"}").build();
		}
		
		try {
			Users newuser = BusinessManager.getInstance().updateUser(userId, name);
			
			return Response.status(Response.Status.CREATED).entity(newuser).build();
		}
		catch (Exception e) {
			
		}
		
		return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\" : \"Empty users\", \"status\" : \"FAIL\"}").build();
	}
}
