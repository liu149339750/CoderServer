<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    This is my JSP page. <br>
    <%
    	out.println(basePath); 
     %>
     
     <%!
     private int initCount;
     private int desCount;
     	public void jspInit() {
     		System.out.println("jspInit");
     		initCount ++;
     	}
     	public void jspDestroy() {
     		System.out.println("jspDestroy");
     		desCount ++;
     	}
      %>
      <%
      response.setIntHeader("Refresh", 5);
      out.println(initCount+":"+desCount);
       %>
       <p>
       	今天是：<%=new Date().toLocaleString() %>
       </p>
       <jsp:include page="data.jsp" flush="true"></jsp:include>
       <jsp:include page="httpInfo.jsp"></jsp:include>
       
  </body>
</html>
