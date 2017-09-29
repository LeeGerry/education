<%@ page language="java"  import="edu.auburn.domain.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Exam Management</title>
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
					href="${pageContext.request.contextPath }/teacher">ALT</a>      
			</div>
			   <%
				Lesson l = (Lesson) (request.getAttribute("lesson"));
			%>
			<button
				onclick="window.location.href='${pageContext.request.contextPath }/teacher'"
				class="w3-button w3-blue">Return Main Page</button>
			<button
				onclick="window.location.href='${pageContext.request.contextPath }/teacher?method=managestudent&lid=<% out.print(l.getLid());%>'"
				class="w3-button w3-pink">Manage Users</button>

			<button onclick="window.location.href='${pageContext.request.contextPath }/teacher?method=details&lid=<%out.print(l.getLid()); %>'" class="w3-button w3-green">Manage
				Files</button>
				
			<button onclick="window.location.href='${pageContext.request.contextPath }/teacher?method=examlist&lid=<% out.print(l.getLid());%>'" class="w3-button w3-yellow">Manage
				Exams</button>
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
	<h1>Lesson:<%out.print(l.getName()); %></h1>
	<hr>
		<h2>Current Exams</h2>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Name</th>
					<th>0:Exam/1:Practice</th>
					<th>Due Date</th>
					<th>Created by</th>
					<th>Delete</th>
					<th>Result</th>
					<th>Edit</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list }" var="item" varStatus="counter">
					<tr>
						<td><a href="${pageContext.request.contextPath }/teacher?method=examdetails&lid=<%out.print(l.getLid()); %>&eid=${item.eid}"><c:out
									value="${item.name }" /></a></td>
						<td>${item.ifPractice}</td>
						<td>${item.edue }</td>
						<td>${item.uname }</td>
						<td><a href="${pageContext.request.contextPath }/teacher?method=delexam&lid=<%out.print(l.getLid()); %>&eid=${item.eid}">x</a></td>
						<td><a href="${pageContext.request.contextPath }/teacher?method=checke&lid=<%out.print(l.getLid()); %>&eid=${item.eid}">check</a></td>
						<td><a href="${pageContext.request.contextPath }/teacher?method=edite&lid=<%out.print(l.getLid()); %>&eid=${item.eid}">edit</a></td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
		<div class="wells">
			<form class="form-horizontal" action="${pageContext.request.contextPath }/teacher?method=addexam&lid=<%out.print(l.getLid()); %>"
				method="post">
				<div class="form-group">
					<label class="control-label col-sm-2" for="level">Choose type</label>
					<div class="col-sm-10">
						<select name="isprac">
							<option value="0">exam</option>
							<option value="1">practice</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="level">Choose the keyboard:</label>
					<div class="col-sm-10">
						<select name="etype">
							<option value="0">Basic keyboard</option>
							<option value="1">Advanced keyboard</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="examName">Exam
						Name:</label>
					<div class="col-sm-10">
						<input type="examName" class="form-control" id="examName"
							placeholder="Enter Exam Name" name="ename">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="examDescription">Exam
						Description:</label>
					<div class="col-sm-10">
						<input type="examDescription" class="form-control"
							id="examDescription" placeholder="Enter Exam Desciprtion"
							name="edesc">
					</div>
				</div>
				<div class="form-inline">
					<label class="control-label col-sm-2" for="dueDate">Due
						Date:</label>
					<div class="col-sm-10">
						<input type="datetime-local" class="form-control" id="date"
							placeholder="Enter Due Date" name="edue">
					</div>
					<div class="w3-center">
						<button class="w3-button w3-circle w3-green">+</button>
					</div>
			</form>
		</div>
	</div>
</body>
</html>