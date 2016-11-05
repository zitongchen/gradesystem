<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updateuserpassword.jsp' starting page</title>
    
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
    <form action="${pageContext.request.contextPath}/ExpertServlet" method="post">
    <input type="hidden" name="method" value="updateExpertPassword">
    	学号：${sessionScope.session_expert.expacount}<br>
    	<input type="hidden" name="expacount" value="${sessionScope.session_expert.expacount}">
    	${requestScope.passwordError}
    	旧密码：<input type="text" name="password"/><br>
    	新密码：<input type="text" name="newPassword">
    	<!--  确认新密码：<input type="text" name="">-->
    	<input type="submit" value="提交"/>
    </form>
    
  </body>
</html>
