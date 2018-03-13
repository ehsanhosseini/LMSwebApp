<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.BookLoans"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="Bootstrap2.html" %>
<%
AdminService adminService = new AdminService();

List<Author> authors = new ArrayList<>();
if (request.getAttribute("authors") != null) {
	authors = (List<Author>) request.getAttribute("authors");
} else {
	authors = adminService.getAuthors(null, 1);
}
Integer totalAuthors = adminService.getAuthorsCount();
int pageSize = (int) Math.ceil(totalAuthors / 10 + 1);

%>


 <div class="jumbotron">
<h1>Welcome to GCIT Library Management System</h1>
	<h2>List of Authors in LMS</h2>
	
	${ message }
	
	<nav aria-label="Page navigation">
  <ul class="pagination">
    <li><a href="#" aria-label="Previous"> <span
					aria-hidden="true">&laquo;</span>
			</a></li>
			<%
				for (int i = 1; i <= pageSize; i++) {
			%>
			<li><a href="pageAuthors?pageNo=<%=i%>"><%=i%></a></li>
			<%
				}
			%>
			<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
  </ul>
</nav>
	
	<table class="table table-striped">
		<tr>
			<th>Author ID</th>
			<th>Author Name</th>
			<th>Books Written</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<%for(Author a: authors){ %>
			<tr>
				<td><%out.println(authors.indexOf(a)+1); %></td>
				<td><%=a.getAuthorName() %></td>
				<td><%for(Book b: a.getBooks()){
					out.println(b.getTitle()+" | ");
				}%>
				</td>
				<td><button class="btn btn-warning" onclick="javascript:location.href='editAuthor?authorId=<%=a.getAuthorId()%>'">Edit</button></td>
				<td><button class="btn btn-danger" onclick="javascript:location.href='deleteAuthor?authorId=<%=a.getAuthorId()%>'">Delete</button></td>
			</tr>
		<%} %>
	</table>

</div>
