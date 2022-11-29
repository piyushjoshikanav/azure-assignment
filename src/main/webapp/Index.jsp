<%@page import="com.nagarro.training.model.AllBooks"%>
<%@page import="com.nagarro.training.model.BooksModel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%
if (request.getSession() == null) {
	response.sendRedirect("login.jsp");
}

String str = (String) session.getAttribute("user");
%>
<link rel="stylesheet" href="/style/Index.css">
</head>

<body>
	<div id="header">
		<label>welcome <%=str%></label>
		<button>
			<a href="/logout">Logout</a>
		</button>
	</div>
	<div id="add">
		<div>
			<span></span>
		</div>
		<div>
			<h1>Books Listing</h1>
		</div>
		<div>
			<form action=add method="get">
				<input id="btn" type=submit value=add>
			</form>
		</div>
	</div>

	<div id="form">
		<table>
			<tr>
				<th width="10%">Book Code</th>
				<th width="20%">Book Name</th>
				<th width="20%">Author</th>
				<th width="10%">Data Added</th>
				<th width="20%">Actions</th>
			</tr>
			<%
			AllBooks pr = (AllBooks) request.getAttribute("result");
			List<BooksModel> bm = pr.getBooks();
			for (BooksModel m : bm) {
			%>
			<tr>
				<td><%=m.getBookCode()%></td>
				<td><%=m.getbName()%></td>
				<td><%=m.getAuthor()%></td>
				<td><%=m.printDate()%></td>
				<td>
					<button>
						<a href="/delete?Id=<%=m.getBookCode()%>">delete</a>
					</button>
					<button>
						<a href="/edit?Id=<%=m.getBookCode()%>">Edit</a>
					</button>
				</td>
			</tr>
			<%
			}
			%>
		</table>
	</div>

</body>
</html>