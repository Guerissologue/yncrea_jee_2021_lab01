<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Authentication</title>
</head>
<body>
	<h1>Welcome, please login</h1>
	<%
        if(request.getAttribute("loginError")!=null) {
            out.print((String)request.getAttribute("loginError"));
        }else{
        	out.print("You have to authenticate yourself");
        }                  
    %>
	<form method="post" action="login">
		<table>
			<tr>
				<td>Your login :</td>
				<td><input type="text" name="login" /></td>
			</tr>
			<tr>
				<td>Your password :</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="login" /></td>
			</tr>
		</table>

	</form>

	

</body>
</html>
