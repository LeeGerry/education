<%@ page language="java" import="edu.auburn.domain.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<h1 class="w3-center">${email }</h1>
		<form class="form-horizontal"
			action="${pageContext.request.contextPath }/user?method=reset"
			method="post">
			<div class="form-group">
				<label class="control-label col-sm-2" for="email">Code:</label>
				<input hidden="true" type="text" class="form-control" id="mail"
						value="${email }" name="mail">
				<div class="col-sm-3">
					<input type="text" class="form-control" id="code"
						placeholder="The code you received in your email" name="code">
				</div>
			</div>
			

			<div class="form-group">
				<label class="control-label col-sm-2" for="email">New Password:</label>
				<div class="col-sm-3">
					<input type="password" class="form-control" id="pwd1"
						placeholder="Set your new password" name="pwd">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="email">Confirm:</label>
				<div class="col-sm-3">
					<input type="password" class="form-control" id="pwd2"
						placeholder="Confirm your new password" name="confirm">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-3">
					<button type="submit" class="btn btn-default">Reset</button>
				</div>
			</div>
		</form>
		
		
		
		
	</div>

</body>
</html>