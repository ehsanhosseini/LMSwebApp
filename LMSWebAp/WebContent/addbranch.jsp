<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.BookLoans"%>

<%
AdminService adminService = new AdminService();
List<Branch> branches = adminService.getBranches(null);

%>

<%@include file="Bootstrap2.html" %>

 <div class="jumbotron">

<h1>Welcome to GCIT Library Management System</h1>
	<h2>Hello Admin: Enter details to add a new Branch</h2>
	<form action="addBranch" method="post">
		Add New Branch: <input type="text" name="branchName" maxlength="45"><br />
		Add New Address: <input type="text" name="address" maxlength="45"><br />
		<button type="submit">Save</button>
	</form>

</div>