<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import ="edu.sjsu.cmpe282.domain.User"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
    <%@taglib prefix="c1" uri="http://java.sun.com/jsp/jstl/core" %>
    <%User user = ((User) request.getSession().getAttribute("user")); %>
   <%int userid = user.getUserid();%>
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
<div>
<form class="well span6" onsubmit="return false;">
		<input type="text" id="productId" class="span3" placeholder="Product ID" required autofocus pattern="[0-9]{16}"/><br><br>
		<input type="text" id="productName" class="span3" placeholder="Product Name"/><br><br>
		<input type="text" id="productDesc" class="span3" placeholder="Product Description" /><br><br>
		<input type="text" id="category" class="span6" placeholder="category" /><br><br>
		<input type="text" id="quantity" class="span6" placeholder="quantity" /><br><br>
		<input type="text" id="price" class="span6" placeholder="price" /><br><br><br><br>
		<button class="btn btn-primary" id="add" onclick="addProduct()">Add Product</button>
		<button class="btn" id="clear"> Clear </button>
	</form>
	</div>
	<input id="userid" type = "hidden"  value="<%=userid %>" >
</body>

<script src="assets/js/bootstrap.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="assets/js/signUp.js"></script>
<script type="text/javascript">
	
	//$(document).ready(function(){
	//	
	//})
	
function addProduct(){

	//alert("amrit");//+formToJSON());
	//var bk=document.getElementById("admin").checked;
	var userid = document.getElementById('userid').value;
	var URL = "/cloudservices1/rest/user/" + userid + "/category/addproduct/";
	
	$.ajax({
			type: "POST",
			url: URL,
			contentType: "application/json",
			dataType: 'json',
			data : formToJSON(),
				//success: function () { //success(data); }
			success: function(data, textStatus, jqXHR){
					//alert("you are signed up");
					//alert("userid: " + data);
					//alert ("uid" + request.getSession().getAttribute("userid"));
					window.location.href = "category.jsp";
					//System.out.println( request.getSession(true).getAttribute("userid"));
					
				},
			error: function(textStatus, jqXHR,errorThrown){
				alert(textStatus+" "+jqXHR);
			}

		});
}

function formToJSON() {
    return JSON.stringify({
    "productId": $('#productId').val(),
    "productName": $('#productName').val(),
    "productDesc": $('#productDesc').val(),
    "category": $('#category').val(),
    "quantity": $('#quantity').val(),
    "price": $('#price').val(),
    });
}
	</script>
</html>