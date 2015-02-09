package edu.sjsu.cmpe282.api.resources;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Context;
import javax.servlet.http.HttpServletRequest;

import com.mongodb.DBObject;

import edu.sjsu.cmpe282.dao.ProductMongoDao;
import edu.sjsu.cmpe282.domain.Product;
import edu.sjsu.cmpe282.domain.User;

@Path("user/{uid}/category")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Product_Catalog {
	
	
	private ProductMongoDao productMongoDao = new ProductMongoDao();
	@GET
	@Path("/Accessories/")
	public List<DBObject> getAccessories(@Context HttpServletRequest request, @PathParam("uid") String uid) throws Exception {
		System.out.println("in accessories on server" + uid);
		//return Response.status(200).entity(" Accessories ").build();
		//Product product = new Product();
		List<DBObject> products = new ArrayList<DBObject>();
		//productDao.createItems();
		//product = productDao.retrieveItem();
		String category = "Accessories";
		products = productMongoDao.retrieveItems(category);
		//productDao.deleteItem();
		//product.setProductName("accessory 1");
		request.getSession().setAttribute("products", products);
		return products;
		
	}
	
	
	@GET
	@Path("/Clothing/")
	public List<DBObject> getClothing(@Context HttpServletRequest request, @PathParam("uid") int uid) throws Exception {
		System.out.println("in Fashion server" + uid);
		//return Response.status(200).entity(" Accessories ").build();
		//Product product = new Product();
		List<DBObject> products = new ArrayList<DBObject>();
		//productDao.createItems();
		//product = productDao.retrieveItem();
		String category = "Clothing";
		products = productMongoDao.retrieveItems(category);
		//productDao.deleteItem();
		//product.setProductName("accessory 1");
		request.getSession().setAttribute("products", products);
		return products;
		
	}
	
	@GET
	@Path("/Books/")
	public List<DBObject> getBooks(@Context HttpServletRequest request, @PathParam("uid") int uid) throws Exception {
		System.out.println("in accessories on server" + uid);
		//return Response.status(200).entity(" Accessories ").build();
		//Product product = new Product();
		List<DBObject> products = new ArrayList<DBObject>();
		//productDao.createItems();
		//product = productDao.retrieveItem();
		String category = "Books";
		products = productMongoDao.retrieveItems(category);
		//productDao.deleteItem();
		//product.setProductName("accessory 1");
		request.getSession().setAttribute("products", products);
		return products;
		
	}
	
	@GET
	@Path("/Electronic/")
	public List<DBObject> getElectronics(@Context HttpServletRequest request, @PathParam("uid") int uid) throws Exception {
		System.out.println("in accessories on server" + uid);
		//return Response.status(200).entity(" Accessories ").build();
		//Product product = new Product();
		List<DBObject> products = new ArrayList<DBObject>();
		//productDao.createItems();
		//product = productDao.retrieveItem();
		String category = "Electronic";
		products = productMongoDao.retrieveItems(category);
		//productDao.deleteItem();
		//product.setProductName("accessory 1");
		request.getSession().setAttribute("products", products);
		return products;
		
	}
	
	@POST
	@Path("/addproduct")
	public Response addProduct(@Context HttpServletRequest request, @PathParam("uid") int uid, Product product) throws ClassNotFoundException, UnknownHostException {
		System.out.print("product created: "+ product.getProductName());
		int result = productMongoDao.addProduct(uid, product);
		if (result!=0)
		{
	        return Response.ok(200).build();
		}
		else
			return Response.status(400).entity(" unable to add product").build();
		
	}
	
	
	 

	
}
