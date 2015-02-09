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
    <%List<DBObject> products = (List<DBObject>)(request.getSession().getAttribute("products")); %>
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
						<td><b>Action</b></td>
					</tr>

					 <%
if(products.size() >0 ){
for (int j=0; j<products.size();j++){%>
<tr>
   		<td><%out.println((products.get(j).get("productId")));%></td> 
   		<td><%out.println((products.get(j).get("productName")));%></td> 
   		<td><%out.println((products.get(j).get("productDesc")));%></td> 
   		<td><%out.println((products.get(j).get("price")));%></td> 
   		<td><%out.println((products.get(j).get("quantity")));%></td> 
   			<td><a href="javascript:addTocart( <%=(products.get(j).get("productId"))%> )" >Add To Cart</a> </td>
   			<tr>
   		<%}%>     
      
<%}%>
</tr>
</tr> 
</tbody>
</table>
 <input id="userid" type = "hidden"  value="<%=userid %>" >                			
		
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
function addTocart(pid){
	console.log("inside Accessories cart");
	var userid = document.getElementById('userid').value;
	console.log(userid);
	//var formEle=document.getElementById('#form1');
	//alert(prd.productId);
	//jQuery("input[name=selectedItem]").click(
	//		   var chkbox = $(this);
	//		   $("input[name=" + checkbox.val() + "-mileage]").attr("disabled", checkbox[0].checked ? "" : "disabled");
	//		);
	alert(pid);
	//var pid = "121";
	var URL = "/cloudservices1/rest/user/" + userid + "/addTocart/" + pid;
	alert("accessories");
	//alert("Enter amount");
	//var person = prompt("Please enter quantity", "1");

	$.ajax({
			type: "POST",
			url: URL,
			contentType: "application/json",
			//dataType: 'json',
			data : userid,
				//success: function () { //success(data); }
			success: function(data, textStatus, jqXHR){
					alert("Accessories in jsp");
					//alert("userid: " + data);
					//alert ("uid" + request.getSession().getAttribute("userid"));
					window.location.href = "Checkout.jsp";
					//System.out.println( request.getSession(true).getAttribute("userid"));
					
					
					//userid from prev jsp, request to server, server request handling
					
					
				},
			error: function(textStatus, jqXHR,errorThrown){
				alert(textStatus+" "+jqXHR);
			}

		});
	alert("accessories1");
}

</script>
</html>