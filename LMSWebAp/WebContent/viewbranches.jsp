<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.BookLoans"%>

<%
AdminService adminService = new AdminService();

List<Branch> branches = new ArrayList<>();
if (request.getAttribute("branches") != null) {
	branches = (List<Branch>) request.getAttribute("branches");
} else {
	branches = adminService.get
}
Integer totalBranches = adminService.getAuthorsCount();
int pageSize = (int) Math.ceil(totalBranches / 10 + 1);

%>

<%@include file="Bootstrap2.html" %>

 <div class="jumbotron">

<h1>Welcome to GCIT Library Management System</h1>
	<h2>List of Branches in LMS</h2>
	
	${ message }
	<table class="table table-striped">
		<tr>
			<th>Branch ID</th>
			<th>Branch Name</th>
			<th>Branch Address</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<%for(Branch a: branches){ %>
			<tr>
				<td><%out.println(branches.indexOf(a)+1); %></td>
				<td><%=a.getBranchName() %></td>
				<td><%=a.getAddress() %></td>
				
				<td><button class="btn btn-warning" onclick="javascript:location.href='editBranch?branchId=<%=a.getBranchId()%>'">Edit</button></td>
				<td><button class="btn btn-danger" onclick="javascript:location.href='deleteBranch?branchId=<%=a.getBranchId()%>'">Delete</button></td>
			</tr>
		<%} %>
	</table>

</div>