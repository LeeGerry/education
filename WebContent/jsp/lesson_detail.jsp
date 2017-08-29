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
		<table class="table table-hover">
			<thead>
				<tr>
					<th>File</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list }" var="item" varStatus="counter">
					<tr>
						<td>
						<c:url var="url" value="/teacher">
								<c:param name="method" value="teacher_download"></c:param>
								<c:param name="fid" value="${item.fid }"></c:param>
							</c:url> <a href="${url }">${item.name }</a>
						
						
						</td>
						<td><a
							href="${pageContext.request.contextPath }/teacher?method=deletef&fid=${item.fid }&lid=<% out.print(l.getLid());%>">x</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<p>Support for only MP4</p>
		<div class="wells">
			<form class="form-horizontal" name="l_file"
				action="${pageContext.request.contextPath }/teacher?method=upload&lid=<% out.print(l.getLid());%>"
				method="post" enctype="multipart/form-data">
				<div class="form-group">
					<label class="control-label col-sm-2" for="upload">File
						description: </label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="upload"
							placeholder="Please describe the file" name="fdesc">
					</div>
				</div>

				<div>
					<label class="control-label col-sm-2" for="upload"></label>
					<div class="col-sm-10">
						<input type="file" id="upload" name="filename" >
					</div>
					
				</div>


				<div class="w3-center">
					<br> <input class="w3-button w3-circle w3-green" type="submit"
						value="+" />
				</div>

			</form>
		</div>
		<p>
			<%
			String message = (String)request.getAttribute("message");
				if (message != null) {
					out.println(message);
				}
			%>
		</p>
	</div>
</body>
</html>