package edu.sjsu.cmpe282.dao;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;
import org.codehaus.jackson.map.ObjectMapper;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.util.JSON;

import edu.sjsu.cmpe282.domain.Product;
//import com.mongodb.MongoClient;


public class ProductMongoDao {

public static DB Mongoconnection() throws UnknownHostException{
        
       
        MongoClientURI uri  = new MongoClientURI("mongodb://Akhil:amundsenscott@ds035740.mongolab.com:35740/productcatalog"); 
        MongoClient client = new MongoClient(uri);
        DB db = client.getDB(uri.getDatabase());
        return db;
}

public List<DBObject> retrieveItems(String category) throws UnknownHostException, JSONException 
{
	DB db = Mongoconnection();
	DBCollection prodcata = db.getCollection("productcatalog");
	BasicDBObject searchQuery1 = new BasicDBObject().append("category", category);
	//List<Product> products = new ArrayList<Product>();
	Product product = new Product();
	DBCursor cursor = prodcata.find(searchQuery1);
	List<DBObject> products = new ArrayList<DBObject>();
	while (cursor.hasNext()) {
		System.out.println("datatype of cursor" + (cursor.iterator().getClass()));
		products.add(cursor.next());
		
	}
	
	return products;	
}

public DBObject retrieveItemByPid(int pid) throws UnknownHostException, JSONException 
{
	DB db = Mongoconnection();
	DBCollection prodcata = db.getCollection("productcatalog");
	BasicDBObject searchQuery1 = new BasicDBObject().append("productId", pid);
	//List<Product> products = new ArrayList<Product>();
	DBObject product = new BasicDBObject();
	DBCursor cursor = prodcata.find(searchQuery1);
	//List<DBObject> products = new ArrayList<DBObject>();
	if (cursor.count() != 0) {
		System.out.println("datatype of cursor" + (cursor.iterator().getClass()));
		//products.add(cursor.next());
		System.out.println("before typecast");
		product = cursor.next();
		System.out.println("after typecast");
	}
	
	return product;
	
}

public int addProduct(int userid, Product product) throws UnknownHostException {
	DB db = Mongoconnection();
	DBCollection produ = db.getCollection("productcatalog");
    BasicDBObject prod = new BasicDBObject();
    try{
    prod.put("productId", product.getProductId());
    prod.put("category", product.getCategory());
    prod.put("categoryId", 1);
    prod.put("productName", product.getProductName());
    prod.put("productDesc", product.getProductDesc());
    prod.put("price", product.getPrice());
    prod.put("quantity", product.getQuantity());
    //prod.put("userid", userid);
    produ.insert(prod);
    return 1;
    }
    catch (Exception ase) {
      System.err.println("Create items failed.");
      ase.printStackTrace();
      return 0;
    }
   }


public int updateProduct(int productId, int quantity) throws UnknownHostException, JSONException
{
	DB db = ProductMongoDao.Mongoconnection();
	DBCollection prod = db.getCollection("productcatalog");
	BasicDBObject query = new BasicDBObject();
	query.put("productId", productId);
	DBObject prevObj = new BasicDBObject();
	prevObj = retrieveItemByPid(productId);
	int prevQuant = (Integer) prevObj.get("quantity");
	if (prevQuant != 0)
	{
		int newQuant = prevQuant - 1;
		System.out.println("prevQuant" + prevQuant + "newQuant" + newQuant + "!!!!!!!");
		try{
				BasicDBObject newDocument = new BasicDBObject();
				newDocument.put("quantity", newQuant);
 
				BasicDBObject updateObj = new BasicDBObject();
				updateObj.put("$set", newDocument);
 
				prod.update(query, updateObj);
				return 1;
		}
	catch (Exception e){
		e.printStackTrace();
		return 0;
	}
	}else{
		return 0;
	}
}


public static void getdata() throws UnknownHostException {
	DB db = Mongoconnection();
	DBCollection songs = db.getCollection("productcatalog");
	final BasicDBObject[] seedData = createSeedData();
	songs.insert(seedData);
}

public static BasicDBObject[] createSeedData(){
    
    BasicDBObject seventies = new BasicDBObject();
    seventies.put("productId", 120);
    seventies.put("category", "Books");
    seventies.put("categoryId", 4);
    seventies.put("productName", "Harry Potter");
    seventies.put("productDesc", "by JK Rowling");
    seventies.put("price", 100);
    seventies.put("quantity", 10);
    
    BasicDBObject eighties = new BasicDBObject();
    eighties.put("productId", 121);
    eighties.put("category", "Accessories");
    eighties.put("categoryId", 1);
    eighties.put("productName", "Ring");
    eighties.put("productDesc", "Diamond ring");
    eighties.put("price", 1000);
    eighties.put("quantity", 10);
    
    BasicDBObject nineties = new BasicDBObject();
    nineties.put("productId", 121);
    nineties.put("category", "Clothing");
    nineties.put("categoryId", 2);
    nineties.put("productName", "Dress");
    nineties.put("productDesc", "Beautiful dress");
    nineties.put("price", 110);
    nineties.put("quantity", 12);
    
    final BasicDBObject[] seedData = {seventies, eighties, nineties};
    
    return seedData;
}

}
