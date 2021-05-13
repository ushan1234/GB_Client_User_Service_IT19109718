<%@page import="com.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/User.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>User Management Service</h1>
<form id="formItem" name="formItem">
 First Name: 
 <input id="fname" name="fname" type="text" 
 class="form-control form-control-sm">
 <br> Last Name: 
 <input id="lname" name="lname" type="text" 
 class="form-control form-control-sm">
 <br> Age: 
 <input id="age" name="age" type="text" 
 class="form-control form-control-sm">
 <br> Phone Number: 
 <input id="pnumber" name="pnumber" type="text" 
 class="form-control form-control-sm">
 <br>Address: 
 <input id="address" name="address" type="text" 
 class="form-control form-control-sm">
 <br> Email: 
 <input id="email" name="email" type="text" 
 class="form-control form-control-sm">
 <br> Password: 
 <input id="password" name="password" type="password" 
 class="form-control form-control-sm">
 <br> Category: 
 <input id="category" name="category" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave" 
 name="hidItemIDSave" value="">
</form>
<br>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
	<%
 		User itemObj = new User(); 
 		out.print(itemObj.readUser()); 
 	%>
</div>
</div> </div> </div> 
</body>
</html>