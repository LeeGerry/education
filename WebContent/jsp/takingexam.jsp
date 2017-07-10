<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<h3>Watch these videos to help take your exam:</h3>
		<video height="30%" width="30%" controls="controls">
			<source src="/i/movie.ogg" type="video/ogg" />
			<source
				src="http://192.168.1.104:8080/education/upload/lesson111/spring.mp4"
				type="video/mp4" />
			Your browser does not support the video tag.
		</video>
		<br> 
		<hr>
		<h3>Take exam</h3>
		
		<div class="row">
				<button class="w3-button w3-pink">Prev</button>
			
			<audio  id="mp3" src="basketball.wav"
				controls="controls">
			</audio>
			<script type="text/javascript">
				/* var m = {"basketball.wav","cheese.wav","gate.wav"}; */
				var mycars = new Array(3)
				mycars[0] = "basketball.wav";
				mycars[1] = "cheese.wav";
				mycars[2] = "gate.wav";
				function play() {
					var audio = document.getElementById("mp3");
					audio.src = mycars[1];
					audio.play();
				}
			</script>
			<button onclick="play()" class="w3-button w3-blue">Next</button>
			
		</div>
		<br>
		<div class="row w3-center">

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
		<div class="row w3-center">
			<form class="form-horizontal" action="#" method="post">
				<div class="form-group">
					<label class="control-label col-sm-2" for="email"></label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="ta"
							placeholder="Enter Answer" name="studentanswer">

					</div>
				</div>
				<button class="w3-button w3-teal w3-round-large w3-center"
					onclick="clean()">Clear</button>
				<button type="submit"
					class="w3-button w3-teal w3-round-large w3-center">Submit</button>
			</form>

		</div>
	</div>
</body>
</html>