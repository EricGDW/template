<%@ page contentType="text/html; charset=UTF-8" isErrorPage="true" pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>exception page</title>
</head>
<body>
<hr/>
<pre>
<%
response.getWriter().println("Exception: " + exception); 

if(exception != null)
{
   response.getWriter().println("<pre>"); 
   exception.printStackTrace(response.getWriter()); 
   response.getWriter().println("</pre>"); 
}

response.getWriter().println("<hr/>"); 
%>
</body>
</html>