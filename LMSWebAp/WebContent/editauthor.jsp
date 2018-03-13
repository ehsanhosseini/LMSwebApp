
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%Author author = (Author)request.getAttribute("author"); %>    
<%@include file="Bootstrap2.html" %>

 <div class="jumbotron">

<h1>Welcome to GCIT Library Management System</h1>
	<h2>Hello Admin: Enter details to edit a Author</h2>
	<form action="addAuthor" method="post">
		Author Name: <input type="text" name="authorName" maxlength="45" value="<%=author.getAuthorName()%>"><br />
		<input type="hidden" name="authorId" value="<%=author.getAuthorId() %>">
		<button type="submit">Save</button>
	</form>
</div>