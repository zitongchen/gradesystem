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
    	<table border="1"width="50%" align="center" >
    		<thead>
    			<tr>
					<th>学号</th>
					<th>课程名称</th>
					<th>平时成绩</th>
				</tr>
    			
    		</thead>
    		<tbody>
    			
			    <c:forEach items="${requestScope.studentgrades}" var="studentList">
			    	<tr>
			    		<td>${studentList.user_acount}</td>
			    		<td><input type="text" name="title" value="${studentList.docourse.title}"></td>
			    		<td><input type="text" name="psgrade" value="${studentList.psgrade}"></td>
    				</tr>
			    </c:forEach>
    		</tbody>
    	</table>
    </c:if>
  </body>
</html>
