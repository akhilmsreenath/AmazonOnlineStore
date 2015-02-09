<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SignIn</title>
</head>
<body>
<div class="container">
	<h1><a>Simple Amazon Store</a></h1>
	
	<div class="navbar">
              <div class="navbar-inner">
                <div class="container">
                  <ul class="nav">
					<li class="span2"><a href="../Signup.jsp"><b><h3>Signup</h3></b></a></li>
                  </ul>
                </div>
              </div>
            </div>
	<img src="../assets/img/cloudServices.jpg" class="span5" />
	<!-- h1><a href="#">CMPE 282 Cloud Services</a></h1>
	
	<h1 class="span2"><a href="SignIn.html">Sign In</a></h1>
	<h3><a href="#">My Cart</a></h3-->

	<form class="well span6" onsubmit="return false;">
		<input type="text" id="userName" class="span3" placeholder="Username"/>
		<input type="password" id="passwd" class="span3" placeholder="Password" />
		<button class="btn btn-primary" id="login" onclick="LogInfun()">Login</button>
		<button id="clear" class="btn"> Clear </button>
	</form>
	</div>
	
	<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
	<!--  script src="../assets/js/signIn.js"></script> -->
	
	<script type="text/javascript">
	
	//$(document).ready(function(){
	//	
	//})
	
function LogInfun(){

	var URL = "http://localhost:8080/cloudservices1/rest/users/signin";
	//alert("signin button clicked");//+formToJSON());
	
	$.ajax({ 
			type: "POST",
			url: URL,
			contentType: "application/json",
			dataType: 'json',
			data : formToJSON(),
				//success: function () { //success(data); }
			success: function(data, textStatus, jqXHR){
					 //alert("you are logged in");
					 window.location.href = "category.jsp";
					 //alert ("hello");
				},
			error: function(textStatus, jqXHR,errorThrown){
				alert("Invalid username and password");
				alert(textStatus+" "+jqXHR);
			}

		});
}

function formToJSON() {
    return JSON.stringify({
    "email": $('#userName').val(),
    "password": $('#passwd').val(),
    });
}
	</script>

</body>
</html>