<%@page import= "com.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
<link rel="stylesheet" href="Views/boostrap.min.css">
<script src="Component/jQuery-3.2.1.min.js"></script>
<script src="Component/users.js"></script>
</head>
<body>

<div class="container"><div class="row"><div class="col-6">

<h1>User Management </h1>
<form id="formUser" name="formUser" method="post" action="users.jsp">
 First Name: 
<input id="firstName" name="firstName" type="text" 
 class="form-control form-control-sm">
<br> Last Name: 
<input id="lastName" name="lastName" type="text" 
 class="form-control form-control-sm">
<br> Email: 
<input id="email" name="email" type="text" 
 class="form-control form-control-sm">
<br> Gender: 
<input id="gender" name="gender" type="text" 
 class="form-control form-control-sm">
<br>
<input id="occupation" name="occupation" type="text" 
 class="form-control form-control-sm">
<br>
<input id="phone" name="phone" type="text" 
 class="form-control form-control-sm">
<br>
<input id="username" name="username" type="text" 
 class="form-control form-control-sm">
<br>
<input id="password" name="password" type="text" 
 class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
<input type="hidden" id="hiduIdSave" name="hiduIdSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>


<br>
<div id="divUsersGrid">

<%
 User userObj = new User(); 
 out.print(userObj.readUsers()); 
%>
</div>

</div></div></div>

</body>
</html>