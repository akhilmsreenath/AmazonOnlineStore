<%@page import="com.mongodb.DBObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import ="edu.sjsu.cmpe282.domain.Product"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    <%@page import="java.util.List"%>
    <%@page import="java.util.*"%>
    <%@ page import ="edu.sjsu.cmpe282.domain.User"%>
    <%User user = ((User) request.getSession().getAttribute("user")); %>
    <%int userid = user.getUserid();%>
    <%List<DBObject> cartproducts = (List<DBObject>)(request.getSession().getAttribute("cartproducts")); %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="container">
		<div id="header">
        	<h1>Simple Amazon WebStore<span class="off"></span></h1>
            <h2></h2>
        </div>   
        <div id="menu">
        	<ul>
            	<li class="menuitem"><a href="category.jsp">Home</a></li>
                <li class="menuitem"><a href="../Signup.jsp">Log Off</a></li>
                
            </ul>
        </div>
        	
</div>
<table >
				<tbody> 
					<tr>
						<td><b>Product ID</b></td>
						<td><b>Product Name</b></td>
						<td><b>Product Description</b></td>
						<td><b>Product Price</b></td>
						<td><b>Quantity</b></td>
						<td><b>Action</b></td>
					</tr>

					<%
if(cartproducts.size() >0 ){
for (int j=0; j<cartproducts.size();j++){%>
<tr>
   		<td><%out.println((cartproducts.get(j).get("productId")));%></td> 
   		<td><%out.println((cartproducts.get(j).get("productName")));%></td> 
   		<td><%out.println((cartproducts.get(j).get("productDesc")));%></td> 
   		<td><%out.println((cartproducts.get(j).get("price")));%></td> 
   		<td><%out.println((cartproducts.get(j).get("quantity")));%></td> 
   			<td><a href="javascript:deleteFromCart( <%=(cartproducts.get(j).get("productId"))%> )" >Remove</a> </td>
   			<tr>
   		<%}%>     
      
<%}%>
</tr>
</tr> 
					</tbody>
</table>
				<br><br><br>
	<form class="well span6" onsubmit="return false;">
		<input  id="ccno" name="ccno" type="text" class="form-control" placeholder="Credit Card No" required autofocus pattern="[0-9]{16}" />
		
         <input type="text" name="ccname" id="ccname" class="form-control" placeholder="Name" required  pattern="[A-Z\sa-z]{2,30}" />
        <br>
         <input type="text" name="exp_date" id="exp_date" class="form-control" placeholder="Expiry Date" required="required" pattern="[0-9]{2}/[0-9]{2}"/>
       <br>
       	<input type="text" name="cvv" id="cvvv" id="cvvv" class="form-control" placeholder="CVV" required="required" pattern="[0-9]{3}"/>
       <br>
		<button class="btn btn-primary" id="Checkout" onclick="checkout()">CheckOut</button>
	</form>
	
	<input id="userid" type = "hidden"  value="<%=userid %>" >
</body>

<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	
	//$(document).ready(function(){
	//	
	//})
	
function checkout(){
	console.log("inside Accessories");
	var userid = document.getElementById('userid').value;
	//var userid = "120";
	console.log(userid);
	var URL = "/cloudservices1/rest/user/" + userid + "/checkout/";
	alert("accessories");
	$.ajax({
			type: "POST",
			url: URL,
			contentType: "application/json",
			//dataType: 'json',
			data : formToJSON(),
				//success: function () { //success(data); }
			success: function(data, textStatus, jqXHR){
					alert("Accessories in jsp");
					//alert("userid: " + data);
					//alert ("uid" + request.getSession().getAttribute("userid"));
					window.location.href = "category.jsp";
					//System.out.println( request.getSession(true).getAttribute("userid"));
					
				},
			error: function(textStatus, jqXHR,errorThrown){
				alert(textStatus+" "+jqXHR);
			}

		});
	//alert("accessories1");
}
	
function formToJSON() {
    return JSON.stringify({
    "ccno": $('#ccno').val(),
    "ccname": $('#ccname').val(),
    "exp_date": $('#exp_date').val(),
    "cvvv": $('#cvvv').val(),
    });
}

function deleteFromCart(pid){
	console.log("inside Accessories");
	var userid = document.getElementById('userid').value;
	//var userid = document.getElementById('userid').value;
	//var userid = "120";
	console.log(userid);
	//var pid =121;
	var URL = "/cloudservices1/rest/user/" + userid + "/addTocart/" + pid +"/deletefromcart/";
	alert("accessories");
	$.ajax({
			type: "DELETE",
			url: URL,
			contentType: "application/json",
			//dataType: 'json',
			data : formToJSON(),
				//success: function () { //success(data); }
			success: function(data, textStatus, jqXHR){
					alert("Accessories in jsp");
					//alert("userid: " + data);
					//alert ("uid" + request.getSession().getAttribute("userid"));
					window.location.href = "DeleteCartMessage.jsp";
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