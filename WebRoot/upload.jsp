<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("utf8");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'upload.jsp' starting page</title>
    
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
  	<form action="upload" method="post" target="_blank" enctype="multipart/form-data">
    	标题：<input type="text" name="title"/>
    	<br/>
    	APK:<input type="file" name="apk"/>
    	<br/>
    	icon:<input type="file" name="icon"/>
    	<br/>
    	详情：<textarea rows="10" cols="40" name="detail"></textarea>
    	<br/>
    	最小版本:<input type="text" name="minVersion"/>
    	<br/>
    	开源链接:<input type="text" name="openlink"/>
    	<br/>
    	<input type="submit" value="上传">
    </form>
    <br/>

  </body>
</html>
