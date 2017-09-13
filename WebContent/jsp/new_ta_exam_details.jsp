<%@ page language="java" import="edu.auburn.domain.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Exam Operations</title>
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
		Exam e = (Exam) (request.getAttribute("exam"));
		Lesson l = (Lesson) (request.getAttribute("lesson"));
	%>
			<div class="navbar-header">
				      <a class="navbar-brand"
					href="${pageContext.request.contextPath }/teacher">ALT</a>     
			</div>
			 <!-- 
			<button
				onclick="window.location.href='${pageContext.request.contextPath }/teacher'"
				class="w3-button w3-blue">Return Main Page</button>
			<button
				onclick="window.location.href='${pageContext.request.contextPath }/teacher?method=managestudent&lid=<% out.print(l.getLid());%>'"
				class="w3-button w3-pink">Manage Users</button>

			<button
				onclick="window.location.href='${pageContext.request.contextPath }/teacher?method=details&lid=<%out.print(l.getLid()); %>'"
				class="w3-button w3-green">Manage Files</button>

			<button
				onclick="window.location.href='${pageContext.request.contextPath }/teacher?method=examlist&lid=<% out.print(l.getLid());%>'"
				class="w3-button w3-yellow">Manage Exams</button>
				
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

		<h1>
			<%
				out.print(e.getName()+"\t----");
				out.print("   Difficult Level: ");
				out.print(e.getEtype());
			%>
		</h1>

		<p>
			<%
				out.print(e.getEdesc());
			%>
		</p>
		<hr>
		

			<p>Words</p>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Word File</th>
						<th>Word Pronunciation</th>
						<th>Action</th>
						<th>Add Video</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${words }" var="item" varStatus="counter">
						<tr>
							<td>${item.name }</td>
							<td>${item.pron }</td>
							<td><a
								href="${pageContext.request.contextPath }/student?method=delw&vid=${item.fid}&eid=<% out.print(e.getEid());%>&lid=<% out.print(l.getLid());%>">x</a></td>
							<td><a href="${pageContext.request.contextPath }/student?method=wordDetails&wid=${item.fid}&eid=<% out.print(e.getEid());%>&lid=<% out.print(l.getLid());%>">Add</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>


		<div style="border: 1px solid #333; margin-top:5px; padding:20px;">
			
			<div class="wells">
			<br>
				<p>Type in the IPA letter and upload the pronunciation file,
					only support WAV.</p>


				<!-- 
				<script type="text/javascript">
					var undo = "";
					var keyLenArr = [];
					function copyText(value) {
						var str1 = unescape(value.replace(
								/\\(u[0-9a-fA-F]{4})/gm, '%$1'));
						var next = document.getElementById("ta").value;
						next += str1;
						document.getElementById("ta").value = next;
					}
					function backspace() {
						//document.getElementById("getSymble").value="";
						var text = document.getElementById("ta").value;
						
						var keyLen = keyLenArr.pop()
						text = text.slice(0, keyLen*-1);
							
						document.getElementById("ta").value = text;
					}
					function clean() {
						document.getElementById("ta").value = "";
					}
					
					function setkeyLen(pressedKey){
						keyLenArr.push(pressedKey.length);
					}
				</script>
				
				 -->
				<c:choose>
    		<c:when test="${exam.etype == 0}">
        		<%@include file="public/keyboard1.jsp" %>
    		</c:when>    
    		<c:otherwise>
        		 <%@include file="public/keyboard2.jsp" %>
    		</c:otherwise>
	</c:choose>
</div>









			<br>
			<form class="form-horizontal" name="l_file"
				action="${pageContext.request.contextPath }/student?method=taaddword&eid=<% out.print(e.getEid());%>&lid=<% out.print(l.getLid());%>"
				method="post" enctype="multipart/form-data">
				<div class="form-group">

					<div class="row w3-center">
						<div class="col-sm-10">
							<input type="text" class="form-control" id="ta"
								placeholder="Please input the ipa letters" name="ipa" readonly="true">
						</div>


					</div>
					<br>
					<div>
						<label class="control-label col-sm-2" for="upload"></label>
						<div class="col-sm-10">
							<input type="file" id="upload" name="fname">
						</div>

					</div>


					<div class="w3-center">
						<br> 
							<button type="submit"
					class="w3-button w3-teal w3-round-large w3-center" value="+">+</button>
					</div>
			</form>
		</div>
	</div>







	</div>
</body>
</html>