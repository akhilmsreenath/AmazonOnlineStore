<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import ="edu.sjsu.cmpe282.domain.User"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    <%@taglib prefix="c1" uri="http://java.sun.com/jsp/jstl/core" %>
    <%User user = ((User) request.getSession().getAttribute("user")); %>
   <%int userid = user.getUserid();%> 
   <%int admin = user.getAdmin(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="#" />
<title>Web Store</title>
</head>

<body>
<div id="container">
		<div id="header">
        	<h1>Simple Amazon WebStore<span class="off"></span></h1>
            
        </div>   
        <div>
        
        <h2> Welcome <%=user.getFname()%></h2>
        <h3>Your last login was on: <%=user.getLast_login()%></h3>
        </div>
        <div id="menu">
        	<ul>
            	<li class="menuitem"><a href="category.jsp">Home</a></li>
                <li class="menuitem"><a href="../Signup.jsp">Log Off</a></li>
            </ul>
        </div>
        <br><br><br>
        		<% if(admin==1) { %>
     			<h3><a href="AddProduct.jsp">Add Products</a></h3>
                <%} %>
		
        <div id="leftmenu">
	<div>
				<form class="well span6" onsubmit="return false;" id="myform1">        
                <button class="btn btn-primary" id="cart" onclick="getCartItems()" value="Cart Items">Cart</button> &nbsp; &nbsp; &nbsp; &nbsp;
                <button class="btn btn-primary" id="payment" onclick="getPaymentHistory()" value="PaymentHist">Payment History</button> &nbsp; &nbsp; &nbsp; &nbsp;
                
				</form>
	</div>
        <div id="leftmenu_top"></div>

				<div id="leftmenu_main">    
                
                <h3>Categories</h3>
                <form class="well span6" onsubmit="return false;" id="myform">        
                <button class="btn btn-primary" id="Accessories" onclick="getAccessories()" value="Accessories">Accessories</button> &nbsp; &nbsp; &nbsp; &nbsp;
                <button class="btn btn-primary" id="Fashion" onclick="getFashion()" value="Fashion">Fashion</button> &nbsp; &nbsp; &nbsp; &nbsp;
                <button class="btn btn-primary" id="Electronic" onclick="getElectronic()" value="Electronic">Electronic</button> &nbsp; &nbsp; &nbsp; &nbsp;
                <button class="btn btn-primary" id="Books" onclick="getBooks()" value="Books">Books</button> &nbsp; &nbsp; &nbsp;
                
                </form>
</div>
               
              <input id="userid" type = "hidden"  value="<%=userid %>" >
              <div id="leftmenu_bottom"></div>
        </div>
                <div>USERID</div>
        <%=userid %>
        </div>
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	
	
function getAccessories(){
	console.log("inside Accessories");
	var userid = document.getElementById('userid').value;
	var category = null;
	console.log(userid);
	var URL = "/cloudservices1/rest/user/" + userid + "/category/" + "Accessories/";
	alert("accessories");
	$.ajax({
			type: "GET",
			url: URL,
			contentType: "application/json",
			//dataType: 'json',
			//data : userid,
				//success: function () { //success(data); }
			success: function(data, textStatus, jqXHR){
					alert("Accessories in jsp");
					//alert("userid: " + data);
					//alert ("uid" + request.getSession().getAttribute("userid"));
					window.location.href = "Accessories.jsp";
					//System.out.println( request.getSession(true).getAttribute("userid"));
					
				},
			error: function(textStatus, jqXHR,errorThrown){
				alert(textStatus+" "+jqXHR);
			}

		});
	//alert("accessories1");
}
	
function getFashion(){
	console.log("inside Fashion");
	var userid = document.getElementById('userid').value;
	console.log(userid);
	var URL = "/cloudservices1/rest/user/" + userid + "/category/Clothing/";
	alert("accessories");
	$.ajax({
			type: "GET",
			url: URL,
			contentType: "application/json",
			//dataType: 'json',
			//data : userid,
				//success: function () { //success(data); }
			success: function(data, textStatus, jqXHR){
					alert("Fashion in jsp");
					//alert("userid: " + data);
					//alert ("uid" + request.getSession().getAttribute("userid"));
					window.location.href = "Fashion.jsp";
					//System.out.println( request.getSession(true).getAttribute("userid"));
					
				},
			error: function(textStatus, jqXHR,errorThrown){
				alert(textStatus+" "+jqXHR);
			}

		});
	//alert("accessories1");
}

function getElectronic(){
	console.log("inside Electronic");
	var userid = document.getElementById('userid').value;
	console.log(userid);
	var URL = "/cloudservices1/rest/user/" + userid + "/category/Electronic/";
	alert("accessories");
	$.ajax({
			type: "GET",
			url: URL,
			contentType: "application/json",
			//dataType: 'json',
			//data : userid,
				//success: function () { //success(data); }
			success: function(data, textStatus, jqXHR){
					alert("Accessories in jsp");
					//alert("userid: " + data);
					//alert ("uid" + request.getSession().getAttribute("userid"));
					window.location.href = "Electronic.jsp";
					//System.out.println( request.getSession(true).getAttribute("userid"));
					
				},
			error: function(textStatus, jqXHR,errorThrown){
				alert(textStatus+" "+jqXHR);
			}

		});
	//alert("accessories1");
}

function getBooks(){
	console.log("inside books");
	var userid = document.getElementById('userid').value;
	console.log(userid);
	var URL = "/cloudservices1/rest/user/" + userid + "/category/Books/";
	alert("accessories");
	$.ajax({
			type: "GET",
			url: URL,
			contentType: "application/json",
			//dataType: 'json',
			//data : userid,
				//success: function () { //success(data); }
			success: function(data, textStatus, jqXHR){
					alert("Accessories in jsp");
					//alert("userid: " + data);
					//alert ("uid" + request.getSession().getAttribute("userid"));
					window.location.href = "Books.jsp";
					//System.out.println( request.getSession(true).getAttribute("userid"));
					
				},
			error: function(textStatus, jqXHR,errorThrown){
				alert(textStatus+" "+jqXHR);
			}

		});
	//alert("accessories1");
}

function getCartItems(){
	console.log("inside cart items");
	var userid = document.getElementById('userid').value;
	var category = null;
	//var category = document.getElementById()formname.elements[""];
	console.log(userid);
	var URL = "/cloudservices1/rest/user/" + userid + "/addTocart/getcart/";
	alert("cart");
	$.ajax({
			type: "GET",
			url: URL,
			contentType: "application/json",
			//dataType: 'json',
			//data : userid,
				//success: function () { //success(data); }
			success: function(data, textStatus, jqXHR){
					alert("Accessories in jsp");
					//alert("userid: " + data);
					//alert ("uid" + request.getSession().getAttribute("userid"));
					window.location.href = "Checkout.jsp";
					//System.out.println( request.getSession(true).getAttribute("userid"));
					
				},
			error: function(textStatus, jqXHR,errorThrown){
				alert(textStatus+" "+jqXHR);
			}

		});
	//alert("accessories1");
}	
	
function getPaymentHistory(){
		console.log("inside payement items");
		var userid = document.getElementById('userid').value;
		var category = null;
		//var category = document.getElementById()formname.elements[""];
		console.log(userid);
		var URL = "/cloudservices1/rest/user/" + userid + "/paymenthistory/";
		alert("cart");
		$.ajax({
				type: "GET",
				url: URL,
				contentType: "application/json",
				//dataType: 'json',
				//data : userid,
					//success: function () { //success(data); }
				success: function(data, textStatus, jqXHR){
						alert("Accessories in jsp");
						//alert("userid: " + data);
						//alert ("uid" + request.getSession().getAttribute("userid"));
						window.location.href = "PaymentHistory.jsp";
						//System.out.println( request.getSession(true).getAttribute("userid"));
						
					},
				error: function(textStatus, jqXHR,errorThrown){
					alert(textStatus+" "+jqXHR);
				}

			});
		//alert("accessories1");
	
}
</script>
</html>
