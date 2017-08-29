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
</style>
 
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
 
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
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
		var undo = "";
		function copyText(value) {
			var str1 = unescape(value.replace(/\\(u[0-9a-fA-F]{4})/gm, '%$1'));
			var next = document.getElementById("ta").value;
			next += str1;
			document.getElementById("ta").value = next;
		}
		function backspace() {
			//document.getElementById("getSymble").value="";
			var text = document.getElementById("ta").value;
			text = text.slice(0, -1);
			document.getElementById("ta").value = text;
		}
		function clean() {
			document.getElementById("ta").value = "";
		}
		
		
		function save() {
			var select = document.getElementById("select");
			var index = select.selectedIndex ;  
			var showIndex = "answer" + index;
			var showLabel = document.getElementById(showIndex);
			var ans = document.getElementById("ta");
			showLabel.innerHTML = ans.value;
		}
		
		
		function submitResult(number) {
	       var arr = "";
	       for (var i = 0; i < number; i++) {  
	        	var showIndex = "answer" + i;
				var showLabel = document.getElementById(showIndex);
				arr += showLabel.innerHTML + "/";
	        } 
	       var url = document.getElementById("sub").value;
	       //url += "&result="+arr;
	       /* alert(result); */
	      	/* alert(arr); */
			var myForm = document.createElement("form");  
	        myForm.method = "post";  
	        myForm.action = url;  
	        
	        var myInput = document.createElement("input");  
	        myInput.name = "result";
	        myInput.value = arr;
	        myForm.appendChild(myInput);  
	        document.body.appendChild(myForm);  
	        myForm.submit();  
	        return myForm;
		}
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
	<h3>Total words: ${total}, current position: ${current+1 }, Your answer of current word: ${answer }</h3>
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
		<div class="row w3-center">

			<button class="btn btn btn-default" onclick="copyText(value)"
						value="p" style="width: 30px; height: 30px;">p</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="t" style="width: 30px; height: 30px;">t</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="k" style="width: 30px; height: 30px;">k</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="b" style="width: 30px; height: 30px;">b</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="d" style="width: 30px; height: 30px;">d</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="ɡ" style="width: 30px; height: 30px;">ɡ</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="t͡ʃ" style="width: 30px; height: 30px;">t͡ʃ</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="d͡ʒ" style="width: 30px; height: 30px;">d͡ʒ</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="s" style="width: 30px; height: 30px;">s</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="ʃ" style="width: 30px; height: 30px;">ʃ</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="z" style="width: 30px; height: 30px;">z</button>
					<br>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="ʒ" style="width: 30px; height: 30px;">ʒ</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="f" style="width: 30px; height: 30px;">f</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="θ" style="width: 30px; height: 30px;">θ</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="v" style="width: 30px; height: 30px;">v</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="ð" style="width: 30px; height: 30px;">ð</button>

					<button class="btn btn btn-default" onclick="copyText(value)"
						value="h" style="width: 30px; height: 30px;">h</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="n" style="width: 30px; height: 30px;">n</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="m" style="width: 30px; height: 30px;">m</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="ŋ" style="width: 30px; height: 30px;">ŋ</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="ʔ" style="width: 30px; height: 30px;">ʔ</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="l" style="width: 30px; height: 30px;">l</button>
					<br>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="r" style="width: 30px; height: 30px;">r</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="w" style="width: 30px; height: 30px;">w</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="j" style="width: 30px; height: 30px;">j</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="ɾ" style="width: 30px; height: 30px;">ɾ</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="ɫ" style="width: 30px; height: 30px;">ɫ</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="i" style="width: 30px; height: 30px;">i</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="ɪ" style="width: 30px; height: 30px;">ɪ</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="ɛ" style="width: 30px; height: 30px;">ɛ</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="e͡ɪ" style="width: 30px; height: 30px;">e͡ɪ</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="æ" style="width: 30px; height: 30px;">æ</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="ɑ" style="width: 30px; height: 30px;">ɑ</button>
					<br>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="ɑ͡u" style="width: 30px; height: 30px;">ɑ͡u</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="ɑ͡ɪ" style="width: 30px; height: 30px;">ɑ͡ɪ</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="ʌ" style="width: 30px; height: 30px;">ʌ</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="ɔ" style="width: 30px; height: 30px;">ɔ</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="ɔ͡ɪ" style="width: 30px; height: 30px;">ɔ͡ɪ</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="o͡ʊ" style="width: 30px; height: 30px;">o͡ʊ</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="ʊ" style="width: 30px; height: 30px;">ʊ</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="u" style="width: 30px; height: 30px;">u</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="ɝ" style="width: 30px; height: 30px;">ɝ</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="ə" style="width: 30px; height: 30px;">ə</button>
					<button class="btn btn btn-default" onclick="copyText(value)"
						value="ɚ" style="width: 30px; height: 30px;">ɚ</button>
		</div>
		<br>
			<form action="${pageContext.request.contextPath }/student?method=saveanswer&current=${current }&eid=<%out.print(exam.getEid()); %>&wid=<%out.print(word.getFid()); %>" method="post">
			<div class="form-group">
				
				<div class="col-sm-9">
					<input type="text" class="form-control" id="ta"
						placeholder="Enter your answer by clicking the keyboard above." name="studentanswer">
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
						onclick="window.location.href='${pageContext.request.contextPath }/student?method=takeexamspe&current=${current-1 }&eid=<%out.print(exam.getEid()); %>'">Last</button>
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