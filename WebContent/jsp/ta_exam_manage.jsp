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
					href="${pageContext.request.contextPath }/student">ALT</a>     
			</div>
			
			<button
				onclick="window.location.href='${pageContext.request.contextPath }/student'"
				class="w3-button w3-blue">Main Page</button>
				<button
				onclick="window.location.href='${pageContext.request.contextPath }/student?method=tme&lid=<% out.print(l.getLid());%>'"
				class="w3-button w3-blue">Return Exam</button>
			<button
				onclick="window.location.href='${pageContext.request.contextPath }/student?method=lessons&lid=<% out.print(l.getLid());%>'"
				class="w3-button w3-pink">Lesson List</button>
 			<!-- 
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
				out.print(e.getName());
			%>
		</h1>

		<p>
			<%
				out.print(e.getEdesc());
			%>
		</p>
		<hr>
		<div style="border: 1px solid #000; padding: 20px;">
			<p>Videos</p>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>File (MP4)</th>
						<th>File Describe</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${videos }" var="item" varStatus="counter">
						<tr>
							<td>${item.name }</td>
							<td>${item.desc }</td>
							<td><a
								href="${pageContext.request.contextPath }/student?method=deletev&vid=${item.vid}&eid=<% out.print(e.getEid());%>&lid=<% out.print(l.getLid());%>">x</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="wells">
				<p>Upload video. Only support for MP4.</p>
				<form class="form-horizontal" name="l_file"
					action="${pageContext.request.contextPath }/student?method=uploadv&eid=<% out.print(e.getEid());%>&lid=<% out.print(l.getLid());%>"
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
							<input type="file" id="upload" name="filename">
						</div>

					</div>


					<div class="w3-center">
						<br> <input class="w3-button w3-circle w3-green"
							type="submit" value="+" />
					</div>

				</form>
			</div>
		</div>


		<div style="border: 1px solid #333; margin-top:5px; padding:20px;">
			<p>Words</p>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Word (MP4)</th>
						<th>Word Pronunciation</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${words }" var="item" varStatus="counter">
						<tr>
							<td>${item.name }</td>
							<td>${item.pron }</td>
							<td><a
								href="${pageContext.request.contextPath }/student?method=deletew&vid=${item.fid}&eid=<% out.print(e.getEid());%>&lid=<% out.print(l.getLid());%>">x</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="wells">
			<br>
				<p>Type in the IPA letter and upload the pronunciation file,
					only support WAV.</p>



				<script type="text/javascript">
					var undo = "";
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
						text = text.slice(0, -1);
						document.getElementById("ta").value = text;
					}
					function clean() {
						document.getElementById("ta").value = "";
					}
				</script>

				<button class="btn btn btn-default" onclick="copyText(value)"
					value="ɪ" style="width: 30px; height: 30px;">ɪ</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="ə" style="width: 30px; height: 30px;">ə</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="ɑ" style="width: 30px; height: 30px;">ɑ</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="ʊ" style="width: 30px; height: 30px;">ʊ</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="ʌ" style="width: 30px; height: 30px;">ʌ</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="ɛ" style="width: 30px; height: 30px;">ɛ</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="æ" style="width: 30px; height: 30px;">æ</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="i" style="width: 30px; height: 30px;">i</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="ɜ" style="width: 30px; height: 30px;">ɜ</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="ɔ" style="width: 30px; height: 30px;">ɔ</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="u" style="width: 30px; height: 30px;">u</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="ɑ" style="width: 30px; height: 30px;">ɑ</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="e" style="width: 30px; height: 30px;">e</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="aɪ" style="width: 30px; height: 30px;">aɪ</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="ɔɪ" style="width: 30px; height: 30px;">ɔɪ</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="aʊ" style="width: 30px; height: 30px;">aʊ</button>
				<br>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="o" style="width: 30px; height: 30px;">o</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="ɪr" style="width: 30px; height: 30px;">ɪr</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="ɛr" style="width: 30px; height: 30px;">ɛr</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="ʊr" style="width: 30px; height: 30px;">ʊr</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="p" style="width: 30px; height: 30px;">p</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="t" style="width: 30px; height: 30px;">t</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="k" style="width: 30px; height: 30px;">k</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="f" style="width: 30px; height: 30px;">f</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="θ" style="width: 30px; height: 30px;">θ</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="s" style="width: 30px; height: 30px;">s</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="ʃ" style="width: 30px; height: 30px;">ʃ</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="tʃ" style="width: 30px; height: 30px;">tʃ</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="b" style="width: 30px; height: 30px;">b</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="d" style="width: 30px; height: 30px;">d</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="ɡ" style="width: 30px; height: 30px;">ɡ</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="v" style="width: 30px; height: 30px;">v</button>
				<br>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="ð" style="width: 30px; height: 30px;">ð</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="z" style="width: 30px; height: 30px;">z</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="ʒ" style="width: 30px; height: 30px;">ʒ</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="dʒ" style="width: 30px; height: 30px;">dʒ</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="h" style="width: 30px; height: 30px;">h</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="m" style="width: 30px; height: 30px;">m</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="n" style="width: 30px; height: 30px;">n</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="ŋ" style="width: 30px; height: 30px;">ŋ</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="l" style="width: 30px; height: 30px;">l</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="r" style="width: 30px; height: 30px;">r</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="j" style="width: 30px; height: 30px;">j</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="w" style="width: 30px; height: 30px;">w</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="dr" style="width: 30px; height: 30px;">dr</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="dz" style="width: 30px; height: 30px;">dz</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="tr" style="width: 30px; height: 30px;">tr</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="ts" style="width: 30px; height: 30px;">ts</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="ˈ" style="width: 30px; height: 30px;">ˈ</button>
				<button class="btn btn btn-default" onclick="copyText(value)"
					value="ˌ" style="width: 30px; height: 30px;">ˌ</button>
			</div>










			<br>
			<form class="form-horizontal" name="l_file"
				action="${pageContext.request.contextPath }/student?method=uploadw&eid=<% out.print(e.getEid());%>&lid=<% out.print(l.getLid());%>"
				method="post" enctype="multipart/form-data">
				<div class="form-group">

					<div class="row w3-center">
						<div class="col-sm-10">
							<input type="text" class="form-control" id="ta"
								placeholder="Please input the ipa letters" name="ipa">
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