<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"> 
	<style>
	body{
		background-image:url("/jsp/gray.jpg");
	}
	</style>
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="text-align: center;">
	<nav class="navbar navbar-inverse">
  		<div class="container-fluid">
    			<div class="navbar-header">
      				<a class="navbar-brand" href="#">ALT</a>
    			</div>
  		</div>
	</nav>
	<div class="w3-container" >
		<div class="row">
		<div class="col-lg-1 w3-gray w3-round-large" ></div>
			<div class="col-lg-5 w3-gray w3-round-large"  >
				<h1 class="w3-center">Login</h1>
				<br>
				<form class="form-horizontal" action="${pageContext.request.contextPath }/user?method=login" method="post">
    					<div class="form-group">
      						<label class="control-label col-sm-2" for="email">Email:</label>
      						<div class="col-sm-10">
        						<input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
      						</div>
    					</div>
					<br>
    					<div class="form-group">
      						<label class="control-label col-sm-2" for="pwd">Password:</label>
      						<div class="col-sm-10">          
        						<input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
      						</div>
    					</div>
					<br>
    					<div class="form-group">        
      						<div class="col-sm-offset-2 col-sm-10">
        						<button type="submit" class="btn btn-default">Submit</button>
							<a href="${pageContext.request.contextPath }/user?method=gotoreset">Forgot Password?</a>
      						</div>
    					</div>
				<br>
				<br>
  				</form>
			</div>
			<div class="col-lg-5 w3-blue w3-round-large" >	
				<h1 class="w3-center">Register</h1>
				<p>Please register with AU mail to make it successfully.</p>
				<br>
				<form class="form-horizontal" action="${pageContext.request.contextPath }/user?method=register" method="post">
    					<div class="form-group">
      						<label class="control-label col-sm-2" for="email">Email:</label>
      						<div class="col-sm-10">
        						<input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
      						</div>
    					</div>
    					<div class="form-group">
      						<label class="control-label col-sm-2" for="pwd">Password:</label>
      						<div class="col-sm-10">          
        						<input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
      						</div>
    					</div>
					<div class="form-group">
      						<label class="control-label col-sm-2" for="pwd">Confirm:</label>
      						<div class="col-sm-10">          
        						<input type="password" class="form-control" id="pwd" placeholder="Enter password" name="confirm">
      						</div>
    					</div>
					<div style="display: none;" class="form-group">
      						<label class="control-label col-sm-2" for="pwd">Username:</label>
      						<div class="col-sm-10">          
        						<input type="username" class="form-control" id="username" placeholder="Enter Username" name="username">
      						</div>
    					</div>
    					<div class="form-group">        
      						<div class="col-sm-offset-2 col-sm-10">
        						<button type="submit" class="btn btn-default">Register</button>
      						</div>
    					</div>
  				</form>
			</div>
			<div class="col-lg-1 w3-gray w3-round-large" ></div>
			
		</div>
		<br>
		<h2>Please use Google Chrome browser.</h2>
	</div>
</body>
</html>