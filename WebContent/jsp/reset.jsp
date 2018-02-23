<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Login</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
 
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
body {
	background-image: url("/jsp/gray.jpg");
}
</style>
 
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
 
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="text-align: center;">
	<nav class="navbar navbar-inverse">
		 
		<div class="container-fluid">
			   
			<div class="navbar-header">
				      <a class="navbar-brand" href="#">ALT</a>    
			</div>
			 
		</div>
	</nav>
	<div class="w3-container">
		<h1 class="w3-center">Find password</h1>
		<form class="form-horizontal"
			action="${pageContext.request.contextPath }/user?method=send"
			method="post">
			<div class="form-group">
				<label class="control-label col-sm-2" for="email">Email:</label>
				<div class="col-sm-3">
					<input type="email" class="form-control" id="email"
						placeholder="Enter email" name="email">
						
				</div>
				<button type="submit" class="btn btn-default">Send code to
					email</button>
			</div>
			<div class="col-sm-offset-2 col-sm-10">
				
			</div>
		</form>
	</div>

</body>
</html>