<%@page import="java.util.List"%>
<%@ page language="java" import="edu.auburn.domain.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Word Result</title>
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
					WordStudent ws = (WordStudent) (request.getAttribute("wordstudent"));
					Exam e = (Exam) (request.getAttribute("exam"));
					ExamWord ew = (ExamWord) (request.getAttribute("ew"));
				%>
			<button
				onclick="javascript:history.back(-1);"
				class="w3-button w3-blue">Return</button>
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
		<h1>Professor's answer:   <% out.print(ew.getPron());%>&nbsp;&nbsp;&nbsp;&nbsp;
		Your answer:   <% out.print(ws.getAnswer());%></h1>
		<hr>
		
		<script type="text/javascript">
			function playA() {
				
				var audio = document.getElementById("playaudio");
				//alert(audio.src);
				audio.play();
			}
		</script>
	
		 Listen to the word by clicking the icon:
		 <audio hidden="true" id="playaudio" src="${ew.getPath() }"
				controls="controls">
			</audio>
		<button onclick="playA()"
				class="w3-button w3-circle w3-teal  w3-small">
				<span class="glyphicon glyphicon-play-circle"></span>
		</button>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a style="color: BLUE;" href="${pageContext.request.contextPath }/student?method=checkdistribution&eid=<%out.print(e.getEid());%>&wid=<%out.print(ew.getFid());%>">Click here to see the distribution.</a>
		<br>Other students' answer:
		<table class="table table-hover">
			<thead>
				<tr>
					<th style="width: 10%;">Index</th>
					<th>Answer</th> 
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${result }" var="item" varStatus="counter">
					<tr>
						<td>${counter.index+1 }</td>
						<td>${item.answer }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>