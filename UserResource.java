package edu.sjsu.cmpe282.api.resources;


import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.sjsu.cmpe282.domain.User;
import edu.sjsu.cmpe282.dao.ProductMongoDao;
import edu.sjsu.cmpe282.dao.UserDao;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
	
	private UserDao userdao = new UserDao();
	
	@POST
	@Path("/signup/{isAdmin}")
	public User signUp(@Context HttpServletRequest request, User user, @PathParam("isAdmin") boolean isAdmin) throws ClassNotFoundException {
		System.out.print("user created: "+user.getFname());
		User User = new User(); 
		user.setAdmin(isAdmin);
		if(isAdmin)
			user.setAdmin(1);
		else
			user.setAdmin(0);
		User = userdao.addUser(user);
		//return user;
		System.out.println("isadmin from patharam" + isAdmin);
		System.out.println("isAdmin " + user.isAdmin());
		System.out.println("isadmin from patharam" + isAdmin);
		request.getSession().setAttribute("user", User); 
		return User;
	}

	@POST
	@Path("/signin")
	public Response signIn(@Context HttpServletRequest request, User user) throws ClassNotFoundException, UnknownHostException
	{
		//return userdao.checkUser(user);
		User user1 = new User();
		user1 = userdao.checkUser(user);
		//out.println("result " + result);
		System.out.println("signin" + user.getFname() + "admin " + user.getAdmin());
		if (user1!=null)
		{
			request.getSession().setAttribute("user", user1);
			return Response.ok(200).build();
		}
		else
			return Response.status(400).entity(" email and password dont match").build();
	}

	
	
}
