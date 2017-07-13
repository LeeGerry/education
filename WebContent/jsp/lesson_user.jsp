<%@ page language="java" import="edu.auburn.domain.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Lesson Operations</title>
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
			   <%
		Lesson l = (Lesson) (request.getAttribute("lesson"));
	%>
			<div class="navbar-header">
				     <a class="navbar-brand"
					href="${pageContext.request.contextPath }/teacher">ALT</a>     
			</div>
			   
			<button
				onclick="window.location.href='${pageContext.request.contextPath }/teacher'"
				class="w3-button w3-blue">Return Main Page</button>
			<button onclick="window.location.href='#'" class="w3-button w3-pink">Manage
				Users</button>

			<button
				onclick="window.location.href='${pageContext.request.contextPath }/teacher?method=details&lid=<%out.print(l.getLid()); %>'"
				class="w3-button w3-green">Manage Files</button>
			<button
				onclick="window.location.href='${pageContext.request.contextPath }/teacher?method=examlist&lid=<% out.print(l.getLid());%>'"
				class="w3-button w3-yellow">Manage Exams</button>
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
		<hr>
		<div class="wells">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>User</th>
						<th>Role</th>
						<th>Update to TA</th>
						<th>Update to Student</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list }" var="item" varStatus="counter">
						<tr>
							<td><c:out value="${item.sname }" /></td>
							<td><c:out value="${item.role }" /></td>
							<td><a
								href="${pageContext.request.contextPath }/teacher?method=update&sid=${item.sid }&lid=${item.lid}"
								class="w3-button w3-green w3-round-large"> <span
									class="glyphicon glyphicon-arrow-up"></span>
							</a></td>
							<td><a
								href="${pageContext.request.contextPath }/teacher?method=degrade&sid=${item.sid }&lid=${item.lid}"
								class="w3-button w3-yellow w3-round-large"> <span
									class="glyphicon glyphicon-arrow-up"></span>
							</a></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>
</body>
</html>