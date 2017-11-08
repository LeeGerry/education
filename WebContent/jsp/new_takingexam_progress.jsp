<%@page import="edu.auburn.domain.WordVideo"%>
<%@page import="edu.auburn.domain.Exam"%>
<%@page import="edu.auburn.domain.ExamWord"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Taking Exam</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
 
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
body {
	background-image: url("gray.jpg");
}
video::-internal-media-controls-download-button {
    display:none;
}

video::-webkit-media-controls-enclosure {
    overflow:hidden;
}

video::-webkit-media-controls-panel {
    width: calc(100% + 30px); /* Adjust as needed */
}
hr {
	display: block;
	margin-top: 0.5em;
	margin-bottom: 0.5em;
	margin-left: auto;
	margin-right: auto;
	border-style: inset;
	border-width: 3px;
}

.audio-button {
	width: 150px;
	height: 150px;
}

.video-button {
	width: 300px;
	height: 150px;
}
#myProgress {
    width: 100%;
    background-color: grey;
}
#myBar {
    width: 1%;
    height: 30px;
    background-color: green;

}
#barText{
	width: 100%;
	height: 30px;
	text-align: center; /* To center it horizontally*/
    line-height: 30px; /* To center it vertically */
    color: white; }
</style>
 
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
 
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
</head>
<body onload = "updateBar()">
<%
		int total = (int) request.getAttribute("total");
		int current = (int) request.getAttribute("current");
		Exam exam = (Exam)request.getAttribute("exam");
		int eid = exam.getEid();
		String answer = (String)request.getAttribute("answer");
		ExamWord word = (ExamWord)(request.getAttribute("word"));
		List<ExamWord> words = (List<ExamWord>)request.getAttribute("words");
		List<WordVideo> videos = (List<WordVideo>)request.getAttribute("videos");
	%>
	
<script type="text/javascript">
	function updateBar(){
		var width = (<%=current%> +1) / <%= total%> * 100;
		var elem = document.getElementById("myBar");
		elem.style.width = width + '%';
	}
	window.onload = updateBar;
	</script>
	<nav class="navbar navbar-inverse">
		 
		<div class="container-fluid">
			   
			<div class="navbar-header">
				      <a class="navbar-brand"
					href="${pageContext.request.contextPath }/student">ATL</a>    
			</div>
			   
			<!-- <button class="w3-button w3-pink">Grades</button>
			<button class="w3-button w3-blue">Exams</button>
			<button class="w3-button w3-green">Lessons</button> -->
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
	<h3>Take exam: <%out.print(exam.getName()); %></h3>
	<h4><%out.print(exam.getEdesc()); %></h2>
	<div id="myProgress">
		<div id="myBar"><div id = "barText"></div></div>
	</div>
	<h3>Current position: ${current+1 }, Total words: ${total}, Your answer for the current word: ${answer }</h3>
	<!-- 
	<c:forEach items="${words }" var="item" varStatus="counter">
			<button  class="w3-button w3-blue"><c:out value="${counter.index + 1}"></c:out></button>
		</c:forEach>
		 -->
	<hr>
	<h3>Watch these videos to help take your exam:</h3>
		<c:forEach items="${videos }" var="item" varStatus="counter">
			<video height="30%" width="30%" controls="controls">
				<source src="${item.path}" type="video/ogg" />
				<source src="${item.path}" type="video/mp4" />
				Your browser does not support the video tag.
			</video>
		</c:forEach>
		<br>
		<hr>

		
		<script type="text/javascript">
			function playA() {
				var audio = document.getElementById("playaudio");
				//alert(audio.src);
				audio.play();
			}
		</script>
	
		 Listen to the word:
		 <audio hidden="true" id="playaudio" src="${word.getPath() }"
				controls="controls">
			</audio>
		<button onclick="playA()"
				class="w3-button w3-circle w3-teal  w3-small">
				<span class="glyphicon glyphicon-play-circle"></span>
		</button>
		<br>
		<c:choose>
    		<c:when test="${exam.etype == 0}">
        		<%@include file="public/keyboard1.jsp" %>
    		</c:when>    
    		<c:otherwise>
        		 <%@include file="public/keyboard2.jsp" %>
    		</c:otherwise>
	</c:choose>
		<br>
			<form action="${pageContext.request.contextPath }/student?method=saveanswer&current=${current }&eid=<%out.print(exam.getEid()); %>&wid=<%out.print(word.getFid()); %>" method="post">
			<div class="form-group">
				
				<div class="col-sm-9">
					<input type="text" class="form-control" id="ta"
						placeholder="Enter your answer by clicking the keyboard above." name="studentanswer" readonly="true">
				</div>
				<div class="col-sm-3">
					<button 
						class="w3-button w3-teal w3-round-large w3-center">Save</button>
				</div>
			</div>
			</form>
			<div style="margin-top: 50px;" class="form-group">
				<div class="col-sm-6"> 
					<button <%if(current==0){ %>style="display:none"<% }%> class="w3-button w3-teal w3-round-large w3-center arrow right" 
						onclick="window.location.href='${pageContext.request.contextPath }/student?method=takeexamspe&current=${current-1 }&eid=<%out.print(exam.getEid()); %>'">Previous</button>
				</div>
				<div id="next" class="col-sm-6">
					<button onclick="window.location.href='${pageContext.request.contextPath }/student?method=takeexamspe&current=${current+1 }&eid=<%out.print(exam.getEid()); %>'"
					<%if(current==total-1){ %>style="display:none"<% }%>	class="w3-button w3-teal w3-round-large w3-center">Next</button>
					<button 
					onclick="window.location.href='${pageContext.request.contextPath }/student?method=submitresult&eid=<%out.print(exam.getEid()); %>'"
					<%if(current!=total-1){ %>style="display:none"<% }%>	class="w3-button w3-teal w3-round-large w3-center">Submit</button>
				</div>
			</div>
	</div>
	
</body>
</html>