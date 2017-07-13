<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Welcome</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
 
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
.btn-sq {
	width: 400px;
	height: 400px;
}

body {
	background-image: url("gray.jpg");
}
</style>
 
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
 
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-inverse">
		 
		<div class="container-fluid">
			   
			<div class="navbar-header">
				      <a class="navbar-brand"
					href="${pageContext.request.contextPath }/ta">ALT</a>    
			</div>
			<ul class="nav navbar-nav navbar-right">
				      
				<li><a href="#"><span class="glyphicon glyphicon-user"></span>
						<%
							if (session == null || session.getAttribute("user") == null) {
						%> <input type="submit" value="LogIn"> <input
						type="submit" value="SignUp"> <%
 	}
 	if (session.getAttribute("user") != null) {
 		out.println("hello, " + session.getAttribute("user"));
 %> <%
 	}
 %> </a></li>   
 <li>
					<button
						onclick="window.location.href='${pageContext.request.contextPath }/logout'"
						class="btn btn-danger navbar-btn">
						<span class="glyphicon glyphicon-log-out">Logout 
					</button>
				</li>   
			</ul>
			 
		</div>
	</nav>
	<div class="container w3-center">

		<div class="row">

			<div class="col-lg-12">

				<a href="${pageContext.request.contextPath }/exammanage"
					class="w3-btn btn-sq w3-cyan w3-circle w3-xxxlarge w3-ripple">
					<br> <br> <span class="glyphicon glyphicon-inbox"></span><br />

					Manage Exams <br>

				</a> <a href="${pageContext.request.contextPath }/lessonmanage"
					class="w3-btn btn-sq w3-lime w3-circle w3-xxxlarge w3-ripple">

					<br> <br> <span class="glyphicon glyphicon-edit"></span><br />
					Edit Lessons <br>
				</a> <a href="${pageContext.request.contextPath }/logout"
					class="w3-btn btn-sq w3-red w3-round-xlarge w3-xxxlarge w3-ripple">
					<br> <br> <span class="glyphicon glyphicon-off"></span><br />
					Logout <br>
				</a>
			</div>
		</div>
	</div>
</body>
</html>