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
    
    <title>My JSP 'student_list.jsp' starting page</title>
    
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
    <c:if test="${not empty requestScope.studentgrades }">
    	<c:forEach items="${requestScope.studentgrades}" var="studentList">
    		<c:out value="${studentList.user_acount}"></c:out>
    		<c:out value="${studentList.docourse.title }"></c:out>
    		<c:out value="${studentList.psgrade }"></c:out>
    		<br>
    		<input type="text" name="user_acount" value="${studentList.user_acount}">
    		<input type="text" name="title" value="${studentList.docourse.title}">
    		<input type="text" name="psgrade" value="${studentList.psgrade}">
    		<br>
    	</c:forEach>
    </c:if>
  </body>
</html>
