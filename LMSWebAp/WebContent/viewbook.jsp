<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.entity.Genre"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="Bootstrap2.html" %>
<%
AdminService adminService = new AdminService();
List<Book> books = adminService.getBooks(null);

%>



 <div class="jumbotron">
<h1>Welcome to GCIT Library Management System</h1>
	<h2>List of Books in LMS</h2>
	${ message }
	<table class="table table-striped">
		<tr>
			<th>Book ID</th>
			<th>Book Title</th>
			<th>Author Name</th>
			
			
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<%for(Book a: books){ %>
			<tr>
				<td><%out.println(books.indexOf(a)+1); %></td>
				<td><%=a.getTitle()+" | " %></td>
									
				<td><%for(Author b: a.getAuthors()){
					out.println(b.getAuthorName()+" | ");
				}%>
				</td>
				
				<td><button class="btn btn-warning" onclick="javascript:location.href='editBook?bookId=<%=a.getBookId()%>'">Edit</button></td>
				<td><button class="btn btn-danger" onclick="javascript:location.href='deleteBook?bookId=<%=a.getBookId()%>'">Delete</button></td>
			
			
			</tr>
		<%} %>
	</table>
</div>
