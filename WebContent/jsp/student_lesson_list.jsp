<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Lesson Management</title>
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
			   
			<button
				onclick="window.location.href='${pageContext.request.contextPath }/student'"
				class="w3-button w3-pink">Return Main Page</button>
			<!-- <button class="w3-button w3-blue">Manage Exams</button>  -->
			<ul class="nav navbar-nav navbar-right">
				      
				<li><a href="#"><span class="glyphicon glyphicon-user"></span>
						Hello, <%
				out.println(session.getAttribute("user"));
			%></a></li>
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
		<h1>Current Lessons</h1>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Lesson</th>
					<th>Level</th>
					<th>Advisor</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${allLessons }" var="item" varStatus="counter">
					<tr>
						<td><a
							href="${pageContext.request.contextPath }/student?method=ldetails&lid=${item.lid }"><c:out
									value="${item.name }" /></a></td>
						<td>${item.type }</td>
						<td>${item.uname }</td>
					</tr>

				</c:forEach>
			</tbody>
		</table>


		<h1>Registered Lessons</h1>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Lesson</th>
					<th>Role</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${registeredLessons }" var="item"
					varStatus="counter">
					<tr>
						<td><a href="${pageContext.request.contextPath }/student?method=learnlesson&lid=${item.lid }">${item.lname }</a></td>
						<td>${item.role }</td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>