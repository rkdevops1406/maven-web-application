<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>D2SOL INC- Home Page</title>
<link href="images/mithunlogo.jpg" rel="icon">
</head>
</head>
<body>
<h1 align="center">Welcome to D2SOL INC. Ph No: (919) 338-5553  MORRISVILLE,NORTH CAROLINA,USA </h1>
<h1 align="center"> D2SOL INC- Your goals are our goals & we look forward to helping you reach new heights.</h1>
<hr>
<br>
	<h1><h3> Server Side IP Address </h3><br>

<% 
String ip = "";
InetAddress inetAddress = InetAddress.getLocalHost();
ip = inetAddress.getHostAddress();
out.println("Server Host Name :: "+inetAddress.getHostName()); 
%>
<br>
<%out.println("Server IP Address :: "+ip);%>
</h1>
<br>
<h1><h3> Client Side IP Address </h3><br>
<%out.print( "Client IP Address :: " + request.getRemoteAddr() ); %><br>
<%out.print( "Client Name Host :: "+ request.getRemoteHost() );%><br></h1>
<hr>
<div style="text-align: center;">
	<span>
		<img src="images/mithunlogo.jpg" alt="" width="400">
	</span>
	<span style="font-weight: bold;">
		D2SOL INC, 
		Morrisville,
		North Carolina,
		(919) 338-5553,
		Info@gmail.com
		<br>
		<a href="mailto:Info@d2sol.com">Mail to D2SOL INC</a>
	</span>
</div>
<hr>
	<p> Service : <a href="services/employee/getEmployeeDetails">Get Employee Details </p>
<hr>
<hr>
<p align=center>D2SOL - INC, At D2Sol, we believe confidence comes from knowing more about the team handling your technology services.</p>
<p align=center><small>Copyrights 2022 by <a href="http://D2SOL.com/">D2SOL,North Carolina</a> </small></p>

</body>
</html>
