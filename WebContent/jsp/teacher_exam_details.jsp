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
								href="${pageContext.request.contextPath }/teacher?method=delv&vid=${item.vid}&eid=<% out.print(e.getEid());%>&lid=<% out.print(l.getLid());%>">x</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="wells">
				<p>Upload video. Only support for MP4.</p>
				<form class="form-horizontal" name="l_file"
					action="${pageContext.request.contextPath }/teacher?method=addvideo&eid=<% out.print(e.getEid());%>&lid=<% out.print(l.getLid());%>"
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
								href="${pageContext.request.contextPath }/teacher?method=delw&vid=${item.fid}&eid=<% out.print(e.getEid());%>&lid=<% out.print(l.getLid());%>">x</a></td>
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

				<button class="btn btn btn-default" onclick="copyText(value)"
						value="p" style="width: 40px; height: 40px;">p</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="t" style="width: 40px; height: 40px;">t</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="k" style="width: 40px; height: 40px;">k</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="b" style="width: 40px; height: 40px;">b</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="d" style="width: 40px; height: 40px;">d</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="ɡ" style="width: 40px; height: 40px;">ɡ</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="t͡ʃ" style="width: 40px; height: 40px;">t͡ʃ</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="d͡ʒ" style="width: 40px; height: 40px;">d͡ʒ</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="s" style="width: 40px; height: 40px;">s</button>
					<br>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="ʃ" style="width: 40px; height: 40px;">ʃ</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="z" style="width: 40px; height: 40px;">z</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="ʒ" style="width: 40px; height: 40px;">ʒ</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="f" style="width: 40px; height: 40px;">f</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="θ" style="width: 40px; height: 40px;">θ</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="v" style="width: 40px; height: 40px;">v</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="ð" style="width: 40px; height: 40px;">ð</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="h" style="width: 40px; height: 40px;">h</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="n" style="width: 40px; height: 40px;">n</button>
					<br>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="m" style="width: 40px; height: 40px;">m</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="ŋ" style="width: 40px; height: 40px;">ŋ</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="ʔ" style="width: 40px; height: 40px;">ʔ</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="l" style="width: 40px; height: 40px;">l</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="r" style="width: 40px; height: 40px;">r</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="w" style="width: 40px; height: 40px;">w</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="j" style="width: 40px; height: 40px;">j</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="ɾ" style="width: 40px; height: 40px;">ɾ</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="ɫ" style="width: 40px; height: 40px;">ɫ</button>
					<br>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="i" style="width: 40px; height: 40px;">i</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="ɪ" style="width: 40px; height: 40px;">ɪ</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="ɛ" style="width: 40px; height: 40px;">ɛ</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="e͡ɪ" style="width: 40px; height: 40px;">e͡ɪ</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="æ" style="width: 40px; height: 40px;">æ</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="ɑ" style="width: 40px; height: 40px;">ɑ</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="ɑ͡u" style="width: 40px; height: 40px;">ɑ͡u</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="ɑ͡ɪ" style="width: 40px; height: 40px;">ɑ͡ɪ</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="ʌ" style="width: 40px; height: 40px;">ʌ</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="ɔ" style="width: 40px; height: 40px;">ɔ</button>
					<br>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="ɔ͡ɪ" style="width: 40px; height: 40px;">ɔ͡ɪ</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="o͡ʊ" style="width: 40px; height: 40px;">o͡ʊ</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="ʊ" style="width: 40px; height: 40px;">ʊ</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="u" style="width: 40px; height: 40px;">u</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="ɝ" style="width: 40px; height: 40px;">ɝ</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="ə" style="width: 40px; height: 40px;">ə</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="ɚ" style="width: 40px; height: 40px;">ɚ</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="n̩" style="width: 40px; height: 40px;">n̩</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="m̩" style="width: 40px; height: 40px;">m̩</button>
					<button class="btn btn btn-default" onclick="copyText(value);setkeyLen(value);"
						value="l̩" style="width: 40px; height: 40px;">l̩</button>
					<br>
					<button class="btn btn btn-default" onclick="backspace()"
					style="width: 100px; height: 40px;">Backspace</button>
					<button class="btn btn btn-default" onclick="clean()"
					style="width: 100px; height: 40px;">Clear</button>
			</div>










			<br>
			<form class="form-horizontal" name="l_file"
				action="${pageContext.request.contextPath }/teacher?method=addword&eid=<% out.print(e.getEid());%>&lid=<% out.print(l.getLid());%>"
				method="post" enctype="multipart/form-data">
				<div class="form-group">

					<div class="row w3-center">
						<div class="col-sm-10">
							<input type="text" class="form-control" id="ta"
								placeholder="Please input the ipa letters" name="ipa"  readonly="true">
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