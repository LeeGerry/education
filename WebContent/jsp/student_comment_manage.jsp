<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Teacher Comment</title>
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
					href="${pageContext.request.contextPath }">ALT</a>    
			</div>
			<button
				onclick="window.location.href='${pageContext.request.contextPath }/student'"
				class="w3-button w3-blue">Return Main Page</button>
			<!-- 
			<button onclick="window.location.href='/edu_system/LessonManageServlet'" class="w3-button w3-green">Edit Lessons</button>
			 -->
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
		<!-- 
		<h1>Comment List</h1>
		<table class="table table-hover" style="table-layout:fixed;">
			<thead>
				<tr>
					<th style="width: 10%">User</th>
					<th>Comment</th>
					<th style="width: 20%">Date</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list }" var="item" varStatus="counter">
					<tr>
						<td><c:out value="${item.uname }" /></td>
						<td><c:out value="${item.comment }" /></td>
						<td><c:out value="${item.date }" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		 -->
		Please input your comments here. Character number(5-2000).
				<br>
	<form class="form-horizontal"
		action="${pageContext.request.contextPath }/student?method=addComment"
		method="post">
		<div class="center">
			<div class="col-sm-12">
				
				<textarea name="comment" rows="5" cols="130"></textarea>
			</div>


		</div>
		<div class="w3-center">
			<button type="submit"
				class="w3-button w3-teal w3-round-large w3-center" value="Submit">Submit</button>
		</div>
	</form>
	<%String message = (String)request.getAttribute("message"); %>
	<div class="container w3-center">
		<h3 style="color: red">${message }</h3>

	</div>
	</div>
	
</body>
</html>