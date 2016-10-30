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
	    	<c:if test="${not empty sessionScope.session_user}">
	    		<c:out value="${sessionScope.session_user.user_acount}"></c:out>
	    		<c:out value="${sessionScope.session_user.realname}"></c:out>
	    		<c:out value="${sessionScope.session_user.nickname}"></c:out>
	    		
	    	</c:if>
	    	<br>
	    	<a href="${pageContext.request.contextPath}/UserServlet?method=exit">
	    	<button>退出</button></a><br>
	    	<a href="${pageContext.request.contextPath}/UserServlet?method=findUserMessage&user_acount=${sessionScope.session_user.user_acount}">
	    	<button>显示学生信息</button></a>
	    	<div>
	    		<c:if test="${not empty requestScope.request_user}">
	    			<c:out value="requestSession test"></c:out>
	    			<c:out value="${requestScope.request_user.user_acount}"></c:out>
	    			<c:out value="${requestScope.request_user.realname}"></c:out>
	    			<c:out value="${requestScope.request_user.nickname}"></c:out>
	    		</c:if>
	    	</div>
	    	<a href="${pageContext.request.contextPath}/UserServlet?method=queryUserGrade&user_acount=${sessionScope.session_user.user_acount}">
	    	<button>显示学生成绩</button></a>
	    	<div>
	    		<c:if test="${not empty requestScope.gradeList}">
	    			<c:forEach	var="item" items="${requestScope.gradeList}">
	    				<c:out value="${item.user_acount}"></c:out>
	    				<c:out value="${item.docourse.visit_count}"></c:out>
	    				<c:out value="${item.docourse.title}"></c:out>
	    				<c:out value="${item.expert.name}"></c:out>
	    				<c:out value="${item.psgrade}"></c:out>
	    			</c:forEach>
	    		</c:if>
	    	</div>
	    	<a href="jsps/user/updateuserpassword.jsp"><button>修改密码</button></a>
  </body>
  
</html>
