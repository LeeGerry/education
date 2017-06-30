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
				      <a class="navbar-brand" href="${pageContext.request.contextPath }/teacher">ALT</a>    
			</div>
			   
			<button
				onclick="window.location.href='${pageContext.request.contextPath }/teacher'"
				class="w3-button w3-pink">Return Main Page</button>
			<!-- <button class="w3-button w3-blue">Manage Exams</button>  -->
			<ul class="nav navbar-nav navbar-right">
				      
				<li><a href="#"><span class="glyphicon glyphicon-user"></span>
						Hello, <%
				out.println(session.getAttribute("user"));
			%></a></li>
				<li>
					<button onclick="window.location.href='${pageContext.request.contextPath }/logout'"
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
					<th>Teacher</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list }" var="item" varStatus="counter">
					<tr>
						<td><a href="${pageContext.request.contextPath }/teacher?method=details&lid=${item.lid }"><c:out
									value="${item.name }" /></a></td>
						<td>${item.type }</td>
						<td>${item.uname }</td>
						<td><a href="${pageContext.request.contextPath }/teacher?method=delete&lid=${item.lid }">x</a></td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
		<div class="wells">
			<form class="form-horizontal"
				action="${pageContext.request.contextPath }/teacher?method=add"
				method="post">
				<div class="form-group">
					<label class="control-label col-sm-3" for="lessonName">Choose the level:</label>
					<div class="col-sm-9">
						<select name="ltype">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-3" for="lessonName">Lesson
						Name:</label>
					<div class="col-sm-9">
						<input type="lessonName" class="form-control" id="lessonName"
							placeholder="Enter Lesson Name" name="lname">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="lessonDescription">Lesson
						Description:</label>
					<div class="col-sm-9">
						<input type="lessonDescription" class="form-control"
							id="lessonDescription" placeholder="Enter Lesson Desciprtion"
							name="ldesc">
					</div>
				</div>
				<div class="w3-center">
					<!-- 					<button class="w3-button w3-circle w3-green">+</button>
 -->
					<input class="w3-button w3-circle w3-green" type="submit" value="+" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>