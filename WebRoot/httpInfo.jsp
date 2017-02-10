<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	
	
	<table align="left">
  <tr>
    <th>Head Name</th>
    <th>Head Value</th>
  </tr>

<%
	Enumeration e = request.getHeaderNames();
	while(e.hasMoreElements()) {
		String name = e.nextElement().toString();
		String v = request.getHeader(name);
		out.print("<tr><td>" + name + "</td>\n");
		out.print("<td>" + v + "</td></tr>\n");
	}
%>
</table>