<%@ page language="java" import="edu.auburn.domain.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Lesson Details</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
 
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
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
					href="${pageContext.request.contextPath }/student">ALT</a>     
			</div>
			<%
				Lesson l = (Lesson) (request.getAttribute("lesson"));
			%>
			   
			<button
				onclick="window.location.href='${pageContext.request.contextPath }/student'"
				class="w3-button w3-blue">Main Page</button>
			<button
				onclick="window.location.href='${pageContext.request.contextPath }/student?method=lessons'"
				class="w3-button w3-green">Lesson List</button>

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
	<%
		boolean isReg = (boolean) request.getAttribute("isreg");
	%>
	<div class="container">
		<h1>
			<%
				out.print(l.getName());
			%>
		</h1>

		<p>
			<%
				out.print(l.getDesc());
			%>
		</p>
		<p>


			<%
				if (isReg) {
			%>
			<button
				onclick="window.location.href='${pageContext.request.contextPath }/student?method=unreg&lid=<% out.print(l.getLid());%>'"
				class="w3-button w3-green">Unregister</button>
			<%
				}
				if (!isReg) {
			%>
			<button
				onclick="window.location.href='${pageContext.request.contextPath }/student?method=reg&lid=<% out.print(l.getLid());%>'"
				class="w3-button w3-green">Register</button>
			<%
				}
			%>

			<button
				onclick="window.location.href='${pageContext.request.contextPath }/student?method=lessons'"
				class="w3-button w3-green">Return lesson list</button>

		</p>
	</div>
</body>
</html>