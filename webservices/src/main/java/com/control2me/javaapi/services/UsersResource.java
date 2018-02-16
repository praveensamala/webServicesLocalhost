package com.control2me.javaapi.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/users")
@Api(value="/users", description="manage users")
public class UsersResource
{
	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value="find user", notes="This API retrieves the public information")
	public Response getUserById(@PathParam("userId") String userId)
	{
		if (userId == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\" : \"Empty userId\", \"status\" : \"FAIL\"}").build();
		}
		
		Users user = new Users();
		user.setId(userId);
		user.setName("Tom");
		
		return Response.status(Response.Status.OK).entity(user).build();
	}
}
