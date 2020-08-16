<%@page import="yncrea.lab01.model.*, java.util.List" contentType="text/html" pageEncoding="UTF-8"%>
<%! List<Drug> drugs; %>
<%drugs = (List<Drug>) request.getAttribute("drugs"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Drugs management</title>
</head>
<body>
	<h1>Drugs management</h1>
	<table>
		<tr>
			<th>Name</th>
			<th>Lab</th>
		</tr>
		<% for(Drug currentDrug : drugs){ %>
		<tr>
			<td><%=currentDrug.getName() %></td>
			<td><%=currentDrug.getLab() %></td>
		</tr>
		<%} %>
	</table>
	<hr />
	<h2>Add a new drug</h2>
	<form method="post" action="drugs">
		<p><label>Name</label><input type="text" name="name" /></p>
		<p><label>Lab</label> <input type="text" name="lab" /></p>
		<p><input type="submit" value="submit" /></p>
	</form>
	<hr />
	<a href="login?logout">logout</a>
</body>
</html>
