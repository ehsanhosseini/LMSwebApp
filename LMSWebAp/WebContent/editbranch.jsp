<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="com.gcit.lms.entity.Branch"%>
<%Branch branch = (Branch)request.getAttribute("branch"); %>    
    

<%@include file="Bootstrap2.html" %>

 <div class="jumbotron">
<h1>Welcome to GCIT Library Management System</h1>
	<h2>Hello Admin: Enter details to edit a Branch</h2>
	<form action="editBranch" method="post">
	
		Branch Name: <input type="text" name="branchName" maxlength="45" value="<%=branch.getBranchName()%>"><br />
		Address: <input type="text" name="address" maxlength="45" value="<%=branch.getAddress()%>"><br />
		<input type="hidden" name="branchId" value="<%=branch.getBranchId() %>">
		<button type="submit">Save</button>
	
	</form>
</div>