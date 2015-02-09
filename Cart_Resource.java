
package edu.sjsu.cmpe282.api.resources;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mongodb.DBObject;


import edu.sjsu.cmpe282.dao.CartMongoDao;

import edu.sjsu.cmpe282.domain.Product;

@Path("user/{uid}/addTocart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Cart_Resource {
	
	//CartDao cartDao = new CartDao();
	//ProductDao productDao = new ProductDao();
	//CartDao cartDao = new CartDao();
	CartMongoDao cartMongoDao = new CartMongoDao();
	@POST
	@Path("/{pid}")
	public Response addToCart(@Context HttpServletRequest request, @PathParam("uid") int uid, @PathParam("pid") int pid) throws Exception {
		//uid = 120;
		System.out.println("uid in resource " + uid + " pid " + pid);
		List<DBObject> cartproducts = new ArrayList<DBObject>();
		int result = cartMongoDao.addProductInCart(uid, pid);
		//cartDao.addExistingItemInCart(pid);
		System.out.println("result " + result);
		if (result != 0)
		{
			cartproducts = cartMongoDao.retrieveCartItems(uid);
			request.getSession().setAttribute("cartproducts", cartproducts);
			return Response.ok(200).build();
		}
		else
			return Response.status(400).entity(" Could not add product to cart").build();
		
	}
	
	
	@POST
	@Path("/checkout")
	public Response buyCartItems(@Context HttpServletRequest request, @PathParam("uid") int uid) throws Exception {
		//System.out.println("in buy cart items");
		int result = cartMongoDao.buyProductInCart(uid);
		if (result == 1)
		{
			return Response.ok(200).build();
		}
		else 
			return Response.status(400).entity(" Could not buy product in cart").build();
	}
	
	@GET
	@Path("/getcart/")
	public List<DBObject> getCartItems(@Context HttpServletRequest request, @PathParam("uid") int uid) throws Exception {
		System.out.println("in get cart items");
		List<DBObject> cartproducts = new ArrayList<DBObject>();
		System.out.println("userid for retrieve cart items " + uid);
		cartproducts = cartMongoDao.retrieveCartItems(uid);
		System.out.println("$$$$$$$$$$$$$$$$$$no. of cart items " + cartproducts.size());
		request.getSession().setAttribute("cartproducts", cartproducts);
		return cartproducts;

	}
	
	@DELETE
	@Path("/{pid}/deletefromcart/")
	public Response deleteFromCart(@Context HttpServletRequest request, @PathParam("uid") int uid, @PathParam("pid") int pid) throws Exception {
		System.out.println("in delete cart items");
		List<DBObject> cartproducts = new ArrayList<DBObject>(); 
		int result = cartMongoDao.deleteCart(uid, pid);
		//System.out.println("$$$$$$$$$$$$$$$$$$no. of cart items " + cartproducts.size());
		//request.getSession().setAttribute("cartproducts", cartproducts);
		if (result == 1)
		{
			return Response.ok(200).build();
		}
		else 
			return Response.status(400).entity(" Could not delete product from cart").build();

	}	
}
	


