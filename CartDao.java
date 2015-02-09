package edu.sjsu.cmpe282.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;
import org.codehaus.jackson.map.ObjectMapper;

import com.amazonaws.util.json.JSONException;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.util.JSON;

import edu.sjsu.cmpe282.domain.Product;

public class CartMongoDao {

	ProductMongoDao productMongoDao = new ProductMongoDao();
	
	public int addProductInCart(int userid, int pid) throws UnknownHostException, JSONException {
		DB db = ProductMongoDao.Mongoconnection();
		DBCollection cart = db.getCollection("shoppingcart");
		System.out.println("in dao");
	    DBObject prod = new BasicDBObject();
	    DBObject prod1 = new BasicDBObject();
	    System.out.println("before retrieving item -------");
	    prod1 = productMongoDao.retrieveItemByPid(pid);
	    
	    if (prod1!= null && Integer.parseInt(prod1.get("quantity").toString()) != 0)
	    {
	    try{
	    	
	    prod.put("userid", userid);
	    prod.put("productId", Integer.parseInt(prod1.get("productId").toString()));
	    prod.put("category", prod1.get("category"));
	    //prod.put("categoryId", prod1.getCategory());
	    prod.put("productName", prod1.get("productName").toString());
	    prod.put("productDesc", prod1.get("productDesc").toString());
	    prod.put("price", Double.parseDouble(prod1.get("price").toString()));
	    prod.put("quantity", 1);
	    cart.insert(prod);
	    //prod.put("userid", userid);
	    System.out.println("***********************");
	    return 1;
	    }catch (Exception ase) {
		      System.err.println("Create items failed.");
		      ase.printStackTrace();
		      return 0;   
	    }
	    }
	    else
	    	return 0;
	    
	   }

	
	public List<DBObject> retrieveCartItems(int userid) throws UnknownHostException, JSONException 
	{
		DB db = ProductMongoDao.Mongoconnection();
		DBCollection cartcata = db.getCollection("shoppingcart");
		BasicDBObject searchQuery1 = new BasicDBObject().append("userid", userid);
		//BasicDBObject searchQuery1 = new BasicDBObject().append("productId", 121);
		//List<Product> products = new ArrayList<Product>();
		//Product product = new Product();
		List<DBObject> products = new ArrayList<DBObject>();
		try {
		DBCursor cursor = cartcata.find(searchQuery1);
		System.out.println("retrieve cart items " + cursor.size());
		while (cursor.hasNext()) {
			System.out.println("datatype of cursor" + (cursor.iterator().getClass()));
			products.add(cursor.next());
			
		}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return products;
				
	}
	
	
	public int buyProductInCart(int userid) throws UnknownHostException, JSONException {
		System.out.println("in dao for Checkout !!!!!!");
		try {
		List<DBObject> cartItems = new ArrayList<DBObject>();
		System.out.println("1. userid" + userid);
		cartItems = retrieveCartItems(userid);
		int quantity = 1;
		//int productId = 121;
		int productId=0;
		System.out.println("cartitems total" + cartItems.size());
		for (DBObject item : cartItems){
			productId = Integer.parseInt(item.get("productId").toString());
			int result2 = productMongoDao.updateProduct(productId, quantity);
			if (result2 != 0)
			{
				System.out.println("3. userid " + userid);
				int result1 = addBuyHistory(item, userid);
				if (result1 != 0)
				{
					//int pid = 121;
					System.out.println("before deletecart function call");
					System.out.println("4. userid" + userid);
					int result = deleteCart(userid, productId);
					if (result == 0)
						return 0;
				}
			}
			else{
				return 0;
			}			
		}
		}
		catch (Exception e){
			e.printStackTrace();
			return 0; 
		}
		return 1;
	}
	
	
	public int deleteCart(int userid, int pid) throws UnknownHostException
	{
		DB db = ProductMongoDao.Mongoconnection();
		DBCollection cart = db.getCollection("shoppingcart");
		System.out.println("in delete cart option!!!!");
		try{
		BasicDBObject searchQuery = new BasicDBObject();
		System.out.println("userid " + userid);
		searchQuery.put("userid", userid); // DO MULTIPLE CONDITION QUERY
	 
		cart.remove(searchQuery);
		System.out.println("after delete cart!!!!!");
		return 1;
		}
		catch (Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	public int addBuyHistory(DBObject boughtItem, int userid) throws UnknownHostException, JSONException {
		DB db = ProductMongoDao.Mongoconnection();
		DBCollection buyHist = db.getCollection("buyhistory");
		System.out.println("in dao buyhistory !!!!!!!!");
	    DBObject prod = new BasicDBObject();
	    DBObject prod1 = new BasicDBObject();
	    System.out.println("before retrieving item -------");
	    //prod1 = productMongoDao.retrieveItemByPid(pid);
	    //System.out.println("data retrieved" + ((Product) prod1).getProductDesc());
	    //prod((Object)prod1.get("response")).get("quantity")!=0
	    //if (prod1!= null)
	    //{
	    try{
	    //	System.out.println("#######################33");
	    	System.out.println("2. userid" + userid);
	    prod.put("userid", userid);
	    prod.put("productId", boughtItem.get("productId").toString());
	    prod.put("category", boughtItem.get("category").toString());
	    //prod.put("categoryId", prod1.getCategory());
	    prod.put("productName", boughtItem.get("productName").toString());
	    prod.put("productDesc", boughtItem.get("productDesc").toString());
	    prod.put("price", Integer.parseInt(boughtItem.get("quantity").toString()));
	    prod.put("quantity", 1);
	    buyHist.insert(prod);
	    //prod.put("userid", userid);
	    System.out.println("***********************");
	    return 1;
	    }catch (Exception ase) {
		      System.err.println("Create items failed.");
		      ase.printStackTrace();
		      return 0;   
	    }
	    
	    }
	
	
	public List<DBObject> retrievePaymentHistory(int userid) throws UnknownHostException, JSONException 
	{
		DB db = ProductMongoDao.Mongoconnection();
		DBCollection buyprod = db.getCollection("buyhistory");
		//BasicDBObject searchQuery1 = new BasicDBObject().append("userid", userid);
		BasicDBObject searchQuery1 = new BasicDBObject().append("userid", userid);
			List<DBObject> products = new ArrayList<DBObject>();
		try {
		DBCursor cursor = buyprod.find(searchQuery1);
		System.out.println("retrieve cart items " + cursor.size());
		while (cursor.hasNext()) {
			System.out.println("datatype of cursor" + (cursor.iterator().getClass()));
			products.add(cursor.next());
			
		}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return products;
				
	}	
	
	    	    



}

	

