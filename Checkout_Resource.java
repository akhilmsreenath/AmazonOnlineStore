package edu.sjsu.cmpe282.api.resources;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
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

@Path("user/{uid}/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Checkout_Resource {
	
	CartMongoDao cartMongoDao = new CartMongoDao();
	@POST
	@Path("checkout/")
	public Response checkout(@Context HttpServletRequest request, @PathParam("uid") int uid) throws Exception {
		System.out.println("at checkout server !!!!!!!");
		System.out.println("5. user" + uid);
		int result = cartMongoDao.buyProductInCart(uid);
		if (result == 1)
			return Response.ok(200).build();
		else
			return Response.status(400).entity(" Could not add product to cart").build();
		
	}
	
	@GET
	@Path("/paymenthistory/")
	public List<DBObject> paymentHistory(@Context HttpServletRequest request, @PathParam("uid") int uid) throws Exception {
		System.out.println("at payment server !!!!!!!");
		System.out.println("5. user" + uid);
		List<DBObject> buyproducts = new ArrayList<DBObject>();
		buyproducts = cartMongoDao.retrievePaymentHistory(uid);
		request.getSession().setAttribute("buyproducts", buyproducts);
		System.out.println("buyprod" + buyproducts.size());
		return buyproducts;
	}
	
}
